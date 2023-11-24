package com.example.movienewsfeed.repository;

import com.example.movienewsfeed.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

}
