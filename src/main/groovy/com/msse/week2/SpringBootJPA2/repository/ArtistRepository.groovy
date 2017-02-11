package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Artist
import org.springframework.data.jpa.repository.JpaRepository


interface ArtistRepository extends JpaRepository<Artist,Long> {

}