package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class Network {

    private String description;
    private String ipAddress;
    private String ipDhcp;
    private String ipGateway;
    private String ipMask;
    private String ipSubnet;
    private String macAddr;
    private String pciId;
    private String pnpDeviceId;
    private String speed;
    private String status;
    private String virtualDev;

    @XmlElement(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "IPADDRESS")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @XmlElement(name = "IPDHCP")
    public String getIpDhcp() {
        return ipDhcp;
    }

    public void setIpDhcp(String ipDhcp) {
        this.ipDhcp = ipDhcp;
    }

    @XmlElement(name = "IPGATEWAY")
    public String getIpGateway() {
        return ipGateway;
    }

    public void setIpGateway(String ipGateway) {
        this.ipGateway = ipGateway;
    }

    @XmlElement(name = "IPMASK")
    public String getIpMask() {
        return ipMask;
    }

    public void setIpMask(String ipMask) {
        this.ipMask = ipMask;
    }

    @XmlElement(name = "IPSUBNET")
    public String getIpSubnet() {
        return ipSubnet;
    }

    public void setIpSubnet(String ipSubnet) {
        this.ipSubnet = ipSubnet;
    }

    @XmlElement(name = "MACADDR")
    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    @XmlElement(name = "PCIID")
    public String getPciId() {
        return pciId;
    }

    public void setPciId(String pciId) {
        this.pciId = pciId;
    }

    @XmlElement(name = "PNPDEVICEID")
    public String getPnpDeviceId() {
        return pnpDeviceId;
    }

    public void setPnpDeviceId(String pnpDeviceId) {
        this.pnpDeviceId = pnpDeviceId;
    }

    @XmlElement(name = "SPEED")
    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @XmlElement(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "VIRTUALDEV")
    public String getVirtualDev() {
        return virtualDev;
    }

    public void setVirtualDev(String virtualDev) {
        this.virtualDev = virtualDev;
    }

    @Override
    public String toString() {
        return "Network{" +
                "description='" + description + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", ipDhcp='" + ipDhcp + '\'' +
                ", ipGateway='" + ipGateway + '\'' +
                ", ipMask='" + ipMask + '\'' +
                ", ipSubnet='" + ipSubnet + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", pciId='" + pciId + '\'' +
                ", pnpDeviceId='" + pnpDeviceId + '\'' +
                ", speed='" + speed + '\'' +
                ", status='" + status + '\'' +
                ", virtualDev='" + virtualDev + '\'' +
                '}';
    }
}
