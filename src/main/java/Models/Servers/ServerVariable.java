package Models.Servers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerVariable {

    private String defaultValue;
    private String description;
    private List<String> enums = new ArrayList<>();
    private List<String> examples = new ArrayList<>();

    public ServerVariable(JsonNode serverVariableNode){
        if (serverVariableNode.has("default")) this.defaultValue = serverVariableNode.get("default").asText();
        if (serverVariableNode.has("description")) this.description = serverVariableNode.get("description").asText();
        if (serverVariableNode.has("enum")){
            for (Iterator<JsonNode> it = serverVariableNode.get("enum").elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.enums.add(x.asText());
            }
        }
        if (serverVariableNode.has("examples")){
            for (Iterator<JsonNode> it = serverVariableNode.get("examples").elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.examples.add(x.asText());
            }
        }
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getEnums() {
        return enums;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setEnums(List<String> enums) {
        this.enums = enums;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }
}
