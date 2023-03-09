package client.controllers;

import client.view.Content;
import client.view.Menu;
import server.Connection;
import server.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MenuController {
    private Menu menu;
    private Controller mainController;

    public MenuController(Menu menu,Controller mainController) {
        this.menu = menu;
        menu.setController(this);
        this.mainController = mainController;
    }
    public Menu getMenu(){
        menu.displayMainMenu();
        return menu;
    }
    public void onJoinGameClick(ActionEvent event){
        String address = askForAddress();
        if(address == null) return;
        int port = askForPort();
        if(port < 0) return;
        try {
            new ContentControllerRemote(new Content(),new Connection(new Socket(address,port)),mainController);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void onHostGameClick(ActionEvent event){
       while (true){
           int port = askForPort();
           if(port < 0)return;
           try {
               new ContentControllerLocal(new Content(),mainController,port);
               return;
           }catch (NumberFormatException e){
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
    }
    private String askForAddress(){
            String address = JOptionPane.showInputDialog(
                    menu,
                    "Enter server address",
                    "Connection to server",
                    JOptionPane.QUESTION_MESSAGE
            );
            return address;
    }
    private int askForPort(){
        while (true){
            String portString = JOptionPane.showInputDialog(
                    menu,
                    "Enter port",
                    "Server configuration",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (portString == null) return -1;
            try {
                return Integer.valueOf(portString.trim());
            }catch (NumberFormatException e){

            }
        }
    }
}
