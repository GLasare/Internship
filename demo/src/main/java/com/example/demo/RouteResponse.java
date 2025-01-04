package com.example.demo;

import java.util.List;

public class RouteResponse {
    private final List<Transfer> transfers;
    private final int totalCost;

    public RouteResponse(List<Transfer> transfers, int totalCost) {
        this.transfers = transfers;
        this.totalCost = totalCost;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public int getTotalCost() {
        return totalCost;
    }
}
