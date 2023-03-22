package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class LicenseInfOs {

    private String components;
    private String fullName;
    private String key;
    private String name;
    private String productId;

    @XmlElement(name = "COMPONENTS")
    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    @XmlElement(name = "FULLNAME")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlElement(name = "KEY")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public String toString() {
        return "LicenseInfOs{" +
                "components='" + components + '\'' +
                ", fullName='" + fullName + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
