package Models;

import com.fasterxml.jackson.databind.JsonNode;

public class OAuthFlows {

    private OAuthFlow implicit;
    private OAuthFlow password;
    private OAuthFlow clientCredentials;
    private OAuthFlow authorizationCode;

    public OAuthFlows(JsonNode OAuthFlows){
        if (OAuthFlows.has("implicit")) this.implicit = new OAuthFlow(OAuthFlows.get("implicit"));
        if (OAuthFlows.has("password")) this.password = new OAuthFlow(OAuthFlows.get("password"));
        if (OAuthFlows.has("clientCredentials")) this.clientCredentials = new OAuthFlow(OAuthFlows.get("clientCredentials"));
        if (OAuthFlows.has("authorizationCode")) this.authorizationCode = new OAuthFlow(OAuthFlows.get("authorizationCode"));
    }

    public OAuthFlow getImplicit() {
        return implicit;
    }

    public OAuthFlow getPassword() {
        return password;
    }

    public OAuthFlow getClientCredentials() {
        return clientCredentials;
    }

    public OAuthFlow getAuthorizationCode() {
        return authorizationCode;
    }

    @Override
    public String toString() {
        return "OAuthFlows{" +
                "implicit=" + implicit +
                ", password=" + password +
                ", clientCredentials=" + clientCredentials +
                ", authorizationCode=" + authorizationCode +
                '}';
    }
}
