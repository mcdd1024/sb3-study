package jzxy.cbq.service;

import jzxy.cbq.entity.Account;

import java.util.List;

/**
 * @author: cbq1024
 * @description: AccountService
 * @since 2024/7/11 上午8:52
 */
public interface AccountService {

    List<Account> list();

    int add(Account account);

    int update(Account account);

    int delete(int id);
}
