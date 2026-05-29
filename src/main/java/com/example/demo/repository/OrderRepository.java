package com.example.demo.repository;

import com.example.demo.model.entity.Order;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Override
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Optional<Order> findById(Long id);

    @Query("SELECT o FROM orders o WHERE o.orderedAt >= :start AND o.orderedAt <= :end")
    List<Order> findOrdersInRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
