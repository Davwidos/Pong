package client.controllers;

import client.view.Content;
import server.connection.DataPack;
import server.game.Player;
import server.game.SerializableGameField;
import server.Server;

import java.io.IOException;

public class ContentControllerLocal extends ContentController {
    private class GameLoop extends Thread{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                synchronized (gameField){
                    try {
                        gameField.wait();
                        DataPack dataPack = DataPack.extractDataPackFormGameField(gameField);
                        contentView.update(dataPack.getData(),dataPack.getScoreOfLocalPlayer(),dataPack.getScoreOFRemotePlayer());
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }
    private SerializableGameField gameField;
    protected ContentControllerLocal(Content contentView,Controller mainController,int port) throws IOException {
        super(contentView,mainController);
        Server server = new Server(port);
        Thread thread = new Thread(server);
        thread.setDaemon(true);
        thread.start();
        this.gameField = server.getGameField();
        GameLoop gameLoop = new GameLoop();
        gameLoop.setDaemon(true);
        gameLoop.start();
        mainController.showGame();
    }

    @Override
    public void moveUp() {
        gameField.getLocalPlayer().setDirection(Player.Direction.UP);
    }

    @Override
    public void moveDown() {
        gameField.getLocalPlayer().setDirection(Player.Direction.DOWN);
    }
}
