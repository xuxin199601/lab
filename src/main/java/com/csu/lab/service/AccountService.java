package com.csu.lab.service;

import com.csu.lab.pojo.Account;

import java.util.List;

public interface AccountService {
    public Account loginVaildata(Account account);
    public Integer addAccount(Account account);
    public Integer deleteAccount(Integer aid);
    public List<Account> getAccountList(Integer page_index,Integer page_size);
    public Account equrySingleAccount(Account account);
}
