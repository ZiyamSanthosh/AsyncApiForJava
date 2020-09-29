package Models.Message;

import Models.CorrelationId;
import Models.ExternalDocumentation;
import Models.Reference;
import Models.Tags.Tag;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Message {

    private Reference $ref;
    private JsonNode headers;
    private Reference schemaReference;
    private JsonNode payload;
    private CorrelationId correlationId;
    private Reference correlationIdReference;
    private String schemaFormat;
    private String contentType;
    private String name;
    private String title;
    private String summary;
    private String description;
    private List<Tag> tags;
    private ExternalDocumentation externalDocs;
    //bindings
    private List<Map<String, Object>> examples;
    private List<MessageTrait> traits;

    public Message(JsonNode messageNode, JsonNode mainTree){
        if (messageNode.has("$ref")) {
            this.$ref = new Reference(messageNode.get("$ref"));
            JsonPointer jsonPointer = JsonPointer.compile(this.$ref.get$ref());
            messageNode = mainTree.at(jsonPointer);
        }
        if (messageNode.has("headers")) {
            if (messageNode.get("headers").has("$ref")){
                this.schemaReference = new Reference(messageNode.get("headers").get("$ref"));
            }
            //ObjectMapper objectMapper = new ObjectMapper();
            if (this.schemaReference != null){
                JsonPointer jsonPointer = JsonPointer.compile(this.schemaReference.get$ref());
                    /*try {
                        this.headers = SchemaLoader.load(new JSONObject(objectMapper.writeValueAsString(getSchemaJsonTree().at(jsonPointer))));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }*/
                this.headers = mainTree.at(jsonPointer);
            } else {
                    /*try {
                        this.headers = SchemaLoader.load(new JSONObject(objectMapper.writeValueAsString(messageNode.get("headers"))));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }*/
                this.headers = messageNode.get("headers");
            }
        }
        if (messageNode.has("payload")) {
            JsonNode payloadNode = messageNode.get("payload");
            if (payloadNode.has("$ref")){
                JsonPointer jsonPointer = JsonPointer.compile(new Reference(payloadNode.get("$ref")).get$ref());
                payloadNode = mainTree.at(jsonPointer);
                /*ObjectMapper objectMapper = new ObjectMapper();
                try {
                    this.payload = objectMapper.readValue(x.textValue(), Schema.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }*/
            }
            this.payload = payloadNode;
        }
        if (messageNode.has("correlationId")) {
            if (messageNode.get("correlationId").has("$ref")){
                this.correlationIdReference = new Reference(messageNode.get("correlationId").get("$ref"));
            }
            if (this.correlationIdReference != null){
                JsonPointer jsonPointer = JsonPointer.compile(this.correlationIdReference.get$ref());
                this.correlationId = new CorrelationId(mainTree.at(jsonPointer));
            } else {
                this.correlationId = new CorrelationId(messageNode.get("correlationId"));
            }
        }
        if (messageNode.has("schemaFormat")) this.schemaFormat = messageNode.get("schemaFormat").asText();
        if (messageNode.has("contentType")) this.contentType = messageNode.get("contentType").asText();
        if (messageNode.has("name")) this.name = messageNode.get("name").asText();
        if (messageNode.has("title")) this.title = messageNode.get("title").asText();
        if (messageNode.has("summary")) this.summary = messageNode.get("summary").asText();
        if (messageNode.has("description")) this.description = messageNode.get("description").asText();
        if (messageNode.has("tags")) {
            JsonNode tagsNode = messageNode.get("tags");
            for (Iterator<JsonNode> it = tagsNode.elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.tags.add(new Tag(x));
            }
        }
        if (messageNode.has("externalDocs")) this.externalDocs = new ExternalDocumentation(messageNode.get("externalDocs"));

    }

    public Reference get$ref() {
        return $ref;
    }

    public JsonNode getHeaders() {
        return headers;
    }

    public Reference getSchemaReference() {
        return schemaReference;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public CorrelationId getCorrelationId() {
        return correlationId;
    }

    public Reference getCorrelationIdReference() {
        return correlationIdReference;
    }

    public String getSchemaFormat() {
        return schemaFormat;
    }

    public String getContentType() {
        return contentType;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public List<Map<String, Object>> getExamples() {
        return examples;
    }

    public List<MessageTrait> getTraits() {
        return traits;
    }
}
