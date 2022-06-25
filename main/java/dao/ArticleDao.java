package dao;

import entity.Article;
import exceptions.DaoException;


import java.util.List;

public interface ArticleDao {

    /**
     * Method to save an article
     *
     * @param article article to save
     */
    void saveArticle(Article article) throws DaoException;

    /**
     * Method to update an article
     *
     * @param article article to update
     */
    void updateArticle(Article article) throws DaoException;

    /**
     * Method to delete an article
     *
     * @param id article's id
     */
    void deleteArticle(int id) throws DaoException;

    /**
     * Method to get article by id
     *
     * @param id article's id
     * @return article
     */
    Article getArticle(int id) throws DaoException;

    /**
     * Method to get all articles
     *
     * @return list of articles
     */
    List<Article> getAllArticles() throws DaoException;
}
