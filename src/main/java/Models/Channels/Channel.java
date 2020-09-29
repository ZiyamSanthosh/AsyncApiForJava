package Models.Channels;

import Models.Operation.Operation;
import Models.Parameter;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Channel {

    private String $ref;
    private String description;
    private Operation subscribe;
    private Operation publish;
    private Map<String, Parameter> parameters = new HashMap<>();
    //bindings

    public Channel(JsonNode channelNode, JsonNode mainTree){
        if (channelNode.has("$ref")) this.$ref = channelNode.get("ref").asText();
        if (channelNode.has("description")) this.description = channelNode.get("description").asText();
        //subscribe
        if (channelNode.has("publish")) this.publish = new Operation(channelNode.get("publish"), mainTree);
        if (channelNode.has("subscribe")) this.subscribe = new Operation(channelNode.get("subscribe"), mainTree);
        if (channelNode.has("parameters")){
            JsonNode parametersNode = channelNode.get("parameters");
            for (Iterator<String> it = parametersNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                //System.out.println(parametersNode.get(x));
                this.parameters.put(x, new Parameter(parametersNode.get(x), mainTree));
            }
        }
    }
}
