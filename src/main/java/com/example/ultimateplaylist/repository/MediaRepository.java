package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Media findByTitle(String title);
    List<Media> findMediaByMediaTypeContaining(String mediaType);
}
