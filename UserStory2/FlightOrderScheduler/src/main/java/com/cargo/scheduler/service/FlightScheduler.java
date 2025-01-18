package com.cargo.scheduler.service;

import com.cargo.scheduler.model.CargoFlight;
import com.cargo.scheduler.model.Order;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlightScheduler {
    private final List<CargoFlight> scheduledFlights;

    public FlightScheduler() {
        this.scheduledFlights = new ArrayList<>();
    }

    public void initializeDefaultSchedule() {
        createDayOneFlights();
        createDayTwoFlights();
    }

    private void createDayOneFlights() {
        scheduledFlights.addAll(Arrays.asList(
            new CargoFlight(1, "YUL", "YYZ", 1),
            new CargoFlight(2, "YUL", "YYC", 1),
            new CargoFlight(3, "YUL", "YVR", 1)
        ));
    }

    private void createDayTwoFlights() {
        scheduledFlights.addAll(Arrays.asList(
            new CargoFlight(4, "YUL", "YYZ", 2),
            new CargoFlight(5, "YUL", "YYC", 2),
            new CargoFlight(6, "YUL", "YVR", 2)
        ));
    }

    public void scheduleOrder(Order order) {
        scheduledFlights.stream()
            .filter(flight -> flight.servesDestination(order.getDestinationAirport()))
            .filter(CargoFlight::hasAvailableCapacity)
            .findFirst()
            .ifPresent(flight -> flight.assignOrder(order));
    }

    public List<String> getFlightSchedule() {
        return scheduledFlights.stream()
            .map(CargoFlight::getFlightSchedule)
            .collect(Collectors.toList());
    }
}
