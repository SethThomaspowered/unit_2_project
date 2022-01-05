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


    @OneToOne
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;

    public Podcast getPodcast() {
        return podcast;
    }
}
