package ru.itis.servlets;

import ru.itis.exceptions.NoSuchLoginException;
import ru.itis.exceptions.WrongPasswordException;
import ru.itis.services.SecurityService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SecurityService securityService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        securityService = (SecurityService) context.getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            securityService.signIn(req.getParameter("email"), req.getParameter("psw"));
            resp.sendRedirect("/profile");
        } catch (NoSuchLoginException | WrongPasswordException e) {
            req.setAttribute("message", "Неверный логин или пароль");
        }
        req.getRequestDispatcher("/jsp/signIn.jsp").forward(req,resp);
    }
}
