package ru.az.mz.util;

import java.util.HashMap;
import java.util.Map;

public class UtilZ {

    private UtilZ(){}

    public static Map<String, String> getFio(String fio) {
        HashMap<String, String> mapFio = new HashMap<>();
        String[] split = fio.trim().split("\\s+");
        switch (split.length) {
            case 0:
                mapFio.put("lastName", "");
                mapFio.put("firstName", "");
                mapFio.put("middleName", "");
                break;
            case 1:
                mapFio.put("lastName", split[0]);
                mapFio.put("firstName", "");
                mapFio.put("middleName", "");
                break;
            case 2:
                mapFio.put("lastName", split[0]);
                mapFio.put("firstName", split[1]);
                mapFio.put("middleName", "");
                break;
            default:
                mapFio.put("lastName", split[0]);
                mapFio.put("firstName", split[1]);
                mapFio.put("middleName", split[2]);
        }
        return mapFio;
    }

}
