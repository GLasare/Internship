package com.example.demo;

public class Transfer {
    private final int cost;
    private final int weight;

    public Transfer(int weight, int cost) {
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
