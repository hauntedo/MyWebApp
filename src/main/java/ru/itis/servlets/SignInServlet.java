package ru.itis.servlets;

import ru.itis.exceptions.NoSuchLoginException;
import ru.itis.exceptions.WrongPasswordException;
import ru.itis.models.User;
import ru.itis.services.SecurityService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SecurityService securityService;
    private UserService userService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        securityService = (SecurityService) context.getAttribute("securityService");
        userService = (UserService) context.getAttribute("userService");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context.getRequestDispatcher("/WEB-INF/views/signIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            securityService.signIn(req.getParameter("email"), req.getParameter("psw"), req.getSession());
            if (userService.getByEmail(req.getParameter("email")).isPresent()) {
                User u = userService.getByEmail(req.getParameter("email")).get();
                Cookie cookie = new Cookie("user", userService.getTokenByUser(u));
                cookie.setMaxAge(60*60*24*365);
                resp.addCookie(cookie);
                resp.sendRedirect(context.getContextPath() + "/profile");
                return;
            }
        } catch (NoSuchLoginException | WrongPasswordException e) {
            req.setAttribute("message", "Неверный логин или пароль");
        }
        resp.sendRedirect(req.getContextPath() + "/signIn");
    }

}
