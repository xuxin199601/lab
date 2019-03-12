package com.csu.lab.service.imp;

import com.csu.lab.enums.ResultEnum;
import com.csu.lab.exception.AccountException;
import com.csu.lab.mapper.AccountMapper;
import com.csu.lab.pojo.Account;
import com.csu.lab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Account equrySingleAccount(Account account) {

        return accountMapper.selectOne(account);

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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Account loginVaildata(Account account) {
//        Account account1 = new Account();
//        account1.setUsername(account.getUsername());
//        usernew.setPassword(password);
        Account user = accountMapper.selectOne(account);
        if (user != null) {
            if (user.getPassword().equals(account.getPassword())) {
                return user;
            } else {
                throw new AccountException(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getCode(), ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getMsg());
            }
        } else {
            throw new AccountException(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getCode(), ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getMsg());
        }
//        if(false){
//            throw  new AccountException(ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getCode(),ResultEnum.ACCOUNT_OR_PASSWORD_ERROR.getMsg());
//        }
//        return null;
    }
}
