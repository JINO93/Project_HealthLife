package com.jino.healthLife.hl.models;

/**
 * Created by Administrator on 2016/4/6.
 */
public class ItemInfo extends Bean {
    private String title;
    private String img;
    private int count;


    private String time;
    private int id;

    public ItemInfo(String title, String img, int count, String time, int id) {
        this.title = title;
        this.img = img;
        this.count = count;
        this.time = time;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", count=" + count +
                ", time='" + time + '\'' +
                ", id=" + id +
                '}';
    }
}
