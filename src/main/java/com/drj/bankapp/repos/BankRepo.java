package com.drj.bankapp.repos;

import com.drj.bankapp.model.Bankuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<Bankuser,Long> {

    Bankuser findByUsername(String username);

}
