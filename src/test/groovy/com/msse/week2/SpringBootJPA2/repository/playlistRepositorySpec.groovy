package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Account
import com.msse.week2.SpringBootJPA2.model.Artist
import com.msse.week2.SpringBootJPA2.model.Playlist
import com.msse.week2.SpringBootJPA2.model.Release
import com.msse.week2.SpringBootJPA2.model.Song
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException

@SpringBootTest
@Transactional
class playlistRepositorySpec extends Specification {

    @Autowired
    PlaylistRepository playlistRepository
    @Autowired
    AccountRepository accountRepository
    @Autowired
    SongRepository songRepository
    @Autowired
    ArtistRepository artistRepository
    @Autowired
    ReleaseRepository releaseRepository


    @Shared Account myAccount

    def setupSpec() { /* setting up the myAccount instance to be used in the invalid parameters test method */
        myAccount = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')
    }

    def 'P1. A valid playlist requires a name and related account'() {
        given:
            def account = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu') /* creating an account */
            accountRepository.save(account) /* saving the account */

            def recCount = playlistRepository.count()
            def playlist1 = new Playlist(playlistname: 'Playlist1',account: account) /* creating Playlist1 for account 'MSSE') */

        when:
            playlistRepository.save(playlist1)   /* saving the created playlist */

        then:
            playlistRepository.count() == recCount + 1
    }

    def 'P2. A playlist is related to an ordered list of songs'() {

        given:
            def account = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu') /* creating an account */
            accountRepository.save(account)  /* saving the account */

            def newArtist = new Artist(artistname:'TheSinger')
            artistRepository.save(newArtist)

            def newRelease = new Release(releaseName:'TopHits',artist: newArtist, releaseType:'single')
            releaseRepository.save(newRelease)

            def song1 = new Song(songName: 'song1', release: newRelease)
            songRepository.save(song1) /* saving the first song */

            def song2 = new Song(songName: 'song2', release: newRelease)
            songRepository.save(song2) /* saving the second song */

            def recCount = playlistRepository.count()
            def playlist1 = new Playlist(playlistname: 'Playlist1', account: account, songs: [(Song) song1, (Song) song2])
            /* adding the songs 1 and 2 to the playlist 1 for the account 'MSSE' */

        when:
            playlistRepository.save(playlist1)   /* saving the Playlist 1  */

        then:
            playlistRepository.count() == recCount + 1
    }

    def 'P3. A song can be included in multiple Playlists'() {

        given:
            def account = new Account(username: 'MSSE', password: 'pass1.ms#.coM', email: 'msse@umn.edu')  /* creating an account */
            accountRepository.save(account) /* saving the account */

            def newArtist = new Artist(artistname:'TheSinger')
            artistRepository.save(newArtist)

            def newRelease = new Release(releaseName:'TopHits',artist: newArtist, releaseType:'single')
            releaseRepository.save(newRelease)

            def song1 = new Song(songName: 'song1', release: newRelease) /* creating a new song 'Song1 */
            songRepository.save(song1)  /* saving the first song */

            def recCount = playlistRepository.count()
            def playlist1 = new Playlist(playlistname: 'Playlist1', account: account, songs: [(Song) song1]) /* adding the song 'Song1' to Playlist1 */
            def playlist2 = new Playlist(playlistname: 'Playlist2', account: account, songs: [(Song) song1]) /* adding the song 'Song2' to Playlist1 */

        when:
            playlistRepository.save(playlist1) /* Saving Playlist1 with 'Song1' */
            playlistRepository.save(playlist2) /* Saving Playlist1 with 'Song2' */

        then:
            playlistRepository.count() == recCount + 2
    }

    def 'P4. Saving an invalid playlist'() {

        given:
            accountRepository.save(myAccount) /* saving the account */
            def playlist = new Playlist(Parameters)

        when:
            playlistRepository.save(playlist)

        then:
            thrown(exceptionType)

        where:
            Description   | exceptionType                | Parameters
            'missing all' | ConstraintViolationException | [:]   /* missing both playlist name and account */
            'null check1' | ConstraintViolationException | [playlistname: null, account: myAccount]  /* missing playlist name */
            'null check2' | ConstraintViolationException | [playlistname: 'Playlist1', account: null] /* missing account information */
    }
}
