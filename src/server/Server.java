package server;

import server.connection.Connection;
import server.connection.DataPack;
import server.connection.Message;
import server.game.Player;
import server.game.SerializableGameField;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable{
    private ServerSocket serverSocket;
    private Connection connection;

    private SerializableGameField gameField = new SerializableGameField();
    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public SerializableGameField getGameField() {
        return gameField;
    }

    @Override
    public void run() {
        while (!acceptConnection());
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()){
                    try {
                        Object received = connection.receive();
                        if(received instanceof Player.Direction){
                            synchronized (gameField){
                                gameField.getRemotePlayer().setDirection((Player.Direction) received);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        gameField.setSpeed(10,5);
        while (!Thread.currentThread().isInterrupted()){
            try {
                synchronized (gameField){
                    gameField.move();
                    connection.send(DataPack.extractDataPackFormGameField(gameField));
                    gameField.notify();
                }
                Thread.sleep(10);
            } catch (IOException | InterruptedException e) {
                break;
            }
        }
    }
    private boolean acceptConnection() {
        try {
            connection = new Connection(serverSocket.accept());
            while (true) {
                Object received = connection.receive();
                if (received instanceof Message) {
                    Message message = (Message) received;
                    switch (message) {
                        case NEW:
                            connection.send(Message.CONFIRM);
                            break;
                        case ASK_FOR_GAME_FIELD:
                            return true;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }
}
