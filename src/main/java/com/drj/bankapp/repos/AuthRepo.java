package com.drj.bankapp.repos;

import com.drj.bankapp.model.BankUserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<BankUserAuthority, Long> {

}
