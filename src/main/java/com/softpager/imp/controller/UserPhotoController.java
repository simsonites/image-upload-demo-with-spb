package com.softpager.imp.controller;

import com.softpager.imp.entity.User;
import com.softpager.imp.entity.UserPhoto;
import com.softpager.imp.service.UserPhotoService;
import com.softpager.imp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Controller
@RequestMapping("/photo")
public class UserPhotoController {

    private UserService userService;
    private UserPhotoService userPhotoService;

    @Autowired
    public UserPhotoController(UserService userService, UserPhotoService userPhotoService) {
        this.userService = userService;
        this.userPhotoService = userPhotoService;
    }

    @GetMapping
    public String getUploadForm(@RequestParam("userId") User user, Model model) {
        User theUser = userService.getUser(user.getId());
        log.info("The user ID is : {} " + theUser.getId());
        model.addAttribute("user", theUser);
        return "/user-photo";
    }

    private UserPhoto uploadFile(@RequestParam("userId") User user, @RequestParam("file") MultipartFile photo) {
        User theUser = userService.getUser(user.getId());
        UserPhoto userPhoto = userPhotoService.saveUserPhoto(photo);
        theUser.setPhoto(userPhoto);
        userService.save(theUser);
        return userPhoto;
    }

    @PostMapping("/upload")
    public String updateUserPhoto(@RequestParam("userId") long theId, MultipartFile file){
        User theUser = userService.getUser(theId);
        if(theUser != null){
            theUser.setPhoto(this.uploadFile(theUser,file));
        }
        return "redirect:/user";
    }


}