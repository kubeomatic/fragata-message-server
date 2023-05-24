package io.kubeomatic.messageServer.dto.action;


import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Null;
//import javax.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;


import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uuid",
        "job_id",
        "provider",
        "kind",
        "environment",
        "wait",
        "break_on_error",
        "action",
        "executor",
        "description",
        "resource_data",
        "provider_data",
        "alien"
})
@Generated("jsonschema2pojo")
public class PayloadAction {

    @Null
    @JsonProperty("uuid")
    private String uuid;
    @NotBlank(message = "job_id is required")
    @JsonProperty("job_id")
    private String jobId;


    @NotBlank(message = "provider is required")
    @JsonProperty("provider")
    private String provider;

    @NotBlank(message = "kind is required")
    @JsonProperty("kind")
    private String kind;

    @NotBlank(message = "environment is required")
    @JsonProperty("environment")
    private String environment;
    @JsonProperty("wait")
    private Boolean wait;
    @JsonProperty("break_on_error")
    private Boolean breakOnError;

    @Pattern(regexp = "^create|delete|recreate|start|stop|status|list|update|docs|show",
            message="Action must be one of create|delete|recreate|start|stop|status|list|update|docs|show")
    @NotBlank(message = "action is required")
    @JsonProperty("action")
    private String action;

    @NotBlank(message = "executor is required")
    @JsonProperty("executor")
    private String executor;

    @NotBlank(message = "description is required")
    @JsonProperty("description")
    private String description;

    @Null(message = "This value should be null or not present in the payload")
    @JsonProperty("alien")
    private Object alien;

    @NotNull(message = "resource_data is required.")
    @JsonProperty("resource_data")
    private ResourceData resourceData;

    @NotNull(message = "provider_data is required.")
    @JsonProperty("provider_data")
    private ProviderData providerData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("job_id")
    public String getJobId() {
        return jobId;
    }

    @JsonProperty("job_id")
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("environment")
    public String getEnvironment() {
        return environment;
    }

    @JsonProperty("environment")
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @JsonProperty("wait")
    public Boolean getWait() {
        return wait;
    }

    @JsonProperty("wait")
    public void setWait(Boolean wait) {
        this.wait = wait;
    }

    @JsonProperty("break_on_error")
    public Boolean getBreakOnError() {
        return breakOnError;
    }

    @JsonProperty("break_on_error")
    public void setBreakOnError(Boolean breakOnError) {
        this.breakOnError = breakOnError;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("executor")
    public String getExecutor() {
        return executor;
    }

    @JsonProperty("executor")
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("resource_data")
    public ResourceData getResourceData() {
        return resourceData;
    }

    @JsonProperty("resource_data")
    public void setResourceData(ResourceData resourceData) {
        this.resourceData = resourceData;
    }

    @JsonProperty("provider_data")
    public ProviderData getProviderData() {
        return providerData;
    }

    @JsonProperty("provider_data")
    public void setProviderData(ProviderData providerData) {
        this.providerData = providerData;
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