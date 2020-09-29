import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;

import java.util.*;

public class AsyncAPI2 {

    private String schemaString;
    private JsonNode schemaJsonTree;
    private String asyncApiVersion;
    private String id;
    private Info info;
    private Map<String, Server> servers = new HashMap<>();
    private final String defaultContentType = "application/json";
    private Map<String, Channel> channels = new HashMap<>();
    private List<Component> components = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private ExternalDocumentation externalDocs;

    public AsyncAPI2(String schemaInJson){
        this.schemaString = schemaInJson;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.schemaJsonTree = objectMapper.readTree(this.schemaString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (!getSchemaJsonTree().isEmpty()){
            //add asyncapi version
            this.asyncApiVersion = getSchemaJsonTree().get("asyncapi").asText();
            //add id
            if (getSchemaJsonTree().has("id")) this.id = getSchemaJsonTree().get("id").asText();
            //add info
            this.info = new Info(getSchemaJsonTree().get("info"));
            //add servers
            if (getSchemaJsonTree().has("servers")){
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
                this.channels.put(x, new Channel(channelsNode.get(x)));
            }
            //Add Components
            //add tags
            if (getSchemaJsonTree().has("tags")){
                JsonNode tagsNode = getSchemaJsonTree().get("tags");
                for (Iterator<JsonNode> it = tagsNode.elements(); it.hasNext(); ) {
                    JsonNode x = it.next();
                    this.tags.add(new Tag(x));
                }
            }
            //add external docs
            if (getSchemaJsonTree().has("externalDocs")) this.externalDocs = new ExternalDocumentation(getSchemaJsonTree().get("externalDocs"));
        }
    }

    //getters and setters of AsyncAPI

    public String getSchemaString() {
        return schemaString;
    }

    public void setSchemaString(String schemaString) {
        this.schemaString = schemaString;
    }

    public JsonNode getSchemaJsonTree() {
        return schemaJsonTree;
    }

    public void setSchemaJsonTree(JsonNode schemaJsonTree) {
        this.schemaJsonTree = schemaJsonTree;
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

    public void setServers(Map<String, Server> servers) {
        this.servers = servers;
    }

    public String getDefaultContentType() {
        return defaultContentType;
    }

    public Map<String, Channel> getChannels() {
        return channels;
    }

    public void setChannels(Map<String, Channel> channels) {
        this.channels = channels;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    public static class Info {

        private String title;
        private String version;
        private String description;
        private String termsOfService;
        private Contact contact;
        private License license;
        
        public Info(JsonNode infoJsonNode){
            if (infoJsonNode.has("title")) this.title = infoJsonNode.get("title").asText();
            if (infoJsonNode.has("version")) this.version = infoJsonNode.get("version").asText();
            if (infoJsonNode.has("description")) this.description = infoJsonNode.get("description").asText();
            if (infoJsonNode.has("termsOfService")) this.termsOfService = infoJsonNode.get("termsOfService").asText();
            if (infoJsonNode.has("contact")) this.contact = new Contact(infoJsonNode.get("contact"));
            if (infoJsonNode.has("license")) this.license = new License(infoJsonNode.get("license"));
        }

        //getters and setters for Info
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTermsOfService() {
            return termsOfService;
        }

        public void setTermsOfService(String termsOfService) {
            this.termsOfService = termsOfService;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public License getLicense() {
            return license;
        }

        public void setLicense(License license) {
            this.license = license;
        }

        public static class Contact {
            
            private String name;
            private String url;
            private String email;

            private Contact(JsonNode contactInfoJsonNode){
                if (contactInfoJsonNode.has("name")) this.name = contactInfoJsonNode.get("name").asText();
                if (contactInfoJsonNode.has("url")) this.url = contactInfoJsonNode.get("url").asText();
                if (contactInfoJsonNode.has("email")) this.email = contactInfoJsonNode.get("email").asText();
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }
        }

        public static class License {

            private String name;
            private String url;

            public License(JsonNode licenseInfoNode){
                this.name = licenseInfoNode.get("name").asText();
                if (licenseInfoNode.has("url")) this.url = licenseInfoNode.get("url").asText();
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    public static class Server {

        private String url;
        private String protocol;
        private String protocolVersion;
        private String description;
        private Map<String, ServerVariable> variables = new HashMap<>();
        private List<SecurityRequirement> securityRequirements = new ArrayList<>();

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getProtocolVersion() {
            return protocolVersion;
        }

        public void setProtocolVersion(String protocolVersion) {
            this.protocolVersion = protocolVersion;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Map<String, ServerVariable> getVariables() {
            return variables;
        }

        public void setVariables(Map<String, ServerVariable> variables) {
            this.variables = variables;
        }

        public List<SecurityRequirement> getSecurityRequirements() {
            return securityRequirements;
        }

        public void setSecurityRequirements(List<SecurityRequirement> securityRequirements) {
            this.securityRequirements = securityRequirements;
        }

        public Server(JsonNode serverNode){
            this.url = serverNode.get("url").asText();
            this.protocol = serverNode.get("protocol").asText();
            if (serverNode.has("protocolVersion")) this.protocolVersion = serverNode.get("protocolVersion").asText();
            if (serverNode.has("description")) this.description = serverNode.get("description").asText();
            if (serverNode.has("variables")) {
                JsonNode variables = serverNode.get("variables");
                for (Iterator<String> it = variables.fieldNames(); it.hasNext(); ) {
                    String x = it.next();
                    this.variables.put(x, new ServerVariable(variables.get(x)));
                }
            }
            if (serverNode.has("security")){
                JsonNode security = serverNode.get("security");
                for (Iterator<JsonNode> it = security.elements(); it.hasNext(); ) {
                    JsonNode x = it.next();
                    for (Iterator<String> iter = x.fieldNames(); iter.hasNext(); ) {
                        String y = iter.next();
                        this.securityRequirements.add(new SecurityRequirement(y, x.get(y)));
                    }
                }
            }


        }

        private static class ServerVariable {

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

            public void setDefaultValue(String defaultValue) {
                this.defaultValue = defaultValue;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<String> getEnums() {
                return enums;
            }

            public void setEnums(List<String> enums) {
                this.enums = enums;
            }

            public List<String> getExamples() {
                return examples;
            }

            public void setExamples(List<String> examples) {
                this.examples = examples;
            }
        }

        private static class SecurityRequirement {

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

            public void setKey(String key) {
                this.key = key;
            }

            public List<String> getValue() {
                return value;
            }

            public void setValue(List<String> value) {
                this.value = value;
            }
        }
    }

    public class Channel {

        private String $ref;
        private String description;
        private Operation subscribe;
        private Operation publish;
        private Map<String, Parameter> parameters = new HashMap<>();
        //bindings

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        //bindings

        private class Operation {

            private String operationId;
            private String summary;
            private String description;
            private List<Tag> operationTags;
            private ExternalDocumentation externalDOcs;
            //bindings
            private List<OperationTrait> traits = new ArrayList<>();
            private Message message;

            public String getOperationId() {
                return operationId;
            }

            public void setOperationId(String operationId) {
                this.operationId = operationId;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setOperationTags(List<Tag> operationTags) {
                this.operationTags = operationTags;
            }

            public ExternalDocumentation getExternalDOcs() {
                return externalDOcs;
            }

            public void setExternalDOcs(ExternalDocumentation externalDOcs) {
                this.externalDOcs = externalDOcs;
            }

            public Message getMessage() {
                return message;
            }

            public void setMessage(Message message) {
                this.message = message;
            }

            private class OperationTrait {

                private Reference $ref;
                private String operationId;
                private String summary;
                private String description;
                private List<Tag> operationTraitTags;
                private ExternalDocumentation externalDocs;
                //bindings

                public OperationTrait(JsonNode operationTraitNode){
                    if (operationTraitNode.has("$ref")) {
                        this.$ref = new Reference(operationTraitNode.get("$ref"));
                        JsonPointer jsonPointer = JsonPointer.compile(this.$ref.get$ref());
                        operationTraitNode = getSchemaJsonTree().at(jsonPointer);
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

                public String getOperationId() {
                    return operationId;
                }

                public void setOperationId(String operationId) {
                    this.operationId = operationId;
                }

                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public ExternalDocumentation getExternalDocs() {
                    return externalDocs;
                }

                public void setExternalDocs(ExternalDocumentation externalDocs) {
                    this.externalDocs = externalDocs;
                }

                public void setOperationTraitTags(List<Tag> operationTraitTags) {
                    this.operationTraitTags = operationTraitTags;
                }
            }

            public Operation(JsonNode operationNode){
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
                if (operationNode.has("externalDocs")) this.externalDOcs = new ExternalDocumentation(operationNode.get("externalDocs"));
                //bindings
                if (operationNode.has("traits")){
                    JsonNode operationTraitsNode = operationNode.get("traits");
                    for (Iterator<JsonNode> it = operationTraitsNode.elements(); it.hasNext(); ) {
                        JsonNode x = it.next();
                        this.traits.add(new OperationTrait(x));
                    }
                }
                if (operationNode.has("message")) this.message = new Message(operationNode.get("message"));
            }
        }

        private class Parameter {

            private String description;
            private JsonNode schema;
            private String location;
            private Reference $ref;

            public Parameter(JsonNode parameterNode){
                if (parameterNode.has("$ref")){
                    this.$ref = new Reference(parameterNode.get("$ref"));
                    JsonPointer jsonPointer = JsonPointer.compile(this.$ref.get$ref());
                    parameterNode = getSchemaJsonTree().at(jsonPointer);
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

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public Reference get$ref() {
                return $ref;
            }

            public void set$ref(Reference $ref) {
                this.$ref = $ref;
            }

            public JsonNode getSchema() {
                return schema;
            }

            public void setSchema(JsonNode schema) {
                this.schema = schema;
            }
        }

        public Channel(JsonNode channelNode){
            if (channelNode.has("$ref")) this.$ref = channelNode.get("ref").asText();
            if (channelNode.has("description")) this.description = channelNode.get("description").asText();
            //subscribe
            if (channelNode.has("publish")) this.publish = new Operation(channelNode.get("publish"));
            if (channelNode.has("subscribe")) this.subscribe = new Operation(channelNode.get("subscribe"));
            if (channelNode.has("parameters")){
                JsonNode parametersNode = channelNode.get("parameters");
                for (Iterator<String> it = parametersNode.fieldNames(); it.hasNext(); ) {
                    String x = it.next();
                    //System.out.println(parametersNode.get(x));
                    this.parameters.put(x, new Parameter(parametersNode.get(x)));
                }
            }
        }
    }

    public static class Tag {

        private String name;
        private String description;
        private ExternalDocumentation externalDocs;

        public Tag(JsonNode singleTagNode){
            if (singleTagNode.has("name")) this.name = singleTagNode.get("name").asText();
            if (singleTagNode.has("description")) this.description = singleTagNode.get("description").asText();
            if (singleTagNode.has("externalDOcs")) this.externalDocs = new ExternalDocumentation(singleTagNode.get("externalDocs"));
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ExternalDocumentation getExternalDocs() {
            return externalDocs;
        }

        public void setExternalDocs(ExternalDocumentation externalDocs) {
            this.externalDocs = externalDocs;
        }
    }

    public static class ExternalDocumentation {

        private String description;
        private String url;

        public ExternalDocumentation (JsonNode externalDocNode){
            if (externalDocNode.has("description")) this.description = externalDocNode.get("description").asText();
            if (externalDocNode.has("url")) this.url = externalDocNode.get("url").asText();
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Reference {

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

    private class Message {
        private Reference $ref;
        private JsonNode headers;
        private Reference schemaReference;
        private Schema payload;
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

        public JsonNode getHeaders() {
            return headers;
        }

        public void setHeaders(JsonNode headers) {
            this.headers = headers;
        }

        public CorrelationId getCorrelationId() {
            return correlationId;
        }

        public void setCorrelationId(CorrelationId correlationId) {
            this.correlationId = correlationId;
        }

        public String getSchemaFormat() {
            return schemaFormat;
        }

        public void setSchemaFormat(String schemaFormat) {
            this.schemaFormat = schemaFormat;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }

        public ExternalDocumentation getExternalDocs() {
            return externalDocs;
        }

        public void setExternalDocs(ExternalDocumentation externalDocs) {
            this.externalDocs = externalDocs;
        }

        public Schema getPayload() {
            return payload;
        }

        public void setPayload(Schema payload) {
            this.payload = payload;
        }

        private class MessageTrait {

        }

        public Message(JsonNode messageNode){
            if (messageNode.has("$ref")) {
                this.$ref = new Reference(messageNode.get("$ref"));
                JsonPointer jsonPointer = JsonPointer.compile(this.$ref.get$ref());
                messageNode = getSchemaJsonTree().at(jsonPointer);
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
                    this.headers = getSchemaJsonTree().at(jsonPointer);
                } else {
                    /*try {
                        this.headers = SchemaLoader.load(new JSONObject(objectMapper.writeValueAsString(messageNode.get("headers"))));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }*/
                    this.headers = messageNode.get("headers");
                }
            }
            /*if (messageNode.has("payload")) {
                if (messageNode.get("payload").has("$ref")){
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonPointer jsonPointer = JsonPointer.compile(new Reference(messageNode.get("payload").get("$ref")).get$ref());
                    JsonNode x = getSchemaJsonTree().at(jsonPointer);
                    try {
                        this.payload = objectMapper.readValue(x.textValue(), Schema.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            };*/
            if (messageNode.has("correlationId")) {
                if (messageNode.get("correlationId").has("$ref")){
                    this.correlationIdReference = new Reference(messageNode.get("correlationId").get("$ref"));
                }
                if (this.correlationIdReference != null){
                    JsonPointer jsonPointer = JsonPointer.compile(this.correlationIdReference.get$ref());
                    this.correlationId = new CorrelationId(getSchemaJsonTree().at(jsonPointer));
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
    }

    private static class CorrelationId {
        private String description;
        private String location;

        private CorrelationId(JsonNode corrIdNode){
            if (corrIdNode.has("description")) this.description = corrIdNode.get("description").asText();
            if (corrIdNode.has("location")) this.location = corrIdNode.get("location").asText();
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }

    public static class Component {

    }
}
