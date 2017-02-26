package com.msse.wd.playlistweb.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column
    @NotEmpty
    String artistname

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Release> releases
}
