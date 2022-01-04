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

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "playlist_id")
    private List<Playlist> playlists;

    @ManyToMany
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<User> user;

    public Music() {}

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


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length='" + length + '\'' +
                ", release date='" + releaseDate+ '\'' +
                '}';
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

//    public void setPlaylist(Playlist playlist) {
//        this.playlist = playlist;
//    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        isPublic = isPublic;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
