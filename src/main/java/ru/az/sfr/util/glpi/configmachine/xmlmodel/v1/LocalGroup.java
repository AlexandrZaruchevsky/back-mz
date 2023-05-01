package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;


import javax.xml.bind.annotation.XmlElement;

public class LocalGroup {

    private String id;
    private String name;

    @XmlElement(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "LocalGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
