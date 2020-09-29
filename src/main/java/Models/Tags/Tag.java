package Models.Tags;

import Models.ExternalDocumentation;
import com.fasterxml.jackson.databind.JsonNode;

public class Tag {

    private String name;
    private String description;
    private ExternalDocumentation externalDocs;

    public Tag(JsonNode singleTagNode){
        if (singleTagNode.has("name")) this.name = singleTagNode.get("name").asText();
        if (singleTagNode.has("description")) this.description = singleTagNode.get("description").asText();
        if (singleTagNode.has("externalDOcs")) this.externalDocs = new ExternalDocumentation(singleTagNode.get("externalDocs"));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }
}
