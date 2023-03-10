package client.controllers;

import client.view.Content;
import server.connection.Connection;
import server.connection.DataPack;
import server.connection.Message;
import server.game.Player;

import java.io.IOException;

public class ContentControllerRemote extends ContentController{
    private final Connection connection;
    protected ContentControllerRemote(Content contentView,Connection connection, Controller mainController) throws IOException, ClassNotFoundException {
        super(contentView, mainController);
        this.connection = connection;
        while (!establishConnection());
        startReceiverThread();
        connection.send(Message.ASK_FOR_GAME_FIELD);
        mainController.showGame();

    }
    private boolean establishConnection() throws IOException, ClassNotFoundException {
        connection.send(Message.NEW);
            Object received = connection.receive();
            if(received instanceof Message) {
                Message message = (Message) received;
                return message.equals(Message.CONFIRM);
            }
        return false;
    }
    private void startReceiverThread(){
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    Object received = connection.receive();
                    if(received instanceof DataPack){
                        DataPack dataPack = (DataPack)received;
                        contentView.update(dataPack.getData(),dataPack.getScoreOfLocalPlayer(),dataPack.getScoreOFRemotePlayer());
                    }
                } catch (IOException | ClassNotFoundException e) {
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
