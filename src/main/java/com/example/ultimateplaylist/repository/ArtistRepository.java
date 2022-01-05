package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByUserIdAndName(Long userId, String name);
    List<Artist> findByUserId(Long userId);
    Artist findByIdAndUserId(Long artistId, Long userId);
}
