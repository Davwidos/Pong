package client.view;


import client.controllers.ContentController;
import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Content extends JComponent {
    private ContentController controller;

    private Set<Rectangle> objects = new HashSet<>();

    public Content() {
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
    }

    public void setController(ContentController controller) {
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Rectangle rect:objects){
            g.fillRect(rect.x,rect.y,rect.width,rect.height);
        }
        g.drawLine(Constants.WIDTH/2,0, Constants.WIDTH/2, Constants.HEIGHT);
        g.drawRect(0,0, Constants.WIDTH, Constants.HEIGHT);
    }
    public void update(Set<Rectangle> objects){
        this.objects = objects;
        repaint();
    }
}
