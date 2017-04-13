package spring.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.security.model.User;

/**
 * @author ss.kovalevskiy
 * @version 1.0
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
