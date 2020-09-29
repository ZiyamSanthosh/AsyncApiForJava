package Models;

import com.fasterxml.jackson.databind.JsonNode;

public class Reference {

    private String $ref;

    public Reference(JsonNode referenceNode){
        this.$ref = referenceNode.asText().substring(1);
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }
}
