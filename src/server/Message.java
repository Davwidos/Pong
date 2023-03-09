package server;

import java.io.Serializable;

public class Message implements Serializable {
    public enum MessageType {
        NEW,
        CONFIRM,
        ASK_FOR_GAME_FIELD;
    }
    private final MessageType type;

    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }
}
