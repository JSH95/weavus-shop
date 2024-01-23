package com.weavus.weavusshoppingmall.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data //Setter, Getter, NoArgsConstructor, toString ....
public class User {

    @Id
    private String id;

    private String password;

    private String userEmail;

    private String cardNumber;

    private String cardCvc;

    private String userName;

    private String userEnName;

//    @CreatedDate
    private LocalDateTime createdAt;

    private char is_active;

    @OneToOne
    @JoinColumn(name = "id")
    private Cart cart;


    @Builder
    public User(String id, String password, String userEmail, String cardNumber, String cardCvc, String userName, String userEnName) {
        this.id = id;
        this.password = password;
        this.userEmail = userEmail;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.userName = userName;
        this.userEnName = userEnName;
    }

}
