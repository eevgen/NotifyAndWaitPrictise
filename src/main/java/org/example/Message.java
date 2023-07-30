package org.example;

import java.util.Objects;

public class Message {

    private final String messageContent;

    public Message(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContent() {
        return messageContent;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(messageContent);
    }

    @Override
    public String toString() {
        return messageContent.getClass().getName() + "[" + messageContent + "]";
    }
}
