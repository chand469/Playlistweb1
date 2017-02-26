package com.msse.wd.playlistweb.repository

import com.msse.wd.playlistweb.model.Artist
import org.springframework.data.jpa.repository.JpaRepository


interface ArtistRepository extends JpaRepository<Artist,Long> {

}