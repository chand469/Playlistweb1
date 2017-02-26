package com.msse.wd.playlistweb.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.NotNull

@Entity
class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column
    @NotEmpty
    String releaseName

    @Column
    @NotEmpty
    String releaseType

    @Column
    Date releaseDate

    @ManyToOne
    @JoinColumn
    @NotNull
    Artist artist

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Song> songs

    @AssertTrue
    boolean isValidType() {
        boolean bool = false
        if(releaseType == 'single' || releaseType == 'album' || releaseType == 'compilation'){
            bool = true
        }
        return bool
    }
}
