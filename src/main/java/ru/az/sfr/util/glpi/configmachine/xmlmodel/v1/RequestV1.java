package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "REQUEST")
public class RequestV1 {

    private Content content;
    private String deviceId;
    private String query;

    @XmlElement(name = "CONTENT")
    @JsonProperty("content")
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @XmlElement(name = "DEVICEID")
    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @XmlElement(name = "QUERY")
    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Request{" +
                "content=" + content +
                ", deviceId='" + deviceId + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
