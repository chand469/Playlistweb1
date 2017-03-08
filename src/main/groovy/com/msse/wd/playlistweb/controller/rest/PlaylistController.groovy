package com.msse.wd.playlistweb.controller.rest

import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.service.PlaylistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping('/playlists')
class PlaylistController {
    @Autowired
    PlaylistService playlistService

    @PostMapping
    Playlist addPlaylist(@RequestBody Playlist playlist) {
        return playlistService.addPlaylist(playlist)
    }

    @GetMapping('/{playlistId}')
    Playlist getPlaylist(@PathVariable Long playlistId, HttpServletResponse response) {
        Playlist playlist = playlistService.getPlaylist(playlistId)
        if (!playlist) {
            response.setStatus(404)
        }
        return playlist
    }

    @PutMapping('/{playlistId}')
    Playlist updatePlaylist(@PathVariable Long playlistId, @RequestBody Playlist playlist, HttpServletResponse response) {
        Playlist playlistdb = playlistService.getPlaylist(playlistId)
        if (!playlistdb) {
            response.setStatus(404)
        }
        return playlistService.updatePlaylist(playlist)
    }

    @DeleteMapping('/{playlistId}')
    boolean deletePlaylist(@PathVariable Long playlistId, HttpServletResponse response) {
        Playlist playlist = playlistService.getPlaylist(playlistId)
        if (!playlist) {
            response.setStatus(404)
        }
        return playlistService.deletePlaylist(playlistId)
    }
}
