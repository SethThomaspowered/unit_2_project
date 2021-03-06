package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.*;
import com.example.ultimateplaylist.service.ArtistService;
import com.example.ultimateplaylist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/api")

public class PlaylistController {

    private PlaylistService playlistService;
    private ArtistService artistService;
    private static final Logger LOGGER = Logger.getLogger(PlaylistController.class.getName());

    @Autowired
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    // http://localhost:9092/api/playlists
    @GetMapping("/playlists")
    public List<Playlist> getPlaylists() {
        LOGGER.info("calling getPlaylists method from controller");

        return playlistService.getPlaylists();
    }

//     http://localhost:9092/api/playlists/1
    @GetMapping(path = "/playlists/{playlistId}")
    public Playlist getPlaylist(@PathVariable Long playlistId) {
        LOGGER.info("calling getPlaylist method from controller");
        return playlistService.getPlaylist(playlistId);
    }

    // http://localhost:9092/api/playlists
    @PostMapping(path = "/playlists")
    public Playlist createPlaylist(@RequestBody Playlist playlistObject) {
        LOGGER.info("calling createPlaylist method from controller");
        return playlistService.createPlaylist(playlistObject);
    }

    // http://localhost:9092/api/playlists/1
    @PutMapping(path = "/playlists/{playlistId}")
    public Playlist updatePlaylist(@PathVariable(value = "playlistId") Long playlistId, @RequestBody Playlist playlistObject) {
        LOGGER.info("calling updatePlaylist method from controller");
        return playlistService.updatePlaylist(playlistId, playlistObject);
    }

    // http://localhost:9092/api/playlists/1
    @DeleteMapping("/playlists/{playlistId}")
    public String deletePlaylist(@PathVariable(value = "playlistId") Long playlistId) {
        LOGGER.info("calling deletePlaylist method from controller");
        return playlistService.deletePlaylist(playlistId);
    }

    // http://localhost:9092/api/playlists/1/music
    @PostMapping("/playlists/{playlistId}/music")
    public Music addPlaylistMusic(
            @PathVariable(value = "playlistId") Long playlistId,
            @RequestBody Music musicObject) {
        LOGGER.info("calling addPlaylistMusic method from controller");
        return playlistService.addPlaylistMusic(playlistId, musicObject);
    }

    // http://localhost:9092/api/playlists/1/music/1
    @GetMapping(path = "/playlists/{playlistId}/music/{musicId}")
    public Music getPlaylistMusic( @PathVariable(value = "playlistId") Long playlistId,
                                   @PathVariable(value = "musicId") Long musicId){
        LOGGER.info("calling getPlaylistMusic method from controller");
        return playlistService.getPlaylistMusic(playlistId, musicId);
    }

    // http://localhost:9092/api/playlists/1/music
    @GetMapping(path = "/playlists/{playlistId}/music")
    public List<Music> getPlaylistMusicList( @PathVariable (value = "playlistId") Long playlistId){
        LOGGER.info("calling getPlaylistMusicList method from controller");
        return playlistService.getPlaylistMusicList(playlistId);
    }

    
    // http://localhost:9092/api/playlists/1/music/1
    @PutMapping(path= "/playlists/{playlistId}/music/{musicId}")
    public Music updatePlaylistMusic( @PathVariable (value = "playlistId") Long playlistId,
                                        @PathVariable(value = "musicId") Long musicId,
                                        @RequestBody Music musicObject){
        return playlistService.updatePlaylistMusic(playlistId,musicId,musicObject);
    }

    // http://localhost:9092/api/playlists/1/music/1
    @DeleteMapping(path = "/playlists/{playlistId}/music/{musicId}")
    public Music deletePlaylistMusic(@PathVariable (value = "playlistId") Long playlistId,
                                       @PathVariable (value = "musicId") Long musicId){
        return playlistService.deletePlaylistMusic(playlistId,musicId);
    }

    // http://localhost:9092/api/artists
    @PostMapping(path = "/artists")
    public Artist createArtist(@RequestBody Artist artistObject) {
        LOGGER.info("calling createArtist method from controller");
        return artistService.createArtist(artistObject);
    }

    //     http://localhost:9092/api/artists/1
    @GetMapping(path = "/artists/{artistId}")
    public Artist getArtist(@PathVariable (value = "artistId") Long artistId) {
        LOGGER.info("calling getArtist method from controller");
        return artistService.getArtist(artistId);
    }

