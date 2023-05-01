package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;


import javax.xml.bind.annotation.XmlElement;

public class Firewall {

    private String profile;
    private String status;

    @XmlElement(name = "PROFILE")
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Firewall{" +
                "profile='" + profile + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
