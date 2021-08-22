package com.i2p.securitypractice;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private Long userId;

    private String email_id;
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private boolean isEnable;

    public UserEntity() {
    }


    public UserEntity(Long userId, String email, String password, String firstName, String lastName, boolean isEnable) {
        this.userId = userId;
        this.email_id = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isEnable = isEnable;
    }
}
