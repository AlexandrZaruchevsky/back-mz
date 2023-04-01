package ru.az.mz.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.*;
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
import java.util.stream.Collectors;

@Service
public class EquipServiceV1Impl implements EquipServiceV1 {

    private final EquipRepo equipRepo;
    private final ArmRepo armRepo;
    private final EquipTypeRepo equipTypeRepo;
    private final EquipModelRepo equipModelRepo;
    private final SecurityService securityService;
    private final SetupParameters setupParameters;

    public EquipServiceV1Impl(
            EquipRepo equipRepo,
            ArmRepo armRepo,
            EquipTypeRepo equipTypeRepo,
            EquipModelRepo equipModelRepo,
            SecurityService securityService,
            SetupParameters setupParameters
    ) {
        this.equipRepo = equipRepo;
        this.armRepo = armRepo;
        this.equipTypeRepo = equipTypeRepo;
        this.equipModelRepo = equipModelRepo;
        this.securityService = securityService;
        this.setupParameters = setupParameters;
    }

    @Override
    public Page<Equip> findAllByName(String name, Pageable pageable) {
        return equipRepo.findAllByShortNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
    public Page<EquipDtoV1> findAll(PageRequestEquipDtoV1 pageRequest) throws MyException {
        if (pageRequest == null || "".equalsIgnoreCase(pageRequest.getSortBy().trim())) {
            return equipRepo.findAllByStatus(
                    EntityStatus.ACTIVE,
                    setupParameters.getPageRequestDefault().withSort(Sort.by("shortName"))
            ).map(EquipDtoV1::createWithEquipType);
        }
        PageRequest request = PageRequest.of(pageRequest.getPageCurrent(), pageRequest.getPageSize());
        if (!"".equalsIgnoreCase(pageRequest.getSortBy().trim())) {
            request = request.withSort(Sort.by(pageRequest.getSortBy().trim()));
        }
        switch (pageRequest.getSortBy().trim()) {
            case "serialNumber":
                return getEquipBySerialNumber(pageRequest, request);
            case "inventoryNumber":
                return getEquipByInventoryNumber(pageRequest, request);
            default:
                return getEquipByShortName(pageRequest, request);
        }
    }

    private Page<EquipDtoV1> getEquipBySerialNumber(PageRequestEquipDtoV1 pageRequest, PageRequest request) throws NotFoundException {
        if (pageRequest.getEquipModelId() <= 0) {
            if (pageRequest.getEquipTypeId() <= 0) {
                return equipRepo.findAllByStatusAndSerialNumberContainingOrSerialNumberIsNull(
                        EntityStatus.ACTIVE,
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            } else {
                return equipRepo.findAllByStatusAndEquipTypeAndSerialNumberContainingOrSerialNumberIsNull(
                        EntityStatus.ACTIVE,
                        equipTypeRepo.findByIdAndStatus(pageRequest.getEquipTypeId(), EntityStatus.ACTIVE)
                                .orElseThrow(() -> new NotFoundException("Equip Type not found", HttpStatus.NOT_FOUND)),
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            }
        } else {
            return equipRepo.findAllByStatusAndEquipModelAndSerialNumberContainingOrSerialNumberIsNull(
                    EntityStatus.ACTIVE,
                    equipModelRepo.findByIdAndStatus(pageRequest.getEquipModelId(), EntityStatus.ACTIVE)
                            .orElseThrow(() -> new NotFoundException("Equip Model not found", HttpStatus.NOT_FOUND)),
                    pageRequest.getSearch(),
                    request
            ).map(EquipDtoV1::createWithEquipType);
        }
    }

    private Page<EquipDtoV1> getEquipByInventoryNumber(PageRequestEquipDtoV1 pageRequest, PageRequest request) throws NotFoundException {
        if (pageRequest.getEquipModelId() <= 0) {
            if (pageRequest.getEquipTypeId() <= 0) {
                return equipRepo.findAllByStatusAndInventoryNumberContainingOrInventoryNumberIsNull(
                        EntityStatus.ACTIVE,
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            } else {
                return equipRepo.findAllByStatusAndEquipTypeAndInventoryNumberContainingOrInventoryNumberIsNull(
                        EntityStatus.ACTIVE,
                        equipTypeRepo.findByIdAndStatus(pageRequest.getEquipTypeId(), EntityStatus.ACTIVE)
                                .orElseThrow(() -> new NotFoundException("Equip Type not found", HttpStatus.NOT_FOUND)),
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            }
        } else {
            return equipRepo.findAllByStatusAndEquipModelAndInventoryNumberContainingOrInventoryNumberIsNull(
                    EntityStatus.ACTIVE,
                    equipModelRepo.findByIdAndStatus(pageRequest.getEquipModelId(), EntityStatus.ACTIVE)
                            .orElseThrow(() -> new NotFoundException("Equip Model not found", HttpStatus.NOT_FOUND)),
                    pageRequest.getSearch(),
                    request
            ).map(EquipDtoV1::createWithEquipType);
        }
    }

    private Page<EquipDtoV1> getEquipByShortName(PageRequestEquipDtoV1 pageRequest, PageRequest request) throws NotFoundException {
        if (pageRequest.getEquipModelId() <= 0) {
            if (pageRequest.getEquipTypeId() <= 0) {
                return equipRepo.findAllByStatusAndShortNameContainingOrShortNameIsNull(
                        EntityStatus.ACTIVE,
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            } else {
                return equipRepo.findAllByStatusAndEquipTypeAndShortNameContainingOrShortNameIsNull(
                        EntityStatus.ACTIVE,
                        equipTypeRepo.findByIdAndStatus(pageRequest.getEquipTypeId(), EntityStatus.ACTIVE)
                                .orElseThrow(() -> new NotFoundException("Equip Type not found", HttpStatus.NOT_FOUND)),
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            }
        } else {
            return equipRepo.findAllByStatusAndEquipModelAndShortNameContainingOrShortNameIsNull(
                    EntityStatus.ACTIVE,
                    equipModelRepo.findByIdAndStatus(pageRequest.getEquipModelId(), EntityStatus.ACTIVE)
                            .orElseThrow(() -> new NotFoundException("Equip Model not found", HttpStatus.NOT_FOUND)),
                    pageRequest.getSearch(),
                    request
            ).map(EquipDtoV1::createWithEquipType);
        }
    }

    @Override
    public Page<Equip> findAllByInventoryNumber(String inventoryNumber, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Equip> findAllBySerialNumber(String serialNumber, Pageable pageable) {
        return null;
    }

    @Override
    public List<Equip> findAllByStatus(EntityStatus status) {
        return equipRepo.findAllByStatus(status);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "equipParents")
    public EquipParentsDtoV1 getEquipParents() {
        return new EquipParentsDtoV1(
                armRepo.findAllByStatus(EntityStatus.ACTIVE).stream().map(ArmDtoV1::create).collect(Collectors.toList()),
                equipTypeRepo.findAllByStatus(EntityStatus.ACTIVE).stream().map(EquipTypeDtoV1::create).collect(Collectors.toList()),
                equipModelRepo.findAllByStatus(EntityStatus.ACTIVE).stream().map(EquipModelDtoV1::createWithEquipType).collect(Collectors.toList())
        );
    }

    private void fillEquip(EquipDtoV1 equipDtoV1, Equip equip) {
        equip.setShortName(equipDtoV1.getShortName());
        equip.setFullName(equipDtoV1.getFullName());
        equip.setSerialNumber(equipDtoV1.getSerialNumber());
        equip.setInventoryNumber(equipDtoV1.getInventoryNumber());
        equip.setManufacturer(equipDtoV1.getManufacturer());
        equip.setDateOfManufacture(equipDtoV1.getDateOfManufacture());
        equip.setArm(
                equipDtoV1.getArmId() != null && equipDtoV1.getArmId() > 0
                        ? armRepo.findByIdAndStatus(equipDtoV1.getArmId(), EntityStatus.ACTIVE).orElse(null)
                        : null
        );
        equip.setEquipType(
                equipDtoV1.getEquipTypeId() != null && equipDtoV1.getEquipTypeId() > 0
                        ? equipTypeRepo.findByIdAndStatus(equipDtoV1.getEquipTypeId(), EntityStatus.ACTIVE).orElse(null)
                        : null
        );
        equip.setEquipModel(
                equipDtoV1.getEquipModelId() != null && equipDtoV1.getEquipModelId() > 0
                        ? equipModelRepo.findByIdAndStatus(equipDtoV1.getEquipModelId(), EntityStatus.ACTIVE).orElse(null)
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
