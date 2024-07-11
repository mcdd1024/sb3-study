package jzxy.cbq.service.impl;

import jzxy.cbq.entity.Account;
import jzxy.cbq.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: cbq1024
 * @description: AccountServiceImpl
 * @since 2024/7/11 上午8:54
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> list() {
        log.info("查找用户");
        return List.of();
    }

    @Override
    public int add(Account account) {
        log.info("添加用户");
        return 0;
    }

    @Override
    public int update(Account account) {
        log.info("更新用户");
        return 0;
    }

    @Override
    public int delete(int id) {
        log.info("删除用户");
        return 0;
    }
}
