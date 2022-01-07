package com.example.ultimateplaylist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;



    @Column
    private String description;

    // one playlist can contain more than one song, one song can be on many playlists
    //not sure if this is set up correctly...should this be join to colummn?
    @OneToMany(mappedBy = "playlist", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Music> musicList;

    @OneToMany(mappedBy = "playlist", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Podcast> podcastList;

    @OneToMany(mappedBy = "playlist", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Audiobook> audiobookList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Playlist(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Playlist() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // to get list of music within playlist
    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public User getUser() {
        return user;
    }

    public List<Podcast> getPodcastList() {
        return podcastList;
    }

    public void setPodcastList(List<Podcast> podcastList) {
        this.podcastList = podcastList;
    }

    public Playlist(Long id, String title, String description, List<Music> musicList, List<Podcast> podcastList, List<Audiobook> audiobookList, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.musicList = musicList;
        this.podcastList = podcastList;
        this.audiobookList = audiobookList;
        this.user = user;
    }

    public List<Audiobook> getAudiobookList() {
        return audiobookList;
    }

    public void setAudiobookList(List<Audiobook> audiobookList) {
        this.audiobookList = audiobookList;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
