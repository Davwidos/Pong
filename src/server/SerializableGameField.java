package server;

import constants.Constants;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SerializableGameField implements Serializable {

    protected Player localPlayer;
    protected Player remotePlayer;
    protected Ball ball;
    public SerializableGameField() {
        localPlayer = new Player(new Rectangle());
        remotePlayer = new Player(new Rectangle());
        ball = new Ball();
        setUpDefaultGameField();
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public Player getRemotePlayer() {
        return remotePlayer;
    }

    public Set<Rectangle> getGameObjects(){
        Set<Rectangle> rectangles = new HashSet<>();
        rectangles.add(new Rectangle(ball.getRectangle()));
        rectangles.add(new Rectangle(localPlayer.getRectangle()));
        rectangles.add(new Rectangle(remotePlayer.getRectangle()));
        return rectangles;
    }
    public void move(){
        if(ball.checkCollisions(localPlayer)) ball.setDirection(Ball.Direction.RIGHT);
        if(ball.checkCollisions(remotePlayer)) ball.setDirection(Ball.Direction.LEFT);
        ball.move();
        localPlayer.move();
        remotePlayer.move();
    }
    public void setSpeed(int ballSpeed,int playerSpeed){
        ball.setSpeed(ballSpeed);
        localPlayer.setSpeed(playerSpeed);
        remotePlayer.setSpeed(playerSpeed);
    }
    public void setUpDefaultGameField(){
        localPlayer.setRectangle(new Rectangle(0,0,20,60));
        remotePlayer.setRectangle(new Rectangle(Constants.WIDTH-20,0,20,60));
        ball.setRectangle(new Rectangle(20,0,20,20));
    }
}
