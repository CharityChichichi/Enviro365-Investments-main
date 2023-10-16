package com.enviro.assessment.grad001.charitychichichi.service.Impl;

import com.enviro.assessment.grad001.charitychichichi.domain.WithdrawalNotice;
import com.enviro.assessment.grad001.charitychichichi.repository.WithdrawalNoticeRepository;
import com.enviro.assessment.grad001.charitychichichi.service.WithdrawalNoticeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalNoticeServiceImpl implements WithdrawalNoticeService {
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;


    public WithdrawalNoticeServiceImpl(WithdrawalNoticeRepository withdrawalNoticeRepository) {
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
    }

    @Override
    public WithdrawalNotice save(WithdrawalNotice withdrawalNotice) {
        return this.withdrawalNoticeRepository.save(withdrawalNotice);

    }

    @Override
    public Optional<WithdrawalNotice> findById(Long id) {
        return withdrawalNoticeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        withdrawalNoticeRepository.deleteById(id);
    }

    @Override
    public List<WithdrawalNotice> withdrawalNotices() {
        return withdrawalNoticeRepository.findAll();
    }

    @Override
    public WithdrawalNotice update(WithdrawalNotice WithdrawalNotice) {
        return withdrawalNoticeRepository.save(WithdrawalNotice);
    }
}
