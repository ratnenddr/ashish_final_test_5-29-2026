package com.example.demo.repository;

import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.Product;
import com.example.demo.model.enums.OrderStatus;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
    Optional<Product> findById(Long id);

    @Query(value = " select  p from products p order by p.unit_sold asc limit 5",nativeQuery = true)
    List<Product> findByUnitSold();
}
