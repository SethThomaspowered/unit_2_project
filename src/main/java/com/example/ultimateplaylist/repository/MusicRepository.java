package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Playlist;
import com.example.ultimateplaylist.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Music findByTitle(String musicTitle);

    List<Music> findByPlaylistId(Long playlistId);

    Music findByTitleAndUserId(String musicTitle, Long userId);
}
