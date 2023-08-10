package gl.librarySec.LibraryManagmentSecurity.controller;

import gl.librarySec.LibraryManagmentSecurity.entity.User;
import gl.librarySec.LibraryManagmentSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping({"", "/"})
    public String getAllBooks(Model model){
        model.addAttribute("users", userService.getAllUsers());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        userService.getAllUsers()
//                .stream()
//                .filter(userT -> userT.getUname().equals(authentication.getName()))
//                .map(user -> model.addAttribute( "currentUser", user.getFname()))
//                .findFirst();
        return "view-all-users";

    }

    @GetMapping("/get/{id}")
    public String getBook(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "view-user";
    }

    @GetMapping("/put/page/{id}")
    public String putBookPage(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "edit-user";
    }

    @PostMapping("/put/post/{id}")
    public RedirectView putBook(Model model, @PathVariable("id") Long id, @ModelAttribute("user") User user){
        userService.putUserById(id, user);
        return new RedirectView("/user/");
    }

    @GetMapping("/add/page/")
    public String addBookPage(Model model){
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add/post/")
    public RedirectView addBook(Model model, @ModelAttribute("user") User user){
        userService.addUser(user);
        return new RedirectView("/user/");
    }

    @GetMapping("/del/page/{id}")
    public RedirectView deleteBook(Model model, @PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new RedirectView("/user");
    }

    @RequestMapping("/403")
    public ModelAndView accessDenied(Principal user){
        ModelAndView model = new ModelAndView();
        if(user!=null){
            model.addObject("msg",  "Hi " + user.getName() + " you cannot access this page");
        } else {
            model.addObject("msg",  "You cannot access this page");
        }
        model.setViewName("403");
        return model;
    }
}
