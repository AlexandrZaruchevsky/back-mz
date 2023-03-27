package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Organization;

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
                Collections.emptyList()
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
                Collections.emptyList()
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
                Collections.emptyList()
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
                getPositionDtoV1s(organization)
        );
    }

    public static OrganizationDtoV1 createWithAll(Organization organization) {
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
                getPositionDtoV1s(organization)
        );
    }

    private static List<PointOfPresenceDtoV1> getPointOfPresenceDtoV1s(Organization organization) {
        return organization != null && organization.getPointOfPresences() != null && organization.getPointOfPresences().size() > 0
                ? organization.getPointOfPresences().stream().map(PointOfPresenceDtoV1::create).collect(Collectors.toList())
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
}
