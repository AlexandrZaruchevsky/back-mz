package ru.az.mz.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.SubnetDtoV1;
import ru.az.mz.inventory.service.JAXBService;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.SubnetMap;
import ru.az.mz.repositories.SubnetMapRepo;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;
import ru.az.mz.services.SubnetServiceV1;
import ru.az.mz.services.utils.SubnetEquip;
import ru.az.mz.services.utils.xml.SubnetEquipXml;
import ru.az.mz.services.utils.xml.SubnetXml;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class SubnetServiceV1Impl implements SubnetServiceV1 {

    private final SubnetMapRepo subnetMapRepo;
    private final SecurityService securityService;
    private final JAXBService<SubnetXml> jaxbService = new JAXBService<>();

    public SubnetServiceV1Impl(SubnetMapRepo subnetMapRepo, SecurityService securityService) {
        this.subnetMapRepo = subnetMapRepo;
        this.securityService = securityService;
    }

    @Override
    @Transactional
    public void add(String subnetName, List<SubnetEquip> equips) throws JAXBException {
        List<SubnetEquipXml> list = equips.stream()
                .map(SubnetEquipXml::create)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        SubnetXml subnetXml = new SubnetXml();
        subnetXml.setSubnetEquipXmls(list);
        String xmlFromInputStream = jaxbService.createXmlFromInputStream(subnetXml, SubnetXml.class);
        SubnetMap subnetMap = new SubnetMap();
        subnetMap.setSubnetName(subnetName);
        subnetMap.setSubnetMapXml(xmlFromInputStream.getBytes(StandardCharsets.UTF_8));
        subnetMap.setSaveByUser(securityService.getUsername());
        findAllByName(subnetName).forEach(item -> changeStatus(item.getId(), EntityStatus.NOT_ACTIVE));
        subnetMapRepo.save(subnetMap);
    }

    @Override
    public void update(String subnetName, List<SubnetEquip> equips) {

    }

    @Override
    public void delete(Long id) {
        changeStatus(id, EntityStatus.DELETED);
    }

    @Override
    public void changeStatus(Long id, EntityStatus status) {
        subnetMapRepo.findById(id)
                .ifPresent(subnetMap -> subnetMap.setStatus(status));
    }

    @Override
    public List<SubnetMap> findAll() {
        return subnetMapRepo.findAll();
    }

    @Override
    public SubnetMap findById(Long id) throws MyException {
        return subnetMapRepo.findByStatusAndId(EntityStatus.ACTIVE, id)
                .orElseThrow(() -> new NotFoundException("Subnet not fount", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<SubnetMap> findAllByName(String name) {
        return subnetMapRepo.findAllBySubnetNameContaining(name).stream()
                .filter(subnetMap -> !EntityStatus.DELETED.equals(subnetMap.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> subnetNameList() {
        return subnetMapRepo.findSubnetNames();
//        return subnetMapRepo.findAll().stream()
//                .filter(subnetMap -> !EntityStatus.DELETED.equals(subnetMap.getStatus()))
//                .map(SubnetMap::getSubnetName)
//                .distinct()
//                .collect(Collectors.toList());
    }

    @Override
    public SubnetDtoV1 getSubnetDto1ById(Long id) throws MyException {
        SubnetMap subnetMap = subnetMapRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Subnet not found", HttpStatus.NOT_FOUND));
        return SubnetDtoV1.createWithAll(subnetMap)
                .orElseThrow(() -> new MyException("Error create SubnetDtoV1", HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<SubnetDtoV1> getListSubnetDtoV1() {
        return subnetMapRepo.findAllAsProjection().stream()
                .map(SubnetDtoV1::create)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
