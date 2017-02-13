package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException


@SpringBootTest
@Transactional
class accountRepositorySpec extends Specification {

    @Autowired
    AccountRepository accountRepository

    @Shared
    String password = 'Password1'
    String username = 'UMSECMSSE'
    String email    = 'msse17@umn.edu'

    def 'A1. Accounts with valid required properties will save: email, password, name #Description'() {

        given:
            def recCount = accountRepository.count()
            def account = new Account(username: username, password: password, email: email)

        when:
            accountRepository.save(account)

        then:
            accountRepository.count() == recCount + 1
            println("Record has been saved successfully")
    }

    def 'A2. Saving an account missing any of the required values of email, password and name will fail  #Description'() {

        given:
            def account = new Account(Parameters)

        when:
            accountRepository.save(account)

        then:
            thrown(exceptionType)

        where:
            Description                |  exceptionType                 |  Parameters
            'Missing All Inputs'       |  ConstraintViolationException  |  [:]
            'Missing Username'         |  ConstraintViolationException  |  [password: 'pass1.ms#.coM', email: 'msse@umn.edu']
            'Missing Password'         |  ConstraintViolationException  |  [username: 'MSSE', email   : 'msse@umn.edu']
            'Missing Email'            |  ConstraintViolationException  |  [username: 'MSSE', password: 'pass1.ms#.coM']
    }

    def 'A3. Saving an account with an invalid password will fail.  #Description'() {

        given:
            def account = new Account(Parameters)

        when:
            accountRepository.save(account)

        then:
            thrown(exceptionType)

        where:
            Description                |  exceptionType                 |  Parameters
            'Verify Null'              |  ConstraintViolationException  |  [username:  null , password:  null      , email: null]
            'Missing Uppercase'        |  ConstraintViolationException  |  [username: 'MSSE', password: 'password1', email: 'msse@umn.edu']
            'Missing Lowercase'        |  ConstraintViolationException  |  [username: 'MSSE', password: 'PASSWORD1', email: 'msse@umn.edu']
            'Missing Number'           |  ConstraintViolationException  |  [username: 'MSSE', password: 'Password' , email: 'msse@umn.edu']
            'Less than 8 Chars'        |  ConstraintViolationException  |  [username: 'MSSE', password: 'Pass1'    , email: 'msse@umn.edu']
            'Greater than 16 chars'    |  ConstraintViolationException  |  [username: 'MSSE', password: 'Password1msseumsec' , email: 'msse@umn.edu']
    }

    def 'A4. Saving account with a non-unique email or handle address must fail #Description'() {

        given:
            def account1 = new Account(username: 'username1', password: 'Password1', email: email)
            accountRepository.save(account1)

            def account2 = new Account(username: 'username2', password: 'Password2', email: email)

        when:
            accountRepository.save(account2)

        then:
            thrown DataIntegrityViolationException

    }

    def 'A5. Password must be saved as encrypted data - not plain text #Description'() {

        given:
            def recCount = accountRepository.count()
            def account = new Account(username: username, password: password, email: email)

        when:
            accountRepository.save(account)

        then:
            accountRepository.count() == recCount + 1
            accountRepository.findOne(account.id).passwordHash == account.passwordHash
            println("username: $account.username,password: $account.password, passwordHash: $account.passwordHash, email: $account.email")
    }


}
