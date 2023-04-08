package ru.az.mz.util.equip.service;

import ru.az.mz.util.equip.model.EquipCsv;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface CsvService {

    Optional<EquipCsv> createFromString(String s);

    List<EquipCsv> getEquipCsv(List<String> listCsv);

    List<EquipCsv> getEquipCsv(InputStream in);

    List<EquipCsv> getEquipCsv(File file);

    List<String> getList(InputStream in);

    List<String> getList(File file);

}
