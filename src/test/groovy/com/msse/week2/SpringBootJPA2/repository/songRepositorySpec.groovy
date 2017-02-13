package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Artist
import com.msse.week2.SpringBootJPA2.model.Release
import com.msse.week2.SpringBootJPA2.model.Song
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException

@SpringBootTest
@Transactional
class songRepositorySpec extends Specification {

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

    //to be used in parameters
    @Shared Release myRelease
    @Shared Artist myArtist

    def 'S1 - Negative test for "Song require a title and a Release to be saved"'(){
        //Create a song with invalid parameters, attempt to insert, throw errors.
        given:
            def song= new Song(Parameters)
            myArtist = new Artist(artistname:'The Singer')
            artistRepository.save(myArtist)
            myRelease = new Release(releaseName:'TopHits', releaseType:'single', artist: myArtist)
            releaseRepository.save(myRelease)

        when:
            songRepository.save(song)

        then:
            thrown(exceptionType)

        where:
            Description                | exceptionType                      | Parameters
            'Missing all'              | ConstraintViolationException       | [:]
            'Missing songName'         | ConstraintViolationException       | [release: myRelease]
            'Missing release'          | ConstraintViolationException       | [songName: 'My Song']
            'All are null'             | ConstraintViolationException       | [songName: null, release:null]
            'null songName'            | ConstraintViolationException       | [songName:null, release: myRelease]
            'null release'             | ConstraintViolationException       | [songName: 'My Song', release:null]
            'empty songName'           | ConstraintViolationException       | [songName:'', release: myRelease]
    }

    def 'S1 - Positive test for "Song require a title and a Release to be saved"'() {
        //Create song with the proper name / release, insert, confirm successful insert by comparing counts before and after
        given:
            def recCount = songRepository.count()
            myArtist = new Artist(artistname:'The Singer')
            artistRepository.save(myArtist)
            myRelease = new Release(releaseName:'TopHits', releaseType:'single', artist: myArtist)
            releaseRepository.save(myRelease)
            def newSong = new Song(songName:'Happy Birthday Song', release: myRelease)

        when:
            songRepository.save(newSong)

        then:
            songRepository.count() == recCount + 1
    }

    def 'S6 -Positive test for "Search for song by title with wildcard (*Love*)"'(){
        // Create a song called "Love Song", search for "love" and find "Love Song"
        given:
            myArtist = new Artist(artistname:'The Singer')
            artistRepository.save(myArtist)
            myRelease = new Release(releaseName:'TopHits', releaseType:'single', artist: myArtist)
            releaseRepository.save(myRelease)
            def newSong = new Song(songName:'Love Song', release: myRelease)
            songRepository.save(newSong)

        when:
            List<Song> songs = songRepository.findBySongNameContainingIgnoreCase('love')

        then:
            songs.find()?.songName == 'Love Song'
    }

    def 'S6 -Negative test for "Search for song by title with wildcard (*Love*)"'(){
        // Create a song called "Happy Birthday Song", search for "love" and find nothing
        given:
            myArtist = new Artist(artistname:'The Singer')
            artistRepository.save(myArtist)
            myRelease = new Release(releaseName:'TopHits', releaseType:'single', artist: myArtist)
            releaseRepository.save(myRelease)
            def newSong = new Song(songName:'Happy Birthday Song', release: myRelease)
            songRepository.save(newSong)

        when:
            List<Song> songs = songRepository.findBySongNameContainingIgnoreCase('love')

        then:
            songs.find() == null
    }

}
