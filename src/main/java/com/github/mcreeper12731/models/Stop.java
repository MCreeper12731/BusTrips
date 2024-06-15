package com.github.mcreeper12731.models;


/**
 * <a href="https://gtfs.org/schedule/reference/#stopstxt">stops.txt field specification</a>, reduced to fields necessary to
 * complete the assignment
 */
public record Stop(
        int stopId,
        //String stopCode,
        String stopName,
        //String ttsStopName,
        //String stopDesc,
        double stopLat,
        double stopLon
        // ...
        ) {

}
