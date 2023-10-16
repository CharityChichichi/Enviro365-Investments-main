package com.enviro.assessment.grad001.charitychichichi.controllers.restApi;

import com.enviro.assessment.grad001.charitychichichi.domain.WithdrawalNotice;
import com.enviro.assessment.grad001.charitychichichi.service.WithdrawalNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/withdrawalNotices")
public class WithdrawalNoticeRestController {


    private final WithdrawalNoticeService withdrawalNoticeService;

    public WithdrawalNoticeRestController(WithdrawalNoticeService withdrawalNoticeService) {
        this.withdrawalNoticeService = withdrawalNoticeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WithdrawalNotice> getAllWithdrawalNotices() {
        return withdrawalNoticeService.withdrawalNotices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WithdrawalNotice getWithdrawalNoticeById(@PathVariable Long id) {
        return withdrawalNoticeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // just to saw another way to throw the error
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WithdrawalNotice createWithdrawalNotice(@RequestBody WithdrawalNotice withdrawalNotice) {

        if(withdrawalNotice!=null){
            withdrawalNotice.getBankingDetails().setWithdrawalNotice(withdrawalNotice);
        }

        return withdrawalNoticeService.save(withdrawalNotice);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public WithdrawalNotice updateWithdrawalNotice(@PathVariable Long id, @RequestBody WithdrawalNotice withdrawalNotice) {
        if (!withdrawalNoticeService.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        withdrawalNotice.setId(id);
        return withdrawalNoticeService.update(withdrawalNotice);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWithdrawalNotice(@PathVariable Long id) {
        if (!withdrawalNoticeService.findById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        withdrawalNoticeService.deleteById(id);
    }

}
