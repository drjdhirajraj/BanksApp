package com.drj.bankapp.repos;


import com.drj.bankapp.AuthenticationModels.CustomUserDetails;
import com.drj.bankapp.model.Bankuser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersRepoService {

    private final BankRepo bankRepo;

    private final AuthRepo authRepo;

    private final PasswordEncoder encoder;

    public void saveUser(Bankuser bankuser) {
                bankRepo.save(bankuser);
    }


    public UserDetails findByUsername(String username) {

        Bankuser user = bankRepo.findByUsername(username);

        return new CustomUserDetails(user);

    }

    public List<Bankuser> findAll() {
       return bankRepo.findAll();
    }
}
