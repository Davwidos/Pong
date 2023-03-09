package client.controllers;

import client.view.Content;
import server.*;

import javax.swing.text.GapContent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ContentControllerRemote extends ContentController{
    private Connection connection;
    protected ContentControllerRemote(Content contentView,Connection connection, Controller mainController) throws IOException, ClassNotFoundException {
        super(contentView, mainController);
        this.connection = connection;
        while (!establishConnection());
        startReceiverThread();
        connection.send(new Message(Message.MessageType.ASK_FOR_GAME_FIELD));
        mainController.showGame();

    }
    private boolean establishConnection() throws IOException, ClassNotFoundException {
        connection.send(new Message(Message.MessageType.NEW));
            Object received = connection.receive();
            if(received instanceof Message) {
                Message message = (Message) received;
                if(message.getType().equals(Message.MessageType.CONFIRM)) return true;
            }
        return false;
    }
    private void startReceiverThread(){
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Object received = connection.receive();
                    if(received instanceof DataPack){
                        contentView.update(((DataPack)received).getData());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void moveUp() {
        try {
            connection.send(Player.Direction.UP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void moveDown() {
        try {
            connection.send(Player.Direction.DOWN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
