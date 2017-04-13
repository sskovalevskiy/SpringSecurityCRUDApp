package spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import spring.security.dao.UserDao;
import spring.security.model.Role;
import spring.security.model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserDetailsService} interface
 *
 * @author ss.kovalevskiy
 * @version 1.0
 */
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Создаем экземпляр пользователя, которого мы ищем по имени пользователя в нашей базе данных
        User user = userDao.findByUsername(username);

//        Создаем разрешения GrantedAuthority
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

//        Получаем все роли пользователя, которые у него есть и запихиваем их в разрешения
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
