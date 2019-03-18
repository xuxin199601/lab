package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.AccountException;
import com.csu.lab.mapper.AccountMapper;
import com.csu.lab.pojo.Account;
import com.csu.lab.service.AccountService;
import com.github.pagehelper.PageHelper;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account loginVaildata(Account account) {
        Account user = accountMapper.selectOne(account);
        return user;
    }

    @Override
    public List<Account> getAccountList() {
        List<Account> list = accountMapper.selectAll();
        return list;
    }

    @Override
    public Integer addAccount(Account account) {
        Account tempAccount = new Account();
        tempAccount.setUsername(account.getUsername());
        Account exitsAccount = equrySingleAccount(tempAccount);
        if (exitsAccount != null) {
            throw new AccountException(ResultEnum.ACCOUNT_EXIST);
        } else {
            Integer flag = accountMapper.insert(account);
            if (flag != 1) {
                throw new AccountException(ResultEnum.ACCOUNT_FAILURE.getCode(), ResultEnum.ACCOUNT_FAILURE.getMsg());
            }
            return flag;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer deleteAccount(Integer aid) {
        return accountMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public Account equrySingleAccount(Account account) {
        return accountMapper.selectOne(account);

    }

    @Override
    public Account getAccount(Integer aid){
        return accountMapper.selectByPrimaryKey(aid);
    }
}
