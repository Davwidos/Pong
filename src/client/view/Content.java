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
    private JLabel score;

    public Content() {
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        initScore();
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
    public void update(Set<Rectangle> objects,int scoreLocal,int scoreRemote){
        this.objects = objects;
        score.setText(String.format("%d : %d",scoreLocal,scoreRemote));
        repaint();
    }
    private void initScore(){
        score = new JLabel("0 : 0",SwingConstants.CENTER);
        score.setBounds(Constants.WIDTH/2-Constants.SCORE_BOX_SIZE/2,0,Constants.SCORE_BOX_SIZE,Constants.SCORE_BOX_SIZE);
        score.setFont(new Font("Serif", Font.PLAIN, 30));
        add(score);
    }
}
