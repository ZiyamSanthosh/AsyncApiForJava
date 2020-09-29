package Models.Servers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SecurityRequirement {

    private String key;
    private List<String> value = new ArrayList<>();

    public SecurityRequirement(String key, JsonNode node){
        this.key = key;
        if (node.isEmpty()){
            value = null;
        } else {
            for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
                JsonNode x = it.next();
                this.value.add(x.asText());
            }
        }
    }

    public String getKey() {
        return key;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
