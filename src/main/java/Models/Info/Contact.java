package Models.Info;

import com.fasterxml.jackson.databind.JsonNode;

public class Contact {

    private String name;
    private String url;
    private String email;

    public Contact(JsonNode contactInfoJsonNode){
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
