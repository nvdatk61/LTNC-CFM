/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Datnv
 */
public class Food {
    private int IDFood;
    private String FoodName;
    private int IDCategory;
    private int Price;

    public Food(int IDFood, String FoodName, int IDCategory, int Price) {
        this.IDFood = IDFood;
        this.FoodName = FoodName;
        this.IDCategory = IDCategory;
        this.Price = Price;
    }

    public int getIDFood() {
        return IDFood;
    }

    public void setIDFood(int IDFood) {
        this.IDFood = IDFood;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String FoodName) {
        this.FoodName = FoodName;
    }

    public int getIDCategory() {
        return IDCategory;
    }

    public void setIDCategory(int IDCategory) {
        this.IDCategory = IDCategory;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
    
}
