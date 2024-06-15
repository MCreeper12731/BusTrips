package com.github.mcreeper12731.models;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * <a href="https://gtfs.org/schedule/reference/#stop_timestxt">stop_times.txt field specification</a>, reduced to fields necessary to
 * complete the assignment
 */
public record StopTime(
        String tripId,
        LocalTime arrivalTime,
        LocalTime departureTime,
        int stopId,
        //int locationGroupId,
        //int locationId,
        int stopSequence
        // ...
        ) implements Comparable<StopTime>{

        /*@Override
        public String toString() {
                return "<" + arrivalTime + " " + departureTime + ">\n";
        }*/

        @Override
        public int compareTo(StopTime stopTime) {
                if (!this.tripId.equals(stopTime.tripId)) {
                        return this.tripId.compareTo(stopTime.tripId);
                }
                return (int) stopTime.departureTime.until(this.arrivalTime, ChronoUnit.SECONDS);
        }
}
