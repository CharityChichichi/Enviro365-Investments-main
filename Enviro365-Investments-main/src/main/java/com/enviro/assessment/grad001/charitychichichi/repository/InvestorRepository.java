package com.enviro.assessment.grad001.charitychichichi.repository;

import com.enviro.assessment.grad001.charitychichichi.domain.Investor;
import com.enviro.assessment.grad001.charitychichichi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestorRepository extends JpaRepository<Investor,Long> {

    List<Investor> findAll();

}
