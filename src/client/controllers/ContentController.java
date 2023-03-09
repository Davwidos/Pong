package client.controllers;

import client.view.Content;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class ContentController implements KeyListener {
    protected Content contentView;
    protected Controller mainController;
    protected ContentController(Content contentView,Controller mainController) {
        this.contentView = contentView;
        contentView.setController(this);
        this.mainController = mainController;
        mainController.setContentController(this);
    }
    public Content getContentView(){
        return contentView;
    }
    public abstract void moveUp();
    public abstract void moveDown();

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'w' : moveUp();break;
            case 's' : moveDown();break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
