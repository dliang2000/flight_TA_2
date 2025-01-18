package com.cargo.scheduler;

import com.cargo.scheduler.service.FlightScheduler;
import com.cargo.scheduler.service.OrderProcessor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlightOrderSchedulerApplication {
    private static final String ORDERS_FILE_PATH = "../../../../resource/coding-assigment-orders.json";

    public static void main(String[] args) {
        try {
            FlightScheduler flightScheduler = initializeFlightSchedule();
            displayFlightSchedule(flightScheduler);
            
            OrderProcessor orderProcessor = processOrders(flightScheduler);
            displayOrderSchedule(orderProcessor);
            
        } catch (Exception error) {
            handleApplicationError(error);
        }
    }

    private static FlightScheduler initializeFlightSchedule() {
        FlightScheduler scheduler = new FlightScheduler();
        scheduler.initializeDefaultSchedule();
        return scheduler;
    }

    private static void displayFlightSchedule(FlightScheduler scheduler) {
        System.out.println("\nFlight Schedule:");
        scheduler.getFlightSchedule().forEach(System.out::println);
    }

    private static OrderProcessor processOrders(FlightScheduler scheduler) throws IOException {
        String jsonContent = loadOrdersFile();
        OrderProcessor processor = new OrderProcessor(scheduler);
        processor.loadOrdersFromJson(jsonContent);
        processor.processOrders();
        return processor;
    }

    private static String loadOrdersFile() throws IOException {
        return new String(Files.readAllBytes(Paths.get(ORDERS_FILE_PATH)));
    }

    private static void displayOrderSchedule(OrderProcessor processor) {
        System.out.println("\nOrder Schedule:");
        processor.getOrderScheduleStatus().forEach(System.out::println);
    }

    private static void handleApplicationError(Exception error) {
        System.err.println("Application Error: " + error.getMessage());
        error.printStackTrace();
    }
}