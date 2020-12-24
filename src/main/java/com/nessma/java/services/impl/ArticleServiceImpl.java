package com.nessma.java.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nessma.java.exceptions.ArticleNotFoundException;
import com.nessma.java.models.Article;
import com.nessma.java.repository.ArticleRepository;
import com.nessma.java.services.IArticleService;

public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> getAllArticles() {
		return articleRepository.findAll();
	}

	@Override
	public Article getArticleById(long articleId) throws ArticleNotFoundException {
		Article article = new Article();
		return articleRepository.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException("Article not found " + articleId));
	}

	@Override
	public boolean addArticle(Article article) {

		return false;
	}

	@Override
	public void updateArticle(Article article) {
		articleRepository.save(article);
	}

	@Override
	public void deleteArticle(int articleId) throws ArticleNotFoundException {
		articleRepository.delete(getArticleById(articleId));
	}

}
