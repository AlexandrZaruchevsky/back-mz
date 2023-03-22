package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Memory {

    private String capacity;
    private String caption;
    private String description;
    private String manufacturer;
    private String model;
    private String numSlots;
    private String serialNumbers;
    private String speed;
    private String type;

    @XmlElement(name = "CAPACITY")
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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

    @XmlElement(name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @XmlElement(name = "NUMSLOTS")
    public String getNumSlots() {
        return numSlots;
    }

    public void setNumSlots(String numSlots) {
        this.numSlots = numSlots;
    }

    @XmlElement(name = "SERIALNUMBERS")
    public String getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(String serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    @XmlElement(name = "SPEED")
    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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
        return "Memory{" +
                "capacity='" + capacity + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", numSlots='" + numSlots + '\'' +
                ", serialNumbers='" + serialNumbers + '\'' +
                ", speed='" + speed + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
