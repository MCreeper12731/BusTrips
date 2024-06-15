package com.github.mcreeper12731.models;

import com.github.mcreeper12731.models.enums.RouteType;

/**
 * <a href="https://gtfs.org/schedule/reference/#routestxt">routes.txt field specification</a>, reduced to fields necessary to
 * complete the assignment
 */
public record Route(
        int routeId,
        //int agencyId,
        String routeShortName,
        String routeLongName,
        //String routeDesc,
        RouteType routeType
        // ...
        ) {
}
