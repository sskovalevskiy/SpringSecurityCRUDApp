package spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.dao.RoleDao;
import spring.security.dao.UserDao;
import spring.security.model.Role;
import spring.security.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserService}  interface
 *
 * @author ss.kovalevskiy
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {

//        Получаем у пользователя пароль, кодируем его с помощью BCrypt, и присваиваем пользователю закодированный пароль
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Присваиваем ему одну единственную роль
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
//        Сохраняем пользователя
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
