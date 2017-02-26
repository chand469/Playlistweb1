package com.msse.wd.playlistweb.repository

import com.msse.wd.playlistweb.model.Song
import org.springframework.data.jpa.repository.JpaRepository

interface SongRepository extends JpaRepository<Song,Long> {

    List<Song> findBySongNameContainingIgnoreCase (String songName)

}