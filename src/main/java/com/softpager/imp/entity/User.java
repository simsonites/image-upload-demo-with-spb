package com.softpager.imp.entity;

import com.softpager.imp.utils.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name="users")
@EqualsAndHashCode(callSuper=false)
public class User extends AuditModel {

    @Id
   @Column(name = "user_id")
   @GeneratedValue
   private long id;

    @NotEmpty
    @Column(name="first_name")
    private String firstName;

    @NotEmpty
    @Column(name="last_name")
    private String lastName;


    @NotEmpty()
    @Column(name="email")
    private String email;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="image_id")
    @ToString.Exclude
    private UserPhoto photo;

    public User() {}

    public User(String firstName, String lastName,String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }
}
