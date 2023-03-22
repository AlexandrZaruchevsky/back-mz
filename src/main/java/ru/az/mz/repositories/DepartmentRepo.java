package ru.az.mz.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.az.mz.model.Department;
import ru.az.mz.model.EntityStatus;
import ru.az.mz.model.Organization;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends PagingAndSortingRepository<Department, Long> {

    @EntityGraph("Department.withOrganization")
    Page<Department> findAllByStatus(EntityStatus status, Pageable pageable);

    @EntityGraph("Department.withOrganization")
    Page<Department> findAllByNameStartingWithAndStatus(String name, EntityStatus status, Pageable pageable);

    @EntityGraph("Department.withOrganization")
    Page<Department> findAllByOrganizationAndStatus(Organization organization, EntityStatus status, Pageable pageable);

//    @Query(value = "select d.*, o.id, o.short_name from department d left outer join organization o on o.id=d.organization_id where d.organization_id=?1 and d.status='ACTIVE'", nativeQuery = true)
    @Query(value = "select d.* from department d where d.organization_id=?1 and d.status='ACTIVE'", nativeQuery = true)
//    @Query("select d from Department d left outer join d.organization o where o.id=?1")
    List<Department> findAllByOrganization(Long id);

//    @Query(value = "select d from Department d where d.organization =?1 and d.status=?2")
    @EntityGraph("Department.withOrganization")
    List<Department> findAllByOrganizationAndStatus(Organization organization, EntityStatus status);

    @EntityGraph("Department.withOrganization")
    Page<Department> findAllByNameStartingWithAndOrganizationAndStatus(
            String name,
            Organization org,
            EntityStatus status,
            Pageable pageable
    );

    @EntityGraph("Department.withOrganizationAndEmployees")
    Optional<Department> findByIdAndStatus(Long id, EntityStatus status);

    long countAllByStatus(EntityStatus status);

}
