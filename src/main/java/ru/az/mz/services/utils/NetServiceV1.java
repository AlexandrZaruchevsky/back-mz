package ru.az.mz.services.utils;

import ru.az.mz.services.MyException;

import java.util.List;

public interface NetServiceV1 {

    boolean isEquipActive(String equipName);

    boolean isNetScanRunning(String subnet) throws MyException;

    void subnetScanStart(String subnet) throws MyException;

    List<String> subnetScan();

    List<SubnetEquip> getSubnetMap(String subnet) throws MyException;

    List<String> getListNameExecutors();

}
