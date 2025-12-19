package se.sprinto.hakan.chatapp;

import se.sprinto.hakan.chatapp.repository.MessageRepository;
import se.sprinto.hakan.chatapp.repository.UserRepository;

public class ChatClientMain {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatClientMain(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public static void main(String[] args) {
        new ChatClient().start();
    }
}

