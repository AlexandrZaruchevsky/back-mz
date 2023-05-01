package ru.az.mz.services.utils.xml;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "subnet-list")
@ToString
public class SubnetXml {

    private List<SubnetEquipXml> subnetEquipXmls;

    @XmlElement(name = "subnet-equip")
    public List<SubnetEquipXml> getSubnetEquipXmls() {
        return subnetEquipXmls;
    }

    public void setSubnetEquipXmls(List<SubnetEquipXml> subnetEquipXmls) {
        this.subnetEquipXmls = subnetEquipXmls;
    }
}
