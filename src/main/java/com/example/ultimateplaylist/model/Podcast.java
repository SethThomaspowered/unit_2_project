package com.example.ultimateplaylist.model;

import javax.persistence.*;

@Entity
@Table(name="podcasts")
public class Podcast {
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

    @Column
    private String series;

    @OneToOne
    @JoinColumn(name = "podcast_id")
    private Media media;

}
