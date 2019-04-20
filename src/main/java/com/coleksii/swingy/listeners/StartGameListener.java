package com.coleksii.swingy.listeners;

import com.coleksii.swingy.RedactorForHtml;
import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.Hero;
import com.coleksii.swingy.model.User;
import com.coleksii.swingy.services.GameEngineService;
import lombok.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

@RequiredArgsConstructor
@NoArgsConstructor
public class StartGameListener implements ActionListener {

    private boolean visible = true;
    @NonNull
    private JFrame gameFrame;
    @NonNull
    private Map<Component, JComponent> map;
    @NonNull
    private User user;
    @NonNull
    private GameEngineService engine;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        String command = e.getActionCommand();
        erase();

        if (command.equals(Component.PLAY_BUTTON.getValue())){
            showMessageAndProccesGame(command);

            JButton buttonCreateHero = (JButton) map.get(Component.CREATE_HERO_BUTTON);
            buttonCreateHero.setLocation(150, 350);
            buttonCreateHero.setVisible(true);

            JButton buttonChooseHero = (JButton) map.get(Component.CHOOSE_HERO_BUTTON);
            buttonChooseHero.setLocation(350, 350);
            buttonChooseHero.setVisible(true);

        } else if(command.equals(Component.CREATE_HERO_BUTTON.getValue())){
            String message = engine.proccess(command);

            JButton submit = (JButton) map.get(Component.SUBMIT_BUTTON);
            submit.setVisible(true);
            submit.setLocation(400, 50);

            JTextField textField = (JTextField) map.get(Component.INPUT_TEXT);
            textField.setLocation(100, 50);
            textField.setVisible(true);

        }else if (command.equals(Component.CHOOSE_HERO_BUTTON.getValue())) {


            showMessageAndProccesGame(command);

            JComponent firstHero = map.get(Component.FIRST_HERO);
            firstHero.setVisible(true);
            firstHero.setLocation(20, 100);
            JComponent secondHero = map.get(Component.SECOND_HERO);
            secondHero.setVisible(true);
            secondHero.setLocation(220, 100);
            JComponent thirdHero = map.get(Component.THIRD_HERO);
            thirdHero.setVisible(true);
            thirdHero.setLocation(420, 100);
            JComponent firstHeroDesc = map.get(Component.FIRST_HERO_DESCR);
            firstHeroDesc.setVisible(true);
            firstHeroDesc.setLocation(20, 300);
            JComponent secondHeroDesc = map.get(Component.SECOND_HERO_DESCR);
            secondHeroDesc.setVisible(true);
            secondHeroDesc.setLocation(220, 300);
            JComponent thirdHeroDesc = map.get(Component.THIRD_HERO_DESCR);
            thirdHeroDesc.setVisible(true);
            thirdHeroDesc.setLocation(420, 300);
            JComponent buttonFirsHero = map.get(Component.FIRST_HERO_BUTTON);
            buttonFirsHero.setVisible(true);
            buttonFirsHero.setLocation(20, 400);
            JComponent buttonSecondHero = map.get(Component.SECOND_HERO_BUTTON);
            buttonSecondHero.setVisible(true);
            buttonSecondHero.setLocation(220, 400);
            JComponent buttonThirdHero = map.get(Component.THIRD_HERO_BUTTON);
            buttonThirdHero.setVisible(true);
            buttonThirdHero.setLocation(420, 400);

        }else if (command.equals(Component.SUBMIT_BUTTON.getValue())) {
                JTextField textField = (JTextField) map.get(Component.INPUT_TEXT);
                String message = engine.proccess(textField.getText());

                JComponent firstHero = map.get(Component.FIRST_HERO);
                firstHero.setLocation(10, 50);
                firstHero.setVisible(true);
                createHeroStatMessageButton(message);

        }else if (user.getState() == State.CHOOSE_HERO){

            Component component;
            JComponent jComponentHero;
            if (e.getActionCommand().equals(Component.FIRST_HERO_BUTTON.getProp())){
                component = Component.FIRST_HERO;
            } else if (e.getActionCommand().equals(Component.SECOND_HERO_BUTTON.getProp())){
                component = Component.SECOND_HERO;
            } else {
                component = Component.THIRD_HERO;
            }
            jComponentHero = map.get(component);
            jComponentHero.setLocation(10, 50);
            jComponentHero.setVisible(true);
            String message = engine.proccess(e.getActionCommand());
            createHeroStatMessageButton(message);
        }
    }

    private void showMessageAndProccesGame(String command) {
        String message = engine.proccess(command);

        JLabel textChooseHero = (JLabel) map.get(Component.CHOOSE_HERO_TEXT);
        textChooseHero.setText(RedactorForHtml.redact(message));
        textChooseHero.setVisible(true);
    }

    private void createHeroStatMessageButton(String message) {
        Hero hero = user.getHero();
        JLabel jLabel = (JLabel) map.get(Component.HERO_STAT);
        jLabel.setText(RedactorForHtml.redact(hero.getStats()));
        jLabel.setLocation(10, 250);
        jLabel.setVisible(true);


        JLabel textlabel = (JLabel) map.get(Component.TEXT_LABEL);
        textlabel.setText(RedactorForHtml.redact(message));
        textlabel.setVisible(true);

        JButton north = (JButton) map.get(Component.NORTH);
        north.setVisible(true);
        JButton west = (JButton) map.get(Component.WEST);
        west.setVisible(true);
        JButton east = (JButton) map.get(Component.EAST);
        east.setVisible(true);
        JButton south = (JButton) map.get(Component.SOUTH);
        south.setVisible(true);


    }

    private void erase(){
        JComponent component;
        for (Map.Entry<Component, JComponent> componentJComponentEntry : map.entrySet()) {
            component = (JComponent) ((Map.Entry) componentJComponentEntry).getValue();
            component.setVisible(false);
        }
    }
}
