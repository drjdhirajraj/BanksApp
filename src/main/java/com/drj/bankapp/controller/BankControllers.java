package com.drj.bankapp.controller;


import com.drj.bankapp.DTO.BankUserRecord;
import com.drj.bankapp.model.BankUserAuthority;
import com.drj.bankapp.model.Bankuser;
import com.drj.bankapp.repos.UsersRepoService;
import com.drj.bankapp.service.CardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BankControllers {


    @Autowired
    CardsService cardsService;



    private final UsersRepoService usersRepoService;
    private final PasswordEncoder encoder;

    @GetMapping("/getAllData")
    @PostFilter("filterObject.username == authentication.principal.username  ")
    public List<Bankuser> login(){
        return usersRepoService.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody BankUserRecord bankUserRecord){


        Bankuser bankuser = new Bankuser();
        bankuser.setUsername(bankUserRecord.username());
        bankuser.setPassword(encoder.encode(bankUserRecord.password()));

        // Convert simple authorities → entity objects and set parent user
        Set<BankUserAuthority> authorities = bankUserRecord.bankAuthorities().stream()
                .map(a -> {
                    BankUserAuthority auth = new BankUserAuthority(a.bankAuthority());
                    auth.setBankuser(bankuser);         // IMPORTANT
                    return auth;
                })
                .collect(Collectors.toSet());

        bankuser.setBankUserAuthorities(authorities);

        usersRepoService.saveUser(bankuser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User Registerd!");

    }


    @GetMapping("/loan")

    public ResponseEntity<String> getLoanData(){
        return ResponseEntity.status(HttpStatus.FOUND).body("LoanData");
    }

    @GetMapping("/cards")
    public ResponseEntity<String> getCardData(){


        String card = cardsService.getCard();
        return ResponseEntity.status(HttpStatus.FOUND).body("12233/3443/9393/0193");
    }

//    @PreAuthorize("hasRole('VIEW_CARDS')")
//    @PostAuthorize("!returnObject.toString().equals('123456')")



    @GetMapping("/balance")
    public ResponseEntity<String> geBalanceData(){
        return ResponseEntity.status(HttpStatus.FOUND).body("INR 120456789.00");
    }




}


