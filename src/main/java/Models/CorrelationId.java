package Models;

import com.fasterxml.jackson.databind.JsonNode;

public class CorrelationId {

    private String description;
    private String location;

    public CorrelationId(JsonNode corrIdNode){
        if (corrIdNode.has("description")) this.description = corrIdNode.get("description").asText();
        if (corrIdNode.has("location")) this.location = corrIdNode.get("location").asText();
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
