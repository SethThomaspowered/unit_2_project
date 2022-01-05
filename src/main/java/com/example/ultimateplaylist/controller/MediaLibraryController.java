package com.example.ultimateplaylist.controller;

import com.example.ultimateplaylist.model.Media;
import com.example.ultimateplaylist.service.MediaLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MediaLibraryController {
    private MediaLibraryService mediaLibraryService;

    public void setMediaLibraryService(MediaLibraryService mediaLibraryService){
        this.mediaLibraryService =mediaLibraryService;
    }
    @GetMapping("/library")
    public List<Media> getAllMedia(){
        return mediaLibraryService.getAllMedia();
    }
}
