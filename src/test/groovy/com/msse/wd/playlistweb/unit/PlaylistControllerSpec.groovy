package com.msse.wd.playlistweb.unit

import com.msse.wd.playlistweb.controller.PlaylistController
import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.service.PlaylistService
import spock.lang.Specification

class PlaylistControllerSpec extends Specification {




        def "add Playlist"(){
            setup:
            def playlistService = Mock(PlaylistService)
            def playlistController = new PlaylistController(playlistService: playlistService)
            def inputPlaylist = new Playlist(playlistname: "Playlist1")
            when:
            def actual = playlistController.addPlaylist(inputPlaylist)

            then:
            playlistService.addPlaylist(inputPlaylist) >> inputPlaylist
            actual == inputPlaylist
        }
    }

