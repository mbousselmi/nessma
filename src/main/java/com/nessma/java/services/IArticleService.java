package com.nessma.java.services;

import java.util.List;

import com.nessma.java.exceptions.ArticleNotFoundException;
import com.nessma.java.models.Article;

public interface IArticleService {

	List<Article> getAllArticles();

	Article getArticleById(long articleId) throws ArticleNotFoundException;

	boolean addArticle(Article article);

	void updateArticle(Article article);

	void deleteArticle(int articleId) throws ArticleNotFoundException;
}
