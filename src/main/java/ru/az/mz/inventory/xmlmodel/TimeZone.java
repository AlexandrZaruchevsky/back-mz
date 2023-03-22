package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class TimeZone {

    private String name;
    private String offset;

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "OFFSET")
    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "TimeZone{" +
                "name='" + name + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
