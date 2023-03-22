package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Input {

    private String caption;
    private String description;
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
