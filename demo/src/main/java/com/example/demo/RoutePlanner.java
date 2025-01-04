package com.example.demo;

import org.apache.commons.lang3.tuple.Pair;
import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {

    public Pair<List<Transfer>, Integer> planRoute(List<Transfer> transfers, int maxWeight) {
        int [][] dp = new int[transfers.size()+1][maxWeight+1];
        for (int i = 1; i <= transfers.size(); i++) {
            Transfer transfer = transfers.get(i-1);
            int curWeight = transfer.getWeight();

            for (int w = 0; w <= maxWeight; w++) {
                if (curWeight <= w) {
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-curWeight] + transfer.getCost());
                } else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        // now find out the transfers that were chosen
        int bactrackWeight = maxWeight;
        List<Transfer> chosenTransfers = new ArrayList<>();
        for (int i = transfers.size(); i > 0 && bactrackWeight > 0; i--) {
            if (dp[i][bactrackWeight] != dp[i-1][bactrackWeight]) {
                bactrackWeight -= transfers.get(i-1).getWeight();
                chosenTransfers.add(transfers.get(i-1));
            }
        }
        return Pair.of(chosenTransfers, dp[transfers.size()][maxWeight]);
    }

}
