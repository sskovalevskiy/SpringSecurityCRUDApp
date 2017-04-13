package spring.security.service;

/**
 * Сервис, который будет отвечать за безопасность нашего приложения
 * @author ss.kovalevskiy
 * @version 1.0
 */
public interface SecurityService {

//    Будем искать залогиненных пользователей
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
