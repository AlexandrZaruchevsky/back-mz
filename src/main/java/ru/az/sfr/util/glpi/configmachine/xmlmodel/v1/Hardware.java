package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;


import javax.xml.bind.annotation.XmlElement;

public class Hardware {

    private String chassis_type;
    private String checksum;
    private String defaultGateway;
    private String dns;
    private String etime;
    private String ipAddr;
    private String lastLoggedUser;
    private String memory;
    private String name;
    private String osComments;
    private String osName;
    private String osVersion;
    private String processorN;
    private String processorS;
    private String processorT;
    private String userId;
    private String uuid;
    private String vmSystem;
    private String winLang;
    private String winOwner;
    private String winProdId;
    private String workgroup;

    @XmlElement(name = "CHASSIS_TYPE")
    public String getChassis_type() {
        return chassis_type;
    }

    public void setChassis_type(String chassis_type) {
        this.chassis_type = chassis_type;
    }

    @XmlElement(name = "CHECKSUM")
    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    @XmlElement(name = "DEFAULTGATEWAY")
    public String getDefaultGateway() {
        return defaultGateway;
    }

    public void setDefaultGateway(String defaultGateway) {
        this.defaultGateway = defaultGateway;
    }

    @XmlElement(name = "DNS")
    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    @XmlElement(name = "ETIME")
    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    @XmlElement(name = "IPADDR")
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @XmlElement(name = "LASTLOGGEDUSER")
    public String getLastLoggedUser() {
        return lastLoggedUser;
    }

    public void setLastLoggedUser(String lastLoggedUser) {
        this.lastLoggedUser = lastLoggedUser;
    }

    @XmlElement(name = "MEMORY")
    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "OSCOMMENTS")
    public String getOsComments() {
        return osComments;
    }

    public void setOsComments(String osComments) {
        this.osComments = osComments;
    }

    @XmlElement(name = "OSNAME")
    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    @XmlElement(name = "OSVERSION")
    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @XmlElement(name = "PROCESSORN")
    public String getProcessorN() {
        return processorN;
    }

    public void setProcessorN(String processorN) {
        this.processorN = processorN;
    }

    @XmlElement(name = "PROCESSORS")
    public String getProcessorS() {
        return processorS;
    }

    public void setProcessorS(String processorS) {
        this.processorS = processorS;
    }

    @XmlElement(name = "PROCESSORT")
    public String getProcessorT() {
        return processorT;
    }

    public void setProcessorT(String processorT) {
        this.processorT = processorT;
    }

    @XmlElement(name = "USERID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlElement(name = "UUID")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @XmlElement(name = "VMSYSTEM")
    public String getVmSystem() {
        return vmSystem;
    }

    public void setVmSystem(String vmSystem) {
        this.vmSystem = vmSystem;
    }

    @XmlElement(name = "WINLANG")
    public String getWinLang() {
        return winLang;
    }

    public void setWinLang(String winLang) {
        this.winLang = winLang;
    }

    @XmlElement(name = "WINOWNER")
    public String getWinOwner() {
        return winOwner;
    }

    public void setWinOwner(String winOwner) {
        this.winOwner = winOwner;
    }

    @XmlElement(name = "WINPRODID")
    public String getWinProdId() {
        return winProdId;
    }

    public void setWinProdId(String winProdId) {
        this.winProdId = winProdId;
    }

    @XmlElement(name = "WORKGROUP")
    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "chassis_type='" + chassis_type + '\'' +
                ", checksum='" + checksum + '\'' +
                ", defaultGateway='" + defaultGateway + '\'' +
                ", dns='" + dns + '\'' +
                ", etime='" + etime + '\'' +
                ", ipAddr='" + ipAddr + '\'' +
                ", lastLoggedUser='" + lastLoggedUser + '\'' +
                ", memory='" + memory + '\'' +
                ", name='" + name + '\'' +
                ", osComments='" + osComments + '\'' +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", processorN='" + processorN + '\'' +
                ", processorS='" + processorS + '\'' +
                ", processorT='" + processorT + '\'' +
                ", userId='" + userId + '\'' +
                ", uuid='" + uuid + '\'' +
                ", vmSystem='" + vmSystem + '\'' +
                ", winLang='" + winLang + '\'' +
                ", winOwner='" + winOwner + '\'' +
                ", winProdId='" + winProdId + '\'' +
                ", workgroup='" + workgroup + '\'' +
                '}';
    }
}
