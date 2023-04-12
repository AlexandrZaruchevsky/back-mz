package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.config.SetupParameters;
import ru.az.mz.dto.v1.EquipModelDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipModel;
import ru.az.mz.model.EquipType;
import ru.az.mz.repositories.EquipModelRepo;
import ru.az.mz.repositories.EquipTypeRepo;
import ru.az.mz.services.EquipModelServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipModelServiceV1Impl implements EquipModelServiceV1 {

    private final EquipModelRepo equipModelRepo;
    private final EquipTypeRepo equipTypeRepo;
    private final SecurityService securityService;
    private final SetupParameters setupParameters;

    public EquipModelServiceV1Impl(
            EquipModelRepo equipModelRepo,
            EquipTypeRepo equipTypeRepo, SecurityService securityService,
            SetupParameters setupParameters) {
        this.equipModelRepo = equipModelRepo;
        this.equipTypeRepo = equipTypeRepo;
        this.securityService = securityService;
        this.setupParameters = setupParameters;
    }

    @Override
    public Page<EquipModel> findAllByName(String name, Pageable pageable) {
        return equipModelRepo.findAllByNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
    public Page<EquipModelDtoV1> findAllByName(PageRequestDtoV1 pageRequest) throws MyException {
        if (pageRequest == null) {
            return equipModelRepo.findAllByStatus(
                    EntityStatus.ACTIVE,
                    setupParameters.getPageRequestDefault()
                            .withSort(Sort.by("name"))
            ).map(EquipModelDtoV1::createWithEquipType);
        }
        PageRequest request = PageRequest.of(pageRequest.getPageCurrent(), pageRequest.getPageSize());
        if ("".equalsIgnoreCase(pageRequest.getSortBy().trim())) {
            request = request.withSort(Sort.by("name"));
        } else {
            request = request.withSort(Sort.by(pageRequest.getSortBy().trim()));
        }
        if (pageRequest.getParentId() >= 0) {
            EquipType orgFromDb = equipTypeRepo.findById(pageRequest.getParentId())
                    .orElseThrow(() -> new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
            return equipModelRepo.findAllByEquipTypeAndNameContainingAndStatus(orgFromDb, pageRequest.getSearch().trim(), EntityStatus.ACTIVE, request)
                    .map(EquipModelDtoV1::createWithEquipType);
        }

        return equipModelRepo.findAllByNameStartingWithAndStatus(pageRequest.getSearch().trim(), EntityStatus.ACTIVE, request)
                .map(EquipModelDtoV1::createWithEquipType);
    }

    @Override
    public List<EquipModel> findAll(EntityStatus status) {
        return equipModelRepo.findAllByStatus(status).stream()
                .sorted(Comparator.comparing(EquipModel::getName))
                .collect(Collectors.toList());
    }

    private void fillEquipModel(EquipModelDtoV1 equipModelDtoV1, EquipModel equipModel) {
        equipModel.setName(equipModelDtoV1.getName());
        if (equipModelDtoV1.getEquipTypeId() >= 0) {
            equipTypeRepo.findById(equipModelDtoV1.getEquipTypeId())
                    .ifPresent(equipModel::setEquipType);
        }
        equipModel.setSaveByUser(securityService.getUsername());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents"}, allEntries = true)
    public EquipModel add(EquipModelDtoV1 equipModelDtoV1) throws MyException {
        EquipModel equipModel = new EquipModel();
        fillEquipModel(equipModelDtoV1, equipModel);
        return equipModelRepo.save(equipModel);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents"}, allEntries = true)
    public EquipModel update(EquipModelDtoV1 equipModelDtoV1) throws MyException {
        EquipModel equipModel = findById(equipModelDtoV1.getId());
        fillEquipModel(equipModelDtoV1, equipModel);
        return equipModelRepo.save(equipModel);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        EquipModel equipModel = findById(id);
        equipModel.setStatus(EntityStatus.DELETED);
        equipModelRepo.save(equipModel);
        return true;
    }

    @Override
    public EquipModel findById(Long id) throws MyException {
        return equipModelRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("EquipModel not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<EquipModel> findAll(Pageable pageable) {
        return equipModelRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<EquipModel> findAll() {
        return equipModelRepo.findAll();
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return equipModelRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return equipModelRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return equipModelRepo.existsById(id);
    }
}
