package client.view;

import client.controllers.MenuController;
import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static constants.Constants.*;


public class Menu extends JComponent {

    private MenuController controller;
    private Set<JButton> buttons = new HashSet<>();
    public Menu(){
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        displayMainMenu();
    }

    public void setController(MenuController controller) {
        this.controller = controller;
    }

    public void displayMainMenu(){
        clear();
        JButton button = new JButton("Join Game");
        button.setBounds(DEFAULT_BUTTON_X,BUTTON_HEIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        button.addActionListener((event)->controller.onJoinGameClick(event));
        add(button);
        buttons.add(button);

        button = new JButton("Host Game");
        button.setBounds(DEFAULT_BUTTON_X,BUTTON_HEIGHT*3,BUTTON_WIDTH,BUTTON_HEIGHT);
        button.addActionListener((event)->controller.onHostGameClick(event));
        add(button);
        buttons.add(button);
    }
    private void clear(){
        for(JButton button:buttons) {
            remove(button);
        }
        buttons.clear();
    }
}
