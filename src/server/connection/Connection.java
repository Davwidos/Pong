package server.connection;

import java.io.*;
import java.net.Socket;

public class Connection implements Closeable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
    public void send(Object object) throws IOException {
        synchronized (out){
            out.writeObject(object);
        }
    }
    public Object receive() throws IOException, ClassNotFoundException {
        synchronized (in){
            return in.readObject();
        }
    }
    @Override
    public void close() throws IOException {
        socket.close();
        in.close();
        out.close();
    }
}
