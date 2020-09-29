package Models.Servers;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;

public class Server {

    private String url;
    private String protocol;
    private String protocolVersion;
    private String description;
    private Map<String, ServerVariable> variables = new HashMap<>();
    private List<SecurityRequirement> securityRequirements = new ArrayList<>();

    public Server(JsonNode serverNode){
        this.url = serverNode.get("url").asText();
        this.protocol = serverNode.get("protocol").asText();
        if (serverNode.has("protocolVersion")) this.protocolVersion = serverNode.get("protocolVersion").asText();
        if (serverNode.has("description")) this.description = serverNode.get("description").asText();
        if (serverNode.has("variables")) {
            JsonNode variables = serverNode.get("variables");
            for (Iterator<String> it = variables.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.variables.put(x, new ServerVariable(variables.get(x)));
            }
        }
        if (serverNode.has("security")){
            JsonNode security = serverNode.get("security");
            for (Iterator<JsonNode> it = security.elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                for (Iterator<String> iter = x.fieldNames(); iter.hasNext(); ) {
                    String y = iter.next();
                    this.securityRequirements.add(new SecurityRequirement(y, x.get(y)));
                }
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, ServerVariable> getVariables() {
        return variables;
    }

    public List<SecurityRequirement> getSecurityRequirements() {
        return securityRequirements;
    }

    public void setVariables(Map<String, ServerVariable> variables) {
        this.variables = variables;
    }

    public void setSecurityRequirements(List<SecurityRequirement> securityRequirements) {
        this.securityRequirements = securityRequirements;
    }
}
