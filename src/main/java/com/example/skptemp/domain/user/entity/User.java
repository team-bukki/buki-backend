package com.example.skptemp.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String token;
    private Long point;
    @Column(name = "kakao_id")
    private Long kakaoId;

    public static User createUser(String firstName, String lastName, Long kakaoId){
        String uuid = makeUuid(false);
        return new User(null, firstName, lastName, uuid, 0L, kakaoId);
    }

    private static String makeUuid(boolean isHyphen){
        if(isHyphen)
            return UUID.randomUUID().toString();
        else
            return UUID.randomUUID().toString().replace("-", "");
    }
}
