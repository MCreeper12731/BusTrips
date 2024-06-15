package com.github.mcreeper12731.models.enums;

public enum DirectionId {

    OUTBOUND,
    INBOUND;

    public static DirectionId fromId(String id) {
        return switch (id) {
            case "0" -> OUTBOUND;
            case "1" -> INBOUND;
            default -> null;
        };
    }

}
