package com.softpager.imp.service;

import com.softpager.imp.entity.UserPhoto;
import com.softpager.imp.exceptions.FileStorageException;
import com.softpager.imp.exceptions.MyFileNotFoundException;
import com.softpager.imp.repository.UserPhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class UserPhotoServiceImpl implements UserPhotoService {

    private UserPhotoRepository userPhotoRepository;

    @Autowired
    public UserPhotoServiceImpl(UserPhotoRepository userPhotoRepository) {
        this.userPhotoRepository = userPhotoRepository;
    }

    @Override
    public UserPhoto saveUserPhoto(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
              throw new FileStorageException("Sorry! Filename contains invalid characters " + fileName);
            }
            UserPhoto userPhoto = new UserPhoto(fileName, file.getContentType(), file.getBytes());
           return userPhotoRepository.save(userPhoto);
        } catch (IOException ex) {
            throw new FileStorageException("Error storing file " + fileName + ". Please try again!", ex);
        }
    }
    @Override
    public UserPhoto getUserPhoto(long fileId) {
        return userPhotoRepository.findById(fileId)
          .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }


}
