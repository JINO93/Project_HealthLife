package com.jino.healthLife.hl.models;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/4.
 */
public class Bean implements Serializable {

    protected long _id;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
