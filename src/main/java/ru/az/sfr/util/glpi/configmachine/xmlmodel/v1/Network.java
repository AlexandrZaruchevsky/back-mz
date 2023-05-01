package ru.az.sfr.util.glpi.configmachine.xmlmodel.v1;

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
    private String ipAddress6;
    private String ipMask6;

    @XmlElement(name = "IPADDRESS6")
    public String getIpAddress6() {
        return ipAddress6;
    }

    public void setIpAddress6(String ipAddress6) {
        this.ipAddress6 = ipAddress6;
    }

    @XmlElement(name = "IPMASK6")
    public String getIpMask6() {
        return ipMask6;
    }

    public void setIpMask6(String ipMask6) {
        this.ipMask6 = ipMask6;
    }

    @XmlElement(name = "IPSUBNET6")
    public String getIpSubnet6() {
        return ipSubnet6;
    }

    public void setIpSubnet6(String ipSubnet6) {
        this.ipSubnet6 = ipSubnet6;
    }

    private String ipSubnet6;

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
