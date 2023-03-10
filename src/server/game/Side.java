package server.game;

import constants.Constants;

public enum Side {
    LEFT,
    RIGHT;
    public static Side getSideOfObject(SerializableGameObject object){
        if(object.getX() < Constants.WIDTH/2) return LEFT;
        return RIGHT;
    }
}
