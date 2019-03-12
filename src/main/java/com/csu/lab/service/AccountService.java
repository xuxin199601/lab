package com.csu.lab.service;

import com.csu.lab.pojo.Account;

import java.util.List;

public interface AccountService {
    public Integer addAccount(Account account);
    public Integer deleteAccount();
    public List<Account> getAccountList(Integer page_index,Integer page_size);
}
