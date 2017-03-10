package com.msse.wd.playlistweb.functional

import com.msse.wd.playlistweb.controller.rest.AccountController
import com.msse.wd.playlistweb.model.Account
import com.msse.wd.playlistweb.model.Artist
import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.model.Release
import com.msse.wd.playlistweb.model.Song
import com.msse.wd.playlistweb.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

    @ContextConfiguration
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class PlaylistFunctionalTests extends Specification {

        @Autowired
        private TestRestTemplate testRestTemplate

        def "/playlists - GET method not allowed"() {
            when:
            ResponseEntity<Exception> response = this.testRestTemplate.getForEntity("/playlists", Exception)

            then:
            response.statusCode == HttpStatus.METHOD_NOT_ALLOWED
            response.body.message == "Request method 'GET' not supported"
        }

        def "/playlists - POST adds playlist to an account"() {
            when:

            def inputAccount = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')

            ResponseEntity<Playlist> response = this.testRestTemplate.postForEntity("/playlists", new Playlist(playlistname: "Playlist1", account:inputAccount ), Playlist.class)

            then:
            response.statusCodeValue == 200
            response.headers.getContentType() == MediaType.APPLICATION_JSON_UTF8
            Playlist actual = response.body
            actual.account == inputAccount
            actual.playlistname == "Playlist1"

           }

        def "/playlists - POST adds song to a playlist"() {
            when:

            def inputAccount = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')
            def inputArtist = new Artist(artistname:'TheSinger')
            def inputRelease = new Release(releaseName:'TopHits',artist: inputArtist, releaseType:'single')
            def inputSong = new Song(songName:'song1',release: inputRelease)

            ResponseEntity<Playlist> response = this.testRestTemplate.postForEntity("/playlists", new Playlist(playlistname: "Playlist2", account: inputAccount,songs: [inputSong]), Playlist.class)

            then:
            response.statusCodeValue == 200
            response.headers.getContentType() == MediaType.APPLICATION_JSON_UTF8
            Playlist actual = response.body
            actual.account == inputAccount
            actual.playlistname == "Playlist2"
            actual.songs == "song1"
          }
    }


