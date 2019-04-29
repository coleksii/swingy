package com.coleksii.swingy.model;

import com.coleksii.swingy.enums.Component;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

/**
 * Created by coleksii on 4/20/19.
 */
@Getter
@Setter
public class Enemy {
    private int strength;
    private JLabel image;
    private String name;
    private Component component;
}
