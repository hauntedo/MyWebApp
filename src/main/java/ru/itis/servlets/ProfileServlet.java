package ru.itis.servlets;

import ru.itis.services.SecurityService;
import ru.itis.services.UserService;
import ru.itis.services.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;
    private SecurityService securityService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        securityService = (SecurityService) context.getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "change":
                    resp.sendRedirect(context.getContextPath() + "/profile/change");
                    return;
                case "quit":
                    securityService.signOut(req, resp);
                    resp.sendRedirect(context.getContextPath()+"/signIn");
                    return;
            }
        }
        context.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
    }
}
