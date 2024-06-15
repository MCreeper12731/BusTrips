package com.github.mcreeper12731;

import com.github.mcreeper12731.managers.StopManager;
import com.github.mcreeper12731.managers.StopTimeManager;
import com.github.mcreeper12731.managers.TripManager;
import com.github.mcreeper12731.models.Result;
import com.github.mcreeper12731.models.RouteArrivalTime;
import com.github.mcreeper12731.models.enums.TimeFormat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

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

        List<Result> routeArrivalTimes = new LinkedList<>();

        stopTimeManager.getStopTimes(stopId, CURRENT_TIME).stream().map(stopTime ->
                new RouteArrivalTime(
                    tripManager.getTrip(stopTime.tripId()).routeId(),
                    stopTime.arrivalTime()
                )
        ).sorted().forEach(routeArrivalTime -> {
            if (routeArrivalTimes.isEmpty() || routeArrivalTimes.get(routeArrivalTimes.size() - 1).routeId() != routeArrivalTime.routeId())
                routeArrivalTimes.add(new Result(routeArrivalTime.routeId(), new LinkedList<>()));
            routeArrivalTimes.get(routeArrivalTimes.size() - 1).arrivalTimes().add(routeArrivalTime.arrivalTime());
        });

        routeArrivalTimes.forEach(result -> {
            System.out.printf("%d: ", result.routeId());
            printSortedArrivalTimes(result.arrivalTimes(), timeFormat, maxEntries);
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