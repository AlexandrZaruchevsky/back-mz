package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

import javax.xml.bind.annotation.XmlElement;

public class Antivirus {

    private String baseVersion;
    private String company;
    private String enabled;
    private String guid;
    private String name;
    private String upToDate;
    private String version;


    @XmlElement(name = "BASE_VERSION")
    public String getBaseVersion() {
        return baseVersion;
    }

    public void setBaseVersion(String baseVersion) {
        this.baseVersion = baseVersion;
    }

    @XmlElement(name = "COMPANY")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @XmlElement(name = "ENABLED")
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @XmlElement(name = "GUID")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "UPTODATE")
    public String getUpToDate() {
        return upToDate;
    }

    public void setUpToDate(String upToDate) {
        this.upToDate = upToDate;
    }

    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Antivirus{" +
                "baseVersion='" + baseVersion + '\'' +
                ", company='" + company + '\'' +
                ", enabled='" + enabled + '\'' +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", upToDate='" + upToDate + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