    // http://localhost:9092/api/artists
    @GetMapping("/artists")
    public List<Artist> getArtists() {
        LOGGER.info("calling getArtists method from controller");

        return artistService.getArtists();
    }

    // http://localhost:9092/api/playlists/1/podcast
    @PostMapping("/playlists/{playlistId}/podcast")
    public Podcast addPlaylistPodcast(
            @PathVariable(value = "playlistId") Long playlistId,
            @RequestBody Podcast podcastObject) {
        LOGGER.info("calling addPlaylistPodcast method from controller");
        return playlistService.addPlaylistPodcast(playlistId, podcastObject);
    }

    // http://localhost:9092/api/playlists/1/podcast/1
    @GetMapping(path = "/playlists/{playlistId}/podcast/{podcastId}")
    public Podcast getPlaylistPodcast( @PathVariable(value = "playlistId") Long playlistId,
                                     @PathVariable(value = "podcastId") Long podcastId){
        LOGGER.info("calling getPlaylistPodcast method from controller");
        return playlistService.getPlaylistPodcast(playlistId, podcastId);
    }
    // http://localhost:9092/api/playlists/1/podcast
    @GetMapping(path = "/playlists/{playlistId}/podcast")
    public List<Podcast> getPlaylistPodcastList( @PathVariable (value = "playlistId") Long playlistId){
        LOGGER.info("calling getPlaylistPodcastList method from controller");
        return playlistService.getPlaylistPodcastList(playlistId);
    }


    // http://localhost:9092/api/playlists/1/podcast/1
    @PutMapping(path= "/playlists/{playlistId}/podcast/{podcastId}")
    public Podcast updatePlaylistPodcast( @PathVariable (value = "playlistId") Long playlistId,
                                      @PathVariable(value = "podcastId") Long podcastId,
                                      @RequestBody Podcast podcastObject){
        return playlistService.updatePlaylistPodcast(playlistId,podcastId,podcastObject);
    }

    // http://localhost:9092/api/playlists/1/podcast/1
    @DeleteMapping(path = "/playlists/{playlistId}/podcast/{podcastId}")
    public Podcast deletePlaylistPodcast(@PathVariable (value = "playlistId") Long playlistId,
                                     @PathVariable (value = "podcastId") Long podcastId){
        return playlistService.deletePlaylistPodcast(playlistId,podcastId);
    }
    // http://localhost:9092/api/playlists/1/podcast
    @PostMapping("/playlists/{playlistId}/audiobook")
    public Audiobook addPlaylistAudiobook(
            @PathVariable(value = "playlistId") Long playlistId,
            @RequestBody Audiobook audiobookObject) {
        LOGGER.info("calling addPlaylistAudiobook method from controller");
        return playlistService.addPlaylistAudiobook(playlistId, audiobookObject);
    }

    // http://localhost:9092/api/playlists/1/audiobook/1
    @GetMapping(path = "/playlists/{playlistId}/audiobook/{audiobookId}")
    public Audiobook getPlaylistAudiobook(@PathVariable(value = "playlistId") Long playlistId,
                                          @PathVariable(value = "audiobookId") Long audiobookId){
        LOGGER.info("calling getPlaylistAudiobook method from controller");
        return playlistService.getPlaylistAudiobook(playlistId, audiobookId);
    }
    // http://localhost:9092/api/playlists/1/audiobook
    @GetMapping(path = "/playlists/{playlistId}/audiobook")
    public List<Audiobook> getPlaylistAudiobookList( @PathVariable (value = "playlistId") Long playlistId){
        LOGGER.info("calling getPlaylistAudiobookList method from controller");
        return playlistService.getPlaylistAudiobookList(playlistId);
    }

    // http://localhost:9092/api/playlists/1/audiobook/1
    @PutMapping(path= "/playlists/{playlistId}/audiobook/{audiobookId}")
    public Audiobook updatePlaylistAudiobook( @PathVariable (value = "playlistId") Long playlistId,
                                          @PathVariable(value = "audiobookId") Long audiobookId,
                                          @RequestBody Audiobook audiobookObject){
        return playlistService.updatePlaylistAudiobook(playlistId,audiobookId,audiobookObject);
    }

    // http://localhost:9092/api/playlists/1/audiobook/1
    @DeleteMapping(path = "/playlists/{playlistId}/audiobook/{audiobookId}")
    public Audiobook deletePlaylistAudiobook(@PathVariable (value = "playlistId") Long playlistId,
                                         @PathVariable (value = "audiobookId") Long audiobookId){
        return playlistService.deletePlaylistAudiobook(playlistId,audiobookId);
    }
}
