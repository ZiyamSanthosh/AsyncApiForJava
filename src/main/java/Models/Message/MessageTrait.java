package Models.Message;

import Models.CorrelationId;
import Models.ExternalDocumentation;
import Models.Tags.Tag;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageTrait {

    private JsonNode headers;
    private CorrelationId correlationId;
    private String schemaFormat;
    private String contentType;
    private String name;
    private String title;
    private String summary;
    private String description;
    private List<Tag> tags = new ArrayList<>();
    private ExternalDocumentation externalDocs;
    //bindings
    private List<Map<String, Object>> examples;

    public MessageTrait(JsonNode messageTraitNode){

    }
}
