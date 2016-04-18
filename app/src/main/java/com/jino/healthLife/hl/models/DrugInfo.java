package com.jino.healthLife.hl.models;

/**
 * Created by Administrator on 2016/4/6.
 */
public class DrugInfo extends Bean {
    private String name;
    private String image;
    private String factory;
    private String price;
    private int count;
    private int id;

    public DrugInfo(String name, String image, String factory, String price, int count, int id) {
        this.name = name;
        this.image = image;
        this.factory = factory;
        this.price = price;
        this.count = count;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        return "DrugInfo{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", factory='" + factory + '\'' +
                ", price='" + price + '\'' +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
