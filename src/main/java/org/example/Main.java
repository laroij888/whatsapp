package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login auth = new Login();

        // Account registration setup
        auth.registerUser("kyl_1", "Ch&&sec@ke99!", "Keegan", "Govender");

        // 1. User must login successfully first
        System.out.println("=== QuickChat User Login ===");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            loggedIn = auth.loginUser(username, password);
            System.out.println(auth.returnLoginStatus(loggedIn));
        }

        // 2. Display Welcome message
        System.out.println("\nWelcome to QuickChat.");

        // 3. Numeric Menu loop
        boolean running = true;
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Enter choice (1-3): ");

            String choiceStr = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleSendMessages(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    System.out.println("Total messages sent during session: " + Message.returnTotalMessagess());
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
        scanner.close();
    }

    private static void handleSendMessages(Scanner scanner) {
        System.out.print("How many messages would you like to enter? ");
        int count = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Entering Message " + (i + 1) + " of " + count + " ---");

            String recipient = "";
            while (true) {
                System.out.print("Enter recipient cell number (with international code, e.g. +27...): ");
                recipient = scanner.nextLine().trim();
                Message tempMsg = new Message("", i, recipient, "");
                String res = tempMsg.checkRecipientCell();
                System.out.println(res);
                if (res.contains("successfully")) {
                    break;
                }
            }

            String text = "";
            while (true) {
                System.out.print("Enter message (max 250 characters): ");
                text = scanner.nextLine();
                Message tempMsg = new Message("", i, recipient, text);
                String validation = tempMsg.validateMessageLength();
                if (validation.equals("Message ready to send.")) {
                    System.out.println("Message ready to send.");
                    break;
                } else {
                    System.out.println(validation);
                }
            }

            String messageID = Message.generateMessageID();
            System.out.println("Message ID generated: " + messageID);

            Message msg = new Message(messageID, i, recipient, text);

            System.out.println("\nSelect an action:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message");
            System.out.print("Choice: ");
            int actionChoice = Integer.parseInt(scanner.nextLine().trim());

            String actionResult = msg.SentMessage(actionChoice);
            System.out.println(actionResult);

            if (actionChoice == 1) {
                System.out.println("\n--- Sent Message Details ---");
                System.out.println(msg.printMessages());
            }
        }

        System.out.println("\nAccumulated sent messages: " + Message.returnTotalMessagess());
    }
}