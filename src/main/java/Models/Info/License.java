package Models.Info;

import com.fasterxml.jackson.databind.JsonNode;

public class License {

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
