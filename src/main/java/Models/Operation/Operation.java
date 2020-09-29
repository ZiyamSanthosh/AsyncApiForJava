package Models.Operation;

import Models.ExternalDocumentation;
import Models.Message.Message;
import Models.Tags.Tag;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Operation {

    private String operationId;
    private String summary;
    private String description;
    private List<Tag> operationTags = new ArrayList<>();
    private ExternalDocumentation externalDocs;
    //bindings
    private List<OperationTrait> traits = new ArrayList<>();
    private Message message;

    public Operation(JsonNode operationNode, JsonNode mainTree){
        if (operationNode.has("operationId")) this.operationId = operationNode.get("operationId").asText();
        if (operationNode.has("summary")) this.summary = operationNode.get("summary").asText();
        if (operationNode.has("description")) this.description = operationNode.get("description").asText();
        if (operationNode.has("tags")) {
            JsonNode tagsNode = operationNode.get("tags");
            for (Iterator<JsonNode> it = tagsNode.elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.operationTags.add(new Tag(x));
            }
        }
        if (operationNode.has("externalDocs")) this.externalDocs = new ExternalDocumentation(operationNode.get("externalDocs"));
        //bindings
        if (operationNode.has("traits")){
            JsonNode operationTraitsNode = operationNode.get("traits");
            for (Iterator<JsonNode> it = operationTraitsNode.elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.traits.add(new OperationTrait(x, mainTree));
            }
        }
        if (operationNode.has("message")) this.message = new Message(operationNode.get("message"), mainTree);
    }

    public String getOperationId() {
        return operationId;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public List<Tag> getOperationTags() {
        return operationTags;
    }

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public List<OperationTrait> getTraits() {
        return traits;
    }

    public Message getMessage() {
        return message;
    }

    public void setOperationTags(List<Tag> operationTags) {
        this.operationTags = operationTags;
    }

    public void setTraits(List<OperationTrait> traits) {
        this.traits = traits;
    }
}
