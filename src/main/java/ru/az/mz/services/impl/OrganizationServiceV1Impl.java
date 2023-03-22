package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.OrganizationDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;
import ru.az.mz.repositories.OrganizationRepo;
import ru.az.mz.services.MyException;
import ru.az.mz.services.NotFoundException;
import ru.az.mz.services.OrganizationServiceV1;
import ru.az.mz.services.SecurityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceV1Impl implements OrganizationServiceV1 {

    private final OrganizationRepo organizationRepo;
    private final SecurityService securityService;

    public OrganizationServiceV1Impl(OrganizationRepo organizationRepo, SecurityService securityService) {
        this.organizationRepo = organizationRepo;
        this.securityService = securityService;
    }

    @Override
    public Page<Organization> findAllByName(String name, Pageable pageable) {
        return organizationRepo.findAllByShortNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public Page<Organization> findAllByInn(String inn, Pageable pageable) {
        return organizationRepo.findAllByInnStartingWithAndStatus(inn, EntityStatus.ACTIVE, pageable);
    }

    @Override
    public Page<Organization> findAllByOgrn(String ogrn, Pageable pageable) {
        return organizationRepo.findAllByOgrnStartingWithAndStatus(ogrn, EntityStatus.ACTIVE, pageable);
    }

    private void fillOrganization(OrganizationDtoV1 organizationDtoV1, Organization organization) {
        organization.setShortName(organizationDtoV1.getShortName());
        organization.setFullName(organizationDtoV1.getFullName());
        organization.setInn(organizationDtoV1.getInn());
        organization.setKpp(organizationDtoV1.getKpp());
        organization.setOgrn(organizationDtoV1.getOgrn());
        organization.setBossId(organizationDtoV1.getBossId());
        organization.setSaveByUser(securityService.getUsername());
    }

    @Override
    public Organization add(OrganizationDtoV1 organizationDtoV1) {
        Organization organization = new Organization();
        fillOrganization(organizationDtoV1, organization);
        return organizationRepo.save(organization);
    }

    @Override
    public Organization update(OrganizationDtoV1 organizationDtoV1) throws MyException {
        Organization organization = findById(organizationDtoV1.getId());
        fillOrganization(organizationDtoV1, organization);
        return organizationRepo.save(organization);
    }

    @Override
    public boolean delete(long id) throws MyException {
        Organization organization = findById(id);
        organization.setStatus(EntityStatus.DELETED);
        organization.setSaveByUser(securityService.getUsername());
        organizationRepo.save(organization);
        return true;
    }

    @Override
    public Organization findById(Long id) throws MyException {
        return organizationRepo.findByIdAndStatus(id, EntityStatus.ACTIVE)
                .orElseThrow(()->new NotFoundException("Organization not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Page<Organization> findAll(Pageable pageable) {
        return organizationRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepo.findAllByStatus(EntityStatus.ACTIVE);
    }

//    @Override
//    public List<Organization> findAll() {
//        List<Organization> organizations = new ArrayList<>();
//        organizationRepo.findAll().forEach(organizations::add);
//        return organizations;
//    }
//
    @Override
    public long countByStatus(EntityStatus status) {
        return organizationRepo.countAllByStatus(status);
    }

    @Override
    public long countAll() {
        return organizationRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return organizationRepo.existsById(id);
    }

}
