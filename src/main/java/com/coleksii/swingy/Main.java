package com.coleksii.swingy;


import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.User;
import com.coleksii.swingy.services.GameEngineProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        User user = new User();
        user.setState(State.NEW_GAME);


        GameEngineProcessor engine = new GameEngineProcessor(user);

        if (args[0].equals("gui")) {
            GameGui gameGui = new GameGui(engine);
            gameGui.start(user);
        } else if (args[0].equals("console")){

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println(engine.proccess(""));
            while ((line = reader.readLine()) != null ){
                if (line.toLowerCase().equals("exit"))
                    break;
                System.out.println(engine.proccess(line));
            }
        } else {
            System.out.println("Have no valid argument");
        }
    }
}
