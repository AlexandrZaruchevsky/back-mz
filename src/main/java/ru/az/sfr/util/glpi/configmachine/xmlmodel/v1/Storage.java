package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

import javax.xml.bind.annotation.XmlElement;

public class Storage {

    private String description;
    private String diskSize;
    private String firmware;
    private String interfase;
    private String manufacturer;
    private String model;
    private String name;
    private String scsi_coid;
    private String scsi_lun;
    private String scsi_unid;
    private String serial;
    private String serialNumber;
    private String type;

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "DISKSIZE")
    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    @XmlElement(name = "FIRMWARE")
    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

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

    @XmlElement(name = "SCSI_COID")
    public String getScsi_coid() {
        return scsi_coid;
    }

    public void setScsi_coid(String scsi_coid) {
        this.scsi_coid = scsi_coid;
    }

    @XmlElement(name = "SCSI_LUN")
    public String getScsi_lun() {
        return scsi_lun;
    }

    public void setScsi_lun(String scsi_lun) {
        this.scsi_lun = scsi_lun;
    }

    @XmlElement(name = "SCSI_UNID")
    public String getScsi_unid() {
        return scsi_unid;
    }

    public void setScsi_unid(String scsi_unid) {
        this.scsi_unid = scsi_unid;
    }

    @XmlElement(name = "SERIAL")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @XmlElement(name = "SERIALNUMBER")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
        return "Storage{" +
                "description='" + description + '\'' +
                ", diskSize='" + diskSize + '\'' +
                ", firmware='" + firmware + '\'' +
                ", interfase='" + interfase + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", scsi_coid='" + scsi_coid + '\'' +
                ", scsi_lun='" + scsi_lun + '\'' +
                ", scsi_unid='" + scsi_unid + '\'' +
                ", serial='" + serial + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
