package com.cargo.scheduler.model;

public class Order {
    private final String orderReference;
    private final String destinationAirport;
    private FlightAssignment flightAssignment;

    public Order(String orderReference, String destinationAirport) {
        this.orderReference = orderReference;
        this.destinationAirport = destinationAirport;
        this.flightAssignment = FlightAssignment.unassigned();
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void assignToFlight(int flightId, String originAirport, int scheduledDay) {
        this.flightAssignment = new FlightAssignment(flightId, originAirport, scheduledDay);
    }

    public String getScheduleStatus() {
        if (flightAssignment.isUnassigned()) {
            return formatUnscheduledOrder();
        }
        return formatScheduledOrder();
    }

    private String formatUnscheduledOrder() {
        return String.format("order: %s, flightId: not scheduled", orderReference);
    }

    private String formatScheduledOrder() {
        return String.format("order: %s, flightId: %d, departure: %s, arrival: %s, day: %d",
            orderReference, 
            flightAssignment.getFlightId(),
            flightAssignment.getOriginAirport(),
            destinationAirport,
            flightAssignment.getScheduledDay());
    }
}