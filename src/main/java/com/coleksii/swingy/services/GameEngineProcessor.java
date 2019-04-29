package com.coleksii.swingy.services;

import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Random;

@RequiredArgsConstructor
public class GameEngineProcessor {

    private MapCell[][]map;

    String gre = "\033[32m";
    String def = "\033[0m";

    @NonNull
    @NotNull
    private User user;

    private int x = 0;
    private int y = 0;

    public String proccess(String command){
        Hero hero = user.getHero();
        if (user.getState() == State.NEW_GAME) {
            user.setState(State.CHOICE);
            return "Welcome to My Awesome Game!\nSelect options to create hero! "+ gre + "\nchoose hero " +def+" or "+gre+"create hero" + def;
        } else if (user.getState() == State.CHOICE && command.equals(Component.CHOOSE_HERO_BUTTON.getValue())) {
            user.setState(State.CHOOSE_HERO);
            return "Choose your hero!" + def + "\nor just input name" + gre + "\nSam Hook " +def + " or " +gre + "Blazer " +def + " or " +gre + "Not Dead" + def;
        } else if (user.getState() == State.CHOICE &&  command.equals(Component.CREATE_HERO_BUTTON.getValue())){
            user.setState(State.CREATE_HERO_NAME);
            return "Please input name";
        } else if (user.getState() == State.CREATE_HERO_NAME || user.getState() == State.CHOOSE_HERO) {
            user.setState(State.GAME_PROCESS);
            createHero(command);
            hero = user.getHero();
            createMap();
            return "So you are in the center of map\nchoose your direction" + def + "\n" + hero.getStats() + gre + "\n" + Component.WEST.getValue()+def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue() + def;
        } else if (user.getState() == State.GAME_PROCESS){
            if (command.equals(Component.WEST.getValue()) || command.equals(Component.EAST.getValue()) || command.equals(Component.SOUTH.getValue()) || command.equals(Component.NORTH.getValue())) {
                x = hero.getX();
                y = hero.getY();
                if (command.equals(Component.WEST.getValue()))
                    hero.setX(hero.getX() - 1);
                else if (command.equals(Component.SOUTH.getValue()))
                    hero.setY(hero.getY() + 1);
                else if (command.equals(Component.EAST.getValue()))
                    hero.setX(hero.getX() + 1);
                else if (command.equals(Component.NORTH.getValue()))
                    hero.setY(hero.getY() - 1);
                return eventHero(hero);
            }
        } else if (user.getState() == State.FIND_MONSTER){
            if (command.equals(Component.RUN.getValue())){
                if (randomCheckIsRun()){
                    hero.setY(y);
                    hero.setX(x);
                    user.setState(State.GAME_PROCESS);
                    return "You are returned on previous position without damage!" + gre+"\n" + Component.WEST.getValue()+def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue()  + def;
                } else {
                    return "Unfortunately, your hero could not escape fight!" + gre+"\n" + "fight" + def;
                }
            } else if (command.equals(Component.FIGHT.getValue())){
                return fightProcess();
            }
        } else if (user.getState().equals(State.NO_ESCAPE)){
            if (command.equals(Component.FIGHT.getValue())){
                return fightProcess();
            }
        } else if (user.getState().equals(State.ARTIFACT)){
            if (command.equals(Component.KEEP.getValue())){
                hero.equipArtifact();
                user.setState(State.GAME_PROCESS);
                return "Okai, I will keep it!" + def + "\n" + hero.getStats()+ gre +"\n" + Component.WEST.getValue()+def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue();
            } else if (command.equals(Component.LEAVE.getValue())){
                user.setState(State.GAME_PROCESS);
                return "Okai, I will leave it" + def + "\n" + hero.getStats() + gre +"\n"+ Component.WEST.getValue()+def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue();
            }
        }
        return "Please try another command";
    }

