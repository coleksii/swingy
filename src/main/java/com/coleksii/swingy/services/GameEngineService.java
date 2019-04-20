package com.coleksii.swingy.services;

import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.Enemy;
import com.coleksii.swingy.model.Hero;
import com.coleksii.swingy.model.MapCell;
import com.coleksii.swingy.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class GameEngineService {

    MapCell[][]map;

    @NonNull
    private User user;

    public String proccess(String command){
        if (user.getState() == State.NEW_GAME) {
            user.setState(State.CHOICE);
            return "Welcome to My Awesome Game!\nSelect options to create hero!";
        } else if (user.getState() == State.CHOICE && command.equals(Component.CHOOSE_HERO_BUTTON.getValue())) {
            user.setState(State.CHOOSE_HERO);
            return "Choose your hero!";
        } else if (user.getState() == State.CHOICE &&  command.equals(Component.CREATE_HERO_BUTTON.getValue())){
            user.setState(State.CREATE_HERO_NAME);
            return "Please input name";
        } else if (user.getState() == State.CREATE_HERO_NAME || user.getState() == State.CHOOSE_HERO) {
            user.setState(State.GAME_PROCCESS);
            createHero(command);
            createMap();
            return "So you are in the center of map\nchoose your direction";
        } else if (user.getState() == State.GAME_PROCCESS){
            return "GAME";
        }
        return "Please try another command";
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
        user.setMap(map);
        generateEnemy(size);

        System.out.println("createMap with size " + size);
    }

    private void generateEnemy(int size) {
        int x = 0;
        int y;
        while (x > size){
            y = getRandom(size);
             if (y != size / 2 + 1) {
                 Enemy enemy = EnemyFactory.createEnemy(x);
                 map[x][y].setEnemy(enemy);
             }
            x++;
        }
    }

    private int getRandom(int max){
        Random random = new Random();
        return random.nextInt(max);
    }
}
