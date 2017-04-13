package spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.security.model.Book;
import spring.security.model.User;
import spring.security.service.BookService;
import spring.security.service.SecurityService;
import spring.security.service.UserService;
import spring.security.validator.UserValidator;

/**
 * Controller for {@link User}'s pages
 *
 * @author ss.kovalevskiy
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
//        Пытаемся валидировать форму которую нам заполнили
        userValidator.validate(userForm, bindingResult);

//        Если какие-то ошибки - перенаправляем снова на страницу регистрации
        if (bindingResult.hasErrors()){
            return "registration";
        }

//        Если всё хорошо - переходим к сохранению полученного пользователя
        userService.save(userForm);
//        И вызываем метод автологин у securityService
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }

        return "login";
    }

    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
//        если id равен 0, что не может быть, если книга существует, то просим BookService добавить книгу в MySQL,
//        в противном случае - делаем update
        if (book.getId() == 0) {
            this.bookService.addBook(book);
        } else {
            this.bookService.updateBook(book);
        }
//        возвращаем редирект на главную страницу.
        return "redirect:/admin";
    }


    //    Страница для удаления книг
    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.removeBook(id);
        return "redirect:/admin";
    }

    //    Страница для редактирования книг
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
//        просим заново прочитать данные по всем книгам в нашей таблице
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "admin";
    }
}
