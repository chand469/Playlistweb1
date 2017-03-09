package com.msse.wd.playlistweb.service

import com.msse.wd.playlistweb.model.Song
import com.msse.wd.playlistweb.repository.SongRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SongService {

    @Autowired
    SongRepository songRepository

    Song getSong(Long id) {
        return songRepository.findOne(id)
    }

    Song addSong(Song song) {
        return songRepository.save(song)
    }

    Song updateSong(Song song) {
        return songRepository.save(song)
    }

    boolean deleteSong(Long id) {
        songRepository.delete(id)
        return true
    }
}
