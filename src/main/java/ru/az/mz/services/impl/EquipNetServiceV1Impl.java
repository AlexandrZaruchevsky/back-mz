package ru.az.mz.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.EquipNetDtoV1;
import ru.az.mz.dto.v1.SubnetEquipDtoV1;
import ru.az.mz.dto.v1.projections.EquipNetDtoProjectionV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.EquipNet;
import ru.az.mz.model.SubnetMap;
import ru.az.mz.repositories.EquipNetRepo;
import ru.az.mz.services.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Primary
public class EquipNetServiceV1Impl implements EquipNetServiceV1 {

    private final EquipNetRepo equipNetRepo;
    private final SecurityService securityService;
    private final SubnetServiceV1 subnetServiceV1;

    private final AtomicBoolean isUpdateEquipNet = new AtomicBoolean(false);

    public EquipNetServiceV1Impl(
            EquipNetRepo equipNetRepo,
            SecurityService securityService,
            SubnetServiceV1 subnetServiceV1
    ) {
        this.equipNetRepo = equipNetRepo;
        this.securityService = securityService;
        this.subnetServiceV1 = subnetServiceV1;
    }

    @Override
    public List<EquipNetDtoProjectionV1> getEquipNetList(Long id) {
        return equipNetRepo.findAllBySubnetIdAsProjection(id);
    }

    @Override
    public EquipNetDtoProjectionV1 findByIdAsDto(Long id) throws MyException {
        return equipNetRepo.findByIdAsProjection(id)
                .orElseThrow(() -> new NotFoundException("EquipNet not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public EquipNet add(EquipNetDtoV1 equipNetDtoV1) throws MyException {
        return null;
    }

    @Override
    public EquipNet update(EquipNetDtoV1 equipNetDtoV1) throws MyException {
        return null;
    }

    @Override
    public boolean delete(long id) throws MyException {
        return false;
    }

    @Override
    public EquipNet findById(Long id) throws MyException {
        return null;
    }

    @Override
    public Page<EquipNet> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<EquipNet> findAll() {
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

    @Transactional
    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.MINUTES)
    public void addOrUpdateFromSubnetMap() {
        if (isUpdateEquipNet.get()) return;
        isUpdateEquipNet.set(true);
        subnetServiceV1.getListSubnetDtoV1().stream()
                .filter(subnetDtoV1 -> "active".equalsIgnoreCase(subnetDtoV1.getStatus()))
                .forEach(subnetDtoV1 -> {
                    try {
                        SubnetMap subnetMap = new SubnetMap();
                        subnetMap.setId(subnetDtoV1.getId());
                        List<EquipNet> equipNets = subnetServiceV1.getSubnetEquipsBySubnetId(subnetDtoV1.getId()).stream()
                                .filter(SubnetEquipDtoV1::isActive)
                                .map(SubnetEquipDtoV1::create)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .collect(Collectors.toList());
                        List<String> hostNames = equipNets.stream()
                                .map(EquipNet::getHostName)
                                .collect(Collectors.toList());
                        List<EquipNetDtoProjectionV1> allByHostNameAsProjection = equipNetRepo.findAllByHostNameAsProjection(hostNames);
                        equipNets.forEach(equipNet ->
                                allByHostNameAsProjection.stream()
                                        .filter(equipNetDtoProjectionV1 -> equipNetDtoProjectionV1.getHostName().equalsIgnoreCase(equipNet.getHostName()))
                                        .findFirst()
                                        .ifPresentOrElse(equipNetDtoProjectionV1 -> {
                                                    if (!equipNetDtoProjectionV1.getLastActive().equals(equipNet.getLastActive())) {
                                                        equipNetRepo.findByHostName(equipNet.getHostName()).ifPresent(equipNet1 -> {
                                                            equipNet1.setLastActive(equipNet.getLastActive());
                                                            equipNet1.setPingTime(equipNet.getPingTime());
                                                            equipNet1.setSubnetMap(subnetMap);
                                                        });
                                                    }
                                                },
                                                () -> {
                                                    try {
                                                        equipNet.setSubnetMap(subnetMap);
                                                        equipNetRepo.save(equipNet);
                                                    } catch (Exception e) {
                                                        System.out.println(equipNet);
                                                    }
                                                }
                                        )
                        );
                    } catch (MyException e) {
                        e.printStackTrace();
                    }
                });
        isUpdateEquipNet.set(false);
    }

}
