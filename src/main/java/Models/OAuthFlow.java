package Models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OAuthFlow {

    private String authorizationUrl;
    private String tokenUrl;
    private String refreshUrl;
    private Map<String, String> scopes = new HashMap<>();

    public OAuthFlow(JsonNode OAuthFlowNode){
        if (OAuthFlowNode.has("authorizationUrl")) this.authorizationUrl = OAuthFlowNode.get("authorizationUrl").asText();
        if (OAuthFlowNode.has("tokenUrl")) this.tokenUrl = OAuthFlowNode.get("tokenUrl").asText();
        if (OAuthFlowNode.has("refreshUrl")) this.refreshUrl = OAuthFlowNode.get("refreshUrl").asText();
        if (OAuthFlowNode.has("scopes")){
            JsonNode scopesNode = OAuthFlowNode.get("scopes");
            for (Iterator<String> it = scopesNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.scopes.put(x, scopesNode.get(x).asText());
            }
        }
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public Map<String, String> getScopes() {
        return scopes;
    }

    public void setScopes(Map<String, String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public String toString() {
        return "OAuthFlow{" +
                "authorizationUrl='" + authorizationUrl + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", refreshUrl='" + refreshUrl + '\'' +
                ", scopes=" + scopes +
                '}';
    }
}
