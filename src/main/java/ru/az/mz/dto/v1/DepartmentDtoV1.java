package ru.az.mz.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import ru.az.mz.model.Department;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class DepartmentDtoV1 {

    Long id;
    String name;
    boolean topLevel;
    Long parentId;
    Long bossId;
    Long orgId;
    String orgName;
    @JsonProperty("organization")
    OrganizationDtoV1 organization;
    List<EmployeeDtoV1> employees;

    private static DepartmentDtoV1 getDepartmentDtoV1Default(Department department, OrganizationDtoV1 organization, List<EmployeeDtoV1> employees) {
        return new DepartmentDtoV1(
                department.getId(),
                department.getName(),
                department.isTopLevel(),
                department.getParentId(),
                department.getBossId(),
                organization != null ? organization.getId() : -1,
                organization != null ? organization.getShortName() : "",
                organization,
                employees
        );
    }

    public static DepartmentDtoV1 create(Department department) {
        return getDepartmentDtoV1Default(department, null, Collections.emptyList());
    }

    public static DepartmentDtoV1 createWithOrganization(Department department) {
        return getDepartmentDtoV1Default(
                department,
                department.getOrganization() == null
                        ? null
                        : OrganizationDtoV1.create(department.getOrganization()),
                Collections.emptyList()
        );
    }

    public static DepartmentDtoV1 createWithEmployees(Department department) {
        return getDepartmentDtoV1Default(
                department,
                null,
                department.getEmployees() == null
                        ? Collections.emptyList()
                        : department.getEmployees().stream()
                        .map(EmployeeDtoV1::create)
                        .collect(Collectors.toList())
        );
    }

    private static DepartmentDtoV1 createWithAll(Department department) {
        return getDepartmentDtoV1Default(
                department,
                department.getOrganization() == null
                        ? null
                        : OrganizationDtoV1.create(department.getOrganization()),
                department.getEmployees() == null
                        ? Collections.emptyList()
                        : department.getEmployees().stream()
                        .map(EmployeeDtoV1::create)
                        .collect(Collectors.toList())
        );
    }

}
