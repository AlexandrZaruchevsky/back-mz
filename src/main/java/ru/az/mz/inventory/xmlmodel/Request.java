package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "REQUEST")
public class Request {

    private Content content;
    private String deviceId;
    private String query;

    @XmlElement(name = "CONTENT")
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @XmlElement(name = "DEVICEID")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @XmlElement(name = "QUERY")
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
