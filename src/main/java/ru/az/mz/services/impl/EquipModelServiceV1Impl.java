package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EquipModelDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipModel;
import ru.az.mz.repositories.EquipModelRepo;
import ru.az.mz.repositories.EquipTypeRepo;
import ru.az.mz.services.EquipModelServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EquipModelServiceV1Impl implements EquipModelServiceV1 {

    private final EquipModelRepo equipModelRepo;
    private final EquipTypeRepo equipTypeRepo;
    private final SecurityService securityService;

    public EquipModelServiceV1Impl(
            EquipModelRepo equipModelRepo,
            EquipTypeRepo equipTypeRepo, SecurityService securityService
    ) {
        this.equipModelRepo = equipModelRepo;
        this.equipTypeRepo = equipTypeRepo;
        this.securityService = securityService;
    }

    @Override
    public Page<EquipModel> findAllByName(String name, Pageable pageable) {
        return equipModelRepo.findAllByNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<EquipModel> findAll(EntityStatus status) {
        return equipModelRepo.findAllByStatus(status);
    }

    private void fillEquipModel(EquipModelDtoV1 equipModelDtoV1, EquipModel equipModel) {
        equipModel.setName(equipModelDtoV1.getName());
        equipModel.setEquipType(
                equipModelDtoV1.getEquipType() != null
                        ? equipTypeRepo.findByIdAndStatus(equipModelDtoV1.getEquipType().getId(), EntityStatus.ACTIVE)
                                .orElse(null)
                        : null
        );
        equipModel.setSaveByUser(securityService.getUsername());
    }

    @Override
    @Transactional
    public EquipModel add(EquipModelDtoV1 equipModelDtoV1) throws MyException {
        EquipModel equipModel = new EquipModel();
        fillEquipModel(equipModelDtoV1, equipModel);
        return equipModelRepo.save(equipModel);
    }

    @Override
    @Transactional
    public EquipModel update(EquipModelDtoV1 equipModelDtoV1) throws MyException {
        EquipModel equipModel = findById(equipModelDtoV1.getId());
        fillEquipModel(equipModelDtoV1, equipModel);
        return equipModelRepo.save(equipModel);
    }

    @Override
    @Transactional
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
