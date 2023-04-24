package com.java.bookStore_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.bookStore_api.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    boolean existsByPublisherName(String publisherName);
    Optional<Publisher> findByPublisherName(String publisherName);
}
