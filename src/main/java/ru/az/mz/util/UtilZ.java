package ru.az.mz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UtilZ {

    private UtilZ() {
    }

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

    public static Map<FIO, String> getFioByEnum(String fio) {
        HashMap<FIO, String> mapFio = new HashMap<>();
        String[] split = fio.trim().split("\\s+");
        switch (split.length) {
            case 0:
                mapFio.put(FIO.LAST_NAME, "");
                mapFio.put(FIO.FIRST_NAME, "");
                mapFio.put(FIO.MIDDLE_NAME, "");
                break;
            case 1:
                mapFio.put(FIO.LAST_NAME, split[0]);
                mapFio.put(FIO.FIRST_NAME, "");
                mapFio.put(FIO.MIDDLE_NAME, "");
                break;
            case 2:
                mapFio.put(FIO.LAST_NAME, split[0]);
                mapFio.put(FIO.FIRST_NAME, split[1]);
                mapFio.put(FIO.MIDDLE_NAME, "");
                break;
            default:
                mapFio.put(FIO.LAST_NAME, split[0]);
                mapFio.put(FIO.FIRST_NAME, split[1]);
                mapFio.put(FIO.MIDDLE_NAME, split[2]);
        }
        return mapFio;
    }

    public static boolean ipV4Validate(String subnet) {
        Pattern pattern = Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$");
        return pattern.matcher(subnet).find();
    }

    public static String getHostNameShort(String hostName) {
        if (hostName == null) return null;
        if (ipV4Validate(hostName)) return hostName;
        String replaceAll = hostName.replaceAll("\\s+", "");
        if (replaceAll.indexOf(".") > 0) {
            return replaceAll.substring(0, replaceAll.indexOf("."));
        } else {
            return replaceAll;
        }
    }

}
