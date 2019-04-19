package com.coleksii.swingy;


import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.User;
import com.coleksii.swingy.services.GameEngineService;

public class Main {
    public static void main(String[] args){

        User user = new User();
        user.setState(State.NEW_GAME);

        GameEngineService engine = new GameEngineService(user);

        GameGui gameGui = new GameGui(engine);
        gameGui.start(user);
    }
}
