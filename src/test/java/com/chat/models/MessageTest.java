package com.chat.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MessageTest {

    private MessageId messageId = new MessageId();
    private Message message = new Message(messageId, "message", "chat");

    @Test
    public void getId() {
        assertEquals(messageId, message.getId());
    }

    @Test
    public void getValue() {
        assertEquals("message", message.getValue());
    }

    @Test
    public void getChat() {
        assertEquals("chat", message.getChat());
    }

    @Test
    public void testEquals() {
        Message messageOuther = new Message(messageId, "message", "chat");
        assertEquals(messageOuther, message);
        Message messageDiffMessageId = new Message(new MessageId(), "message", "chat");
        assertNotEquals(messageDiffMessageId, message);
        Message messageDiffValue = new Message(messageId, "messageDiff", "chat");
        assertNotEquals(messageDiffValue, message);
        Message messageDiffChat = new Message(messageId, "message", "chatDiff");
        assertNotEquals(messageDiffChat, message);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(new Integer(message.hashCode()), new Integer(0));
    }
}