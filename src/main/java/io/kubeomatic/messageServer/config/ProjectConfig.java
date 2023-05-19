package io.kubeomatic.messageServer.config;

import io.kubeomatic.messageServer.dto.credential.Credential;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    static Logger logger = LoggerFactory.getLogger(ProjectConfig.class);

    @Configuration
    public static class SecurityConfiguration {
        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            var users = new InMemoryUserDetailsManager();
            try {

                for (Credential credential : Credentials.getCredentials(AppProperties.credentials)) {
                    UserDetails user = User
                            .withUsername(credential.getUsername().toLowerCase())
                            .username(credential.getUsername().toLowerCase())
                            .password(credential.getPassword())
                            .roles(credential.getRole().toUpperCase())
                            .build();
                    users.createUser(user);
                    logger.info("Loadded user \"" + credential.getUsername() + "\" to UserDetailsService");
                }

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return users;

        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.httpBasic();
            http.authorizeRequests().filterSecurityInterceptorOncePerRequest(false).requestMatchers("/payload").hasRole("ADMIN");
            http.csrf().disable();
            return http.build();
        }
    }
}