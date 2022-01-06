package com.example.ultimateplaylist.service;

import com.example.ultimateplaylist.exception.InformationExistsException;
import com.example.ultimateplaylist.model.Media;
import com.example.ultimateplaylist.model.Podcast;
import com.example.ultimateplaylist.repository.MediaRepository;
import com.example.ultimateplaylist.repository.PodcastRepository;
import com.example.ultimateplaylist.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MediaLibraryService {
    private MediaRepository mediaRepository;
    private PodcastRepository podcastRepository;
    @Autowired
    public void setMediaRepository(MediaRepository mediaRepository){
        this.mediaRepository=mediaRepository;
    }
    @Autowired
    public void setPodcastRepository(PodcastRepository podcastRepository){
        this.podcastRepository=podcastRepository;
    }
    private static final Logger LOGGER=Logger.getLogger(MediaLibraryService.class.getName());
    public Media addNewMedia(@RequestBody Media mediaObject) {
        LOGGER.info("service calling addNewMedia ==>");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
        Media media = mediaRepository.findByTitle(mediaObject.getTitle());
        if (media != null) {
            throw new InformationExistsException("This media already exists.");
        }else{
            return mediaRepository.save(mediaObject);
        }
    }
    public List<Media> getAllMedia(){
        List<Media> medias = mediaRepository.findAll();
        return medias;
    }
    public List<Media> getByMediaType(String mediaType){
        List<Media> medias = mediaRepository.findMediaByMediaTypeContaining(mediaType);
        return medias;
    }
    public Media getMedia(Long mediaId){
        Media media = mediaRepository.getById(mediaId);
        return media;
    }
    public Podcast addNewPodcast(@RequestBody Podcast podcastObject){
        LOGGER.info("calling addNewPodcast method from service");
        Podcast podcast = podcastRepository.findByTitle(podcastObject.getTitle());
        if (podcast != null){
            throw new InformationExistsException("Podcast with title " + podcastObject.getTitle() + " already exists");
        }else{
            return podcastRepository.save(podcast);
        }
    }
}
