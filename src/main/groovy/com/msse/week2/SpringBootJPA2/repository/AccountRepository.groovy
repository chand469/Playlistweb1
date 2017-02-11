package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Account
import org.springframework.data.jpa.repository.JpaRepository


interface AccountRepository extends JpaRepository<Account,Long> {

}