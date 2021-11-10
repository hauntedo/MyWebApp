package ru.itis.servlets;

import ru.itis.models.User;
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

@WebServlet("/profile/change")
public class ChangeProfileServlet extends HttpServlet {

    private ServletContext context;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/changeProfile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "save":
                    String firstName = req.getParameter("fname");
                    String lastName = req.getParameter("lname");
                    String password = req.getParameter("psw");
                    Cookie[] cookies = req.getCookies();
                    if (cookies != null) {
                        for (Cookie c : cookies) {
                            if (c.getName().equals("user")) {
                                Optional<User> u = userService.getUserByToken(c.getValue());
                                if (u.isPresent()) {
                                    User user = u.get();
                                    user.setFirstName(firstName);
                                    user.setLastName(lastName);
                                    user.setPassword(password);
                                    userService.updateUser(user);
                                    req.getSession().setAttribute("user", user);
                                }
                            }
                        }
                    }
                case "cancel":
                    resp.sendRedirect(context.getContextPath() + "/profile");
                    return;
            }
        }
        resp.sendRedirect(context.getContextPath() + "/profile/change");
    }
}
