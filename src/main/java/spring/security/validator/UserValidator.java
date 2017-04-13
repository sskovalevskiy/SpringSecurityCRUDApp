package spring.security.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.security.model.User;
import spring.security.service.UserService;

/**
 * Validator for {@link User} class
 * Implements {@link Validator}
 * @author ss.kovalevskiy
 * @version 1.0
 */
@Component
public class UserValidator implements Validator {
//    Создадим экземпляр UserService
    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
//        Подтверждаем, что aClass является экземпляром User
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");

        if (user.getUsername().length() < 8 || user.getUsername().length() > 32){
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername())!=null){
            errors.rejectValue("username","Duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword","Different.userForm.password");
        }
    }
}
