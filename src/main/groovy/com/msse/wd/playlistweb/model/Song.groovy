package com.msse.wd.playlistweb.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@Entity
class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column
    @NotEmpty
    String songName

    @ManyToOne
    @NotNull
    @JoinColumn
    Release release

}
