package service;

import dao.ArticleDao;
import dao.ArticleDaoImpl;
import entity.Article;
import exceptions.DaoException;
import exceptions.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArticleServiceImpl implements ArticleService{
    private final ArticleDao articleDao = new ArticleDaoImpl();
    private static final Logger logger = LogManager.getLogger(ArticleServiceImpl.class);

    public void getListOfArticles(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        List<Article> listUser;
        try {
            listUser = articleDao.getAllArticles();
            request.setAttribute(Constants.LIST_ARTICLE_NAME, listUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.INDEX_PAGE);
            dispatcher.forward(request, response);
        } catch (DaoException | ServletException | IOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ARTICLE_FORM_PAGE);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        try {
            int id = Integer.parseInt(request.getParameter(Constants.ID_NAME));

            Article existingArticle = articleDao.getArticle(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ARTICLE_FORM_PAGE);
            request.setAttribute(Constants.ARTICLE_NAME, existingArticle);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void insertArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        try {
            String title = request.getParameter(Constants.TITLE_NAME);
            String brief = request.getParameter(Constants.BRIEF_NAME);
            String content = request.getParameter(Constants.CONTENT_NAME);
            String date = getDate();

            Article newArticle = new Article(title, date, brief, content);
            articleDao.saveArticle(newArticle);
            response.sendRedirect(Constants.LIST_NAME);
        } catch (IOException | DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        try {
            int id = Integer.parseInt(request.getParameter(Constants.ID_NAME));
            String title = request.getParameter(Constants.TITLE_NAME);
            String brief = request.getParameter(Constants.BRIEF_NAME);
            String content = request.getParameter(Constants.CONTENT_NAME);
            String date = getDate();

            Article user = new Article(id, title, date, brief, content);
            articleDao.updateArticle(user);
            response.sendRedirect(Constants.LIST_NAME);
        } catch (IOException | DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            int id = Integer.parseInt(request.getParameter(Constants.ID_NAME));

            articleDao.deleteArticle(id);
            response.sendRedirect(Constants.LIST_NAME);
        } catch (IOException | DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    private String getDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(Constants.DATE_PATTERN);
        return formatForDateNow.format(dateNow);
    }
}
