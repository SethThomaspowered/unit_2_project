package com.example.ultimateplaylist.repository;

import com.example.ultimateplaylist.model.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {
}
