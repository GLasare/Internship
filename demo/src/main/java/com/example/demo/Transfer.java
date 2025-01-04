package com.example.demo;

public class Transfer {
    private final int weight;
    private final int cost;

    public Transfer(int cost, int weight) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }
}
