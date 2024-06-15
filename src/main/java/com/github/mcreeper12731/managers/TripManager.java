package com.github.mcreeper12731.managers;

import com.github.mcreeper12731.models.Trip;
import com.github.mcreeper12731.models.enums.DirectionId;
import com.github.mcreeper12731.util.CsvUtility;

import java.util.HashMap;
import java.util.Map;

public class TripManager {

    private final Map<String, Trip> trips = new HashMap<>();

    public TripManager() {
        CsvUtility.getData("trips.txt", "route_id service_id trip_id trip_headsign direction_id").forEach(tripRaw -> trips.put(tripRaw.get(2), new Trip(
                Integer.parseInt(tripRaw.get(0)),
                Integer.parseInt(tripRaw.get(1)),
                tripRaw.get(2),
                tripRaw.get(3),
                DirectionId.fromId(tripRaw.get(4))
        )));
    }

    public Trip getTrip(String id) {
        return trips.get(id);
    }

}
