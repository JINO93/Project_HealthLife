package com.jino.healthLife.hl.models;

/**
 * Created by Administrator on 2016/4/6.
 */
public class FoodDetial extends Bean {

   private String name;
    private String food;
    private String message;

    public FoodDetial(String name, String food, String message) {
        this.name = name;
        this.food = food;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "FoodDetial{" +
                "name='" + name + '\'' +
                ", food='" + food + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
