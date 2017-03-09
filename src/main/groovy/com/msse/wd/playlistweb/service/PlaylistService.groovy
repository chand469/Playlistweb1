package com.msse.wd.playlistweb.service

import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.repository.AccountRepository
import com.msse.wd.playlistweb.repository.PlaylistRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PlaylistService {

    @Autowired
    PlaylistRepository playlistRespository

    @Autowired
    AccountRepository accountRepository

    List<Playlist> accountPlaylists(Long accountId) {
        accountRepository.findOne(accountId)?.playlists
    }

    Page listPlaylists(Pageable request) {
        Page result = playlistRespository.findAll(request)
        result
    }

    Playlist addPlaylist(Playlist playlist) {
        return playlistRespository.save(playlist)
    }


}
