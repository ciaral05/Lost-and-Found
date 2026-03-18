package lostandfoundapp;

public class Message {

    private int messageId;
    private String sender;
    private String content;

    public Message(int messageId, String sender, String content) {
        this.messageId = messageId;
        this.sender = sender;
        this.content = content;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public boolean matchesKeyword(String keyword) {
        return content.toLowerCase().contains(keyword.toLowerCase());
    }
}
