package service;

import exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ArticleService {

    /**
     *  Method to get all articles
     *
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServiceException
     */
    void getListOfArticles(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Method to show new form
     *
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServiceException
     */
    void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Method to show edit form
     *
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServiceException
     */
    void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Method to insert new article
     *
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServiceException
     */
    void insertArticle(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Method to update an article
     *
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServiceException
     */
    void updateArticle(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Method to delete an article
     *
     * @param request HttpServletRequest request
     * @param response HttpServletResponse response
     * @throws ServiceException
     */
    void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
