package jzxy.cbq.controller;

import jzxy.cbq.entity.Account;
import jzxy.cbq.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: cbq1024
 * @description: AccountController
 * @since 2024/7/10 下午11:26
 */
@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping
    List<Account> list() {
        return service.list();
    }

}
