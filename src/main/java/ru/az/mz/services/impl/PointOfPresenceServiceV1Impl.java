package ru.az.mz.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.PointOfPresenceDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.PointOfPresence;
import ru.az.mz.repositories.OrganizationRepo;
import ru.az.mz.repositories.PointOfPresenceRepo;
import ru.az.mz.services.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointOfPresenceServiceV1Impl implements PointOfPresenceServiceV1 {

    private final PointOfPresenceRepo pointOfPresenceRepo;
    private final SecurityService securityService;
    private final OrganizationServiceV1 organizationServiceV1;
    private final OrganizationRepo organizationRepo;

    public PointOfPresenceServiceV1Impl(
            PointOfPresenceRepo pointOfPresenceRepo,
            SecurityService securityService,
            OrganizationServiceV1 organizationServiceV1,
            OrganizationRepo organizationRepo) {
        this.pointOfPresenceRepo = pointOfPresenceRepo;
        this.securityService = securityService;
        this.organizationServiceV1 = organizationServiceV1;
        this.organizationRepo = organizationRepo;
    }

    @Override
    public Page<PointOfPresence> findByName(String name, Pageable pageable) {
        return pointOfPresenceRepo.findAllByShortNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
    public List<PointOfPresence> findAllByOrgId(Long id) {
        List<PointOfPresence> list = new ArrayList<>();
        organizationRepo.findById(id).ifPresent(organization -> {
            list.addAll(
                    pointOfPresenceRepo.findAllByOrganizationAndStatus(organization, EntityStatus.ACTIVE).stream()
                            .sorted(Comparator.comparing(PointOfPresence::getShortName))
                            .collect(Collectors.toList())
            );
        });
        return list;
    }

    private void fillPointOfPresence(PointOfPresenceDtoV1 pointOfPresenceDtoV1, PointOfPresence pointOfPresence) throws MyException {
        pointOfPresence.setShortName(pointOfPresenceDtoV1.getShortName());
        pointOfPresence.setFullName(pointOfPresenceDtoV1.getFullName());
        pointOfPresence.setPostcode(pointOfPresenceDtoV1.getPostcode());
        pointOfPresence.setRegion(pointOfPresenceDtoV1.getRegion());
        pointOfPresence.setDistrict(pointOfPresenceDtoV1.getDistrict());
        pointOfPresence.setCity(pointOfPresenceDtoV1.getCity());
        pointOfPresence.setVillage(pointOfPresenceDtoV1.getVillage());
        pointOfPresence.setStreet(pointOfPresenceDtoV1.getStreet());
        pointOfPresence.setHouse(pointOfPresenceDtoV1.getHouse());
        pointOfPresence.setCorpus(pointOfPresenceDtoV1.getCorpus());
        pointOfPresence.setApartment(pointOfPresenceDtoV1.getApartment());
        pointOfPresence.setBossId(pointOfPresenceDtoV1.getBossId());
        pointOfPresence.setOrganization(
                pointOfPresenceDtoV1.getOrgId() == null
                        ? null
                        : organizationServiceV1.findById(pointOfPresenceDtoV1.getOrgId())
        );
        pointOfPresence.setSaveByUser(securityService.getUsername());
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies"}, allEntries = true)
    public PointOfPresence add(PointOfPresenceDtoV1 pointOfPresenceDtoV1) throws MyException {
        PointOfPresence pointOfPresence = new PointOfPresence();
        fillPointOfPresence(pointOfPresenceDtoV1, pointOfPresence);
        return pointOfPresenceRepo.save(pointOfPresence);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"organizationWithDependencies"}, allEntries = true)
    public PointOfPresence update(PointOfPresenceDtoV1 pointOfPresenceDtoV1) throws MyException {
        PointOfPresence pointOfPresence = findById(pointOfPresenceDtoV1.getId());
        fillPointOfPresence(pointOfPresenceDtoV1, pointOfPresence);
        return pointOfPresenceRepo.save(pointOfPresence);
    }

    @Override
    @CacheEvict(cacheNames = {"organizationWithDependencies"}, allEntries = true)
    public boolean delete(long id) throws MyException {
        PointOfPresence pointOfPresence = findById(id);
        pointOfPresence.setStatus(EntityStatus.DELETED);
        pointOfPresence.setSaveByUser(securityService.getUsername());
        pointOfPresenceRepo.save(pointOfPresence);
        return true;
    }

    @Override
    public PointOfPresence findById(Long id) throws MyException {
        return pointOfPresenceRepo.findByIdAndStatus(id, EntityStatus.ACTIVE).orElseThrow(
                () -> new NotFoundException("PointOfPresence not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Page<PointOfPresence> findAll(Pageable pageable) {
        return pointOfPresenceRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<PointOfPresence> findAll() {
        List<PointOfPresence> pointOfPresences = new ArrayList<>();
        pointOfPresenceRepo.findAll().forEach(pointOfPresences::add);
        return pointOfPresences;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return pointOfPresenceRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return pointOfPresenceRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return pointOfPresenceRepo.existsById(id);
    }

}
