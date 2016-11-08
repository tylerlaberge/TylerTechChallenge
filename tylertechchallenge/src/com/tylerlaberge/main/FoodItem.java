package com.tylerlaberge.main;

public class FoodItem {

    private String name;
    private String food_group;
    private int stock;
    private double price;
    private double weight;
    private double volume;

    public FoodItem(String name, String food_group, int stock, double price, double weight, double volume) {
        this.name = name;
        this.food_group = food_group;
        this.stock = stock;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }
    public String getFood_group() {
        return food_group;
    }
    public int getStock() {
        return stock;
    }
    public double getPrice() {
        return price;
    }
    public double getWeight() {
        return weight;
    }
    public double getVolume() {
        return volume;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
