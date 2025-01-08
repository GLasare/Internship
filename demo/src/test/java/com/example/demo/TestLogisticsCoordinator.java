package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TestLogisticsCoordinator {
    @Test
    void testOptimalRoute() {
        List<Transfer> transfers = new ArrayList<>();
        List<Transfer> expectedTransfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(5, 10);
        Transfer transfer2 = new Transfer(10, 20);
        Transfer transfer3 = new Transfer(3, 5);
        Transfer transfer4 = new Transfer(8,15);

        transfers.add(transfer1);
        transfers.add(transfer2);
        transfers.add(transfer3);
        transfers.add(transfer4);
        expectedTransfers.add(transfer2);
        expectedTransfers.add(transfer1);

        RouteRequest request = new RouteRequest(transfers,15);
        LogisticsCoordinator coordinator = new LogisticsCoordinator();
        ResponseEntity<?> response = coordinator.planExpensiveRoute(request);
        RouteResponse responseBody = (RouteResponse) response.getBody();

        assert responseBody.getTransfers().equals(expectedTransfers);
        assert responseBody.getTotalCost() == 30;
        assert response.getStatusCodeValue() == 200;
    }

    @Test
    void testRouteDoesNotExist() {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(5, 10);
        Transfer transfer2 = new Transfer(10, 20);
        transfers.add(transfer1);
        transfers.add(transfer2);

        RouteRequest request = new RouteRequest(transfers,4);
        LogisticsCoordinator coordinator = new LogisticsCoordinator();
        ResponseEntity<?> response = coordinator.planExpensiveRoute(request);
        RouteResponse responseBody = (RouteResponse) response.getBody();

        assert responseBody.getTransfers().isEmpty();
        assert responseBody.getTotalCost() == 0;
        assert response.getStatusCodeValue() == 200;
    }

    @Test
    void testOnlyOneTransfer() {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(5, 10);
        transfers.add(transfer1);

        LogisticsCoordinator coordinator = new LogisticsCoordinator();
        RouteRequest request = new RouteRequest(transfers,6);
        ResponseEntity<?> response = coordinator.planExpensiveRoute(request);
        RouteResponse responseBody = (RouteResponse) response.getBody();

        assert responseBody.getTransfers().size() == 1;
        assert responseBody.getTotalCost() == 10;
        assert response.getStatusCodeValue() == 200;
    }

    @Test
    void testComplicatedRoute() {
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(4, 20));
        transfers.add(new Transfer(5, 30));
        transfers.add(new Transfer(6, 50));
        transfers.add(new Transfer(8, 70));
        transfers.add(new Transfer(7, 90));
        transfers.add(new Transfer(3, 60));
        transfers.add(new Transfer(2, 40));

        LogisticsCoordinator coordinator = new LogisticsCoordinator();
        RouteRequest request = new RouteRequest(transfers,15);
        ResponseEntity<?> response = coordinator.planExpensiveRoute(request);
        RouteResponse responseBody = (RouteResponse) response.getBody();

        assert responseBody.getTransfers().size() == 3;
        assert responseBody.getTotalCost() == 190;
        assert response.getStatusCodeValue() == 200;
    }
}
