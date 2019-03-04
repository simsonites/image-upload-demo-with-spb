package com.softpager.imp.entity;

import lombok.Data;


import javax.persistence.*;


@Data
@Entity
@Table(name="photos")
public class UserPhoto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private long id;

    @Column(name="image_name")
    private String fileName;

    @Column(name="image_type")
    private String fileType;

    @Lob
    @Column(name="image_data")
    private byte[] image;

    @OneToOne(mappedBy = "photo", cascade = {CascadeType.MERGE,CascadeType.PERSIST,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private User user;

    public UserPhoto() {
    }

    public UserPhoto(String fileName, String fileType, byte[] image) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.image = image;
    }
}
