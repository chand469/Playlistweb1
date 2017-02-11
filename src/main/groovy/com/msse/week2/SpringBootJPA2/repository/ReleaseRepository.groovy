package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Release
import org.springframework.data.jpa.repository.JpaRepository

interface ReleaseRepository extends JpaRepository<Release,Long> {

}