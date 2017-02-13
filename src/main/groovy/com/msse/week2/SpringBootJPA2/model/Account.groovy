package com.msse.week2.SpringBootJPA2.model

import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.GenerationType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.PrePersist
import javax.persistence.Transient
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Entity
class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Column(unique=true)
    @NotEmpty
    @Email
    String email

    @Transient
    @NotNull
    @SecurePassword
    String password = ''

    @Column
    @NotEmpty
    @Length(min = 3)
    String username

    @NotEmpty
    String passwordHash = ''

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Playlist> playlists

    private static def encoder = new BCryptPasswordEncoder()

    @PrePersist
    void encodePassword() {
        if (password) {
            passwordHash = encoder.encode(password)
        }
    }

}


