package com.msse.wd.playlistweb.repository

import com.msse.wd.playlistweb.model.Account
import org.springframework.data.jpa.repository.JpaRepository


interface AccountRepository extends JpaRepository<Account,Long> {

}