package com.enviro.assessment.grad001.charitychichichi.service;

import com.enviro.assessment.grad001.charitychichichi.domain.WithdrawalNotice;

import java.util.List;
import java.util.Optional;

public interface WithdrawalNoticeService {

    WithdrawalNotice save(WithdrawalNotice withdrawalNotice);

    Optional<WithdrawalNotice> findById(Long id);

    void deleteById(Long id);

    List<WithdrawalNotice> withdrawalNotices();

    WithdrawalNotice update(WithdrawalNotice WithdrawalNotice);
}
