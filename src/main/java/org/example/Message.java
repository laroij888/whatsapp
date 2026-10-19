package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalSentMessages = 0;

    public Message() {
    }

    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // Auto-generates a 10-digit numeric Message ID
    public static String generateMessageID() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Ensures Message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // Validates recipient number: must start with '+' (international code) and standard formatting
    public String checkRecipientCell() {
        if (this.recipient != null && this.recipient.startsWith("+") && this.recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // Creates the message hash: first 2 digits of ID : message number : FIRSTLAST words in caps
    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2 || this.messageText == null || this.messageText.trim().isEmpty()) {
            return "";
        }

        String idPrefix = this.messageID.substring(0, 2);

        // Clean words and remove trailing punctuation like '?' or '.'
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        return (idPrefix + ":" + this.messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Validates that message length does not exceed 250 characters
    public String validateMessageLength() {
        if (this.messageText == null) {
            return "Message ready to send.";
        }
        int len = this.messageText.length();
        if (len <= 250) {
            return "Message ready to send.";
        } else {
            int excess = len - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Handles the sending options
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                totalSentMessages++;
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid selection.";
        }
    }

    // Stores message details into a JSON file
    public void storeMessage() {
        String jsonRecord = "{\n" +
                "  \"messageID\": \"" + escapeJson(this.messageID) + "\",\n" +
                "  \"messageHash\": \"" + escapeJson(this.messageHash) + "\",\n" +
                "  \"recipient\": \"" + escapeJson(this.recipient) + "\",\n" +
                "  \"message\": \"" + escapeJson(this.messageText) + "\"\n" +
                "}\n";

        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(jsonRecord);
        } catch (IOException e) {
            System.err.println("Error storing message: " + e.getMessage());
        }
    }

    private String escapeJson(String data) {
        if (data == null) return "";
        return data.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    // Returns formatted details of the message
    public String printMessages() {
        return "Message ID: " + this.messageID + "\n" +
                "Message Hash: " + this.messageHash + "\n" +
                "Recipient: " + this.recipient + "\n" +
                "Message: " + this.messageText;
    }

    public static int returnTotalMessagess() {
        return totalSentMessages;
    }

    public static void resetTotalMessagess() {
        totalSentMessages = 0;
    }

    // Getters and setters
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }
    public int getMessageNumber() { return messageNumber; }
    public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public String getMessageHash() { return messageHash; }
}