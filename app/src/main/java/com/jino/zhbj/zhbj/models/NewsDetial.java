package com.jino.zhbj.zhbj.models;

/**
 * Created by Administrator on 2016/4/6.
 */
public class NewsDetial extends Bean {

    private String title;
    private String message;
    private String time;
    private int count;
    private String tag;

    public NewsDetial(String title, String message, String time, int count, String tag) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.count = count;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public String toString() {
        return "NewsDetial{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", count=" + count +
                ", tag='" + tag + '\'' +
                '}';
    }
}
