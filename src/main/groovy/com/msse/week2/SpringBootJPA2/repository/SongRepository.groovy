package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Song
import org.springframework.data.jpa.repository.JpaRepository

interface SongRepository extends JpaRepository<Song,Long> {

    List<Song> findBySongNameContainingIgnoreCase (String songName)

}