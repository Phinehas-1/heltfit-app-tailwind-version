package com.favour.heltfitapp.appuser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "_user", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private Integer weight;
    private String history;

    /**
     * @param username
     * @param password
     * @param email
     * @param age
     * @param weight
     * @param history
     */
    public AppUser(String username, String password, String email, Integer age, Integer weight, String history) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.history = history;
    }

    
}
