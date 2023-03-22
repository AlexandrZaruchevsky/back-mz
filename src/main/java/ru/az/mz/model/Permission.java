package ru.az.mz.model;

import java.util.stream.Stream;

public enum Permission {

    GUEST("guest"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static Permission getPermission(String permission) {
        return Stream.of(Permission.values())
                .filter(p -> p.name().equalsIgnoreCase(permission.trim().toLowerCase()))
                .findFirst()
                .orElse(GUEST);
    }


}
