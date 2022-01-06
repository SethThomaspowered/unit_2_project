package com.example.ultimateplaylist.model;

import javax.persistence.*;

@Entity
@Table(name = "audiobooks")
public class Audiobook {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private Integer time;
    @Column
    private String releaseDate;
    @Column
    private boolean isPublic;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    public Audiobook(Long id, String title, Integer time, String releaseDate, boolean isPublic) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.releaseDate = releaseDate;
        this.isPublic = isPublic;
    }

    public Audiobook() {
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
