package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.ArmDetailDtoV1;
import ru.az.mz.model.ArmDetail;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.repositories.ArmDetailRepo;
import ru.az.mz.repositories.ArmRepo;
import ru.az.mz.repositories.EquipRepo;
import ru.az.mz.services.ArmDetailServiceV1;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.SecurityService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArmDetailServiceV1Impl implements ArmDetailServiceV1 {

    private final ArmDetailRepo armDetailRepo;
    private final ArmRepo armRepo;
    private final EquipRepo equipRepo;
    private final SecurityService securityService;

    public ArmDetailServiceV1Impl(
            ArmDetailRepo armDetailRepo,
            ArmRepo armRepo,
            EquipRepo equipRepo,
            SecurityService securityService
    ) {
        this.armDetailRepo = armDetailRepo;
        this.armRepo = armRepo;
        this.equipRepo = equipRepo;
        this.securityService = securityService;
    }

    @Override
    @Transactional
    public List<ArmDetailDtoV1> findAllByArm(Long id) {
        List<ArmDetailDtoV1> list = new ArrayList<>();
        armRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .ifPresent(arm ->
                        list.addAll(
                                armDetailRepo.findAllByArmAndStatusOrderByName(arm, EntityStatus.ACTIVE).stream()
                                        .map(ArmDetailDtoV1::create)
                                        .sorted(Comparator.comparing(ArmDetailDtoV1::getName))
                                        .collect(Collectors.toList())
                        ));
        return list;
    }

    private void fillArmDetail(ArmDetailDtoV1 armDetailDtoV1, ArmDetail armDetail) {
        armDetail.setSaveByUser(securityService.getUsername());
        armDetail.setName(armDetailDtoV1.getName());
        armDetail.setDomainName(armDetailDtoV1.getDomainName());
        armDetail.setIpV4(armDetailDtoV1.getIpV4());
        armDetail.setDescription(armDetailDtoV1.getDescription());
        if (armDetailDtoV1.getArmId() != null) {
            armRepo.findByIdAndStatus(armDetailDtoV1.getArmId(), EntityStatus.ACTIVE)
                    .ifPresent(armDetail::setArm);
        }
        if (armDetailDtoV1.getEquipId() != null) {
            equipRepo.findByIdAndStatus(armDetailDtoV1.getEquipId(), EntityStatus.ACTIVE)
                    .ifPresent(armDetail::setEquip);
        }
    }

    @Override
    public ArmDetail add(ArmDetailDtoV1 armDetailDtoV1) throws MyException {
        ArmDetail armDetail = new ArmDetail();
        fillArmDetail(armDetailDtoV1, armDetail);
        return armDetailRepo.save(armDetail);
    }

    @Override
    public ArmDetail update(ArmDetailDtoV1 armDetailDtoV1) throws MyException {
        ArmDetail armDetail = findById(armDetailDtoV1.getId());
        fillArmDetail(armDetailDtoV1, armDetail);
        return armDetailRepo.save(armDetail);
    }

    @Override
    public boolean delete(long id) throws MyException {
        ArmDetail armDetail = findById(id);
        armDetail.setStatus(EntityStatus.DELETED);
        armDetail.setEquip(null);
        armDetail.setSaveByUser(securityService.getUsername());
        armDetailRepo.save(armDetail);
        return true;
    }

    @Override
    public ArmDetail findById(Long id) throws MyException {
        return armDetailRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("ArmDetail not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<ArmDetail> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<ArmDetail> findAll() {
        return null;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return 0;
    }

    @Override
    public long countAll() {
        return 0;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
