package com.softpager.imp.controller;

import com.softpager.imp.entity.User;
import com.softpager.imp.entity.UserPhoto;
import com.softpager.imp.service.UserPhotoService;
import com.softpager.imp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    private UserPhotoService userPhotoService;

    @Autowired
    public UserController(UserService userService, UserPhotoService userPhotoService) {
        this.userService = userService;
        this.userPhotoService = userPhotoService;
    }

    // get all users from the database
    @GetMapping
    public String getUsers(Model model) {
        List<User> allUsers = userService.getUsers();
        model.addAttribute("users", allUsers);
        return "/all-users";
    }

    // get a single user from the database by their email
    @GetMapping("/user")
    public String getUser(@RequestParam("userId") long theId, Model model) {
        User theUser = userService.getUser(theId);
        model.addAttribute("user", theUser);
        return "/profile";
    }

    //getting the user photo from the database
    @GetMapping(value = "/user/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("userId") long theId) throws IOException {
        User theUser = userService.getUser(theId);
        UserPhoto photo = userPhotoService.getUserPhoto(theUser.getPhoto().getId());
        return photo.getImage();
    }

    //show form to create a new user
    @GetMapping("/form")
    public String showForm(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "/add-user";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User theUser){
        userService.save(theUser);
        return "redirect:/users";
    }


}
