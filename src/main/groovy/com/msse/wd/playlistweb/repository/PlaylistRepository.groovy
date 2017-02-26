package com.msse.wd.playlistweb.repository

import com.msse.wd.playlistweb.model.Playlist
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository extends JpaRepository<Playlist,Long> {

}