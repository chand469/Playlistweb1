package com.msse.wd.playlistweb.unit

import com.msse.wd.playlistweb.controller.rest.AccountController
import com.msse.wd.playlistweb.controller.rest.ArtistController
import com.msse.wd.playlistweb.controller.rest.PlaylistController
import com.msse.wd.playlistweb.controller.rest.ReleaseController
import com.msse.wd.playlistweb.controller.rest.SongController
import com.msse.wd.playlistweb.model.Account
import com.msse.wd.playlistweb.model.Artist
import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.model.Release
import com.msse.wd.playlistweb.model.Song
import com.msse.wd.playlistweb.repository.AccountRepository
import com.msse.wd.playlistweb.service.AccountService
import com.msse.wd.playlistweb.service.ArtistService
import com.msse.wd.playlistweb.service.PlaylistService
import com.msse.wd.playlistweb.service.ReleaseService
import com.msse.wd.playlistweb.service.SongService
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class PlaylistControllerSpec extends Specification {

    @Autowired
    AccountRepository accountRepository

    /*@Shared Account myAccount

    def setupSpec() { /* setting up the myAccount instance to be used in the invalid parameters test method */
       // myAccount = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')
  //  }


        def "Creat a Playlist for an account"(){

            setup:

            def accountService = Mock(AccountService)
            def accountController = new AccountController(accountService: accountService)
            def inputAccount = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')

            def playlistService = Mock(PlaylistService)
            def playlistController = new PlaylistController(playlistService: playlistService)
            def inputPlaylist = new Playlist(playlistname: "Playlist1", account: inputAccount)

            when:
            def actual = playlistController.addPlaylist(inputPlaylist)

            then:
            playlistService.addPlaylist(inputPlaylist) >> inputPlaylist
            actual == inputPlaylist
        }


    def "add a Song to a Playlist"(){

        setup:

        def accountService = Mock(AccountService)
        def accountController = new AccountController(accountService: accountService)
        def inputAccount = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')

        def artistService = Mock(ArtistService)
        def artistController = new ArtistController(artistService: artistService)
        def inputArtist = new Artist(artistname:'TheSinger')

        def releaseService = Mock(ReleaseService)
        def releaseController = new ReleaseController(releaseService: releaseService)
        def inputRelease = new Release(releaseName:'TopHits',artist: inputArtist, releaseType:'single')

        def songService = Mock(SongService)
        def songController = new SongController(songService: songService)
        def inputSong = new Song(songName:'song1',release: inputRelease)

        def playlistService = Mock(PlaylistService)
        def playlistController = new PlaylistController(playlistService: playlistService)
        def inputPlaylist = new Playlist(playlistname: "Playlist1", account: inputAccount, songs:[inputSong])



        when:
        def actual = playlistController.addPlaylist(inputPlaylist)

        then:
        playlistService.addPlaylist(inputPlaylist) >> inputPlaylist
        actual == inputPlaylist
    }
    }

