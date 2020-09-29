package Models;

import com.fasterxml.jackson.databind.JsonNode;

public class ExternalDocumentation {

    private String description;
    private String url;

    public ExternalDocumentation (JsonNode externalDocNode){
        if (externalDocNode.has("description")) this.description = externalDocNode.get("description").asText();
        if (externalDocNode.has("url")) this.url = externalDocNode.get("url").asText();
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
