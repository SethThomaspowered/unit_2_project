
package com.example.ultimateplaylist.service;

import com.example.ultimateplaylist.exception.InformationExistsException;
import com.example.ultimateplaylist.exception.InformationNotFoundException;
import com.example.ultimateplaylist.model.Audiobook;
import com.example.ultimateplaylist.model.Music;
import com.example.ultimateplaylist.model.Playlist;
import com.example.ultimateplaylist.model.Podcast;
import com.example.ultimateplaylist.repository.*;
import com.example.ultimateplaylist.security.MyUserDetails;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    private PodcastRepository podcastRepository;
    @Autowired
    public void setPodcastRepository(PodcastRepository podcastRepository){
        this.podcastRepository = podcastRepository;
    }
    private AudiobookRepository audiobookRepository;
    @Autowired
    public void setAudiobookRepository(AudiobookRepository audiobookRepository){
        this.audiobookRepository = audiobookRepository;
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
            playlist.setTitle(playlistObject.getTitle());
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

    public Music addPlaylistMusic(Long playlistId, Music musicObject) {
        System.out.println("service calling addPlaylistMusic ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        if (playlist == null) {
            throw new InformationNotFoundException(
                    "Playlist with id " + playlistId + " not belongs to this user or playlist does not exist");
        }
        Music music = musicRepository.findByTitleAndUserId(musicObject.getTitle(), userDetails.getUser().getId());
        if (music != null) {
            throw new InformationExistsException("Music with title " + music.getTitle() + " already exists");
        }
        musicObject.setUser(userDetails.getUser());
        musicObject.setPlaylist(playlist);
        return musicRepository.save(musicObject);
    }

    public List<Music> getPlaylistMusicList(Long playlistId){
        LOGGER.info("calling getPlaylistMusic method from service");
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            return playlist.get().getMusicList();
        } else {
            throw new InformationNotFoundException("Playlist with id " + playlistId + " not found");
        }
    }
    public Music getPlaylistMusic(Long playlistId, Long musicId) {
        LOGGER.info("calling getPlaylistMusic method from service");
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            Optional<Music> music = musicRepository.findByPlaylistId(playlistId).stream().filter(
                    p -> p.getId().equals(musicId)).findFirst();
            if (music.isEmpty()) {
                throw new InformationNotFoundException("Songs with " + musicId + " not found");
            } else return music.get();
        } else {
            throw new InformationNotFoundException("No playlist with id " + playlistId + " not found");
        }
    }

    public Music updatePlaylistMusic(Long playlistId, Long musicId, Music musicObject) {
        LOGGER.info("service calling updatePlaylistMusic ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        try {
            Music music = (musicRepository.findByPlaylistId(
                    playlistId).stream().filter(p -> p.getId().equals(musicId)).findFirst()).get();
            music.setTitle(musicObject.getTitle());
            music.setLength(musicObject.getLength());
            music.setReleaseDate(musicObject.getReleaseDate());
            music.setArtist(musicObject.getArtist());
            return musicRepository.save(music);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("music track or playlist not found");
        }
    }

    public Music deletePlaylistMusic(Long playlistId, Long musicId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        try {
            Music music = (musicRepository.findByPlaylistId(
                    playlistId).stream().filter(p -> p.getId().equals(musicId)).findFirst()).get();
            musicRepository.deleteById(music.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("music track or playlist not found");
        }
        return null;
    }
    public Podcast addPlaylistPodcast(Long playlistId, Podcast podcastObject) {
        System.out.println("service calling addPlaylistPodcast ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        if (playlist == null) {
            throw new InformationNotFoundException(
                    "Playlist with id " + playlistId + " not belongs to this user or playlist does not exist");
        }
        Podcast podcast = podcastRepository.findByTitleAndUserId(podcastObject.getTitle(), userDetails.getUser().getId());
        if (podcast != null) {
            throw new InformationExistsException("Podcast with title " + podcast.getTitle() + " already exists");
        }
        podcastObject.setUser(userDetails.getUser());
        podcastObject.setPlaylist(playlist);
        return podcastRepository.save(podcastObject);
    }

    public List<Podcast> getPlaylistPodcastList(Long playlistId){
        LOGGER.info("calling getPlaylistPodcastList method from service");
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            return playlist.get().getPodcastList();
        } else {
            throw new InformationNotFoundException("Playlist with id " + playlistId + " not found");
        }
    }
    public Podcast getPlaylistPodcast(Long playlistId, Long podcastId) {
        LOGGER.info("calling getPlaylistPodcast method from service");
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isPresent()) {
            Optional<Podcast> podcast = podcastRepository.findById(playlistId).stream().filter(
                    p -> p.getId().equals(podcastId)).findFirst();
            if (podcast.isEmpty()) {
                throw new InformationNotFoundException("Podcast with " + podcastId + " not found");
            } else return podcast.get();
        } else {
            throw new InformationNotFoundException("No playlist with id " + playlistId + " not found");
        }
    }
    public Podcast updatePlaylistPodcast(Long playlistId, Long podcastId, Podcast podcastObject) {
        LOGGER.info("service calling updatePlaylistPodcast ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        try {
            Podcast podcast = (podcastRepository.findById(
                    playlistId).stream().filter(p -> p.getId().equals(podcastId)).findFirst()).get();
            podcast.setTitle(podcastObject.getTitle());
            podcast.setLength(podcastObject.getLength());
            podcast.setReleaseDate(podcastObject.getReleaseDate());
//            podcast.setArtist(podcastObject.getArtist());
            return podcastRepository.save(podcast);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Podcast or playlist not found");
        }
    }
    public Podcast deletePlaylistPodcast(Long playlistId, Long podcastId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        try {
            Podcast podcast = (podcastRepository.findById(
                    playlistId).stream().filter(p -> p.getId().equals(podcastId)).findFirst()).get();
            podcastRepository.deleteById(podcast.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("Podcast or playlist not found");
        }
        return null;
    }
    public Audiobook addPlaylistAudiobook(Long playlistId, Audiobook audiobookObject) {
        LOGGER.info("service calling addPlaylistAudiobook ==>");
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
        if (playlist == null) {
            throw new InformationNotFoundException(
                    "Playlist with id " + playlistId + " not belongs to this user or playlist does not exist");
        }
        Audiobook audiobook = audiobookRepository.findByTitleAndUserId(audiobookObject.getTitle(), userDetails.getUser().getId());
        if (podcast != null) {
            throw new InformationExistsException("Podcast with title " + podcast.getTitle() + " already exists");
        }
        podcastObject.setUser(userDetails.getUser());
        podcastObject.setPlaylist(playlist);
        return podcastRepository.save(podcastObject);
    }

//    public List<Podcast> getPlaylistPodcastList(Long playlistId){
//        LOGGER.info("calling getPlaylistPodcastList method from service");
//        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
//        if (playlist.isPresent()) {
//            return playlist.get().getPodcastList();
//        } else {
//            throw new InformationNotFoundException("Playlist with id " + playlistId + " not found");
//        }
//    }
//    public Podcast getPlaylistPodcast(Long playlistId, Long podcastId) {
//        LOGGER.info("calling getPlaylistPodcast method from service");
//        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
//        if (playlist.isPresent()) {
//            Optional<Podcast> podcast = podcastRepository.findById(playlistId).stream().filter(
//                    p -> p.getId().equals(podcastId)).findFirst();
//            if (podcast.isEmpty()) {
//                throw new InformationNotFoundException("Podcast with " + podcastId + " not found");
//            } else return podcast.get();
//        } else {
//            throw new InformationNotFoundException("No playlist with id " + playlistId + " not found");
//        }
//    }
//    public Podcast updatePlaylistPodcast(Long playlistId, Long podcastId, Podcast podcastObject) {
//        LOGGER.info("service calling updatePlaylistPodcast ==>");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
//        try {
//            Podcast podcast = (podcastRepository.findById(
//                    playlistId).stream().filter(p -> p.getId().equals(podcastId)).findFirst()).get();
//            podcast.setTitle(podcastObject.getTitle());
//            podcast.setLength(podcastObject.getLength());
//            podcast.setReleaseDate(podcastObject.getReleaseDate());
////            podcast.setArtist(podcastObject.getArtist());
//            return podcastRepository.save(podcast);
//        } catch (NoSuchElementException e) {
//            throw new InformationNotFoundException("Podcast or playlist not found");
//        }
//    }
//    public Podcast deletePlaylistPodcast(Long playlistId, Long podcastId) {
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        Playlist playlist = playlistRepository.findByIdAndUserId(playlistId, userDetails.getUser().getId());
//        try {
//            Podcast podcast = (podcastRepository.findById(
//                    playlistId).stream().filter(p -> p.getId().equals(podcastId)).findFirst()).get();
//            podcastRepository.deleteById(podcast.getId());
//        } catch (NoSuchElementException e) {
//            throw new InformationNotFoundException("Podcast or playlist not found");
//        }
//        return null;
//    }

}