package com.drj.bankapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Bankuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "bankuser",fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    private Set<BankUserAuthority> bankUserAuthorities;


    @Override
    public String toString() {
        return "Bankuser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bankUserAuthorities=" + bankUserAuthorities +
                '}';
    }


}
