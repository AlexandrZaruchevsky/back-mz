package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

import javax.xml.bind.annotation.XmlElement;

public class Printer {

    private String comment;
    private String driver;
    private String name;
    private String network;
    private String port;
    private String printProcessor;
    private String resolution;
    private String shared;
    private String shareName;
    private String status;

    @XmlElement(name = "COMMENT")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @XmlElement(name = "DRIVER")
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "NETWORK")
    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @XmlElement(name = "PORT")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @XmlElement(name = "PRINTPROCESSOR")
    public String getPrintProcessor() {
        return printProcessor;
    }

    public void setPrintProcessor(String printProcessor) {
        this.printProcessor = printProcessor;
    }

    @XmlElement(name = "RESOLUTION")
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @XmlElement(name = "SHARED")
    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
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
        return "Printer{" +
                "comment='" + comment + '\'' +
                ", driver='" + driver + '\'' +
                ", name='" + name + '\'' +
                ", network='" + network + '\'' +
                ", port='" + port + '\'' +
                ", printProcessor='" + printProcessor + '\'' +
                ", resolution='" + resolution + '\'' +
                ", shared='" + shared + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @XmlElement(name = "SHARENAME")
    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
}
