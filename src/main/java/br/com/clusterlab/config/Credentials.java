package br.com.clusterlab.config;

import br.com.clusterlab.dto.credential.Credential;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class Credentials {
    public static Credential[] getCredentials(String credentialString) throws JsonProcessingException {
        byte[] credentialsByte = Base64.getDecoder().decode(credentialString);
        String credentialsString = new String(credentialsByte);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(credentialsString, Credential[].class);
    }
}