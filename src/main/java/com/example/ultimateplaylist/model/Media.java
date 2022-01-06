package com.example.ultimateplaylist.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String mediaType;



    @OneToMany(mappedBy = "media", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Podcast> podcast;

    public Media() {
    }

    public Media(Long id, String mediaType, List<Podcast> podcast) {
        this.id = id;
        this.mediaType = mediaType;

        this.podcast = podcast;
    }

    public List<Podcast> getPodcast() {
        return podcast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }



    public void setPodcast(List<Podcast> podcast) {
        this.podcast = podcast;
    }
}
