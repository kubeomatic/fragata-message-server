package br.com.clusterlab.config;

import br.com.clusterlab.dto.credential.Credential;
import br.com.clusterlab.service.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    static Logger logger = LoggerFactory.getLogger(ProjectConfig.class);

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
//        Credentials credentialsObj = null;
        try {
            for (Credential credential: Credentials.getCredentials(AppProperties.credentials))
            {
                var user = User.withUsername(credential.getUsername().toLowerCase())
                        .password(credential.getPassword())
                        .roles(credential.getRole().toUpperCase())
                        .build();
                manager.createUser(user);
                logger.info("Loadded user \"" + credential.getUsername() + "\" to UserDetailsService");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10, new SecureRandom());
//        return new SCryptPasswordEncoder();
        return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);

//        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        //http.authorizeRequests().anyRequest().hasAnyAuthority("WRITE", "READ");
        //http.authorizeRequests().anyRequest().hasAuthority("WRITE");
//        http.authorizeRequests().anyRequest().access("hasAuthority('WRITE')");
//                .and()
//                .csrf()
//                .ignoringAntMatchers("/payload");
        http.authorizeRequests().filterSecurityInterceptorOncePerRequest(false).mvcMatchers("/payload").hasRole("ADMIN");
        http.csrf().disable();
    }
}
