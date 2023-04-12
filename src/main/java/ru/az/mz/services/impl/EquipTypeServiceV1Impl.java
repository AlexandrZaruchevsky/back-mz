package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EquipTypeDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipType;
import ru.az.mz.repositories.EquipTypeRepo;
import ru.az.mz.services.EquipTypeServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipTypeServiceV1Impl implements EquipTypeServiceV1 {

    private final EquipTypeRepo equipTypeRepo;
    private final SecurityService securityService;

    public EquipTypeServiceV1Impl(
            EquipTypeRepo equipTypeRepo,
            SecurityService securityService
    ) {
        this.equipTypeRepo = equipTypeRepo;
        this.securityService = securityService;
    }

    @Override
    public Page<EquipType> findAllByName(String name, Pageable pageable) {
        return equipTypeRepo.findAllByNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<EquipType> findAll(EntityStatus status) {
        return equipTypeRepo.findAllByStatus(EntityStatus.ACTIVE).stream()
                .sorted(Comparator.comparing(EquipType::getName))
                .collect(Collectors.toList());
    }

    private void fillEquipType(EquipTypeDtoV1 equipTypeDtoV1, EquipType equipType) {
        equipType.setName(equipTypeDtoV1.getName());
        equipType.setSaveByUser(securityService.getUsername());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents"}, allEntries = true)
    public EquipType add(EquipTypeDtoV1 equipTypeDtoV1) throws MyException {
        EquipType equipType = new EquipType();
        fillEquipType(equipTypeDtoV1, equipType);
        return equipTypeRepo.save(equipType);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents"}, allEntries = true)
    public EquipType update(EquipTypeDtoV1 equipTypeDtoV1) throws MyException {
        EquipType equipType = findById(equipTypeDtoV1.getId());
        fillEquipType(equipTypeDtoV1, equipType);
        return equipTypeRepo.save(equipType);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        EquipType equipType = findById(id);
        equipType.setStatus(EntityStatus.DELETED);
        equipTypeRepo.save(equipType);
        return true;
    }

    @Override
    public EquipType findById(Long id) throws MyException {
        return equipTypeRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(()->new NotFoundException("EquipType not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<EquipType> findAll(Pageable pageable) {
        return equipTypeRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<EquipType> findAll() {
        return equipTypeRepo.findAll().stream()
                .filter(equipType -> EntityStatus.ACTIVE.equals(equipType.getStatus()))
                .sorted(Comparator.comparing(EquipType::getName))
                .collect(Collectors.toList());
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return equipTypeRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return equipTypeRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return equipTypeRepo.existsById(id);
    }
}
