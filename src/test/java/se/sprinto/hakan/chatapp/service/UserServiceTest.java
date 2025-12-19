package se.sprinto.hakan.chatapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import se.sprinto.hakan.chatapp.model.User;
import se.sprinto.hakan.chatapp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @MockitoBean
    private CommandLineRunner commandLineRunner;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();
    }

    @Test
    void register_shouldSaveUser() {
        User user = new User();
        user.setUsername("hakan");
        user.setPassword("password");

        User savedUser = userService.register(user);

        assertNotNull(savedUser.getId());
        assertEquals("hakan", savedUser.getUsername());
        assertEquals(1, userRepository.count());
    }

    @Test
    void login_shouldReturnUser_whenCredentialsAreCorrect() {
        User user = new User();
        user.setUsername("hakan");
        user.setPassword("password");

        userService.register(user);

        User loggedIn = userService.login("hakan", "password");

        assertNotNull(loggedIn);
        assertEquals("hakan", loggedIn.getUsername());
    }
}
