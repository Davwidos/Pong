package server.game;

import java.util.concurrent.ThreadLocalRandom;

public class Ball extends SerializableGameObject{
    public static enum Direction{
        LEFT(180),
        RIGHT(0);
        private final double angle;
        Direction(int angle){
            this.angle = angle;
        }
        public double getRandomAngle(){
            return angle + ThreadLocalRandom.current().nextDouble()*90-45;
        }
    }
    private Direction direction;
    public Ball() {
        super(20,0,20,20);
        setDirection(Direction.RIGHT);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        setDirection(direction.getRandomAngle());
    }

    @Override
    public void move() {
        if(checkOnYBorderCollisions()) reflection();
        super.move();
    }
    //temporary method
    private void reflection(){
        setDirection(-getDirection());
    }

}