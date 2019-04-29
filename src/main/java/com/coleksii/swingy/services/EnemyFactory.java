package com.coleksii.swingy.services;

import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.model.Enemy;

import javax.swing.*;

/**
 * Created by coleksii on 4/20/19.
 */
public class EnemyFactory {
    private final static int max = 8;

    public static Enemy createEnemy(int index, int level) {
        String name;
        Component component;
        index = index % max;
        double strenthIndex = 0;

        switch (index) {
            case 1:
                name = "Color Skeleton!";
                component = Component.COLOR_SKELETON;
                strenthIndex = 0.5;
                break;
            case 2:
                name = "Kenny Skeleton";
                component = Component.KENNY_SKELETON;
                strenthIndex = 0.8;
                break;
            case 3:
                name = "Dragon";
                component=Component.DRAGON;
                strenthIndex = 1;
                break;
            case 4:
                name = "Fat Skeleton";
                component = Component.FAT_SKELETON;
                strenthIndex = 1.2;
                break;
            case 5:
                name = "Exception Skeleton";
                component = Component.EXCETIPION_SKELETON;
                strenthIndex = 1.5;
                break;
            case 6:
                name = "Angry Skeleton";
                component = Component.ANGTY_SKELETON;
                strenthIndex = 1.8;
                break;
            case 7:
                name = "Knight";
                component=Component.KNIGHT;
                strenthIndex = 2;
                break;
            case 0:
                name = "Skull";
                component=Component.SKULL;
                strenthIndex = 2.5;
                break;
            default:
                name = "Dark Lord";
                strenthIndex = 10;
                component=null;
                break;
        }
        Enemy enemy = new Enemy();
        enemy.setName(name);
        enemy.setComponent(component);
        enemy.setStrength((int) (level * 10 * strenthIndex));
        return enemy;
    }
}
