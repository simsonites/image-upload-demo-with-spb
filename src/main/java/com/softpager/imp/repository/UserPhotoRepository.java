package com.softpager.imp.repository;

import com.softpager.imp.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {

    @Modifying
    @Query("update UserPhoto u set u.image = ?1 where u.id = ?2")
    UserPhoto updateByPhotoId(long photoId, UserPhoto photo);
}
