package ru.az.mz.dto.v1;

import lombok.Value;
import ru.az.mz.model.PointOfPresence;
import ru.az.mz.model.Position;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PointOfPresenceDtoV1 {

    Long id;
    String shortName;
    String fullName;
    String postcode;
    String region;
    String district;
    String city;
    String village;
    String street;
    String house;
    String corpus;
    String apartment;
    Long bossId;
    Long orgId;
    String orgName;


    private static PointOfPresenceDtoV1 createDefault(
            PointOfPresence pof,
            Boolean withOrg
    ) {
        return new PointOfPresenceDtoV1(
                pof.getId(),
                pof.getShortName(),
                pof.getFullName(),
                pof.getPostcode(),
                pof.getRegion(),
                pof.getDistrict(),
                pof.getCity(),
                pof.getVillage(),
                pof.getStreet(),
                pof.getHouse(),
                pof.getCorpus(),
                pof.getApartment(),
                pof.getBossId(),
                withOrg && pof.getOrganization() != null
                        ? pof.getOrganization().getId()
                        : null,
                withOrg && pof.getOrganization() != null
                        ? pof.getOrganization().getShortName()
                        : null
        );
    }

    public static PointOfPresenceDtoV1 create(PointOfPresence pointOfPresence) {
        return createDefault(pointOfPresence, false);
    }

    public static PointOfPresenceDtoV1 createWithOrganization(PointOfPresence pointOfPresence) {
        return createDefault(pointOfPresence, true);
    }

//    public static PointOfPresenceDtoV1 createWithEmployees(PointOfPresence pointOfPresence) {
//        return createDefault(
//                pointOfPresence,
//                null,
//                pointOfPresence.getEmployees() == null
//                        ? Collections.emptyList()
//                        : pointOfPresence.getEmployees().stream().map(EmployeeDtoV1::create).collect(Collectors.toList())
//        );
//    }
//
//    public static PointOfPresenceDtoV1 createWithAll(PointOfPresence pointOfPresence) {
//        return createDefault(
//                pointOfPresence,
//                pointOfPresence.getOrganization() == null
//                        ? null
//                        : OrganizationDtoV1.create(pointOfPresence.getOrganization()),
//                pointOfPresence.getEmployees() == null
//                        ? Collections.emptyList()
//                        : pointOfPresence.getEmployees().stream().map(EmployeeDtoV1::create).collect(Collectors.toList())
//        );
//    }

}
