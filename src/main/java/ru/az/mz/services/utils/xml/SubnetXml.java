package ru.az.mz.services.utils.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "subnet-list")
public class SubnetXml {

    private List<SubnetEquipXml> subnetEquipXmls;

    @XmlElement(name = "subnet-equip-list")
    public List<SubnetEquipXml> getSubnetEquipXmls() {
        return subnetEquipXmls;
    }

    public void setSubnetEquipXmls(List<SubnetEquipXml> subnetEquipXmls) {
        this.subnetEquipXmls = subnetEquipXmls;
    }
}
