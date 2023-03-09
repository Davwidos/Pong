package server;

import constants.Constants;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class SerializableGameObject implements Serializable {
    //position of object
    private Rectangle rectangle;
    protected double direction;//angle in degrees;
    protected double speed;

    public SerializableGameObject(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public SerializableGameObject(int x, int y,int width,int height) {
        this(new Rectangle(x,y,width,height));

    }
    public void move(){
        double directionInRad = Math.toRadians(direction);
        int newX = (int) Math.round(rectangle.getX()+Math.cos(directionInRad) * speed);
        int newY = (int) Math.round(rectangle.getY()+Math.sin(directionInRad) * speed);
        rectangle.setLocation(newX,newY);
    }

    protected void setDirection(double direction) {
        if(direction >= 360) this.direction = direction - 360;
        else if(direction < 0) this.direction = direction + 360;
        else this.direction = direction;
    }

    public double getDirection() {
        return direction;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getX() {
        return rectangle.getX();
    }

    public double getY() {
        return rectangle.getY();
    }

    public double getWidth() {
        return rectangle.getWidth();
    }

    public double getHeight() {
        return rectangle.getHeight();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean checkCollisions(SerializableGameObject object){
        return rectangle.intersects(object.getRectangle());
    }
    public boolean checkOnXBorderCollisions(){
        return getX() < 0 || getX() + getWidth() > Constants.WIDTH;
    }
    public boolean checkOnYBorderCollisions(){
        return getY() < 0 || getY() + getHeight() > Constants.HEIGHT;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableGameObject object = (SerializableGameObject) o;
        return Double.compare(object.direction, direction) == 0 && Double.compare(object.speed, speed) == 0 && rectangle.equals(object.rectangle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rectangle, direction, speed);
    }
}
