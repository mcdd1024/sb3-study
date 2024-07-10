package jzxy.cbq.dao;

import jzxy.cbq.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: cbq1024
 * @description: AccountMapperImpl
 * @since 2024/7/10 下午11:31
 */
@Repository
public class AccountMapperImpl implements AccountMapper {
    @Override
    public List<Account> list() {
        return List.of(
                new Account(1, "cbq", 22, "cbq@gmail.com"),
                new Account(2, "cb", 18, "cb@qq.com"));
    }
}
