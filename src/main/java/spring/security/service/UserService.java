package spring.security.service;

import spring.security.model.User;

/**
 * Service class for {@link User}
 *
 * @author ss.kovalevskiy
 * @version 1.0
 */
public interface UserService {
    void save(User user);
    User findByUsername(String username);
}

