package com.enviro.assessment.grad001.charitychichichi.repository;

import com.enviro.assessment.grad001.charitychichichi.domain.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice,Long> {

    List<WithdrawalNotice> findAll();
}
