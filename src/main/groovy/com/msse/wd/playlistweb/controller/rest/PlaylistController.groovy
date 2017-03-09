package com.msse.wd.playlistweb.controller.rest

import com.msse.wd.playlistweb.model.Playlist
import com.msse.wd.playlistweb.service.PlaylistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PlaylistController {

    @Autowired
    PlaylistService playlistService

   @RequestMapping("/playlistView")
    String playlistView(Model model, Pageable request) {
        Page page = playlistService.listPlaylists(new PageRequest(request.pageNumber, request.pageSize, new Sort(Sort.Direction.DESC, "createdDate")))
        model.addAttribute("page", page)
        return "playlistView"
    }

    @GetMapping("/playlistView1")
    @ResponseBody
    Map nonMappedView(Model model, Pageable request) {
        return [data: [d1: 1, d2: 2]]
    }

    @PostMapping
    Playlist addPlaylist(@RequestBody Playlist playlist) {
       return playlistService.addPlaylist(playlist)
    }


}