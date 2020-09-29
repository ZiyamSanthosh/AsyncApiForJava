package Models;

import Models.Channels.Channel;
import Models.Components.Components;
import Models.Info.Info;
import Models.Servers.Server;
import Models.Tags.Tag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class AsyncAPI {

    private String schemaString;
    private JsonNode schemaJsonTree;
    private String asyncApiVersion;
    private String id;
    private Info info;
    private Map<String, Server> servers = new HashMap<>();
    private final String defaultContentType = "application/json";
    private Map<String, Channel> channels = new HashMap<>();
    private Components components;
    private List<Tag> tags = new ArrayList<>();
    private ExternalDocumentation externalDocs;

    public AsyncAPI(String schemaInJson) {
        this.schemaString = schemaInJson;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.schemaJsonTree = objectMapper.readTree(this.schemaString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (!getSchemaJsonTree().isEmpty()) {
            //add asyncapi version
            this.asyncApiVersion = getSchemaJsonTree().get("asyncapi").asText();
            //add id
            if (getSchemaJsonTree().has("id")) this.id = getSchemaJsonTree().get("id").asText();
            //add info
            this.info = new Info(getSchemaJsonTree().get("info"));
            //add servers
            if (getSchemaJsonTree().has("servers")) {
                JsonNode servers = getSchemaJsonTree().get("servers");
                for (Iterator<String> it = servers.fieldNames(); it.hasNext(); ) {
                    String x = it.next();
                    this.servers.put(x, new Server(servers.get(x)));
                }
            }
            //Add Channels
            JsonNode channelsNode = getSchemaJsonTree().get("channels");
            for (Iterator<String> it = channelsNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.channels.put(x, new Channel(channelsNode.get(x), getSchemaJsonTree()));
            }
            //Add Components
            if (getSchemaJsonTree().has("components")) this.components = new Components(getSchemaJsonTree().get("components"), getSchemaJsonTree());
            //add tags
            if (getSchemaJsonTree().has("tags")) {
                JsonNode tagsNode = getSchemaJsonTree().get("tags");
                for (Iterator<JsonNode> it = tagsNode.elements(); it.hasNext(); ) {
                    JsonNode x = it.next();
                    this.tags.add(new Tag(x));
                }
            }
            //add external docs
            if (getSchemaJsonTree().has("externalDocs"))
                this.externalDocs = new ExternalDocumentation(getSchemaJsonTree().get("externalDocs"));
        }
    }

    public String getSchemaString() {
        return schemaString;
    }

    public JsonNode getSchemaJsonTree() {
        return schemaJsonTree;
    }

    public String getAsyncApiVersion() {
        return asyncApiVersion;
    }

    public String getId() {
        return id;
    }

    public Info getInfo() {
        return info;
    }

    public Map<String, Server> getServers() {
        return servers;
    }

    public String getDefaultContentType() {
        return defaultContentType;
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    public Components getComponents() {
        return components;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setServers(Map<String, Server> servers) {
        this.servers = servers;
    }

    public void setChannels(Map<String, Channel> channels) {
        this.channels = channels;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}