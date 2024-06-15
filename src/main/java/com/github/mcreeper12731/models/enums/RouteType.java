package com.github.mcreeper12731.models.enums;

public enum RouteType {

    LIGHT_RAIL,
    UNDERGROUND,
    RAIL,
    BUS,
    FERRY,
    CABLE_TRAM,
    AERIAL_LIFT,
    FUNICULAR,
    TROLLEYBUS,
    MONORAIL;

    public static RouteType fromId(String routeTypeId) {
        return switch (routeTypeId) {
            case "0" -> LIGHT_RAIL;
            case "1" -> UNDERGROUND;
            case "2" -> RAIL;
            case "3" -> BUS;
            case "4" -> FERRY;
            case "5" -> CABLE_TRAM;
            case "6" -> AERIAL_LIFT;
            case "7" -> FUNICULAR;
            case "11" -> TROLLEYBUS;
            case "12" -> MONORAIL;
            default -> null;
        };
    }

}
