package com.jino.healthLife.hl.models;

/**
 * Created by Administrator on 2016/4/6.
 */
public class Category extends Bean{

    private String name;

    private String title;
    private long id;

    public Category(String name, String title, long id) {
        this.name = name;
        this.title = title;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
