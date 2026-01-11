package com.drj.bankapp.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class CardsService {

    @PostAuthorize("returnObject != '123456'")
    public String getCard(){
        return "123456";
    }

}
