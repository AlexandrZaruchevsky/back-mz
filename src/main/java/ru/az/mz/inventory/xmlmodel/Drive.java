package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Drive {

    private String description;
    private String encrypt_algo;
    private String encrypt_name;
    private String encrypt_status;
    private String fileSystem;
    private String free;
    private String label;
    private String letter;
    private String serial;
    private String systemDrive;
    private String total;
    private String type;
    private String volumN;

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "ENCRYPT_ALGO")
    public String getEncrypt_algo() {
        return encrypt_algo;
    }

    public void setEncrypt_algo(String encrypt_algo) {
        this.encrypt_algo = encrypt_algo;
    }

    @XmlElement(name = "ENCRYPT_NAME")
    public String getEncrypt_name() {
        return encrypt_name;
    }

    public void setEncrypt_name(String encrypt_name) {
        this.encrypt_name = encrypt_name;
    }

    @XmlElement(name = "ENCRYPT_STATUS")
    public String getEncrypt_status() {
        return encrypt_status;
    }

    public void setEncrypt_status(String encrypt_status) {
        this.encrypt_status = encrypt_status;
    }

    @XmlElement(name = "FILESYSTEM")
    public String getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(String fileSystem) {
        this.fileSystem = fileSystem;
    }

    @XmlElement(name = "FREE")
    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    @XmlElement(name = "LABEL")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlElement(name = "LETTER")
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @XmlElement(name = "SERIAL")
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    @XmlElement(name = "SYSTEMDRIVE")
    public String getSystemDrive() {
        return systemDrive;
    }

    public void setSystemDrive(String systemDrive) {
        this.systemDrive = systemDrive;
    }

    @XmlElement(name = "TOTAL")
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @XmlElement(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "VOLUMN")
    public String getVolumN() {
        return volumN;
    }

    public void setVolumN(String volumN) {
        this.volumN = volumN;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "description='" + description + '\'' +
                ", encrypt_algo='" + encrypt_algo + '\'' +
                ", encrypt_name='" + encrypt_name + '\'' +
                ", encrypt_status='" + encrypt_status + '\'' +
                ", fileSystem='" + fileSystem + '\'' +
                ", free='" + free + '\'' +
                ", label='" + label + '\'' +
                ", letter='" + letter + '\'' +
                ", serial='" + serial + '\'' +
                ", systemDrive='" + systemDrive + '\'' +
                ", total='" + total + '\'' +
                ", type='" + type + '\'' +
                ", volumN='" + volumN + '\'' +
                '}';
    }
}
