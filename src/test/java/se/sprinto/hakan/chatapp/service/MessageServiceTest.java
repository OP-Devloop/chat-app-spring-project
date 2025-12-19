package se.sprinto.hakan.chatapp.service;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.MessageRepository;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MessageServiceTest {

    @MockitoBean
    private CommandLineRunner commandLineRunner;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setup() {
        em.clear();
    }

    @Test
    void save_shouldPersistMessage() {
        User user = userRepository.save(new User("hakan", "password"));

        Message m1 = new Message(user, "Hello", LocalDateTime.now());

        messageService.save(m1);

        assertEquals(1, messageRepository.count());
    }

    @Test
    void getMessages_shouldReturnMessagesForUser() {
        User user = userRepository.save(new User("hakan", "password"));

        Message m1 = new Message(user, "Hello", LocalDateTime.now());
        Message m2 = new Message(user, "Hi", LocalDateTime.now());

        messageRepository.saveAll(List.of(m1, m2));

        List<Message> messages =
                messageService.getMessages(user.getId());

        assertEquals(2, messages.size());
    }

    @Test
    void getMessages_shouldReturnEmptyList_whenNoMessagesExist() {
        List<Message> messages = messageService.getMessages(99L);

        assertNotNull(messages);
        assertTrue(messages.isEmpty());
    }
}
