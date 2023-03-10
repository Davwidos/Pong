package server.game;

import java.awt.*;
import java.util.Objects;

public class Player extends SerializableGameObject{
    public enum Direction{
        UP(270),
        DOWN(90);
        private final double angle;

        Direction(double angle) {
            this.angle = angle;
        }

        public double getAngle() {
            return angle;
        }
    }
    private static int CURRENT_ID = 0;
    private final int id;
    private Direction direction;
    private int score;
    public Player(Rectangle rectangle) {
        super(rectangle);
        setDirection(Direction.DOWN);
        id = CURRENT_ID++;
    }

    public int getId() {
        return id;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        setDirection(direction.getAngle());
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(){
        score++;
    }

    public void setOppositeDirection(){
        switch (direction){
            case UP : setDirection(Direction.DOWN);break;
            case DOWN : setDirection(Direction.UP);break;
        }
    }

    @Override
    public void move() {
        if(checkOnYBorderCollisions()) setOppositeDirection();
        super.move();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
