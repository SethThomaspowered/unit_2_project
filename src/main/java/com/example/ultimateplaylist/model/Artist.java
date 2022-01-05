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

    public Artist(){
    }

    public Artist(Long id, String name, String artistType, boolean isPublic){
        this.id = id;
        this.name = name;
        this.artistType = artistType;
        this.isPublic = isPublic;
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
}
