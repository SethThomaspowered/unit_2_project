package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
