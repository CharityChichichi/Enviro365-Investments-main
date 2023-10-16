package com.enviro.assessment.grad001.charitychichichi.service.Impl;

import com.enviro.assessment.grad001.charitychichichi.domain.Investor;
import com.enviro.assessment.grad001.charitychichichi.repository.InvestorRepository;
import com.enviro.assessment.grad001.charitychichichi.service.InvestorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    public List<Investor> findAll() {
        return investorRepository.findAll();
    }

    @Override
    public Optional<Investor> findById(Long id) {
        return investorRepository.findById(id);
    }

    @Override
    public Investor save(Investor investor) {
        return investorRepository.save(investor);
    }

    @Override
    public void deleteById(Long id) {
        investorRepository.findById(id);
    }

    @Override
    public void update(Investor investor) {
        investorRepository.save(investor);
    }
}
