package utils;

import entity.Article;
import exceptions.ConnectionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory() throws ConnectionException {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, Constants.DRIVER_NAME);
                settings.put(Environment.URL, Constants.URL_NAME);
                settings.put(Environment.USER, Constants.USER_NAME);
                settings.put(Environment.PASS, Constants.PASS_NAME);
                settings.put(Environment.DIALECT, Constants.DIALECT_NAME);

                settings.put(Environment.SHOW_SQL, Constants.SHOW_SQL_NAME);

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, Constants.CURRENT_SESSION_CONTEXT_CLASS_NAME);

                settings.put(Environment.HBM2DDL_AUTO, Constants.HBM2DDL_AUTO_NAME);

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Article.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                logger.info("Hibernate ServiceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception ex) {
                throw new ConnectionException(ex.getMessage());
            }
        }
        return sessionFactory;
    }
}
