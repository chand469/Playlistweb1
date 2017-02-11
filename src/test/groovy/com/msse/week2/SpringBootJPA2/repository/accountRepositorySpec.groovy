package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException

@SpringBootTest
@Transactional
class accountRepositorySpec extends Specification {

    @Autowired
    AccountRepository accountRepository

    def 'A1.save an account with Invalid parameters #Description'() {

        given:
        def recCount = accountRepository.count()
        def account = new Account(Parameters)

        when:
        accountRepository.save(account)

        then:
        thrown(exceptionType)

        where:
        Description                | exceptionType                  | Parameters
        'missing all'              | ConstraintViolationException   | [:]
        'null check'               | ConstraintViolationException   | [username: null, password: null, email: null]
    }

    def 'A2.save an account with valid parameters #Description'() {

        given:
        def recCount = accountRepository.count()
        def account = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')

        when:
        accountRepository.save(account)

        then:
        accountRepository.count() == recCount + 1

    }



}
