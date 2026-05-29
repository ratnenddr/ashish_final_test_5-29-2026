package com.example.demo.repository;

import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Optional<User> findById(Long id);

}
