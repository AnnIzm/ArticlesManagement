package dao;

import java.util.List;

import entity.Article;
import exceptions.ConnectionException;
import exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class ArticleDaoImpl implements ArticleDao{
    private static final Logger logger = LogManager.getLogger(ArticleDaoImpl.class);

    public void saveArticle(Article article) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(article);
            transaction.commit();
            logger.info("Article is saved");
        } catch (ConnectionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to save article!");
            throw new DaoException(e.getMessage());
        }
    }

    public void updateArticle(Article article) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(article);
            transaction.commit();
            logger.info("Article is updated");
        } catch (ConnectionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to update article!");
            throw new DaoException(e.getMessage());
        }
    }

    public void deleteArticle(int id) throws DaoException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Article user = session.get(Article.class, id);
            if (user != null) {
                session.delete(user);
                logger.info("Article is deleted");
            }
            transaction.commit();
        } catch (ConnectionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to delete article!");
            throw new DaoException(e.getMessage());
        }
    }

    public Article getArticle(int id) throws DaoException {

        Transaction transaction = null;
        Article article;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            article = session.get(Article.class, id);
            transaction.commit();
        } catch (ConnectionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get article!");
            throw new DaoException(e.getMessage());
        }
        return article;
    }

    public List <Article> getAllArticles() throws DaoException {
        Transaction transaction = null;
        List <Article> articles = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            articles = session.createQuery("from Article").getResultList();
            transaction.commit();
        } catch (ConnectionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Unable to get all articles!");
            throw new DaoException(e.getMessage());
        }
        return articles;
    }
}
