package com.example.demo;

import java.util.List;

public class RouteRequest {
    private final List<Transfer> transfers;
    private final int maxWeight;

    public RouteRequest(List<Transfer> transfers, int maxWeight) {
        this.transfers = transfers;
        this.maxWeight = maxWeight;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}
