package lostandfoundapp;

import java.util.LinkedList;

public class MessageManager implements CRUDOperations<Message> {

    private LinkedList<Message> messages;

    public MessageManager() {
        messages = new LinkedList<>();
    }

    @Override
    public void create(Message msg) {
        messages.add(msg);
    }

    @Override
    public Message read(int id) {
        for (Message msg : messages) {
            if (msg.getMessageId() == id) {
                return msg;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Message newMsg) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getMessageId() == id) {
                messages.set(i, newMsg);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        messages.removeIf(msg -> msg.getMessageId() == id);
    }

    public LinkedList<Message> getAllMessages() {
        return messages;
    }
}
