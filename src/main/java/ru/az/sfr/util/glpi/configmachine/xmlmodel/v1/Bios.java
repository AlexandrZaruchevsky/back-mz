package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;


import javax.xml.bind.annotation.XmlElement;

public class Bios {

    private String asSetTag;
    private String bDate;
    private String bManufacturer;
    private String bVersion;
    private String mManufacturer;
    private String mModel;
    private String msn;
    private String skuNumber;
    private String sManufacturer;
    private String sModel;
    private String sSn;

    @XmlElement(name = "ASSETTAG")
    public String getAsSetTag() {
        return asSetTag;
    }

    public void setAsSetTag(String asSetTag) {
        this.asSetTag = asSetTag;
    }

    @XmlElement(name = "BDATE")
    public String getbDate() {
        return bDate;
    }

    public void setbDate(String bDate) {
        this.bDate = bDate;
    }

    @XmlElement(name = "BMANUFACTURER")
    public String getbManufacturer() {
        return bManufacturer;
    }

    public void setbManufacturer(String bManufacturer) {
        this.bManufacturer = bManufacturer;
    }

    @XmlElement(name = "BVERSION")
    public String getbVersion() {
        return bVersion;
    }

    public void setbVersion(String bVersion) {
        this.bVersion = bVersion;
    }

    @XmlElement(name = "MMANUFACTURER")
    public String getmManufacturer() {
        return mManufacturer;
    }

    public void setmManufacturer(String mManufacturer) {
        this.mManufacturer = mManufacturer;
    }

    @XmlElement(name = "MMODEL")
    public String getmModel() {
        return mModel;
    }

    public void setmModel(String mModel) {
        this.mModel = mModel;
    }

    @XmlElement(name = "MSN")
    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    @XmlElement(name = "SKUNUMBER")
    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    @XmlElement(name = "SMANUFACTURER")
    public String getsManufacturer() {
        return sManufacturer;
    }

    public void setsManufacturer(String sManufacturer) {
        this.sManufacturer = sManufacturer;
    }

    @XmlElement(name = "SMODEL")
    public String getsModel() {
        return sModel;
    }

    public void setsModel(String sModel) {
        this.sModel = sModel;
    }

    @XmlElement(name = "SSN")
    public String getsSn() {
        return sSn;
    }

    public void setsSn(String sSn) {
        this.sSn = sSn;
    }

    @Override
    public String toString() {
        return "Bios{" +
                "asSetTag='" + asSetTag + '\'' +
                ", bDate='" + bDate + '\'' +
                ", bManufacturer='" + bManufacturer + '\'' +
                ", bVersion='" + bVersion + '\'' +
                ", mManufacturer='" + mManufacturer + '\'' +
                ", mModel='" + mModel + '\'' +
                ", msn='" + msn + '\'' +
                ", skuNumber='" + skuNumber + '\'' +
                ", sManufacturer='" + sManufacturer + '\'' +
                ", sModel='" + sModel + '\'' +
                ", sSn='" + sSn + '\'' +
                '}';
    }
}
