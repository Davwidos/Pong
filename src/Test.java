import server.connection.Connection;
import server.connection.Message;

import java.io.IOException;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Connection connection = new Connection(new Socket("localhost",1515));
        connection.send(Message.NEW);
        Object received = connection.receive();
        if((received instanceof Message) && ((Message)received).equals(Message.CONFIRM) )
            connection.send(Message.ASK_FOR_GAME_FIELD);
        received = connection.receive();
        while (true);
    }
}
