package com.csu.lab.service;

import com.csu.lab.pojo.Account;

import java.util.List;

public interface AccountService {
    Account loginVaildata(Account account);
    Integer addAccount(Account account);
    Integer deleteAccount(Integer aid);
    List<Account> getAccountList();
    Account equrySingleAccount(Account account);
    Account getAccount(Integer aid);
}
