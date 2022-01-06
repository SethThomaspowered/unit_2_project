package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastRepository extends JpaRepository<Podcast, Long> {
    Podcast findByTitle(String title);
    Podcast findByMediaId(Long mediaId);
}
