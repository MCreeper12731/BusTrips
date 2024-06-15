package com.github.mcreeper12731.models;

import java.time.LocalTime;
import java.util.List;

public record Result(int routeId, List<LocalTime> arrivalTimes) {
}
