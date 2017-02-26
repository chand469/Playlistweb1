package com.msse.wd.playlistweb.repository

import com.msse.wd.playlistweb.model.Release
import org.springframework.data.jpa.repository.JpaRepository

interface ReleaseRepository extends JpaRepository<Release,Long> {

}