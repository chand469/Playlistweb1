package com.msse.wd.playlistweb.controller.rest

import com.msse.wd.playlistweb.model.Artist
import com.msse.wd.playlistweb.service.ArtistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

import javax.servlet.http.HttpServletResponse


class ArtistController {
    @Autowired
    ArtistService artistService

    @PostMapping
    Artist addArtist(@RequestBody Artist artist) {
        return artistService.addArtist(artist)
    }

    @GetMapping('/{artistId}')
    Artist getArtist(@PathVariable Long artistId, HttpServletResponse response) {
        Artist artist = artistService.getArtist(artistId)
        if (!artist) {
            response.setStatus(404)
        }
        return artist
    }

    @PutMapping('/{artistId}')
    Artist updateArtist(@PathVariable Long artistId, @RequestBody Artist artist, HttpServletResponse response) {
        Artist artistdb = artistService.getArtist(artistId)
        if (!artistdb) {
            response.setStatus(404)
        }
        return artistService.updateArtist(artist)
    }

    @DeleteMapping('/{artistId}')
    boolean deleteArtist(@PathVariable Long artistId, HttpServletResponse response) {
        Artist artist = artistService.getArtist(artistId)
        if (!artist) {
            response.setStatus(404)
        }
        return artistService.deleteArtist(artistId)
    }

}
