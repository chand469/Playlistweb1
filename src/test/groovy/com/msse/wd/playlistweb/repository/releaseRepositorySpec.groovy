package com.msse.wd.playlistweb.repository

import com.msse.wd.playlistweb.model.Artist
import com.msse.wd.playlistweb.model.Release
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException

@SpringBootTest
@Transactional
class releaseRepositorySpec extends Specification{

    @Autowired
    SongRepository songRepository
    @Autowired
    ReleaseRepository releaseRepository
    @Autowired
    ArtistRepository artistRepository

    @Shared Artist myArtist

    // Run before all the tests:
    def setupSpec() {
        //Create for use in parameters
        myArtist = new Artist(artistname:'TheSinger')
    }

    def 'S2. Negative test case for "Release requires a title, related artist and a release type"'(){
        //Create a Release without required values, attempt to insert, throw errors.
        given:
            def release= new Release(Parameters)

        when:
            artistRepository.save(myArtist)
            releaseRepository.save(release)

        then:
            thrown(exceptionType)

        where:
            Description                           | exceptionType                   | Parameters
            'missing all'                         | ConstraintViolationException    | [:]
            'releaseName missing'                 | ConstraintViolationException    | [releaseType: 'single',  artist:myArtist]
            'releaseType missing'                 | ConstraintViolationException    | [releaseName: 'My Release', artist:myArtist]
            'artist missing'                      | ConstraintViolationException    | [releaseName: 'My Release', releaseType:'single']
            'all null'                            | ConstraintViolationException    | [releaseName:  null ,artist: null, releaseType:null]
            'releaseName null'                    | ConstraintViolationException    | [releaseType: 'single', releaseName:null, artist:myArtist]
            'releaseType null'                    | ConstraintViolationException    | [releaseName: 'TopHits',artist: myArtist, releaseType:null]
            'artist null'                         | ConstraintViolationException    | [releaseName: 'My Release', releaseType:'single', artist:null]
            'releaseName and releaseType  empty'  | ConstraintViolationException    | [releaseName: '' ,artist: myArtist, releaseType:'']
            'releaseName empty'                   | ConstraintViolationException    | [releaseType: 'single', releaseName:'', artist:myArtist]
            'releaseType empty'                   | ConstraintViolationException    | [releaseName: 'TopHits',artist: myArtist, releaseType:'']
    }

    def 'S2. Positive test case for "Release requires a title, related artist and a release type"'() {
        //Create a release with a proper field values, insert, confirm successful insert by comparing counts before and after
        given:
            def recCount = releaseRepository.count()
            def newArtist = new Artist(artistname:'TheSinger')
            artistRepository.save(newArtist)
            def newRelease = new Release(releaseName:'TopHits',artist: newArtist, releaseType:'single')

        when:
            releaseRepository.save(newRelease)

        then:
            releaseRepository.count() == recCount + 1
    }

    def 'S2. Positive test case for "Release Type valid values are (single, album, compilation)"'(){
        //Create releases with the proper types, insert, confirm successful insert by comparing counts before and after
        given:
            def recCount = releaseRepository.count()
            def newArtist = new Artist(artistname:'TheSinger')
            artistRepository.save(newArtist)
            def newReleaseSingle = new Release(releaseName:'Newest Single',artist: newArtist, releaseType:'single')
            def newReleaseAlbum = new Release(releaseName:'Favorite Album',artist: newArtist, releaseType:'album')
            def newReleaseCompilation = new Release(releaseName:'Best Compilation',artist: newArtist, releaseType:'compilation')

        when:
            releaseRepository.save(newReleaseSingle)
            releaseRepository.save(newReleaseAlbum)
            releaseRepository.save(newReleaseCompilation)

        then:
            recCount +3 == releaseRepository.count()
    }

    def 'S2. Negative test case for "Release Type valid values are (single, album, compilation)"'(){
        //Create an Release without proper type value, attempt to insert, throw errors.
        given:
            def newArtist = new Artist(artistname:'TheSinger')
            artistRepository.save(newArtist)
            def newRelease = new Release(releaseName:'TopHits',artist: newArtist, releaseType:'Single1')

        when:
            releaseRepository.save(newRelease)

        then:
            thrown(ConstraintViolationException)
    }

    def 'S3. Release can have an optional date field' (){
        //Create two releases, one with release date, one without. insert records, query to fetch values and show that one has value and the other does noe
        given:
            def newArtist = new Artist(artistname:'TheSinger')
            artistRepository.save(newArtist)
            def releaseWithoutDate = new Release(releaseName:'TopHits',artist: newArtist, releaseType:'single')
            def releaseWithDate = new Release(releaseName:'TopHits',artist: newArtist, releaseType:'single', releaseDate: new Date())
            releaseRepository.save(releaseWithoutDate)
            releaseRepository.save(releaseWithDate)

        when:
            Release fetchedReleaseWithoutDate = releaseRepository.findOne(releaseWithoutDate.id)
            Release fetchedReleaseWithDate = releaseRepository.findOne(releaseWithDate.id)

        then:
            fetchedReleaseWithoutDate.releaseDate==null
            fetchedReleaseWithDate.releaseDate!=null
    }

}
