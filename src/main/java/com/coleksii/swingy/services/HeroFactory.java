package com.coleksii.swingy.services;

import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.model.Hero;

public class HeroFactory {

    public static Hero createHero(Component component){
        return createHero(component, null);
    }

    public static Hero createHero(Component component, String text) {

        Hero hero = null;
        if (text != null){
            hero = Hero.builder().name(text).heroClass(text).build();
            hero.setLevel(1);
            hero.setArmor(10);
            hero.setWeapon(10);
            hero.setHelm(10);
        } else if (component == Component.FIRST_HERO) {
            hero = Hero.builder().name(Component.FIRST_HERO.getProp()).heroClass("Assassin").build();
            hero.setLevel(1);
            hero.setArmor(10);
            hero.setWeapon(10);
            hero.setHelm(10);
        } else if (component == Component.SECOND_HERO) {
            hero = Hero.builder().name(Component.SECOND_HERO.getProp()).heroClass("Tank").build();
            hero.setLevel(3);
            hero.setArmor(30);
            hero.setWeapon(30);
            hero.setHelm(30);
        } else if (component == Component.THIRD_HERO) {
            hero = Hero.builder().name(Component.THIRD_HERO.getProp()).heroClass("Knight").build();
            hero.setLevel(7);
            hero.setArmor(50);
            hero.setWeapon(50);
            hero.setHelm(50);
        }
        return hero;
    }
}
