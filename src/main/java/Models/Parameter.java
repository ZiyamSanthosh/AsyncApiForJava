package Models;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;

public class Parameter {

    private String description;
    private JsonNode schema;
    private String location;
    private Reference $ref;

    public Parameter(JsonNode parameterNode, JsonNode mainTree){
        if (parameterNode.has("$ref")){
            this.$ref = new Reference(parameterNode.get("$ref"));
            JsonPointer jsonPointer = JsonPointer.compile(this.$ref.get$ref());
            parameterNode = mainTree.at(jsonPointer);
        }
        if (parameterNode.has("description")) this.description = parameterNode.get("description").asText();
        if (parameterNode.has("location")) this.location = parameterNode.get("locatioin").asText();
        if (parameterNode.has("schema")) {
                    /*ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        this.schema = SchemaLoader.load(new JSONObject(objectMapper.writeValueAsString(parameterNode.get("schema"))));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }*/
            this.schema = parameterNode.get("schema");
        }
    }

    public String getDescription() {
        return description;
    }

    public JsonNode getSchema() {
        return schema;
    }

    public String getLocation() {
        return location;
    }

    public Reference get$ref() {
        return $ref;
    }
}
