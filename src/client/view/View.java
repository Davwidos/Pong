package client.view;

import client.controllers.ContentController;
import client.controllers.Controller;
import constants.Constants;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private JComponent component;

    public View(String title) throws HeadlessException {
        super(title);
        setVisible(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void displayComponent(JComponent component){
        if(this.component != null) remove(this.component);
        this.component = component;
        getContentPane().add(component);
        setVisible(true);
        pack();
    }
}
