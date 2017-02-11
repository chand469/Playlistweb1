package com.msse.week2.SpringBootJPA2.model

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GenerationType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Transient
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column(unique=true) @NotBlank @NotNull @NotEmpty @Email
    String email

    @Column @Size(min = 8, max = 16) @NotBlank @NotNull @NotEmpty
   // @Transient
    // @Pattern(regexp ="\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}\$\"")
    String password

    @Column @NotBlank @NotNull @NotEmpty
    String username

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Playlist> playlists

}
