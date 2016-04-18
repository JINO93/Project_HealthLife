package com.jino.healthLife.hl.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PreferencesUtils {
    private static String name="mZHBJ";

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public static void setBoolean(Context context,String key,Boolean bool){
        SharedPreferences.Editor editor=getPreferences(context).edit();
        editor.putBoolean(key,bool);
        editor.commit();
    }

    public static Boolean getBoolean(Context context,String key){
        return getPreferences(context).getBoolean(key,true);
    }
}
