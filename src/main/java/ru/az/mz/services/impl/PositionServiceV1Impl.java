package ru.az.mz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.az.mz.dto.v1.PositionDtoV1;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Position;
import ru.az.mz.repositories.OrganizationRepo;
import ru.az.mz.repositories.PositionRepo;
import ru.az.mz.services.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceV1Impl implements PositionServiceV1 {

    private final PositionRepo positionRepo;
    private final SecurityService securityService;
    private final OrganizationRepo organizationRepo;
    private final OrganizationServiceV1 organizationServiceV1;

    public PositionServiceV1Impl(
            PositionRepo positionRepo,
            SecurityService securityService,
            OrganizationRepo organizationRepo, OrganizationServiceV1 organizationServiceV1
    ) {
        this.positionRepo = positionRepo;
        this.securityService = securityService;
        this.organizationRepo = organizationRepo;
        this.organizationServiceV1 = organizationServiceV1;
    }

    @Override
    public Page<Position> findByName(String name, Pageable pageable) {
        return positionRepo.findAllByNameStartingWithAndStatus(name, EntityStatus.ACTIVE, pageable);
    }

    @Override
    @Transactional
    public List<Position> findAllByOrgId(Long id) {
        List<Position> positions = new ArrayList<>();
        organizationRepo.findById(id).ifPresent(organization -> {
            positions.addAll(positionRepo.findAllByOrganizationAndStatus(organization, EntityStatus.ACTIVE));
        });
        return positions;
    }

    @Override
    @Transactional
    public Position add(PositionDtoV1 positionDtoV1) throws MyException {
        Position position = new Position();
        fillPosition(positionDtoV1, position);
        return positionRepo.save(position);
    }

    private void fillPosition(PositionDtoV1 positionDtoV1, Position position) throws MyException {
        position.setName(positionDtoV1.getName());
        position.setSaveByUser(securityService.getUsername());
        position.setOrganization(
                positionDtoV1.getId() == null
                        ? null
                        : organizationServiceV1.findById(positionDtoV1.getOrgId())
        );
    }

    @Override
    @Transactional
    public Position update(PositionDtoV1 positionDtoV1) throws MyException {
        Position position = findById(positionDtoV1.getId());
        fillPosition(positionDtoV1, position);
        return positionRepo.save(position);
    }

    @Override
    public boolean delete(long id) throws MyException {
        Position position = findById(id);
        position.setStatus(EntityStatus.DELETED);
        position.setSaveByUser(securityService.getUsername());
        positionRepo.save(position);
        return true;
    }

    @Override
    public Position findById(Long id) throws MyException {
        return positionRepo.findByIdAndStatus(id, EntityStatus.ACTIVE).orElseThrow(
                () -> new NotFoundException("Position not found", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Page<Position> findAll(Pageable pageable) {
        return positionRepo.findAllByStatus(EntityStatus.ACTIVE, pageable);
    }

    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        positionRepo.findAll().forEach(positions::add);
        return positions;
    }

    @Override
    public long countByStatus(EntityStatus status) {
        return positionRepo.countByStatus(status);
    }

    @Override
    public long countAll() {
        return positionRepo.count();
    }

    @Override
    public boolean existsById(Long id) {
        return positionRepo.existsById(id);
    }
}
