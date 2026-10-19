package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @BeforeEach
    public void setup() {
        Message.resetTotalMessagess();
    }

    @Test
    public void testRecipientNumberSuccess() {
        // Test Case 1: valid international recipient
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientNumberFailure() {
        // Test Case 2: missing international code
        Message msg = new Message("0012345678", 1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashGeneration() {
        // Message ID starts with "00", message index 0, first word "Hi", last word "tonight"
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageLengthSuccess() {
        String validText = "Short valid message well within limits.";
        Message msg = new Message("0012345678", 0, "+27718693002", validText);
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // 260 characters (exceeds 250 by 10)
        String longText = "A".repeat(260);
        Message msg = new Message("0012345678", 0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.validateMessageLength());
    }

    @Test
    public void testMessageSentStatus() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hello World");

        assertEquals("Message successfully sent.", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
        assertEquals("Message successfully stored.", msg.SentMessage(3));
        assertEquals(1, Message.returnTotalMessagess());
    }
}