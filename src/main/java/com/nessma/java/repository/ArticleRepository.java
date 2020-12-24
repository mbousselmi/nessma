package com.nessma.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nessma.java.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
