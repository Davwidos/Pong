import client.controllers.MenuController;
import client.view.Menu;
import client.view.View;
import server.Connection;
import server.Message;
import server.SerializableGameField;

import java.io.IOException;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Connection connection = new Connection(new Socket("localhost",1515));
        connection.send(new Message(Message.MessageType.NEW));
        Object received = connection.receive();
        if((received instanceof Message) && ((Message)received).getType().equals(Message.MessageType.CONFIRM) )
            connection.send(new Message(Message.MessageType.ASK_FOR_GAME_FIELD));
        received = connection.receive();
        while (true);
    }
}
