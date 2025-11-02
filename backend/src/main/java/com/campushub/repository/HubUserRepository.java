package com.campushub.repository;

import com.campushub.model.HubUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HubUserRepository
        extends JpaRepository<HubUser, Integer> {

    boolean existsUserByEmail(String email);
    boolean existsUserById(Integer id);
    Optional<HubUser> findCustomerByEmail(String email);
}
