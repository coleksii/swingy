package com.coleksii.swingy.model;

import com.coleksii.swingy.enums.Component;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Builder
@Setter
@Getter
public class Hero {
    private Component component;
    private JLabel label;
    private String name;
    private String heroClass;

    private int level;
    private int experience;
    private int attack;
    private int defense;
    private int hitPoint;

    private int weapon;
    private int armor;
    private int helm;

    public void setLevel(int level) {
        hitPoint = (int) (level / 2.0 * 100);
        attack = (int) (level * 5);
        defense = (int) (level * 5);
        this.level = level;
    }

    public void addExperience(int experience) {
        int experienceLine = level * 1000 + ((level - 1) * (level - 1) * 450);

        if (getExperience() + experience < level * 1000 + ((level - 1) * (level - 1) * 450)) {
            setExperience(getExperience() + experience);
        } else {
            while (getExperience() + experience >= level * 1000 + ((level - 1) * (level - 1) * 450)) {
                addLevel();
                setExperience(getExperience() + experience - level * 1000 + ((level - 1) * (level - 1) * 450));
            }
        }
    }

    public void addLevel() {
        setLevel(getLevel() + 1);

    }

    public void setWeapon(int weapon) {
        setAttack(getAttack() - getWeapon() + weapon);
        this.weapon = weapon;
    }

    public void setHelm(int helm) {
        setHitPoint(getHitPoint() - getHelm() + helm);
        this.helm = helm;
    }

    public void setArmor(int armor) {
        setDefense(getDefense() - getArmor() + armor);
        this.armor = armor;
    }

    public String getStats() {

        int expLine = level * 1000 + ((level - 1) * (level - 1) * 450);
        return "Name: " + name + "\n" +
                "level: " + level + "\n" +
                "EXP: " + experience + "/" + expLine + "\n" +
                "HP: " + hitPoint + "\n" +
                "Attack: " + attack + "\n" +
                "Defence "+ defense + "\n"+
                "weapon: " + weapon + "\n" +
                "armor: " + armor + "\n" +
                "helm: " + helm + "\n";
    }
}
