package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class UsbDevice {

    private String caption;
    private String manufacturer;
    private String name;
    private String productId;
    private String serial;
    private String vendorId;

    @XmlElement(name = "CAPTION")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
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

    @XmlElement(name = "PRODUCTID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @XmlElement(name = "SERIAL")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @XmlElement(name = "VENDORID")
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "UsbDevice{" +
                "caption='" + caption + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                ", serial='" + serial + '\'' +
                ", vendorId='" + vendorId + '\'' +
                '}';
    }
}
