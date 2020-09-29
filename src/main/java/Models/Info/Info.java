package Models.Info;

import com.fasterxml.jackson.databind.JsonNode;

public class Info {

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

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public Contact getContact() {
        return contact;
    }

    public License getLicense() {
        return license;
    }
}
