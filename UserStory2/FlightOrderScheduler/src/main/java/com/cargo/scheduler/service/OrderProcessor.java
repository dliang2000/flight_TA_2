package com.cargo.scheduler.service;

import com.cargo.scheduler.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class OrderProcessor {
    private final List<Order> orders;
    private final FlightScheduler flightScheduler;

    public OrderProcessor(FlightScheduler flightScheduler) {
        this.orders = new ArrayList<>();
        this.flightScheduler = flightScheduler;
    }

    public void loadOrdersFromJson(String jsonContent) {
        JSONObject ordersJson = new JSONObject(jsonContent);
        
        ordersJson.keySet().stream()
            .sorted()
            .forEach(orderRef -> {
                JSONObject orderData = ordersJson.getJSONObject(orderRef);
                String destination = orderData.getString("destination");
                orders.add(new Order(orderRef, destination));
            });
    }

    public void processOrders() {
        orders.forEach(flightScheduler::scheduleOrder);
    }

    public List<String> getOrderScheduleStatus() {
        return orders.stream()
            .map(ShippingOrder::getScheduleStatus)
            .collect(Collectors.toList());
    }
}