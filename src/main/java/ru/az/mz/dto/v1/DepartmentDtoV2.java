package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Department;

@Value
public class DepartmentDtoV2 {

    Long id;
    String name;
    boolean topLevel;
    Long parentId;
    Long bossId;
    Long orgId;
    String orgName;

    private static DepartmentDtoV2 getDepartmentDtoV1Default(
            Department department, boolean withOrg
    ) {
        return new DepartmentDtoV2(
                department.getId(),
                department.getName(),
                department.isTopLevel(),
                department.getParentId(),
                department.getBossId(),
                withOrg && department.getOrganization() != null ? department.getOrganization().getId() : -1,
                withOrg && department.getOrganization() != null ? department.getOrganization().getShortName() : ""
        );
    }

    public static DepartmentDtoV2 create(Department department) {
        return getDepartmentDtoV1Default(department, false);
    }

    public static DepartmentDtoV2 createWithOrganization(Department department) {
        return getDepartmentDtoV1Default(
                department,
                true
        );
    }

}
