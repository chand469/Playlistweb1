package com.msse.wd.playlistweb.service

import com.msse.wd.playlistweb.model.Account
import com.msse.wd.playlistweb.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class AccountService {

    @Autowired
    AccountRepository accountRepository

    Account getAccount(Long id) {
        return accountRepository.findOne(id)
    }

    Account addAccount(Account account) {
        return accountRepository.save(account)
    }

    Account updateAccount(Account account) {
        return accountRepository.save(account)
    }

    boolean deleteAccount(Long id) {
        accountRepository.delete(id)
        return true
    }

}

