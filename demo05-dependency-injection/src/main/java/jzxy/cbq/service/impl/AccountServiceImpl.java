package jzxy.cbq.service.impl;

import jzxy.cbq.dao.AccountMapper;
import jzxy.cbq.entity.Account;
import jzxy.cbq.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: cbq1024
 * @description: AccountServiceImpl
 * @since 2024/7/10 下午11:26
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper mapper;

    @Override
    public List<Account> list() {
        return mapper.list();
    }
}
