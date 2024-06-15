package com.github.mcreeper12731;

import com.github.mcreeper12731.managers.StopManager;
import com.github.mcreeper12731.managers.StopTimeManager;
import com.github.mcreeper12731.managers.TripManager;
import com.github.mcreeper12731.models.enums.TimeFormat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static final LocalTime CURRENT_TIME = LocalTime.now();
    public static void main(String[] args) {

        // Application assumes correct arguments format
        int stopId = Integer.parseInt(args[0]);
        int maxEntries = Integer.parseInt(args[1]);
        TimeFormat timeFormat = TimeFormat.valueOf(args[2].toUpperCase());

        StopManager stopManager = new StopManager();
        TripManager tripManager = new TripManager();
        // not needed for the task
        //RouteManager routeManager = new RouteManager();
        StopTimeManager stopTimeManager = new StopTimeManager();

        if (stopManager.getStop(stopId) == null) {
            throw new IllegalArgumentException(String.format("Stop with stop id '%d' does not exist!", stopId));
        }

        System.out.printf("Postajalisce %s%n", stopManager.getStop(stopId).stopName());

        Map<Integer, List<LocalTime>> routeArrivalTimes = new HashMap<>();
        stopTimeManager.getStopTimes(stopId, CURRENT_TIME).forEach(stopTime -> {
            int routeId = tripManager.getTrip(stopTime.tripId()).routeId();
            routeArrivalTimes.computeIfAbsent(routeId, k -> new ArrayList<>());
            routeArrivalTimes.get(routeId).add(stopTime.arrivalTime());
        });

        routeArrivalTimes.forEach((routeId, arrivalTimes) -> {
            System.out.printf("%d: ", routeId);
            printSortedArrivalTimes(arrivalTimes, timeFormat, maxEntries);
            System.out.printf("%n");
        });
    }

    private static void printSortedArrivalTimes(List<LocalTime> arrivalTimes, TimeFormat timeFormat, int maxEntries) {
        arrivalTimes.stream().limit(maxEntries).sorted().forEach(time -> System.out.print(
            switch (timeFormat) {
                case RELATIVE -> String.format("%dmin ", ChronoUnit.MINUTES.between(CURRENT_TIME, time));
                case ABSOLUTE -> String.format("%s ", time.format(DateTimeFormatter.ofPattern("HH:mm")));
        }));
    }
}