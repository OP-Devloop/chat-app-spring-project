package se.sprinto.hakan.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.sprinto.hakan.chatapp.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT e FROM User e LEFT JOIN FETCH e.messages WHERE e.username = :username AND e.password = :password")
    User findByUsernameAndPassword(String username, String password);
}
