package com.enviro.assessment.grad001.charitychichichi.repository;

import com.enviro.assessment.grad001.charitychichichi.domain.Product;
import com.enviro.assessment.grad001.charitychichichi.domain.Type;
import com.enviro.assessment.grad001.charitychichichi.domain.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAll();

}
