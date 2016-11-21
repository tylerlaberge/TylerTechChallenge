package com.tylerlaberge.domain;

/**
 * A class for representing food item data.
 */
public class FoodItem {

    private final String name;
    private final String food_group;
    private int stock;
    private final double price;
    private final double weight;
    private final double volume;

    /**
     * Create a new FoodItem instance.
     *
     * @param name          The name of the food item.
     * @param food_group    The food group that the food item belongs to.
     * @param stock         The number of the food item that are in stock.
     * @param price         The price of the food item.
     * @param weight        The weight of the food item.
     * @param volume        The volume of the food item.
     */
    public FoodItem(String name, String food_group, int stock, double price, double weight, double volume) {
        this.name = name;
        this.food_group = food_group;
        this.stock = stock;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
    }

    /**
     * Get the name of this FoodItem.
     *
     * @return  The name of this FoodItem.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the food group that this FoodItem belongs to.
     *
     * @return  The food group of this FoodItem.
     */
    public String getFoodGroup() {
        return food_group;
    }

    /**
     * Get the amount of this FoodItem that are in stock.
     *
     * @return  The stock of this FoodItem.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Get the price of this FoodItem.
     *
     * @return  The price of this FoodItem.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the weight of this FoodItem.
     *
     * @return  The weight of this FoodItem.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Get the volume of this FoodItem.
     *
     * @return  The volume of this FoodItem.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Set the stock of this FoodItem.
     *
     * @param stock The stock to set to the FoodItem.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get a String representation of this FoodItem.
     *
     * @return  The String representation of this FoodItem.
     */
    public String toString() {
        return String.format("<FoodItem name: %s, stock: %d, price: %f, weight: %f, volume: %f, food_group: %s",
                this.name, this.stock, this.price, this.weight, this.volume, this.food_group);
    }
}
