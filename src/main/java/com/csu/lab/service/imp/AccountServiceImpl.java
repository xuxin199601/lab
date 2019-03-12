package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.AccountException;
import com.csu.lab.pojo.Account;
import com.csu.lab.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Integer addAccount(Account account) {
        return null;
    }

    @Override
    public Integer deleteAccount() {
        return null;
    }

    @Override
    public List<Account> getAccountList(Integer page_index, Integer page_size) {
        return null;
    }

    @Override
    public Account loginVaildata(Account account) {

        if(false){
            throw  new AccountException(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getCode(),ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getMsg());
        }
        return null;
    }
}
