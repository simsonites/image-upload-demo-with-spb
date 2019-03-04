package com.softpager.imp.service;

import com.softpager.imp.entity.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

public interface UserPhotoService {
    UserPhoto saveUserPhoto(MultipartFile photo);

    UserPhoto getUserPhoto(long photoId);

}
