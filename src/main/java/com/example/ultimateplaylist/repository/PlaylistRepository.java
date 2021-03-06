package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Playlist findByUserIdAndTitle(Long userId, String title);
    List<Playlist> findByUserId(Long userId);
    Playlist findByIdAndUserId(Long playlistId, Long userId);
}
