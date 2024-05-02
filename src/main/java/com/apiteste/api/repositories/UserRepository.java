package com.apiteste.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiteste.api.models.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNome(String nome);
}