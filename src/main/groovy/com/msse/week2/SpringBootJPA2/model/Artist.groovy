package com.msse.week2.SpringBootJPA2.model

import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull

@Entity
class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column @NotBlank @NotNull @NotEmpty
    String artistname

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Release> releases
}
