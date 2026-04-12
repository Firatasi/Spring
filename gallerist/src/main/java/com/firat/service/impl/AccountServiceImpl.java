package com.firat.service.impl;

import com.firat.dto.DtoAccount;
import com.firat.dto.DtoAccountIU;
import com.firat.model.Account;
import com.firat.repository.AccountRepository;
import com.firat.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Account createAccount(DtoAccountIU dtoAccountIU) {

        Account account = new Account();
        account.setCreatTime(new Date());
        BeanUtils.copyProperties(dtoAccountIU, account);

        return account;
    }

    @Override
    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {

        DtoAccount dtoAccount = new DtoAccount();
        Account savedAccount =  accountRepository.save(createAccount(dtoAccountIU));
        BeanUtils.copyProperties(savedAccount, dtoAccount);

        return dtoAccount;
    }
}
