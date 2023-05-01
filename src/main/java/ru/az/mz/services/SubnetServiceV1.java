package ru.az.mz.services;

import ru.az.mz.dto.v1.SubnetDtoV1;
import ru.az.mz.dto.v1.SubnetEquipDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.SubnetMap;
import ru.az.mz.services.utils.SubnetEquip;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface SubnetServiceV1 {

    void add(String subnetName, List<SubnetEquip> equips) throws JAXBException;

    void update(String subnetName, List<SubnetEquip> equips);

    void delete(Long id);

    void changeStatus(Long id, EntityStatus status);

    List<SubnetMap> findAll();

    SubnetMap findById(Long id) throws MyException;

    List<SubnetMap> findAllByName(String name);

    List<String> subnetNameList();

    SubnetDtoV1 getSubnetDto1ById(Long id) throws MyException;

    List<SubnetDtoV1> getListSubnetDtoV1();

    List<SubnetEquipDtoV1> getSubnetEquipsBySubnetId(Long id) throws MyException;

}
