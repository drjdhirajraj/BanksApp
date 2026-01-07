package com.drj.bankapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BankUserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String bankAuthority;


    @ManyToOne
    @JoinColumn(name = "userid")
    private Bankuser bankuser;


    public BankUserAuthority(String role, Bankuser bankuser) {
        this.bankAuthority = role;
        this.bankuser = bankuser;
    }

    public BankUserAuthority() {

    }

    public BankUserAuthority(String authority) {
        this.bankAuthority = authority;
    }

    @Override
    public String toString() {
        return "BankUserAuthority{" +
                "bankAuthority='" + bankAuthority + '\'' +
                ", bankuser=" + bankuser +
                '}';
    }
}
