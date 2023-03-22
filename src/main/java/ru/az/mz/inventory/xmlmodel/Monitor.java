package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Monitor {

    private String altSerial;
    private String base64;
    private String caption;
    private String description;
    private String manufacturer;
    private String name;
    private String port;
    private String serial;
    private String type;

    @XmlElement(name = "ALTSERIAL")
    public String getAltSerial() {
        return altSerial;
    }

    public void setAltSerial(String altSerial) {
        this.altSerial = altSerial;
    }

    @XmlElement(name = "BASE64")
    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @XmlElement(name = "CAPTION")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "PORT")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @XmlElement(name = "SERIAL")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @XmlElement(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "altSerial='" + altSerial + '\'' +
                ", base64='" + base64 + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", port='" + port + '\'' +
                ", serial='" + serial + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
