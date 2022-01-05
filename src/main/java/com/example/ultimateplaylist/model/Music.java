package com.example.ultimateplaylist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String length;

    @Column
    private String releaseDate;

    @Column
    private boolean isPublic;

    //to join tables multiple music rows to multiple playlists
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    //to join tables multiple music rows to multiple playlists
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Music() {
    }

    public Music(Long id, String title, String length, String releaseDate, boolean isPublic, Playlist playlist, User user) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.releaseDate = releaseDate;
        this.isPublic = isPublic;
        this.playlist = playlist;
        this.user = user;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
