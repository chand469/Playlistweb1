package com.msse.wd.playlistweb.service

import com.msse.wd.playlistweb.model.Account
import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.repository.AccountRepository
import com.msse.wd.playlistweb.repository.PlaylistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaylistService {

    @Autowired
    AccountRepository accountRepository

    @Autowired
    PlaylistRepository playlistRepository

        Playlist getPlaylist(Long id) {
        return playlistRepository.findOne(id)
    }

    Playlist addPlaylist(Playlist playlist) {

        return playlistRepository.save(playlist)
    }

    Playlist updatePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist)
    }

    boolean deletePlaylist(Long id) {
        playlistRepository.delete(id)
        return true
    }


}