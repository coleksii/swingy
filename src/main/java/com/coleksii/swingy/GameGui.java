package com.coleksii.swingy;

import com.coleksii.swingy.enums.Component;
import com.coleksii.swingy.enums.Property;
import com.coleksii.swingy.listeners.ProccesGameListener;
import com.coleksii.swingy.listeners.StartGameListener;
import com.coleksii.swingy.model.User;
import com.coleksii.swingy.services.GameEngineService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Map;

import static com.coleksii.swingy.RedactorForHtml.redact;

@RequiredArgsConstructor
public class GameGui {

    private Map<Component, JComponent> componentMap;

    private JFrame gameFrame;
    private User user;
    @NonNull
    private GameEngineService engine;

    public void start(User user){
        this.user = user;
        intialize();

    }

    private void intialize() {

        gameFrame = new JFrame("GameGui");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setBounds(100, 100, Property.WEIGHT.getInteger(), Property.HEIGHT.getInteger());
        gameFrame.setResizable(false);
        gameFrame.getContentPane().setLayout(null);

        componentMap = new EnumMap<>(Component.class);

        ActionListener listener = new StartGameListener(gameFrame, componentMap, user, engine);

        JLabel tmpLabel = new JLabel(new ImageIcon("./images/Webp.net-resizeimage.png"));
        tmpLabel.setSize(200, 200);
        tmpLabel.setLocation(200, 100);
        addComponent(Component.FIRST_IMAGINE, tmpLabel);

        JLabel textLabel = new JLabel(Component.TEXT_LABEL.getValue());
        textLabel.setSize(200, 200);
        textLabel.setLocation(235, -20);
        addComponent(Component.TEXT_LABEL, textLabel);

        JButton buttonChooseHero = new JButton(Component.CHOOSE_HERO_BUTTON.getValue());
        buttonChooseHero.setSize(150, 50);
        buttonChooseHero.addActionListener(listener);
        buttonChooseHero.setVisible(false);
        addComponent(Component.CHOOSE_HERO_BUTTON, buttonChooseHero);


        JButton buttonCreateHero = new JButton(Component.CREATE_HERO_BUTTON.getValue());
        buttonCreateHero.setSize(150, 50);
        buttonCreateHero.addActionListener(listener);
        buttonCreateHero.setVisible(false);
        addComponent(Component.CREATE_HERO_BUTTON, buttonCreateHero);

        JButton tmpButton = new JButton(Component.PLAY_BUTTON.getValue());
        tmpButton.setSize(100, 50);
        tmpButton.setLocation(250, 350);
        tmpButton.addActionListener(listener);
        addComponent(Component.PLAY_BUTTON, tmpButton);

        JLabel textLabelChooseHero = new JLabel(Component.CHOOSE_HERO_TEXT.getValue());
        textLabelChooseHero.setSize(200, 200);
        textLabelChooseHero.setLocation(235, -50);
        textLabelChooseHero.setVisible(false);
        addComponent(Component.CHOOSE_HERO_TEXT, textLabelChooseHero);

        JTextField inputText = new JTextField(Component.INPUT_TEXT.getValue(), 16);
        inputText.setSize(300, 50);
        inputText.setVisible(false);
        addComponent(Component.INPUT_TEXT, inputText);

        JButton submitTextButton = new JButton(Component.SUBMIT_BUTTON.getValue());
        submitTextButton.setSize(100, 50);
        submitTextButton.setVisible(false);
        submitTextButton.addActionListener(listener);
        addComponent(Component.SUBMIT_BUTTON, submitTextButton);

        JLabel heroStat = new JLabel();
        heroStat.setSize(200, 200);
        heroStat.setVisible(false);
        addComponent(Component.HERO_STAT, heroStat);

        createHeroScreen(listener);
        createDirectionButton();
        gameFrame.setVisible(true);
    }

