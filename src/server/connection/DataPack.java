package server.connection;

import server.game.SerializableGameField;

import java.awt.*;
import java.io.Serializable;
import java.util.Set;

public class DataPack implements Serializable {
    public static DataPack extractDataPackFormGameField(SerializableGameField gameField){
        Set<Rectangle> data = gameField.getGameObjects();
        int scoreLocal = gameField.getLocalPlayer().getScore();
        int scoreRemote = gameField.getRemotePlayer().getScore();
        return new DataPack(data,scoreLocal,scoreRemote);
    }
    private final Set<Rectangle> data;
    private final int scoreOfLocalPlayer;
    private final int scoreOFRemotePlayer;

    public DataPack(Set<Rectangle> data,int scoreOfLocalPlayer,int scoreOFRemotePlayer) {
        this.data = data;
        this.scoreOfLocalPlayer = scoreOfLocalPlayer;
        this.scoreOFRemotePlayer = scoreOFRemotePlayer;
    }

    public Set<Rectangle> getData() {
        return data;
    }

    public int getScoreOfLocalPlayer() {
        return scoreOfLocalPlayer;
    }

    public int getScoreOFRemotePlayer() {
        return scoreOFRemotePlayer;
    }
}
