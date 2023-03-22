package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Software {

    private String arch;
    private String comments;
    private String from;
    private String guid;
    private String helpLink;
    private String installDate;
    private String name;
    private String publisher;
    private String system_category;
    private String uninstall_string;
    private String url_info_about;
    private String version;
    private String version_major;
    private String version_minor;

    @XmlElement(name = "ARCH")
    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    @XmlElement(name = "COMMENTS")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlElement(name = "FROM")
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @XmlElement(name = "GUID")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @XmlElement(name = "HELPLINK")
    public String getHelpLink() {
        return helpLink;
    }

    public void setHelpLink(String helpLink) {
        this.helpLink = helpLink;
    }

    @XmlElement(name = "INSTALLDATE")
    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "PUBLISHER")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @XmlElement(name = "SYSTEM_CATEGORY")
    public String getSystem_category() {
        return system_category;
    }

    public void setSystem_category(String system_category) {
        this.system_category = system_category;
    }

    @XmlElement(name = "UNINSTALL_STRING")
    public String getUninstall_string() {
        return uninstall_string;
    }

    public void setUninstall_string(String uninstall_string) {
        this.uninstall_string = uninstall_string;
    }

    @XmlElement(name = "URL_INFO_ABOUT")
    public String getUrl_info_about() {
        return url_info_about;
    }

    public void setUrl_info_about(String url_info_about) {
        this.url_info_about = url_info_about;
    }

    @XmlElement(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name = "VERSION_MAJOR")
    public String getVersion_major() {
        return version_major;
    }

    public void setVersion_major(String version_major) {
        this.version_major = version_major;
    }

    @XmlElement(name = "VERSION_MINOR")
    public String getVersion_minor() {
        return version_minor;
    }

    public void setVersion_minor(String version_minor) {
        this.version_minor = version_minor;
    }

    @Override
    public String toString() {
        return "\nSoftware{" +
                "arch='" + arch + '\'' +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                ", from='" + from + '\'' +
                ", guid='" + guid + '\'' +
                ", helpLink='" + helpLink + '\'' +
                ", installDate='" + installDate + '\'' +
                ", publisher='" + publisher + '\'' +
                ", system_category='" + system_category + '\'' +
                ", uninstall_string='" + uninstall_string + '\'' +
                ", url_info_about='" + url_info_about + '\'' +
                ", version='" + version + '\'' +
                ", version_major='" + version_major + '\'' +
                ", version_minor='" + version_minor + '\'' +
                '}';
    }
}
