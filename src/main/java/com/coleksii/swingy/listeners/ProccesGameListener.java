package com.coleksii.swingy.listeners;

import com.coleksii.swingy.RedactorForHtml;
import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.User;
import com.coleksii.swingy.services.GameEngineService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by coleksii on 4/20/19.
 */
@RequiredArgsConstructor
public class ProccesGameListener implements ActionListener {
    @NonNull
    private Map<Component, JComponent> map;
    @NonNull
    private User user;
    @NonNull
    private GameEngineService engine;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (user.getState() == State.GAME_PROCCESS){
            System.out.println(e.getActionCommand());
            String message = engine.proccess(e.getActionCommand());

            JLabel textlabel = (JLabel) map.get(Component.TEXT_LABEL);
            textlabel.setText(RedactorForHtml.redact(message));
            textlabel.setVisible(true);

            JLabel herStat = (JLabel)map.get(Component.HERO_STAT);
            herStat.setText(RedactorForHtml.redact(user.getHero().getStats()));


        }
    }

    private void erase(){
        JComponent component;
        for (Map.Entry<Component, JComponent> componentJComponentEntry : map.entrySet()) {
            component = (JComponent) ((Map.Entry) componentJComponentEntry).getValue();
            component.setVisible(false);
        }
    }
}
