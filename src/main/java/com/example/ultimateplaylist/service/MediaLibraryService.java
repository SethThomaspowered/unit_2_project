package com.example.ultimateplaylist.service;

import com.example.ultimateplaylist.model.Media;
import com.example.ultimateplaylist.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MediaLibraryService {
    private MediaRepository mediaRepository;
    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository){
        this.mediaRepository=mediaRepository;
    }
    public List<Media> getAllMedia(){
        List<Media> medias = mediaRepository.findAll();
        return medias;
    }
}
