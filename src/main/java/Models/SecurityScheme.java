package Models;

import com.fasterxml.jackson.databind.JsonNode;

public class SecurityScheme {

    private String type;
    private String description;
    private String name;
    private String in;
    private String scheme;
    private String bearerFormat;
    private OAuthFlows flows;
    private String openIdConnectUrl;

    public SecurityScheme(JsonNode securitySchemeNode){
        if (securitySchemeNode.has("type")) this.type = securitySchemeNode.get("type").asText();
        if (securitySchemeNode.has("description")) this.description = securitySchemeNode.get("description").asText();
        if (securitySchemeNode.has("name")) this.name = securitySchemeNode.get("name").asText();
        if (securitySchemeNode.has("in")) this.in = securitySchemeNode.get("in").asText();
        if (securitySchemeNode.has("scheme")) this.scheme = securitySchemeNode.get("scheme").asText();
        if (securitySchemeNode.has("bearerFormat")) this.bearerFormat = securitySchemeNode.get("bearerFormat").asText();
        if (securitySchemeNode.has("flows")) this.flows = new OAuthFlows(securitySchemeNode.get("flows"));
        if (securitySchemeNode.has("openIdConnectUrl")) this.openIdConnectUrl = securitySchemeNode.get("openIdConnectUrl").asText();
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getIn() {
        return in;
    }

    public String getScheme() {
        return scheme;
    }

    public String getBearerFormat() {
        return bearerFormat;
    }

    public OAuthFlows getFlows() {
        return flows;
    }

    public String getOpenIdConnectUrl() {
        return openIdConnectUrl;
    }

    @Override
    public String toString() {
        return "SecurityScheme{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", in='" + in + '\'' +
                ", scheme='" + scheme + '\'' +
                ", bearerFormat='" + bearerFormat + '\'' +
                ", flows=" + flows +
                ", openIdConnectUrl='" + openIdConnectUrl + '\'' +
                '}';
    }
}
