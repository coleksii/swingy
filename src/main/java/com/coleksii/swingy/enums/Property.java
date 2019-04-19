package com.coleksii.swingy.enums;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum Property {
    HEIGHT("height"),
    WEIGHT("weight");
    private static Properties prop = new Properties();


    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String value;

    Property(String value) {
        this.value = value;
    }

    public String get(){
        return prop.getProperty(value);
    }
    public int getInteger(){
        return Integer.parseInt(prop.getProperty(value));
    }
}
