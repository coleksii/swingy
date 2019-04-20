package com.coleksii.swingy.enums;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public enum Component {
    PLAY_BUTTON("New Game"),
    FIRST_IMAGINE("First Imagine"),
    TEXT_LABEL("New Awesome GameGui"),
    CHOOSE_HERO_TEXT("Choose your hero"),
    FIRST_HERO("first.hero"),
    SECOND_HERO("second.hero"),
    THIRD_HERO("third.hero"),
    FIRST_HERO_DESCR("first.hero.descr"),
    SECOND_HERO_DESCR("second.hero.descr"),
    THIRD_HERO_DESCR("third.hero.descr"),
    FIRST_HERO_BUTTON("first.hero"),
    SECOND_HERO_BUTTON("second.hero"),
    THIRD_HERO_BUTTON("third.hero"),
    CHOOSE_HERO_BUTTON("choose hero"),
    CREATE_HERO_BUTTON("create hero"),
    INPUT_TEXT("Enter Hero Name"),
    SUBMIT_BUTTON("submit"),
    HERO_STAT("hero stat"),
    NORTH("north"), WEST("west"), EAST("east"), SOUTH("south")
    ;

    private static Properties prop = new Properties();


    static {
        try {
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String value;

    Component(String s) {
        value = s;
    }

    public String getProp(){
        return prop.getProperty(value);
    }
    public int getInteger(){
        return Integer.parseInt(prop.getProperty(value));
    }
}
