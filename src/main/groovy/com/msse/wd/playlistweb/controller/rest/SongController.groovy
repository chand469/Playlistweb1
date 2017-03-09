package com.msse.wd.playlistweb.controller.rest

import com.msse.wd.playlistweb.model.Song
import com.msse.wd.playlistweb.service.SongService
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
@RequestMapping('/songs')
class SongController {
    @Autowired
    SongService songService

    @PostMapping
    Song addSong(@RequestBody Song song) {
        return songService.addSong(song)
    }

    @GetMapping('/{songId}')
    Song getSong(@PathVariable Long songId, HttpServletResponse response) {
        Song song = songService.getSong(songId)
        if (!song) {
            response.setStatus(404)
        }
        return song
    }

    @PutMapping('/{songId}')
    Song updateSong(@PathVariable Long songId, @RequestBody Song song, HttpServletResponse response) {
        Song songdb = songService.getSong(songId)
        if (!songdb) {
            response.setStatus(404)
        }
        return songService.updateSong(song)
    }

    @DeleteMapping('/{songId}')
    boolean deleteSong(@PathVariable Long songId, HttpServletResponse response) {
        Song song = songService.getSong(songId)
        if (!song) {
            response.setStatus(404)
        }
        return songService.deleteSong(songId)
    }

}
