package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CONTROLLERS")
public class Controller {

    private String caption;
    private String manufacturer;
    private String name;
    private String pciDubSystemId;
    private String productId;
    private String type;
    private String vendorId;

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "PCIDUBSYSTEMID")
    public String getPciDubSystemId() {
        return pciDubSystemId;
    }

    public void setPciDubSystemId(String pciDubSystemId) {
        this.pciDubSystemId = pciDubSystemId;
    }

    @XmlElement(name = "PRODUCTID")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @XmlElement(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "VENDORID")
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @XmlElement(name = "CAPTION")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "Controller{" +
                "caption='" + caption + '\'' +
                '}';
    }

    @XmlElement(name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
