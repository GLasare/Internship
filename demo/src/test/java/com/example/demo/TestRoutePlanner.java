package com.example.demo;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRoutePlanner {
    @Test
    void testPlansCorrectRoute() {
        RoutePlanner planner = new RoutePlanner();
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(10,10);
        Transfer transfer2 = new Transfer(10,20);
        Transfer transfer3 = new Transfer(3,5);
        transfers.add(transfer1);
        transfers.add(transfer2);
        transfers.add(transfer3);
        Pair<List<Transfer>, Integer> route1 = planner.planRoute(transfers, 12);
        Pair<List<Transfer>, Integer> route2 = planner.planRoute(transfers, 13);
        assert route1.getLeft().getFirst().equals(transfer2);
        assert route1.getRight() == 20;
        assert route2.getLeft().get(0).equals(transfer3);
        assert route2.getLeft().get(1).equals(transfer2);
        assert route2.getRight() == 25;
    }

    @Test
    void testNoRoute() {
        RoutePlanner planner = new RoutePlanner();
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(10,10);
        Transfer transfer2 = new Transfer(10,20);
        transfers.add(transfer1);
        transfers.add(transfer2);
        Pair<List<Transfer>, Integer> route = planner.planRoute(transfers, 9);
        assert route.getLeft().isEmpty();
        assert route.getRight() == 0;
    }
}
