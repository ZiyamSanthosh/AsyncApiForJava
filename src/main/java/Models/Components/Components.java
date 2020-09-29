package Models.Components;

import Models.Bindings.ChannelBinding;
import Models.Bindings.MessageBinding;
import Models.Bindings.OperationBinding;
import Models.Bindings.ServerBinding;
import Models.CorrelationId;
import Models.Message.Message;
import Models.Message.MessageTrait;
import Models.Operation.OperationTrait;
import Models.Parameter;
import Models.Reference;
import Models.SecurityScheme;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Components {

    private Map<String, JsonNode> schemas = new HashMap<>();
    private Map<String, Message> messages = new HashMap<>();
    private Map<String, SecurityScheme> securitySchemes = new HashMap<>();
    private Map<String, Parameter> parameters = new HashMap<>();
    private Map<String, CorrelationId> correlationIds = new HashMap<>();
    private Map<String, OperationTrait> operationTraits = new HashMap<>();
    private Map<String, MessageTrait> messageTraits = new HashMap<>();
    private Map<String, ServerBinding> serverBindings = new HashMap<>();
    private Map<String, ChannelBinding> channelBindings = new HashMap<>();
    private Map<String, OperationBinding> operationBindings = new HashMap<>();
    private Map<String, MessageBinding> messageBindings = new HashMap<>();

    public Components(JsonNode componentsNode, JsonNode mainTree){
        if (componentsNode.has("schemas")) {
            JsonNode schemaNode = componentsNode.get("schemas");
            if (schemaNode.has("$ref")){
                JsonPointer jsonPointer = JsonPointer.compile(new Reference(schemaNode.get("$ref")).get$ref());
                schemaNode = mainTree.at(jsonPointer);
            }
            for (Iterator<String> it = schemaNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.schemas.put(x, schemaNode.get(x));
            }
        }
        if (componentsNode.has("messages")) {
            JsonNode messagesNode = componentsNode.get("messages");
            if (messagesNode.has("$ref")){
                JsonPointer jsonPointer = JsonPointer.compile(new Reference(messagesNode.get("$ref")).get$ref());
                messagesNode = mainTree.at(jsonPointer);
            }
            for (Iterator<String> it = messagesNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.messages.put(x, new Message(messagesNode.get(x), mainTree));
            }
        }
        if (componentsNode.has("securitySchemes")) {
            JsonNode securitySchemesNode = componentsNode.get("securitySchemes");
            if (securitySchemesNode.has("$ref")){
                JsonPointer jsonPointer = JsonPointer.compile(new Reference(securitySchemesNode.get("$ref")).get$ref());
                securitySchemesNode = mainTree.at(jsonPointer);
            }
            for (Iterator<String> it = securitySchemesNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.securitySchemes.put(x, new SecurityScheme(securitySchemesNode.get(x)));
            }
        }
        if (componentsNode.has("parameters")){
            JsonNode parametersNode = componentsNode.get("parameters");
            if (parametersNode.has("$ref")){
                JsonPointer jsonPointer = JsonPointer.compile(new Reference(parametersNode.get("$ref")).get$ref());
                parametersNode = mainTree.at(jsonPointer);
            }
            for (Iterator<String> it = parametersNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.parameters.put(x, new Parameter(parametersNode.get(x), mainTree));
            }
        }
        if (componentsNode.has("correlationIds")){
            JsonNode correlationIdsNode = componentsNode.get("correlationIds");
            for (Iterator<String> it = correlationIdsNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.correlationIds.put(x, new CorrelationId(correlationIdsNode.get(x)));
            }
        }
        if (componentsNode.has("operationTraits")){
            JsonNode operationTraitsNode = componentsNode.get("operationTraits");
            for (Iterator<String> it = operationTraitsNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.operationTraits.put(x, new OperationTrait(operationTraitsNode.get(x), mainTree));
            }
        }
        if (componentsNode.has("messageTraits")){
            JsonNode messageTraitsNode = componentsNode.get("messageTraits");
            for (Iterator<String> it = messageTraitsNode.fieldNames(); it.hasNext(); ) {
                String x = it.next();
                this.messageTraits.put(x, new MessageTrait(messageTraitsNode.get(x)));
            }
        }
    }

    public Map<String, JsonNode> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, JsonNode> schemas) {
        this.schemas = schemas;
    }

    public Map<String, Message> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Message> messages) {
        this.messages = messages;
    }

    public Map<String, SecurityScheme> getSecuritySchemes() {
        return securitySchemes;
    }

    public void setSecuritySchemes(Map<String, SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
    }

    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
    }

    public Map<String, CorrelationId> getCorrelationIds() {
        return correlationIds;
    }

    public void setCorrelationIds(Map<String, CorrelationId> correlationIds) {
        this.correlationIds = correlationIds;
    }

    public Map<String, OperationTrait> getOperationTraits() {
        return operationTraits;
    }

    public void setOperationTraits(Map<String, OperationTrait> operationTraits) {
        this.operationTraits = operationTraits;
    }

    public Map<String, MessageTrait> getMessageTraits() {
        return messageTraits;
    }

    public void setMessageTraits(Map<String, MessageTrait> messageTraits) {
        this.messageTraits = messageTraits;
    }

    public Map<String, ServerBinding> getServerBindings() {
        return serverBindings;
    }

    public void setServerBindings(Map<String, ServerBinding> serverBindings) {
        this.serverBindings = serverBindings;
    }

    public Map<String, ChannelBinding> getChannelBindings() {
        return channelBindings;
    }

    public void setChannelBindings(Map<String, ChannelBinding> channelBindings) {
        this.channelBindings = channelBindings;
    }

    public Map<String, OperationBinding> getOperationBindings() {
        return operationBindings;
    }

    public void setOperationBindings(Map<String, OperationBinding> operationBindings) {
        this.operationBindings = operationBindings;
    }

    public Map<String, MessageBinding> getMessageBindings() {
        return messageBindings;
    }

    public void setMessageBindings(Map<String, MessageBinding> messageBindings) {
        this.messageBindings = messageBindings;
    }
}
