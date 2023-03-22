package ru.az.mz.dto.v1.projections;

public interface OrganizationWithBossV1 {

    Long getId();
    String getShortName();
    String getFullName();
    String getInn();
    String getKpp();
    String getOgrn();
    Long getBossId();
    String getBossLastName();
    String getBossFirstName();
    String getBossMiddleName();
    String getBossPosition();

}