    private void createDirectionButton() {
        ProccesGameListener listener = new ProccesGameListener(componentMap, user, engine);
        JButton north = new JButton(Component.NORTH.getValue());
        north.setVisible(false);
        north.setLocation(350, 350);
        north.setSize(100, 20);
        north.addActionListener(listener);
        north.setVisible(false);
        addComponent(Component.NORTH, north);

        JButton west = new JButton(Component.WEST.getValue());
        west.setVisible(false);
        west.addActionListener(listener);
        addComponent(Component.WEST, west);
        west.setLocation(420, 400);
        west.setSize(100, 20);

        JButton east = new JButton(Component.EAST.getValue());
        east.setVisible(false);
        east.addActionListener(listener);
        east.setLocation(280, 400);
        east.setSize(100, 20);
        addComponent(Component.EAST, east);

        JButton south = new JButton(Component.SOUTH.getValue());
        south.setVisible(false);
        south.addActionListener(listener);
        south.setLocation(350, 450);
        south.setSize(100, 20);
        addComponent(Component.SOUTH, south);
    }

    private void createHeroScreen(ActionListener listener) {
        JLabel firstHero = new JLabel(new ImageIcon("./images/pixel/Hero/hero01.png"));
        firstHero.setSize(180, 200);
        firstHero.setVisible(false);
        addComponent(Component.FIRST_HERO, firstHero);

        JLabel firstHeroDescribes = new JLabel(redact(Component.FIRST_HERO_DESCR.getProp()));
        firstHeroDescribes.setSize(180, 60);
        firstHeroDescribes.setVisible(false);
        addComponent(Component.FIRST_HERO_DESCR, firstHeroDescribes);

        JButton buttonFirsHero = new JButton(Component.FIRST_HERO_BUTTON.getProp());
        buttonFirsHero.setSize(160, 20);
        buttonFirsHero.setVisible(false);
        buttonFirsHero.addActionListener(listener);
        addComponent(Component.FIRST_HERO_BUTTON, buttonFirsHero);

        JLabel secondHero = new JLabel(new ImageIcon("./images/pixel/Hero/hero02.png"));
        secondHero.setSize(180, 200);
        secondHero.setVisible(false);
        addComponent(Component.SECOND_HERO, secondHero);

        JLabel secondHeroDescribes = new JLabel(redact(Component.SECOND_HERO_DESCR.getProp()));
        secondHeroDescribes.setSize(180, 60);
        secondHeroDescribes.setVisible(false);
        addComponent(Component.SECOND_HERO_DESCR, secondHeroDescribes);

        JButton buttonSecondHero = new JButton(Component.SECOND_HERO_BUTTON.getProp());
        buttonSecondHero.setSize(160, 20);
        buttonSecondHero.setVisible(false);
        buttonSecondHero.addActionListener(listener);
        addComponent(Component.SECOND_HERO_BUTTON, buttonSecondHero);

        JLabel thirdHero = new JLabel(new ImageIcon("./images/pixel/Hero/hero03.png"));
        thirdHero.setSize(180, 200);
        thirdHero.setVisible(false);
        addComponent(Component.THIRD_HERO, thirdHero);

        JLabel thirdHeroDescribes = new JLabel(redact(Component.THIRD_HERO_DESCR.getProp()));
        thirdHeroDescribes.setSize(180, 60);
        thirdHeroDescribes.setVisible(false);
        addComponent(Component.THIRD_HERO_DESCR, thirdHeroDescribes);

        JButton buttonThirdHero = new JButton(Component.THIRD_HERO_BUTTON.getProp());
        buttonThirdHero.setSize(160, 20);
        buttonThirdHero.setVisible(false);
        buttonThirdHero.addActionListener(listener);
        addComponent(Component.THIRD_HERO_BUTTON, buttonThirdHero);

    }



    private void addComponent(Component componentEnum, JComponent component){
        componentMap.put(componentEnum, component);
        gameFrame.getContentPane().add(component);
    }
}