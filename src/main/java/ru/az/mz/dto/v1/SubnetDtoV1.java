package ru.az.mz.dto.v1;

import lombok.Data;
import ru.az.mz.dto.v1.projections.SubnetMapProjectionV1;
import ru.az.mz.inventory.service.JAXBService;
import ru.az.mz.model.SubnetMap;
import ru.az.mz.services.utils.xml.SubnetXml;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class SubnetDtoV1 {

    private Long id;
    private String subnetName;
    private String status;
    private List<SubnetEquipDtoV1> subnetEquips;
    private static final JAXBService<SubnetXml> jaxbService = new JAXBService<>();

    public static Optional<SubnetDtoV1> create(SubnetMap subnetMap) {
        if (subnetMap == null) return Optional.empty();
        SubnetDtoV1 subnetDtoV1 = new SubnetDtoV1();
        subnetDtoV1.setId(subnetMap.getId());
        subnetDtoV1.setStatus(subnetMap.getStatus().name());
        subnetDtoV1.setSubnetName(subnetMap.getSubnetName());
        return Optional.of(subnetDtoV1);
    }

    public static Optional<SubnetDtoV1> create(SubnetMapProjectionV1 subnetMap) {
        if (subnetMap == null) return Optional.empty();
        SubnetDtoV1 subnetDtoV1 = new SubnetDtoV1();
        subnetDtoV1.setId(subnetMap.getId());
        subnetDtoV1.setSubnetName(subnetMap.getSubnetName());
        subnetDtoV1.setStatus(subnetMap.getStatus());
        return Optional.of(subnetDtoV1);
    }

    public static Optional<SubnetDtoV1> createWithAll(SubnetMap subnetMap) {
        if (subnetMap == null) return Optional.empty();
        SubnetDtoV1 subnetDtoV1 = new SubnetDtoV1();
        subnetDtoV1.setId(subnetMap.getId());
        subnetDtoV1.setSubnetName(subnetMap.getSubnetName());
        subnetDtoV1.setStatus(subnetMap.getStatus().name());
        byte[] subnetMapXml = subnetMap.getSubnetMapXml();
        if (subnetMapXml != null) {
            try {
                SubnetXml subnetXml = jaxbService.getObjectFromFile(new ByteArrayInputStream(subnetMapXml), SubnetXml.class);
                List<SubnetEquipDtoV1> subnetEquipDtoV1s = subnetXml.getSubnetEquipXmls().stream()
                        .map(SubnetEquipDtoV1::create)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());
                subnetDtoV1.setSubnetEquips(subnetEquipDtoV1s);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return Optional.of(subnetDtoV1);
    }

}
