package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.Media;
import com.example.ultimateplaylist.model.Music;
import com.example.ultimateplaylist.model.Podcast;
import com.example.ultimateplaylist.service.MediaLibraryService;
import com.example.ultimateplaylist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class MediaLibraryController {
    private MediaLibraryService mediaLibraryService;
    private static final Logger LOGGER=Logger.getLogger(MediaLibraryController.class.getName());
    @Autowired
    public void setMediaLibraryService(MediaLibraryService mediaLibraryService){
        this.mediaLibraryService =mediaLibraryService;
    }

    @GetMapping("/library")
    public List<Media> getAllMedia(){
        LOGGER.info("calling getAllMedia from controller");
        return mediaLibraryService.getAllMedia();
    }
    @PostMapping("/library")
    public Media addNewMedia(@RequestBody Media mediaObject){
        LOGGER.info("calling addNewMedia from controller");
        return mediaLibraryService.addNewMedia(mediaObject);
    }
    @GetMapping("/library/{mediaType}")
    public List<Media> getByMediaType(@PathVariable("mediaType") String mediaType){
        return mediaLibraryService.getByMediaType(mediaType);
    }
    // http://localhost:9092/api/library/1/podcast
    @PostMapping("/library/{mediaId}/podcast")
    public Podcast addNewPodcast(
            @PathVariable(value = "mediaId") Long mediaId,
            @RequestBody Podcast podcast) {
        LOGGER.info("calling addPlaylistMusic method from controller");
        return mediaLibraryService.addNewPodcast(mediaId, podcast);
    }
    @PostMapping("/library/{mediaId}/music")
    public Music addNewMusic(@PathVariable(value = "mediaId") Long mediaId, @RequestBody Music musicObject){
        LOGGER.info("calling addNewMusic from controller");
        return mediaLibraryService.addNewMusic(mediaId, musicObject);
    }
    @DeleteMapping("/library/{mediaId}/podcast/{podcastId}")
    public Podcast deletePodcast(@PathVariable("mediaId") Long mediaId, @PathVariable("podcastId") Long podcastId){
        return mediaLibraryService.deletePodcast(mediaId, podcastId);
    }
    @GetMapping("/library/1/podcast")
    public List<Podcast> getAllPodcasts(){
        return mediaLibraryService.getAllPodcasts();
    }
}
