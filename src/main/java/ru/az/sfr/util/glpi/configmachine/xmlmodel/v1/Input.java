package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;


import javax.xml.bind.annotation.XmlElement;

public class Input {

    private String caption;
    private String description;
    private String interfase;
    private String manufacturer;
    private String pointIngType;

    @XmlElement(name = "INTERFACE")
    public String getInterfase() {
        return interfase;
    }

    public void setInterfase(String interfase) {
        this.interfase = interfase;
    }

    @XmlElement(name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @XmlElement(name = "POINTINGTYPE")
    public String getPointIngType() {
        return pointIngType;
    }

    public void setPointIngType(String pointIngType) {
        this.pointIngType = pointIngType;
    }

    private String layout;
    private String name;

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

    @XmlElement(name = "LAYOUT")
    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Input{" +
                "caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", layout='" + layout + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
