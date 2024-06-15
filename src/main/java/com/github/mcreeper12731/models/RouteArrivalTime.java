package com.github.mcreeper12731.models;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public record RouteArrivalTime(int routeId, LocalTime arrivalTime) implements Comparable<RouteArrivalTime> {

    @Override
    public int compareTo(RouteArrivalTime routeArrivalTime) {
        if (this.routeId == routeArrivalTime.routeId)
            return (int) routeArrivalTime.arrivalTime.until(this.arrivalTime, ChronoUnit.SECONDS);
        return this.routeId - routeArrivalTime.routeId;
    }
}
