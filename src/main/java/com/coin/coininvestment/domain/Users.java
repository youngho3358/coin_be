package com.coin.coininvestment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long userId;

    private String nickname;
    private String password;
}
