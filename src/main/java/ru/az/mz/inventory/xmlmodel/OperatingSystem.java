package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;

public class OperatingSystem {

    private String arch;
    private String boot_time;
    private String dns_domain;
    private String fqdn;
    private String full_name;
    private String install_date;
    private String kernel_name;
    private String kernel_version;
    private String name;
    private String service_pack;
    private TimeZone timeZone;

    @XmlElement(name = "ARCH")
    public String getArch() {
        return arch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    @XmlElement(name = "BOOT_TIME")
    public String getBoot_time() {
        return boot_time;
    }

    public void setBoot_time(String boot_time) {
        this.boot_time = boot_time;
    }

    @XmlElement(name = "DNS_DOMAIN")
    public String getDns_domain() {
        return dns_domain;
    }

    public void setDns_domain(String dns_domain) {
        this.dns_domain = dns_domain;
    }

    @XmlElement(name = "FQDN")
    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    @XmlElement(name = "FULL_NAME")
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @XmlElement(name = "INSTALL_DATE")
    public String getInstall_date() {
        return install_date;
    }

    public void setInstall_date(String install_date) {
        this.install_date = install_date;
    }

    @XmlElement(name = "KERNEL_NAME")
    public String getKernel_name() {
        return kernel_name;
    }

    public void setKernel_name(String kernel_name) {
        this.kernel_name = kernel_name;
    }

    @XmlElement(name = "KERNEL_VERSION")
    public String getKernel_version() {
        return kernel_version;
    }

    public void setKernel_version(String kernel_version) {
        this.kernel_version = kernel_version;
    }

    @XmlElement(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "SERVICE_PACK")
    public String getService_pack() {
        return service_pack;
    }

    public void setService_pack(String service_pack) {
        this.service_pack = service_pack;
    }

    @XmlElement(name = "TIMEZONE")
    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "OperatingSystem{" +
                "arch='" + arch + '\'' +
                ", boot_time='" + boot_time + '\'' +
                ", dns_domain='" + dns_domain + '\'' +
                ", fqdn='" + fqdn + '\'' +
                ", full_name='" + full_name + '\'' +
                ", install_date='" + install_date + '\'' +
                ", kernel_name='" + kernel_name + '\'' +
                ", kernel_version='" + kernel_version + '\'' +
                ", name='" + name + '\'' +
                ", service_pack='" + service_pack + '\'' +
                ", timeZone=" + timeZone +
                '}';
    }
}
