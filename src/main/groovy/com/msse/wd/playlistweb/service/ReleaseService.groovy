package com.msse.wd.playlistweb.service


import com.msse.wd.playlistweb.model.Release
import com.msse.wd.playlistweb.repository.ReleaseRepository
import org.springframework.beans.factory.annotation.Autowired


class ReleaseService {

    @Autowired
    ReleaseRepository releaseRepository

    Release getRelease(Long id) {
        return releaseRepository.findOne(id)
    }

    Release addRelease(Release release) {
        return releaseRepository.save(release)
    }

    Release updateRelease(Release release) {
        return releaseRepository.save(release)
    }

    boolean deleteRelease(Long id) {
        releaseRepository.delete(id)
        return true
    }
}
