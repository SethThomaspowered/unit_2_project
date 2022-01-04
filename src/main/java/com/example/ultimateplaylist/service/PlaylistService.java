package com.example.ultimateplaylist.service;

import com.example.ultimateplaylist.exception.InformationExistsException;
import com.example.ultimateplaylist.exception.InformationNotFoundException;
import com.example.ultimateplaylist.model.Music;
import com.example.ultimateplaylist.model.Playlist;
import com.example.ultimateplaylist.repository.MusicRepository;
import com.example.ultimateplaylist.repository.PlaylistRepository;
import com.example.ultimateplaylist.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;
    private MusicRepository musicRepository;
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
    public List<Playlist> getPlaylists(){
        LOGGER.info("calling getPlaylists method from service");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        List<Playlist> playlists = playlistRepository.findByUserId(userDetails.getUser().getId());
        if(playlists.isEmpty()){
            throw new InformationNotFoundException("No playlists are listed for this user");
        }else{
            return playlists;
        }
    }
    public Playlist getPlaylist(Long playlistId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        if (playlist == null) {
            throw new InformationNotFoundException("Playlist with id " + playlistId + " not found");
        } else{
            return playlist;
        }
    }
    public Playlist updatePlaylist(Long playlistId, Playlist playlistObject) {
        LOGGER.info("service calling updatePlaylist ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        if (playlist == null) {
            throw new InformationNotFoundException("Playlist with id " + playlistId + " not found");
        } else {
            playlist.setDescription(playlistObject.getDescription());
            playlist.setTitle(playlist.getTitle());
            playlist.setUser(userDetails.getUser());
            return playlistRepository.save(playlist);
        }
    }
    public String deletePlaylist(Long playlistId) {
        LOGGER.info("service calling deletePlaylist ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        if (playlist == null) {
            throw new InformationNotFoundException("Playlist with id " + playlistId + " not found");
        } else {
            playlistRepository.deleteById(playlistId);
            return "Playlist with id " + playlistId + " has been successfully deleted";
        }
    }
    @Autowired
    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }
    



//    @Autowired
//    public void setMusicRepository(MusicRepository musicRepository) {
//        this.musicRepository = musicRepository;
//    }
//
//    public Music updatePlaylistMusic(Long playlistId, Long musicId, Music musicObject) {
//        LOGGER.info("service calling updatePlaylistMusic ==>");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        try {
//            Music music = (musicRepository.findByPlaylistId(
//                    playlistId).stream().filter(p -> p.getId().equals(musicId)).findFirst()).get();
//            music.setTitle(musicObject.getTitle());
//            music.setLength(musicObject.getLength());
//            music.setReleaseDate(musicObject.getReleaseDate());
//            return musicRepository.save(music);
//        } catch (NoSuchElementException e) {
//            throw new InformationNotFoundException("music track or playlist not found");
//        }
//    }
//
//    public Music deletePlaylistMusic(Long playlistId, Long musicId) {
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        //Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
//        try {
//            Music music = (musicRepository.findByPlaylistId(
//                    playlistId).stream().filter(p -> p.getId().equals(musicId)).findFirst()).get();
//            musicRepository.deleteById(music.getId());
//        } catch (NoSuchElementException e) {
//            throw new InformationNotFoundException("music track or playlist not found");
//        }
//        return null;
//    }
}
