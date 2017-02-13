package com.msse.week2.SpringBootJPA2.repository

import com.msse.week2.SpringBootJPA2.model.Artist
import com.msse.week2.SpringBootJPA2.model.Release
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import javax.transaction.Transactional
import javax.validation.ConstraintViolationException

@SpringBootTest
@Transactional
class artistRepositorySpec extends Specification{

    @Autowired
    ArtistRepository artistRepository
    @Autowired
    ReleaseRepository releaseRepository

    def 'S4 - Negative test case for "Artist can be saved with a required name field"'(){
        //Create an Artist without name or with an empty string as name, attempt to insert, throw errors.
        given:
            def artist= new Artist(Parameters)

        when:
            artistRepository.save(artist)

        then:
            thrown(exceptionType)

        where:
            Description                | exceptionType                       | Parameters
            'artistname missing'       | ConstraintViolationException        | [:]
            'artistname null'          | ConstraintViolationException        | [artistname: null]
            'artistname empty'         | ConstraintViolationException        | [artistname: '']

    }

    def 'S4 - Positive test case for "Artist can be saved with a required name field"'() {
        //Create an Artist with a proper name, insert, confirm successful insert by comparing counts before and after
        given:
            def recCount = artistRepository.count()
            def newArtist = new Artist(artistname:'The Singer')

        when:
            artistRepository.save(newArtist)

        then:
            artistRepository.count() == recCount + 1
    }

    def 'S5 - An artist can be related to many releases'(){
        //Show that it is possible to have an Artist with 0, 1, or many releases
        // Insert Artists and releases, query for Artists with releases, and check counts.
        given:
            def artistWithZero = new Artist(artistname:'The Singer')
            def artistWithOne = new Artist(artistname:'The Guitar Player')
            def artistWithMany = new Artist(artistname:'Prince')
            artistRepository.save(artistWithZero)
            artistRepository.save(artistWithOne)
            artistRepository.save(artistWithMany)

            def Release1 = new Release(releaseName:'Top Hits',artist: artistWithMany, releaseType:'single')
            def Release2 = new Release(releaseName:'Greatest Hits',artist: artistWithMany, releaseType:'single')
            def Release3 = new Release(releaseName:'Best Hits',artist: artistWithOne, releaseType:'single')
            releaseRepository.save([(Release)Release1, (Release)Release2, (Release)Release3])

            def ManyReleaseList = [(Release)Release1, (Release)Release2]
            def oneReleaseList = [(Release)Release3]
            artistWithMany.releases=ManyReleaseList
            artistWithOne.releases=oneReleaseList
            artistWithZero.releases= []
            artistRepository.save(artistWithOne)
            artistRepository.save(artistWithMany)
            artistRepository.save(artistWithZero)

        when:
            Artist fetchedArtistWithMany = artistRepository.findOne(artistWithMany.id)
            Artist fetchedArtistWithOne = artistRepository.findOne(artistWithOne.id)
            Artist fetchedArtistWithZero = artistRepository.findOne(artistWithZero.id)

        then:
            fetchedArtistWithMany?.releases?.size()>1
            fetchedArtistWithOne?.releases?.size()==1
            fetchedArtistWithZero?.releases?.size()==0
    }
}
