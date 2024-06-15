package com.github.mcreeper12731.managers;

import com.github.mcreeper12731.models.Stop;
import com.github.mcreeper12731.util.CsvUtility;

import java.util.HashMap;
import java.util.Map;

public class StopManager {

    private final Map<Integer, Stop> stops = new HashMap<>();

    public StopManager() {

        CsvUtility.getData("stops.txt", "stop_id stop_name stop_lat stop_lon").forEach(stopRaw -> stops.put(Integer.parseInt(stopRaw.get(0)), new Stop(
                Integer.parseInt(stopRaw.get(0)),
                stopRaw.get(1),
                Double.parseDouble(stopRaw.get(2)),
                Double.parseDouble(stopRaw.get(3))
        )));

    }

    public Stop getStop(int id) {
        return stops.get(id);
    }
}
