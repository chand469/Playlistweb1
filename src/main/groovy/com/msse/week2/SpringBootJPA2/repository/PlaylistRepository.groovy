package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Playlist
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository extends JpaRepository<Playlist,Long> {

}