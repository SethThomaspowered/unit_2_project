package com.example.ultimateplaylist.service;

import com.example.ultimateplaylist.exception.InformationExistsException;
import com.example.ultimateplaylist.model.Playlist;
import com.example.ultimateplaylist.repository.PlaylistRepository;
import com.example.ultimateplaylist.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Logger;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;
    private static final Logger LOGGER = Logger.getLogger(PlaylistService.class.getName());
    @Autowired
    private void setPlaylistRepository(PlaylistRepository playlistRepository){
        this.playlistRepository=playlistRepository;
    }
    public Playlist createPlaylist(@RequestBody Playlist playlistObject){
        LOGGER.info("calling createPlaylist from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByUserIdAndTitle(userDetails.getUser().getId(), playlistObject.getTitle());
        if (playlist != null) {
            throw new InformationExistsException("Playlist with title " + playlist.getTitle() + " already exists");
        } else {
            playlistObject.setUser(userDetails.getUser());
            return playlistRepository.save(playlistObject);
        }
    }
}
