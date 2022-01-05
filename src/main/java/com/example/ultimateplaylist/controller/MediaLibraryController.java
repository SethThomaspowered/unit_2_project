package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.Media;
import com.example.ultimateplaylist.service.MediaLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class MediaLibraryController {
    private MediaLibraryService mediaLibraryService;
    private static final Logger LOGGER=Logger.getLogger(MediaLibraryController.class.getName());
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
}
