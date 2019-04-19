package com.coleksii.swingy.services;

import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.Hero;
import com.coleksii.swingy.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameEngineService {
    @NonNull
    private User user;

    public String proccess(String command){
        if (user.getState() == State.NEW_GAME) {
            user.setState(State.CHOICE);
            return "Welcome to My Awesome Game!\nSelect options to create hero!";
        } else if (user.getState() == State.CHOICE && command.equals(Component.CHOOSE_HERO_BUTTON.getValue())) {
            user.setState(State.CHOOSE_HERO);
            return "Choose your hero!";
        } else if (command.equals(Component.CREATE_HERO_BUTTON.getValue())){
            user.setState(State.CREATE_HERO_NAME);
            return "Please input name";
        } else if (user.getState() == State.CREATE_HERO_NAME || user.getState() == State.CHOOSE_HERO) {
            user.setState(State.START_GAME);
            createHero(command);
            return "So you are in the center of map choose your direction";
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
}
