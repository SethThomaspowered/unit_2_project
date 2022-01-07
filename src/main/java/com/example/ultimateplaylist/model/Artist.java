package com.example.ultimateplaylist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String artistType;

    @Column
    private boolean isPublic;

    @OneToMany(mappedBy = "artist", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Music> musicList;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Artist(){
    }

    public Artist(Long id, String name, String artistType, boolean isPublic){
        this.id = id;
        this.name = name;
        this.artistType = artistType;
        this.isPublic = isPublic;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistType() {
        return artistType;
    }

    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist type='" + artistType + '\'' +
                '}';
    }
    @OneToMany(mappedBy = "artist", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Podcast> PodcastList;
    @OneToMany(mappedBy = "artist", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Audiobook> audiobookList;

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public List<Podcast> getPodcastList() {
        return PodcastList;
    }

    public void setPodcastList(List<Podcast> podcastList) {
        PodcastList = podcastList;
    }

    public List<Audiobook> getAudiobookList() {
        return audiobookList;
    }

    public void setAudiobookList(List<Audiobook> audiobookList) {
        this.audiobookList = audiobookList;
    }
}
