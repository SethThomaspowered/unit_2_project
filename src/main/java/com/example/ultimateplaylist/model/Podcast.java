package com.example.ultimateplaylist.model;

import javax.persistence.*;

@Entity
@Table
public class Podcast {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    public Podcast(Long id, String title, String length, String releaseDate, boolean isPublic, String series, Media media) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.releaseDate = releaseDate;
        this.isPublic = isPublic;
        this.series = series;
        this.media = media;
    }

    @Column
    private String length;

    @Column
    private String releaseDate;

    @Column
    private boolean isPublic;

    @Column
    private String series;

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    private Media media;

    public Podcast() {
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}
