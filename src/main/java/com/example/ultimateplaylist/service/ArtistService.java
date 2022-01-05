package com.example.ultimateplaylist.service;
import com.example.ultimateplaylist.exception.InformationExistsException;
import com.example.ultimateplaylist.exception.InformationNotFoundException;
import com.example.ultimateplaylist.model.Artist;
import com.example.ultimateplaylist.repository.ArtistRepository;
import com.example.ultimateplaylist.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ArtistService {
    private ArtistRepository artistRepository;
    private static final Logger LOGGER = Logger.getLogger(PlaylistService.class.getName());

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Intention is to create user admin and use their credentials only to edits Artists
    public Artist createArtist(@RequestBody Artist artistObject){
        LOGGER.info("calling createArtist from service");

        Artist artist = artistRepository.findByName(artistObject.getName());
        if (artist != null) {
            throw new InformationExistsException("Artist with name " + artist.getName() + " already exists");
        } else {
            return artistRepository.save(artistObject);
        }
    }

    public List<Artist> getArtists(){
        LOGGER.info("calling getPlaylists method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Artist> artists = artistRepository.findAll();
        if(artists.isEmpty()){
            throw new InformationNotFoundException("No Artist are listed for this user");
        }else{
            return artists;
        }
    }

    public Artist getArtist(Long artistId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Artist artist = artistRepository.getById(artistId);
        if (artist == null) {
            throw new InformationNotFoundException("Artist with id " + artistId + " not found");
        } else{
            return artist;
        }
    }
}
