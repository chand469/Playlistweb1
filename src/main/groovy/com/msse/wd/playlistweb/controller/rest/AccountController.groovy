package com.msse.wd.playlistweb.controller.rest

import com.msse.wd.playlistweb.model.Account
import com.msse.wd.playlistweb.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping('/accounts')
class AccountController {

    @Autowired
    AccountService accountService

    @PostMapping
    Account addAccount(@RequestBody Account account) {
        return accountService.addAccount(account)
    }

    @GetMapping('/{accountId}')
    Account getAccount(@PathVariable Long accountId, HttpServletResponse response) {
        Account account = accountService.getAccount(accountId)
        if (!account) {
            response.setStatus(404)
        }
        return account
    }

    @PutMapping('/{accountId}')
    Account updateAccount(@PathVariable Long accountId, @RequestBody Account account, HttpServletResponse response) {
        Account accountdb = accountService.getAccount(accountId)
        if (!accountdb) {
            response.setStatus(404)
        }
        return accountService.updateAccount(account)
    }

    @DeleteMapping('/{accountId}')
    boolean deleteAccount(@PathVariable Long accountId, HttpServletResponse response) {
        Account account = accountService.getAccount(accountId)
        if (!account) {
            response.setStatus(404)
        }
        return accountService.deleteAccount(accountId)
    }

}
