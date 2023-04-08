package ru.az.mz.util.equip.service.impl;

import org.springframework.stereotype.Service;
import ru.az.mz.util.equip.model.EquipCsv;
import ru.az.mz.util.equip.service.CsvService;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CsvServiceImpl implements CsvService {

    @Override
    public Optional<EquipCsv> createFromString(String s) {
        String[] strings = s.split(";");
        switch (strings.length) {
            case 0:
                return Optional.empty();
            case 1:
                return Optional.of(createEquipCsv(strings[0], null, null, null, null, null));
            case 2:
                return Optional.of(createEquipCsv(strings[0], strings[1], null, null, null, null));
            case 3:
                return Optional.of(createEquipCsv(strings[0], strings[1], strings[2], null, null, null));
            case 4:
                return Optional.of(createEquipCsv(strings[0], strings[1], strings[2], strings[3], null, null));
            case 5:
                return Optional.of(createEquipCsv(strings[0], strings[1], strings[2], strings[3], strings[4], null));
            default:
                return Optional.of(createEquipCsv(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5]));
        }
    }

    @Override
    public List<EquipCsv> getEquipCsv(List<String> listCsv) {
        return listCsv.stream()
                .map(this::createFromString)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<EquipCsv> getEquipCsv(InputStream in) {
        return getEquipCsv(getList(in));
    }

    @Override
    public List<EquipCsv> getEquipCsv(File file) {
        return getEquipCsv(getList(file));
    }

    @Override
    public List<String> getList(InputStream in) {
        return new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8)
        ).lines().collect(Collectors.toList());
    }

    @Override
    public List<String> getList(File file) {
        return List.of(Objects.requireNonNull(file.list()));
    }

    private EquipCsv createEquipCsv(
            String name,
            String inventoryNumber,
            String smetaCode,
            String quantity,
            String apartment,
            String accountName
    ) {
        return new EquipCsv(
                name != null ? name.trim() : null,
                inventoryNumber != null ? inventoryNumber.trim() : null,
                smetaCode != null ? smetaCode.trim() : null,
                quantity != null ? quantity.trim() : null,
                apartment != null ? apartment.trim() : null,
                accountName != null ? accountName.trim() : null
        );
    }

}
