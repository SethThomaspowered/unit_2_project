package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
