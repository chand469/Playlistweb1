package com.msse.wd.playlistweb.service

import com.msse.wd.playlistweb.model.Artist
import com.msse.wd.playlistweb.model.Song
import com.msse.wd.playlistweb.repository.ArtistRepository
import com.msse.wd.playlistweb.repository.SongRepository
import org.springframework.beans.factory.annotation.Autowired

class ArtistService {

    @Autowired
    ArtistRepository artistRepository

    Artist getArtist(Long id) {
        return artistRepository.findOne(id)
    }

    Artist addArtist(Artist artist) {
        return artistRepository.save(artist)
    }

    Artist updateArtist(Artist artist) {
        return artistRepository.save(artist)
    }

    boolean deleteArtist(Long id) {
        artistRepository.delete(id)
        return true
    }
}
