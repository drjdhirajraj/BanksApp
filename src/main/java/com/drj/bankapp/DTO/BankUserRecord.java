package com.drj.bankapp.DTO;

import com.drj.bankapp.model.BankUserAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public record BankUserRecord(String username,
                             String password,
                             Set<BankAuthorityRecord> bankAuthorities) {

    public Set<BankUserAuthority> toEntityAuthorities() {
        return bankAuthorities.stream()
                .map(a -> new BankUserAuthority(a.bankAuthority()))
                .collect(Collectors.toSet());
    }
}


