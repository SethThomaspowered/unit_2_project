package com.example.ultimateplaylist.service;
import com.example.ultimateplaylist.exception.InformationExistsException;
import com.example.ultimateplaylist.exception.InformationNotFoundException;
import com.example.ultimateplaylist.model.Artist;
import com.example.ultimateplaylist.model.Playlist;
import com.example.ultimateplaylist.repository.ArtistRepository;
import com.example.ultimateplaylist.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.logging.Logger;

public class ArtistService {
    private ArtistRepository artistRepository;
    private static final Logger LOGGER = Logger.getLogger(PlaylistService.class.getName());

    // Intention is to create user admin and use their credentials only to edits Artists
    public Artist createArtist(@RequestBody Artist artistObject){
        LOGGER.info("calling createPlaylist from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Artist artist = artistRepository.findByUserIdAndName(userDetails.getUser().getId(), artistObject.getName());
        if (artist != null) {
            throw new InformationExistsException("Artist with name " + artist.getName() + " already exists");
        } else {
            artistObject.setUser(userDetails.getUser());
            return artistRepository.save(artistObject);
        }
    }

    public List<Artist> getArtists(){
        LOGGER.info("calling getPlaylists method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Artist> artists = artistRepository.findByUserId(userDetails.getUser().getId());
        if(artists.isEmpty()){
            throw new InformationNotFoundException("No playlists are listed for this user");
        }else{
            return artists;
        }
    }

    public Artist getArtist(Long artistId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Artist artist = artistRepository.findByIdAndUserId(artistId, userDetails.getUser().getId());
        if (artist == null) {
            throw new InformationNotFoundException("Artist with id " + artistId + " not found");
        } else{
            return artist;
        }
    }
}
