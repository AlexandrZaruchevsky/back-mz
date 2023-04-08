package ru.az.mz.services;

import ru.az.mz.util.equip.model.EquipCsv;
import ru.az.sfr.util.ad.xml.model.dom.ADUser;

import java.util.List;

public interface UploadServiceV1 {

    void loadTopDeps(List<ADUser> adUsers) throws MyException;

    void loadDeps(List<ADUser> adUsers) throws MyException;

    void loadPositions(List<ADUser> adUsers) throws MyException;

    void loadPointOfPresences(List<ADUser> adUsers) throws MyException;

    void loadEmployees(List<ADUser> adUsers) throws MyException;

    void clearEmployeesWithDependencies();

    void loadEquipFromCsv(List<EquipCsv> listEquipCsv);

    void clearEquip();

}
