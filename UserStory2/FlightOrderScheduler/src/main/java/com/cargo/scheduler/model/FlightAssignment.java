package com.cargo.scheduler.model;

public class FlightAssignment {
    private final int flightId;
    private final String originAirport;
    private final int scheduledDay;

    public FlightAssignment(int flightId, String originAirport, int scheduledDay) {
        this.flightId = flightId;
        this.originAirport = originAirport;
        this.scheduledDay = scheduledDay;
    }

    public static FlightAssignment unassigned() {
        return new FlightAssignment(null, null, null);
    }

    public boolean isUnassigned() {
        return flightId == null;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getOriginAirport() {
        return originAirport;
    }

    public int getScheduledDay() {
        return scheduledDay;
    }
}