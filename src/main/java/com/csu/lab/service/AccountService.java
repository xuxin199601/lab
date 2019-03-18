package com.csu.lab.service;

import com.csu.lab.pojo.Account;

import java.util.List;

public interface AccountService {
    Account loginVaildata(Account account);
    Integer addAccount(Account account);
    void updateAccount(Account account);
    Integer deleteAccount(Integer aid);
    List<Account> getAccountList();
    Account equrySingleAccount(Account account);
    Account queryAccountById(Integer aid);
    // 根据条件查询
    List<Account> queryByProperty(String property, Object value);
}
