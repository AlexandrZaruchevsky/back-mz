package ru.az.mz.inventory.xmlmodel;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Content {

    private AccessLog accessLog;
    private Antivirus antivirus;
    private Bios bios;
    private List<Controller> controllers;
    private Cpu cpu;
    private List<Drive> drives;
    private List<Env> envs;
    private List<Firewall> firewalls;
    private Hardware hardware;
    private List<Input> inputs;
    private List<LicenseInfOs> licenseInfOs;
    private List<LocalGroup> localGroups;
    private List<Memory> memories;
    private List<Monitor> monitors;
    private List<Network> networks;
    private OperatingSystem operatingSystem;
    private List<Port> ports;
    private List<Printer> printers;
    private List<Slot> slots;
    private List<Software> softwares;
    private List<Sound> sounds;
    private List<Storage> storages;
    private List<UsbDevice> usbDevices;
    private List<User> users;
    private String versionClient;
    private VersionProvider versionProvider;
    private List<Video> videos;

    @XmlElement(name = "VIDEOS")
    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @XmlElement(name = "VERSIONPROVIDER")
    public VersionProvider getVersionProvider() {
        return versionProvider;
    }

    public void setVersionProvider(VersionProvider versionProvider) {
        this.versionProvider = versionProvider;
    }

    @XmlElement(name = "VERSIONCLIENT")
    public String getVersionClient() {
        return versionClient;
    }

    public void setVersionClient(String versionClient) {
        this.versionClient = versionClient;
    }

    @XmlElement(name = "USERS")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @XmlElement(name = "USBDEVICES")
    public List<UsbDevice> getUsbDevices() {
        return usbDevices;
    }

    public void setUsbDevices(List<UsbDevice> usbDevices) {
        this.usbDevices = usbDevices;
    }

    @XmlElement(name = "STORAGES")
    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    @XmlElement(name = "SOUNDS")
    public List<Sound> getSounds() {
        return sounds;
    }

    public void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }

    @XmlElement(name = "SOFTWARES")
    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    @XmlElement(name = "SLOTS")
    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    @XmlElement(name = "PRINTERS")
    public List<Printer> getPrinters() {
        return printers;
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }

    @XmlElement(name = "PORTS")
    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    @XmlElement(name = "OPERATINGSYSTEM")
    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @XmlElement(name = "NETWORKS")
    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    @XmlElement(name = "MONITORS")
    public List<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<Monitor> monitors) {
        this.monitors = monitors;
    }

    @XmlElement(name = "MEMORIES")
    public List<Memory> getMemories() {
        return memories;
    }

    public void setMemories(List<Memory> memories) {
        this.memories = memories;
    }

    @XmlElement(name = "LOCAL_GROUPS")
    public List<LocalGroup> getLocalGroups() {
        return localGroups;
    }

    public void setLocalGroups(List<LocalGroup> localGroups) {
        this.localGroups = localGroups;
    }

    @XmlElement(name = "LICENSEINFOS")
    public List<LicenseInfOs> getLicenseInfOs() {
        return licenseInfOs;
    }

    public void setLicenseInfOs(List<LicenseInfOs> licenseInfOs) {
        this.licenseInfOs = licenseInfOs;
    }

    @XmlElement(name = "INPUTS")
    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    @XmlElement(name = "HARDWARE")
    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    @XmlElement(name = "FIREWALL")
    public List<Firewall> getFirewalls() {
        return firewalls;
    }

    public void setFirewalls(List<Firewall> firewalls) {
        this.firewalls = firewalls;
    }

    public Content() {
    }

    @XmlElement(name = "ENVS")
    public List<Env> getEnvs() {
        return envs;
    }

    public void setEnvs(List<Env> envs) {
        this.envs = envs;
    }

    @XmlElement(name = "DRIVES")
    public List<Drive> getDrives() {
        return drives;
    }

    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }

    @XmlElement(name = "CPUS")
    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    @XmlElement(name = "CONTROLLERS")
    public List<Controller> getControllers() {
        return controllers;
    }

    public void setControllers(List<Controller> controllers) {
        this.controllers = controllers;
    }

    @XmlElement(name = "BIOS")
    public Bios getBios() {
        return bios;
    }

    public void setBios(Bios bios) {
        this.bios = bios;
    }

    @XmlElement(name = "ACCESSLOG")
    public AccessLog getAccessLog() {
        return accessLog;
    }

    public void setAccessLog(AccessLog accessLog) {
        this.accessLog = accessLog;
    }

    @XmlElement(name = "ANTIVIRUS")
    public Antivirus getAntivirus() {
        return antivirus;
    }

    public void setAntivirus(Antivirus antivirus) {
        this.antivirus = antivirus;
    }

    @Override
    public String toString() {
        return "Content{" +
                "videos=" + videos +
                '}';
    }

}
