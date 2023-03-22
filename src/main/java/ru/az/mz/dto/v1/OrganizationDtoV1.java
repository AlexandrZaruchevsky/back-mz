package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.Organization;

@Value
public class OrganizationDtoV1 {

    Long id;
    String shortName;
    String fullName;
    String inn;
    String kpp;
    String ogrn;
    Long bossId;

    public static OrganizationDtoV1 create(Organization organization){
        return new OrganizationDtoV1(
                organization.getId(),
                organization.getShortName(),
                organization.getFullName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getBossId()
        );
    }

}
