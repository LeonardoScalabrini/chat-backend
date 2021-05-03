package com.chat.models;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MessageIdTest {

    private final MessageId messageId = new MessageId();

    @Test
    public void getId() {
        final String REGEX_UUID = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$";
        final Pattern pattern = Pattern.compile(REGEX_UUID);
        assertTrue(pattern.matcher(messageId.getId()).find());
    }

    @Test
    public void testEquals() {
        MessageId outher = new MessageId();
        assertNotEquals(outher, messageId);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(new Integer(messageId.hashCode()), new Integer(0));
    }
}