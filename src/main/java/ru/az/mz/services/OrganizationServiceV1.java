package ru.az.mz.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.az.mz.dto.v1.OrganizationDtoV1;
import ru.az.mz.model.Organization;

public interface OrganizationServiceV1 extends CrudServiceV1<Organization, OrganizationDtoV1> {

    Page<Organization> findAllByName(String name, Pageable pageable);
    Page<Organization> findAllByInn(String inn, Pageable pageable);
    Page<Organization> findAllByOgrn(String ogrn, Pageable pageable);

    Organization findByIdWithAllDependencies(Long orgId) throws MyException;

}
