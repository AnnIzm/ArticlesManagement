package controller;

import exceptions.ServiceException;
import service.ArticleService;
import service.ArticleServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();
        ArticleService articleService = new ArticleServiceImpl();
        try {
            switch (action) {
                case "/new":
                    articleService.showNewForm(request, response);
                    break;
                case "/insert":
                    articleService.insertArticle(request, response);
                    break;
                case "/delete":
                    articleService.deleteArticle(request, response);
                    break;
                case "/edit":
                    articleService.showEditForm(request, response);
                    break;
                case "/update":
                    articleService.updateArticle(request, response);
                    break;
                default:
                    articleService.getListOfArticles(request, response);
                    break;
            }
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
