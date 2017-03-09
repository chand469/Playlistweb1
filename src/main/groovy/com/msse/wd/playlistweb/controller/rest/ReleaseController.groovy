package com.msse.wd.playlistweb.controller.rest

import com.msse.wd.playlistweb.model.Release
import com.msse.wd.playlistweb.service.ReleaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

import javax.servlet.http.HttpServletResponse

class ReleaseController {

    @Autowired
    ReleaseService releaseService

    @PostMapping
    Release addRelease(@RequestBody Release release) {
        return releaseService.addRelease(release)
    }

    @GetMapping('/{releaseId}')
    Release getRelease(@PathVariable Long ReleaseId, HttpServletResponse response) {
        Release release = releaseService.getRelease(releaseId)
        if (!release) {
            response.setStatus(404)
        }
        return release
    }

    @PutMapping('/{releaseId}')
    Release updateRelease(@PathVariable Long releaseId, @RequestBody Release release, HttpServletResponse response) {
        Release releasedb = releaseService.getRelease(releaseId)
        if (!releasedb) {
            response.setStatus(404)
        }
        return releaseService.updateRelease(release)
    }

    @DeleteMapping('/{playlistId}')
    boolean deleteRelease(@PathVariable Long releaseId, HttpServletResponse response) {
        Release release = releaseService.getRelease(releaseId)
        if (!release) {
            response.setStatus(404)
        }
        return releaseService.deleteRelease(releaseId)
    }
}
