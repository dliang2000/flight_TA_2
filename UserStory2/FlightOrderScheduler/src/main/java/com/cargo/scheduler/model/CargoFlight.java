package com.cargo.scheduler.model;

import java.util.ArrayList;
import java.util.List;

public class CargoFlight {
    private final int flightId;
    private final String originAirport;
    private final String destinationAirport;
    private final int scheduledDay;
    private final List<ShippingOrder> assignedOrders;
	
	private static final int MAXIMUM_BOX_CAPACITY = 20;

    public CargoFlight(int flightId, String originAirport, String destinationAirport, int scheduledDay) {
        this.flightId = flightId;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.scheduledDay = scheduledDay;
        this.assignedOrders = new ArrayList<>();
    }

    public boolean hasAvailableCapacity() {
        return assignedOrders.size() < MAXIMUM_BOX_CAPACITY;
    }

    public boolean servesDestination(String destination) {
        return destinationAirport.equals(destination);
    }

    public void assignOrder(Order order) {
        if (hasAvailableCapacity()) {
            assignedOrders.add(order);
            order.assignToFlight(flightId, originAirport, scheduledDay);
        }
    }

    public String getFlightSchedule() {
        return String.format("Flight: %d, departure: %s, arrival: %s, day: %d",
            flightId, originAirport, destinationAirport, scheduledDay);
    }
}