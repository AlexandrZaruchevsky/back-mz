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
import ru.az.mz.dto.v1.ArmDtoV1;
import ru.az.mz.dto.v1.PageRequestDtoV1;
import ru.az.mz.model.Arm;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.ArmRepo;
import ru.az.mz.repositories.EmployeeRepo;
import ru.az.mz.repositories.PointOfPresenceRepo;
import ru.az.mz.services.ArmServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ArmServiceV1Impl implements ArmServiceV1 {

    private final ArmRepo armRepo;
    private final PointOfPresenceRepo pointOfPresenceRepo;
    private final EmployeeRepo employeeRepo;
    private final SecurityService securityService;
    private final SetupParameters setupParameters;

    public ArmServiceV1Impl(
            ArmRepo armRepo,
            PointOfPresenceRepo pointOfPresenceRepo,
            EmployeeRepo employeeRepo,
            SecurityService securityService,
            SetupParameters setupParameters
    ) {
        this.armRepo = armRepo;
        this.pointOfPresenceRepo = pointOfPresenceRepo;
        this.employeeRepo = employeeRepo;
        this.securityService = securityService;
        this.setupParameters = setupParameters;
    }

    @Override
    public Page<Arm> findByName(String name, Pageable pageable) {
        return armRepo.findAllByNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Cacheable(
            cacheNames = {"armPage"},
            key = "{#pageRequest.pageCurrent, #pageRequest.pageSize, #pageRequest.sortBy, #pageRequest.search}"
    )
    public Page<ArmDtoV1> findAll(PageRequestDtoV1 pageRequest) {
        PageRequest request;
        Sort sort = Sort.by("name");
        if (pageRequest == null) {
            request = setupParameters.getPageRequestDefault().withSort(sort);
            return armRepo.findAllByStatus(EntityStatus.ACTIVE, request)
                    .map(ArmDtoV1::createWithPop);
        }
        request = PageRequest.of(pageRequest.getPageCurrent(), pageRequest.getPageSize(), sort);
        return armRepo.findAllByNameContainingAndStatus(pageRequest.getSearch(), EntityStatus.ACTIVE, request)
                .map(ArmDtoV1::createWithPop);
    }

    @Override
    public List<ArmDtoV1> findAllByNameForChoice(String name) {
        return Collections.emptyList();
    }

    private void fillArm(ArmDtoV1 armDtoV1, Arm arm) {
        arm.setName(armDtoV1.getName());
        arm.setOffice(armDtoV1.getOffice());
        arm.setPointOfPresence(
                armDtoV1.getPopId() != null && armDtoV1.getPopId() > 0
                        ? pointOfPresenceRepo.findByIdAndStatus(armDtoV1.getPopId(), EntityStatus.ACTIVE).orElse(null)
                        : null
        );
        arm.setDescription(armDtoV1.getDescription());
        arm.setMol(armDtoV1.getMol());
        arm.setMolFio(armDtoV1.getMolFio());
        arm.setSaveByUser(securityService.getUsername());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents", "armPage"}, allEntries = true)
    public Arm add(ArmDtoV1 armDtoV1) throws MyException {
        Arm arm = new Arm();
        fillArm(armDtoV1, arm);
        return armRepo.save(arm);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents", "armPage"}, allEntries = true)
    public Arm update(ArmDtoV1 armDtoV1) throws MyException {
        Arm arm = armRepo.findByIdAndStatus(armDtoV1.getId(), EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Arm not found", HttpStatus.NOT_FOUND));
        fillArm(armDtoV1, arm);
        return armRepo.save(arm);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"equipParents", "armPage"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        Arm arm = findById(id);
        arm.setStatus(EntityStatus.DELETED);
        armRepo.save(arm);
        return true;
    }

    @Override
    public Arm findById(Long id) throws MyException {
        return armRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("Arm not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Arm> findAll(Pageable pageable) {
        return armRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Arm> findAll() {
        List<Arm> arms = new ArrayList<>();
        armRepo.findAll().forEach(arms::add);
        return arms;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return armRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return armRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return armRepo.existsById(id);
    }
}
