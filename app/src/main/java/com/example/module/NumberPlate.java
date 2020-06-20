package com.example.module;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NumberPlate {
    private String location = "UT";
    private int number;
    private Calendar c = Calendar.getInstance();
    private String str = "2020-6-8 16:50:00";

    public void setLocation(String name) {
        this.location = name;
    }

    public String getLocation() {
        return location;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public int getNumber() {
        return number;
    }

    public void setStr(String str){
        this.str = str;
    }

    public void setTime(int year, int month, int date, int hour, int minute, int second) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(year, month, date, hour, minute, second);
        str = form.format(c);
    }

    public String toJson(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("id",getNumber());
            jo.put("name",getLocation());
            jo.put("time",str);

            return jo.toString();
        }catch (JSONException e){
            e.printStackTrace();
            return "";
        }
    }

}