    private String fightProcess() {
        Hero hero = user.getHero();
        Enemy enemy = map[hero.getX()][hero.getY()].getEnemy();
        int strenght = enemy.getStrength();
        boolean criticalHit = false;
        boolean artifact = false;
        Artifact artifactName = null;
        if (Math.random() <= 0.3){
            criticalHit = true;
        }
        if (Math.random() <= 0.5){
            artifact = true;
            artifactName = artifacrGenerator(enemy.getStrength());
            hero.setArtifact(artifactName);
            user.setState(State.ARTIFACT);
        }else
            user.setState(State.GAME_PROCESS);
        hero.addExperience(enemy.getStrength() * 50);
        map[hero.getX()][hero.getY()].setEnemy(null);
        if (criticalHit) {
            if (artifact){
                return "CRITICAL HIT! You defeat the monster without Any damage! Also was dropped artifact: " + artifactName.getType() + "that " + artifactName.getDescribe()+ gre + "\nkeep" + def + " or " + gre + "leave" + def;
            }else
                return "CRITICAL HIT! You defeat the monster without Any damage!" + def + "\n" + hero.getStats()+ gre +"\n" + Component.WEST.getValue()+def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue() + def;
        }
        int damage = strenght * 2 / (hero.getAttack() + hero.getDefense()) * strenght;
        hero.setHitPoint(hero.getHitPoint() - damage);
        if (hero.getHitPoint() <= 0){
            user.setState(State.DEFEAT);
            return "DEFEAT";
        }
        if (artifact){
            return "You defeat the monster with damage " + damage + " Also was dropped artifact: " + artifactName.getType() + "that " + artifactName.getDescribe() + gre + "\nkeep" + def + " or " + gre + "leave" + def;
        }
        return "You defeat the monster with damage " + damage + def + "\n" + hero.getStats() + gre +"\n"+ Component.WEST.getValue()+ def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue() + def;
    }

    private Artifact artifacrGenerator(int strength) {
        Artifact artifact = new Artifact();
        artifact.setStrenth(strength / 2);
        if (Math.random() <= 0.3){
            artifact.setType("Armor");
            artifact.setDescribe("increases defense by " + artifact.getStrenth());
        } else if (Math.random() <= 0.3){
            artifact.setType("Weapon");
            artifact.setDescribe("increases the attack by " + artifact.getStrenth());
        } else {
            artifact.setType("Helmet");
            artifact.setDescribe("increases hit points by " + artifact.getStrenth());
        }
        return artifact;
    }

    private boolean randomCheckIsRun() {
        Random random = new Random();
        int i = random.nextInt();
        return i % 2 == 0;
    }

    private String eventHero(Hero hero) {
        if (hero.getX() > map.length - 1 || hero.getY() > map.length - 1 || hero.getX() < 0 || hero.getY() < 0) {
            user.setState(State.FINISH);
            return "FINISH! YOU WIN";
        }
        Enemy enemy = map[hero.getX()][hero.getY()].getEnemy();
        if (enemy != null){
            user.setState(State.FIND_MONSTER);
            return enemy.getName() + " attack you, " + "his strength:" + enemy.getStrength() + gre + "\n" + Component.RUN.getValue() + def+" or " + gre+Component.FIGHT.getValue() + def;
        }
        else
            return "Nothing Happen Here"+ def +"\n" + "you are on : " + hero.getX() + " " + hero.getY() + gre +"\n"+ Component.WEST.getValue()+ def + " or " +gre + Component.EAST.getValue()+def + " or " +gre + Component.SOUTH.getValue() +def + " or " +gre+ Component.NORTH.getValue() + def;
    }

    private void createHero(String name) {
        Hero hero;
        if (name.equals(Component.FIRST_HERO.getProp())){
            hero = HeroFactory.createHero(Component.FIRST_HERO);
        }else if (name.equals(Component.SECOND_HERO.getProp())){
            hero = HeroFactory.createHero(Component.SECOND_HERO);
        } else if (name.equals(Component.THIRD_HERO.getProp())){
            hero = HeroFactory.createHero(Component.THIRD_HERO);
        } else {
            hero = HeroFactory.createHero(Component.FIRST_HERO, name);
        }
        user.setHero(hero);
    }

    private void createMap(){
        int size =  (user.getHero().getLevel()-1)*5+10-(user.getHero().getLevel()%2);
        map = new MapCell[size][size];

        int i = 0, j = 0;
        while (i < map.length){
            j = 0;
            while (j < map[i].length){
                map[i][j]=new MapCell();
                j++;
            }
            i++;
        }
        user.setMap(map);
        user.getHero().setX(size / 2 + 1);
        user.getHero().setY(size / 2 + 1);
        generateEnemy(size);
        generateEnemy(size);
        generateEnemy(size);
        generateEnemy(size);
        generateEnemy(size);

        System.out.println("createMap with size " + size);
    }

    private void generateEnemy(int size) {
        int x = 0;
        int y;
        while (x < size){
            y = getRandom(size);
                 Enemy enemy = EnemyFactory.createEnemy(x, user.getHero().getLevel());
                 map[x][y].setEnemy(enemy);
            x++;
        }
    }

    private int getRandom(int max){
        Random random = new Random();
        return random.nextInt(max);
    }
}
