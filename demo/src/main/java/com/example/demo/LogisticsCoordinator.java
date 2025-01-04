package com.example.demo;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logistics")
public class LogisticsCoordinator {
    private final RoutePlanner routePlanner = new RoutePlanner();

    @PostMapping("/optimize")
    public ResponseEntity<?> planExpensiveRoute(@RequestBody RouteRequest request) {
        try {
            Pair<List<Transfer>,Integer> res = routePlanner.planRoute(request.getTransfers(),
                    request.getMaxWeight());
            RouteResponse response = new RouteResponse(res.getLeft(), res.getRight());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
