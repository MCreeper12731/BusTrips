package com.github.mcreeper12731.managers;

import com.github.mcreeper12731.models.StopTime;
import com.github.mcreeper12731.util.CsvUtility;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopTimeManager {

    private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Map<Integer, List<StopTime>> stopIdStopTimes = new HashMap<>();

    public StopTimeManager() {
        CsvUtility.getData("stop_times.txt", "trip_id arrival_time departure_time stop_id stop_sequence").forEach(stopTimeRaw -> {
            int stopId = Integer.parseInt(stopTimeRaw.get(3));
            StopTime stopTime = new StopTime(
                    stopTimeRaw.get(0),
                    LocalTime.parse(stopTimeRaw.get(1), FORMAT),
                    LocalTime.parse(stopTimeRaw.get(2), FORMAT),
                    Integer.parseInt(stopTimeRaw.get(3)),
                    Integer.parseInt(stopTimeRaw.get(4))
            );
            if (stopIdStopTimes.get(stopId) == null) {
                List<StopTime> newList = new ArrayList<>();
                newList.add(stopTime);
                stopIdStopTimes.put(stopId, newList);
            } else {
                stopIdStopTimes.get(stopId).add(stopTime);
            }
        });
    }

    public List<StopTime> getStopTimes(int stopId, LocalTime currentTime) {
        return stopIdStopTimes.get(stopId).stream().filter(stopTime -> stopTime.arrivalTime().isAfter(currentTime) &&
                stopTime.arrivalTime().isBefore(currentTime.plusHours(1))).toList();
    }
}
