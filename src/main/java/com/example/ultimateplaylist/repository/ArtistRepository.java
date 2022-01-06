package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(String name);
}
