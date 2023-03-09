package server;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DataPack implements Serializable {
    private final Set<Rectangle> data;

    public DataPack(Set<Rectangle> data) {
        this.data = data;
    }

    public Set<Rectangle> getData() {
        return data;
    }
}
