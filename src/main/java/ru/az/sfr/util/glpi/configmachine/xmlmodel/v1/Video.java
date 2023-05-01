package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

import javax.xml.bind.annotation.XmlElement;

public class Video {

    private String chipset;
    private String name;
    private String resolution;

    @XmlElement(name = "CHIPSET")
    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "RESOLUTION")
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "Video{" +
                "chipset='" + chipset + '\'' +
                ", name='" + name + '\'' +
                ", resolution='" + resolution + '\'' +
                '}';
    }
}
