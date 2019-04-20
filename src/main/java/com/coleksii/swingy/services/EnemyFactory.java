package com.coleksii.swingy.services;

import com.coleksii.swingy.model.Enemy;

import javax.swing.*;

/**
 * Created by coleksii on 4/20/19.
 */
public class EnemyFactory {
    private final static int max = 8;
    public static Enemy createEnemy(int index){
        Enemy enemy = new Enemy();
        JLabel jLabel = new JLabel();//todo
        return enemy;
    }
}
