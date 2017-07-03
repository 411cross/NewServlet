package com.company.Util;

/**
 * Created by derrickJ on 2017/7/1.
 */
public class Tuple {

    private String key;
    private String value;
    private int dataType = 0; // 0:int 1:String

    public Tuple(String key, String value, int dataType) {
        this.key = key;
        this.value = value;
        this.dataType = dataType;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public int getDataType() {
        return dataType;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}