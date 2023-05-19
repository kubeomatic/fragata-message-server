package io.kubeomatic.messageServer.dto.credential;


import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.LinkedHashMap;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "password",
        "encoder",
        "role",
        "sourceIp"
})
@Generated("jsonschema2pojo")
public class Credential {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("encoder")
    private String encoder;
    @JsonProperty("role")
    private String role;
    @JsonProperty("sourceIp")
    private String sourceIp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("encoder")
    public String getEncoder() {
        return encoder;
    }

    @JsonProperty("encoder")
    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty("sourceIp")
    public String getSourceIp() {
        return sourceIp;
    }

    @JsonProperty("sourceIp")
    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}