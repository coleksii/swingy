package com.coleksii.swingy.listeners;

import com.coleksii.swingy.RedactorForHtml;
import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.State;
import com.coleksii.swingy.model.User;
import com.coleksii.swingy.services.GameEngineProcessor;
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
    private GameEngineProcessor engine;

    private JComponent enemyComponent;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (user.getState() == State.GAME_PROCESS || user.getState().equals(State.ARTIFACT)) {
            erase();
            String message = engine.proccess(e.getActionCommand());
            JLabel hero = user.getHero().getLabel();
            hero.setVisible(true);
            JComponent heroDesk = map.get(Component.HERO_STAT);
            heroDesk.setVisible(true);

            JLabel textlabel = (JLabel) map.get(Component.TEXT_LABEL);
            textlabel.setText(RedactorForHtml.redact(message));
            textlabel.setVisible(true);

            JButton west = (JButton) map.get(Component.WEST);
            west.setText(Component.WEST.getValue());
            west.setVisible(true);
            JButton east = (JButton) map.get(Component.EAST);
            east.setText(Component.EAST.getValue());
            east.setVisible(true);
            JComponent north = map.get(Component.NORTH);
            north.setVisible(true);
            JComponent south = map.get(Component.SOUTH);
            south.setVisible(true);

            JLabel herStat = (JLabel) map.get(Component.HERO_STAT);
            herStat.setText(RedactorForHtml.redact(user.getHero().getStats()));
            if (message.contains("attack")) {
                String enemyName = message.split(" attack")[0];
                west.setText(Component.FIGHT.getValue());
                east.setText(Component.RUN.getValue());
                north.setVisible(false);
                south.setVisible(false);
                enemyComponent = getEnenmyComponent(enemyName);
                enemyComponent.setVisible(true);
            }
        } else if (user.getState() == State.FIND_MONSTER) {
            erase();
            JLabel hero = user.getHero().getLabel();
            hero.setVisible(true);
            JComponent heroDesk = map.get(Component.HERO_STAT);
            heroDesk.setVisible(true);
            String message = engine.proccess(e.getActionCommand());
            JLabel textlabel = (JLabel) map.get(Component.TEXT_LABEL);
            textlabel.setText(RedactorForHtml.redact(message));
            textlabel.setVisible(true);
            enemyComponent.setVisible(true);
            JLabel herStat = (JLabel) map.get(Component.HERO_STAT);
            herStat.setText(RedactorForHtml.redact(user.getHero().getStats()));
            if (message.contains("fight")) {
                JButton west = (JButton) map.get(Component.WEST);
                west.setText(Component.FIGHT.getValue());
                west.setVisible(true);
                JButton east = (JButton) map.get(Component.EAST);
                east.setText(Component.EAST.getValue());
                east.setVisible(false);
            } else if (message.contains("artifact")){
                enemyComponent.setVisible(false);
                JButton west = (JButton) map.get(Component.WEST);
                west.setText(Component.KEEP.getValue());
                west.setVisible(true);
                JButton east = (JButton) map.get(Component.EAST);
                east.setText(Component.LEAVE.getValue());
                east.setVisible(true);
                JComponent north = map.get(Component.NORTH);
                north.setVisible(false);
                JComponent south = map.get(Component.SOUTH);
                south.setVisible(false);
            } else {
                enemyComponent.setVisible(false);
                JButton west = (JButton) map.get(Component.WEST);
                west.setText(Component.WEST.getValue());
                west.setVisible(true);
                JButton east = (JButton) map.get(Component.EAST);
                east.setText(Component.EAST.getValue());
                east.setVisible(true);
                JComponent north = map.get(Component.NORTH);
                north.setVisible(true);
                JComponent south = map.get(Component.SOUTH);
                south.setVisible(true);
            }
        }
    }

    private JComponent getEnenmyComponent(String enemyName) {
        JComponent component = null;
        if (enemyName.equals(Component.SKULL.getValue()))
            component = map.get(Component.SKULL);
        else if (enemyName.equals(Component.COLOR_SKELETON.getValue())) {
            component = map.get(Component.COLOR_SKELETON);
        } else if (enemyName.equals(Component.KENNY_SKELETON.getValue())) {
            component = map.get(Component.KENNY_SKELETON);
        } else if (enemyName.equals(Component.DRAGON.getValue())) {
            component = map.get(Component.DRAGON);
        } else if (enemyName.equals(Component.FAT_SKELETON.getValue())) {
            component = map.get(Component.FAT_SKELETON);
        } else if (enemyName.equals(Component.EXCETIPION_SKELETON.getValue())) {
            component = map.get(Component.EXCETIPION_SKELETON);
        } else if (enemyName.equals(Component.ANGTY_SKELETON.getValue())) {
            component = map.get(Component.ANGTY_SKELETON);
        } else if (enemyName.equals(Component.KNIGHT.getValue())) {
            component = map.get(Component.KNIGHT);
        } else {
            component = map.get(Component.KNIGHT);
        }
        return component;
    }

    private void erase() {
        JComponent component;
        for (Map.Entry<Component, JComponent> componentJComponentEntry : map.entrySet()) {
            component = (JComponent) ((Map.Entry) componentJComponentEntry).getValue();
            component.setVisible(false);
        }
    }
}
