package com.github.mcreeper12731.models;

import com.github.mcreeper12731.models.enums.DirectionId;

/**
 * <a href="https://gtfs.org/schedule/reference/#tripstxt">trips.txt field specification</a>, reduced to fields necessary to
 * complete the assignment
 */
public record Trip(
        int routeId,
        int serviceId,
        String tripId,
        String tripHeadsign,
        // String tripShortName,
        DirectionId directionId
        // ...
        ) {

        @Override
        public String toString() {
                return "" + routeId;
        }
}
