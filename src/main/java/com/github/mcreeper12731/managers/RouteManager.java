package com.github.mcreeper12731.managers;

import com.github.mcreeper12731.models.Route;
import com.github.mcreeper12731.models.enums.RouteType;
import com.github.mcreeper12731.util.CsvUtility;

import java.util.HashMap;
import java.util.Map;

public class RouteManager {

    private final Map<Integer, Route> routes = new HashMap<>();

    public RouteManager() {
        CsvUtility.getData("routes.txt", "route_id route_short_name route_long_name route_type").forEach(routeRaw -> routes.put(Integer.parseInt(routeRaw.get(0)), new Route(
                Integer.parseInt(routeRaw.get(0)),
                routeRaw.get(1),
                routeRaw.get(2),
                RouteType.fromId(routeRaw.get(3))
        )));
    }

    public Route getRoute(int routeId) {
        return routes.get(routeId);
    }

}
