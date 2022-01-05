package com.example.ultimateplaylist.model;

import javax.persistence.*;

@Entity
@Table(name = "media")
public class Media {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String mediaType;
    @Column
    private String title;


    @OneToOne
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;

    public Media() {
    }

    public Media(Long id, String mediaType, String title, Podcast podcast) {
        this.id = id;
        this.mediaType = mediaType;
        this.title = title;
        this.podcast = podcast;
    }

    public Podcast getPodcast() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPodcast(Podcast podcast) {
        this.podcast = podcast;
    }
}
