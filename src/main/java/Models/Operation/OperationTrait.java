package Models.Operation;

import Models.ExternalDocumentation;
import Models.Reference;
import Models.Tags.Tag;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.List;

public class OperationTrait {

    private Reference $ref;
    private String operationId;
    private String summary;
    private String description;
    private List<Tag> operationTraitTags;
    private ExternalDocumentation externalDocs;
    //bindings

    public OperationTrait(JsonNode operationTraitNode, JsonNode mainTree){
        if (operationTraitNode.has("$ref")) {
            this.$ref = new Reference(operationTraitNode.get("$ref"));
            JsonPointer jsonPointer = JsonPointer.compile(this.$ref.get$ref());
            operationTraitNode = mainTree.at(jsonPointer);
        }
        if (operationTraitNode.has("operationId")) this.operationId = operationTraitNode.get("operationId").asText();
        if (operationTraitNode.has("summary")) this.summary = operationTraitNode.get("summary").asText();
        if (operationTraitNode.has("description")) this.description = operationTraitNode.get("description").asText();
        if (operationTraitNode.has("tags")){
            JsonNode operationTraitTagsNode = operationTraitNode.get("tags");
            for (Iterator<JsonNode> it = operationTraitTagsNode.elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.operationTraitTags.add(new Tag(x));
            }
        }
        if (operationTraitNode.has("externalDocs")) this.externalDocs = new ExternalDocumentation(operationTraitNode.get("externalDocs"));
        //bindings
    }
}
