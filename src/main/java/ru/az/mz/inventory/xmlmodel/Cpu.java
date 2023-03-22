package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Cpu {

    private String core;
    private String description;
    private String familyNumber;
    private String id;
    private String manufacturer;
    private String model;
    private String name;
    private String speed;
    private String stepping;
    private String thread;

    @XmlElement(name = "CORE")
    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "FAMILYNUMBER")
    public String getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }

    @XmlElement(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @XmlElement(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "SPEED")
    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @XmlElement(name = "STEPPING")
    public String getStepping() {
        return stepping;
    }

    public void setStepping(String stepping) {
        this.stepping = stepping;
    }

    @XmlElement(name = "THREAD")
    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "core='" + core + '\'' +
                ", description='" + description + '\'' +
                ", familyNumber='" + familyNumber + '\'' +
                ", id='" + id + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", speed='" + speed + '\'' +
                ", stepping='" + stepping + '\'' +
                ", thread='" + thread + '\'' +
                '}';
    }
}
