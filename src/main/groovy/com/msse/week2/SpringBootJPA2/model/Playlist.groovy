package com.msse.week2.SpringBootJPA2.model

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @ManyToOne
    @JoinColumn
    @NotNull
    Account account

    @Column
    @NotEmpty
    @NotNull
    @Size(min = 5)
    String playlistname

    @ManyToMany
    List<Song> songs

}
