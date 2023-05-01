package ru.az.mz.services.utils.xml;

import lombok.ToString;
import org.springframework.beans.BeanUtils;
import ru.az.mz.services.utils.SubnetEquip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Optional;

@XmlRootElement(name = "subnet-equip")
@ToString
public class SubnetEquipXml {

    private String hostAddress;
    private String hostName;
    private String canonicalHostName;
    private boolean active;
    private long pingTime;
    private LocalDateTime pingCurrentTime;

    public static Optional<SubnetEquipXml> create(SubnetEquip subnetEquip) {
        if (subnetEquip == null) return Optional.empty();
        SubnetEquipXml subnetEquipXml = new SubnetEquipXml();
        BeanUtils.copyProperties(subnetEquip, subnetEquipXml);
        return Optional.of(subnetEquipXml);
    }


    @XmlElement(name = "host-address")
    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    @XmlElement(name = "host-name")
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @XmlElement(name = "canonical-host-name")
    public String getCanonicalHostName() {
        return canonicalHostName;
    }

    public void setCanonicalHostName(String canonicalHostName) {
        this.canonicalHostName = canonicalHostName;
    }

    @XmlElement(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlElement(name = "ping-time")
    public long getPingTime() {
        return pingTime;
    }

    public void setPingTime(long pingTime) {
        this.pingTime = pingTime;
    }

    @XmlElement(name = "ping-current-time")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getPingCurrentTime() {
        return pingCurrentTime;
    }

    public void setPingCurrentTime(LocalDateTime pingCurrentTime) {
        this.pingCurrentTime = pingCurrentTime;
    }
}
