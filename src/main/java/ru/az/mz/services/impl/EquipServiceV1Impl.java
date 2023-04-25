package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.*;
import ru.az.mz.model.*;
import ru.az.mz.repositories.*;
import ru.az.mz.services.EquipServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipServiceV1Impl implements EquipServiceV1 {

    private final EquipRepo equipRepo;
    private final ArmRepo armRepo;
    private final EquipTypeRepo equipTypeRepo;
    private final EquipModelRepo equipModelRepo;
    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;
    private final SetupParameters setupParameters;

    public EquipServiceV1Impl(
            EquipRepo equipRepo,
            ArmRepo armRepo,
            EquipTypeRepo equipTypeRepo,
            EquipModelRepo equipModelRepo,
            EmployeeRepo employeeRepo,
            SecurityService securityService,
            SetupParameters setupParameters
    ) {
        this.equipRepo = equipRepo;
        this.armRepo = armRepo;
        this.equipTypeRepo = equipTypeRepo;
        this.equipModelRepo = equipModelRepo;
        this.employeeRepo = employeeRepo;
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
            return equipRepo.findAllByStatusAndChildren(
                    EntityStatus.ACTIVE,
                    false,
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
                return equipRepo.findAllByStatusAndChildrenAndSerialNumberContainingOrSerialNumberIsNull(
                        EntityStatus.ACTIVE,
                        false,
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            } else {
                return equipRepo.findAllByStatusAndChildrenAndEquipTypeAndSerialNumberContainingOrSerialNumberIsNull(
                        EntityStatus.ACTIVE,
                        false,
                        equipTypeRepo.findByIdAndStatus(pageRequest.getEquipTypeId(), EntityStatus.ACTIVE)
                                .orElseThrow(() -> new NotFoundException("Equip Type not found", HttpStatus.NOT_FOUND)),
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            }
        } else {
            return equipRepo.findAllByStatusAndChildrenAndEquipModelAndSerialNumberContainingOrSerialNumberIsNull(
                    EntityStatus.ACTIVE,
                    false,
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
                return equipRepo.findAllByStatusAndChildrenAndInventoryNumberContainingOrInventoryNumberIsNull(
                        EntityStatus.ACTIVE,
                        false,
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            } else {
                return equipRepo.findAllByStatusAndChildrenAndEquipTypeAndInventoryNumberContainingOrInventoryNumberIsNull(
                        EntityStatus.ACTIVE,
                        false,
                        equipTypeRepo.findByIdAndStatus(pageRequest.getEquipTypeId(), EntityStatus.ACTIVE)
                                .orElseThrow(() -> new NotFoundException("Equip Type not found", HttpStatus.NOT_FOUND)),
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            }
        } else {
            return equipRepo.findAllByStatusAndChildrenAndEquipModelAndInventoryNumberContainingOrInventoryNumberIsNull(
                    EntityStatus.ACTIVE,
                    false,
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
                return equipRepo.findAllByStatusAndChildrenAndShortNameContainingOrShortNameIsNull(
                        EntityStatus.ACTIVE,
                        false,
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            } else {
                return equipRepo.findAllByStatusAndChildrenAndEquipTypeAndShortNameContainingOrShortNameIsNull(
                        EntityStatus.ACTIVE,
                        false,
                        equipTypeRepo.findByIdAndStatus(pageRequest.getEquipTypeId(), EntityStatus.ACTIVE)
                                .orElseThrow(() -> new NotFoundException("Equip Type not found", HttpStatus.NOT_FOUND)),
                        pageRequest.getSearch(),
                        request
                ).map(EquipDtoV1::createWithEquipType);
            }
        } else {
            return equipRepo.findAllByStatusAndChildrenAndEquipModelAndShortNameContainingOrShortNameIsNull(
                    EntityStatus.ACTIVE,
                    false,
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
        return equipRepo.findAllByStatusAndChildren(status, true);
    }

    @Override
    public List<EquipDtoV1> findAllChildren(Long parentId) {
        return equipRepo
                .findAllByChildrenAndParentIdAndStatus(
                        true,
                        parentId,
                        EntityStatus.ACTIVE
                ).stream()
                .map(EquipDtoV1::createWithEquipType)
                .collect(Collectors.toList());
    }

    @Override
    public EquipDtoV1 findChildById(Long id) throws MyException {
        return EquipDtoV1.createWithAll(
                equipRepo.findByIdAndChildrenAndStatus(
                        id,
                        true,
                        EntityStatus.ACTIVE
                ).orElseThrow(() -> new NotFoundException("Equip child not found", HttpStatus.NOT_FOUND))
        );
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "equipParents")
    public EquipParentsDtoV1 getEquipParents() {
        return new EquipParentsDtoV1(
                armRepo.findAllByStatus(EntityStatus.ACTIVE).stream()
                        .sorted(Comparator.comparing(Arm::getName))
                        .map(ArmDtoV1::create)
                        .collect(Collectors.toList()),
                equipTypeRepo.findAllByStatus(EntityStatus.ACTIVE).stream()
                        .sorted(Comparator.comparing(EquipType::getName))
                        .map(EquipTypeDtoV1::create)
                        .collect(Collectors.toList()),
                equipModelRepo.findAllByStatus(EntityStatus.ACTIVE).stream()
                        .sorted(Comparator.comparing(EquipModel::getName))
                        .map(EquipModelDtoV1::createWithEquipType)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public EquipDtoV1 findByIdWithMol(Long id) throws MyException {
        Equip equipFromDb = equipRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Equip not found", HttpStatus.NOT_FOUND));
        Optional<Employee> employee = equipFromDb.getEmployeeMol() != null && !equipFromDb.getEmployeeMol().equalsIgnoreCase("NONE")
                ? employeeRepo.findByAccountNameAndStatus(equipFromDb.getEmployeeMol(), EntityStatus.ACTIVE)
                : Optional.empty();
        if (employee.isEmpty()) {
            return EquipDtoV1.createWithAll(equipFromDb);
        } else {
            Employee empl = employee.get();
            return EquipDtoV1.createWithAll(
                    equipFromDb,
                    String.join(" ", empl.getLastName(), empl.getFirstName(), empl.getMiddleName())
            );
        }
    }

    @Override
    @Cacheable(cacheNames = {"choice-arm-list"}, key = "#name")
    public List<EquipDtoV1> findAllByNameForChoice(String name) {
        return equipRepo.findAllByStatusAndShortNameContaining(
                EntityStatus.ACTIVE,
                name,
                PageRequest.of(0, 50, Sort.by("shortName"))
        ).map(EquipDtoV1::createWithEquipType).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipDtoV1> findAllForChoice(String sortBy, String search) {
        PageRequest request = PageRequest.of(0, 50);
        if (sortBy != null && "inventoryNumber".equalsIgnoreCase(sortBy.trim())) {
            return equipRepo.findAllByStatusAndInventoryNumberContaining(
                    EntityStatus.ACTIVE,
                    search,
                    request.withSort(Sort.by("inventoryNumber"))
            ).map(EquipDtoV1::createWithEquipType).stream()
                    .collect(Collectors.toList());
        }
        return equipRepo.findAllByStatusAndShortNameContaining(
                EntityStatus.ACTIVE,
                search,
                request.withSort(Sort.by("shortName"))
        ).map(EquipDtoV1::createWithEquipType).stream()
                .collect(Collectors.toList());
    }

    private void fillEquip(EquipDtoV1 equipDtoV1, Equip equip) {
        equip.setShortName(equipDtoV1.getShortName());
        equip.setFullName(equipDtoV1.getFullName());
        equip.setSerialNumber(equipDtoV1.getSerialNumber());
        equip.setInventoryNumber(equipDtoV1.getInventoryNumber());
        equip.setManufacturer(equipDtoV1.getManufacturer());
        equip.setDateOfManufacture(equipDtoV1.getDateOfManufacture());
        equip.setGroupAccounting(equipDtoV1.isGroupAccounting());
        equip.setParentId(equipDtoV1.getParentId());
        equip.setChildren(equipDtoV1.isChildren());
        equip.setEmployeeMol(
                equipDtoV1.getEmployeeMol() != null && !"NONE".equalsIgnoreCase(equipDtoV1.getEmployeeMol().trim())
                        && employeeRepo.existsByAccountNameAndStatus(equipDtoV1.getEmployeeMol().trim(), EntityStatus.ACTIVE)
                        ? equipDtoV1.getEmployeeMol().trim()
                        : "NONE"
        );
        equip.setIpV4(equipDtoV1.getIpV4());
//        equip.setArm(
//                equipDtoV1.getArmId() != null && equipDtoV1.getArmId() > 0
//                        ? armRepo.findByIdAndStatus(equipDtoV1.getArmId(), EntityStatus.ACTIVE).orElse(null)
//                        : null
//        );
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
    @CacheEvict(cacheNames = {"choice-arm-list"}, allEntries = true)
    public Equip add(EquipDtoV1 equipDtoV1) throws MyException {
        Equip equip = new Equip();
        fillEquip(equipDtoV1, equip);
        return equipRepo.save(equip);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"choice-arm-list"}, allEntries = true)
    public Equip update(EquipDtoV1 equipDtoV1) throws MyException {
        Equip equip = findById(equipDtoV1.getId());
        fillEquip(equipDtoV1, equip);
        return equipRepo.save(equip);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"choice-arm-list"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        Equip equip = findById(id);
        equip.setStatus(EntityStatus.DELETED);
        equipRepo.save(equip);
        return true;
    }

    @Override
    public Equip findById(Long id) throws MyException {
        return equipRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Equip not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Equip> findAll(Pageable pageable) {
        return equipRepo.findAllByStatusAndChildren(EntityStatus.ACTIVE, true, pageable);
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
