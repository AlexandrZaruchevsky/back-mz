package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class OrganizationDtoV1 {

    Long id;
    String shortName;
    String fullName;
    String inn;
    String kpp;
    String ogrn;
    Long bossId;
    List<PointOfPresenceDtoV1> pofs;
    List<DepartmentDtoV2> departments;
    List<PositionDtoV1> positions;
    List<EmployeeDtoV1> employees;
    EmployeeDtoV1 boss;

    public static OrganizationDtoV1 create(Organization organization) {
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );
    }

    public static OrganizationDtoV1 createWithPointOfPresences(Organization organization) {
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                getPointOfPresenceDtoV1s(organization),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );
    }

    public static OrganizationDtoV1 createWithDepartments(Organization organization) {
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                Collections.emptyList(),
                getDepartmentDtoV2s(organization),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );
    }

    public static OrganizationDtoV1 createWithPositions(Organization organization) {
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                Collections.emptyList(),
                Collections.emptyList(),
                getPositionDtoV1s(organization),
                Collections.emptyList(),
                null
        );
    }

    public static OrganizationDtoV1 createWithAll(Organization organization, Employee employee) {
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                getPointOfPresenceDtoV1s(organization),
                getDepartmentDtoV2s(organization),
                getPositionDtoV1s(organization),
                Collections.emptyList(),
                employee != null ? EmployeeDtoV1.create(employee) : null
        );
    }

    public static OrganizationDtoV1 createWithAll(
            Organization organization,
            List<PointOfPresence> pointOfPresences,
            List<Department> departments,
            List<Position> positions,
            Employee employee
    ) {
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                getPointOfPresenceDtoV1s(pointOfPresences),
                getDepartmentDtoV2s(departments),
                getPositionDtoV1s(positions),
                Collections.emptyList(),
                employee != null ? EmployeeDtoV1.create(employee) : null
        );
    }

    public static OrganizationDtoV1 createWithEmployees(
            Organization organization,
            List<Employee> employees
    ){
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                getEmployeeDtoV1s(employees),
                null
        );
    }

    private static List<PointOfPresenceDtoV1> getPointOfPresenceDtoV1s(Organization organization) {
        return organization != null && organization.getPointOfPresences() != null && organization.getPointOfPresences().size() > 0
                ? organization.getPointOfPresences().stream().map(PointOfPresenceDtoV1::create).collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<PointOfPresenceDtoV1> getPointOfPresenceDtoV1s(List<PointOfPresence> pointOfPresences) {
        return pointOfPresences != null && pointOfPresences.size() > 0
                ? pointOfPresences.stream().map(PointOfPresenceDtoV1::create).collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<DepartmentDtoV2> getDepartmentDtoV2s(List<Department> departments) {
        return departments != null && departments.size() > 0
                ? departments.stream().map(DepartmentDtoV2::create).collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<DepartmentDtoV2> getDepartmentDtoV2s(Organization organization) {
        return organization != null && organization.getDepartments() != null && organization.getDepartments().size() > 0
                ? organization.getDepartments().stream().map(DepartmentDtoV2::create).collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<PositionDtoV1> getPositionDtoV1s(Organization organization) {
        return organization != null && organization.getPositions() != null && organization.getPositions().size() > 0
                ? organization.getPositions().stream().map(PositionDtoV1::create).collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<PositionDtoV1> getPositionDtoV1s(List<Position> positions) {
        return positions != null && positions.size() > 0
                ? positions.stream().map(PositionDtoV1::create).collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<EmployeeDtoV1> getEmployeeDtoV1s(List<Employee> employees) {
        return employees != null && employees.size() > 0
                ? employees.stream().map(EmployeeDtoV1::create).collect(Collectors.toList())
                : Collections.emptyList();
    }
}
