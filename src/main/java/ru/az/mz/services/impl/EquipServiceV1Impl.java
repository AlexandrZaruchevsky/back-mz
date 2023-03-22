package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EquipDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Equip;
import ru.az.mz.repositories.ArmRepo;
import ru.az.mz.repositories.EquipModelRepo;
import ru.az.mz.repositories.EquipRepo;
import ru.az.mz.repositories.EquipTypeRepo;
import ru.az.mz.services.EquipServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipServiceV1Impl implements EquipServiceV1 {

    private final EquipRepo equipRepo;
    private final ArmRepo armRepo;
    private final EquipTypeRepo equipTypeRepo;
    private final EquipModelRepo equipModelRepo;
    private final SecurityService securityService;

    public EquipServiceV1Impl(
            EquipRepo equipRepo,
            ArmRepo armRepo,
            EquipTypeRepo equipTypeRepo,
            EquipModelRepo equipModelRepo,
            SecurityService securityService
    ) {
        this.equipRepo = equipRepo;
        this.armRepo = armRepo;
        this.equipTypeRepo = equipTypeRepo;
        this.equipModelRepo = equipModelRepo;
        this.securityService = securityService;
    }


    @Override
    public Page<Equip> findAllByName(String name, Pageable pageable) {
        return equipRepo.findAllByShortNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public Page<Equip> findAllByInventoryNumber(String inventoryNumber, Pageable pageable) {
        return equipRepo.findAllByInventoryNumberStartingWithAndStatus(inventoryNumber, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public Page<Equip> findAllBySerialNumber(String serialNumber, Pageable pageable) {
        return equipRepo.findAllBySerialNumberStartingWithAndStatus(serialNumber, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Equip> findAllByStatus(EntityStatus status) {
        return equipRepo.findAllByStatus(status);
    }

    private void fillEquip(EquipDtoV1 equipDtoV1, Equip equip) {
        equip.setShortName(equipDtoV1.getShortName());
        equip.setFullName(equipDtoV1.getFullName());
        equip.setSerialNumber(equipDtoV1.getSerialNumber());
        equip.setInventoryNumber(equipDtoV1.getInventoryNumber());
        equip.setManufacturer(equipDtoV1.getManufacturer());
        equip.setDateOfManufacture(equipDtoV1.getDateOfManufacture());
        equip.setArm(
                equipDtoV1.getArm() != null
                        ? armRepo.findByIdAndStatus(equipDtoV1.getArm().getId(), EntityStatus.ACTIVE).orElse(null)
                        : null
        );
        equip.setEquipType(
                equipDtoV1.getEquipType() != null
                        ? equipTypeRepo.findByIdAndStatus(equipDtoV1.getEquipType().getId(), EntityStatus.ACTIVE).orElse(null)
                        : null
        );
        equip.setEquipModel(
                equipDtoV1.getEquipModel() != null
                        ? equipModelRepo.findByIdAndStatus(equipDtoV1.getEquipModel().getId(), EntityStatus.ACTIVE).orElse(null)
                        : null
        );
        equip.setSaveByUser(securityService.getUsername());
    }

    @Override
    @Transactional
    public Equip add(EquipDtoV1 equipDtoV1) throws MyException {
        Equip equip = new Equip();
        fillEquip(equipDtoV1, equip);
        return equipRepo.save(equip);
    }

    @Override
    @Transactional
    public Equip update(EquipDtoV1 equipDtoV1) throws MyException {
        Equip equip = findById(equipDtoV1.getId());
        fillEquip(equipDtoV1, equip);
        return equipRepo.save(equip);
    }

    @Override
    @Transactional
    public boolean delete(long id) throws MyException {
        Equip equip = findById(id);
        equip.setStatus(EntityStatus.DELETED);
        equipRepo.save(equip);
        return true;
    }

    @Override
    public Equip findById(Long id) throws MyException {
        return equipRepo.findByIdAndStatus(id, EntityStatus.ACTIVE).orElseThrow(() -> new NotFoundException("Equip not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Equip> findAll(Pageable pageable) {
        return equipRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Equip> findAll() {
        return equipRepo.findAll();
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return equipRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return equipRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return equipRepo.existsById(id);
    }
}
