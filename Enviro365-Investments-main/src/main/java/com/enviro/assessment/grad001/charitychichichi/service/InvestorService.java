package com.enviro.assessment.grad001.charitychichichi.service;

import com.enviro.assessment.grad001.charitychichichi.domain.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface InvestorService{

    List<Investor> findAll();

    Optional<Investor> findById(Long id);

    Investor save(Investor investor);

    void deleteById(Long id);

    void update(Investor investor);
}
