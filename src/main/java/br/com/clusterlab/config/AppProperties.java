package br.com.clusterlab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@Component
public class AppProperties {
    public static String credentials ;
    @Value("${io.kubeomatic.fragata.credentials}")
    public void setCredentials(String credentials) {
        AppProperties.credentials = credentials;
    }
}
