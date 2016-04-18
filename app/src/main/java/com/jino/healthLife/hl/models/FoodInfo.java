package com.jino.healthLife.hl.models;

/**
 * Created by Administrator on 2016/4/6.
 */
public class FoodInfo extends Bean {
    private String name;
    private String img;
    private String tag;
    private String food;
    private int count;
    private int id;

    public FoodInfo(String name, String img, String tag, String food, int count, int id) {

        this.name = name;
        this.img = img;
        this.tag = tag;
        this.food = food;
        this.count = count;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FoodInfo{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", tag='" + tag + '\'' +
                ", food='" + food + '\'' +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
