package client.controllers;

import client.view.Menu;
import client.view.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {
    private ContentController contentController;
    private MenuController menuController = new MenuController(new Menu(),this);
    private View view;

    public Controller(View view) {
        this.view = view;
        view.setController(this);
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public void setContentController(ContentController contentController) {
        this.contentController = contentController;
    }
    public void displayMenu(){
        view.displayComponent(menuController.getMenu());
    }
    public void showGame(){
        view.addKeyListener(contentController);
        view.displayComponent(contentController.getContentView());
    }
    public static void main(String[] args) {
        View view = new View("Pong");
        Controller controller = new Controller(view);
        controller.displayMenu();
    }
}
