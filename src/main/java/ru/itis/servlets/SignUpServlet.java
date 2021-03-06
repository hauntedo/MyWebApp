package ru.itis.servlets;

import ru.itis.exceptions.InvalidEmailException;
import ru.itis.models.User;
import ru.itis.services.SecurityService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SecurityService securityService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        securityService = (SecurityService) context.getAttribute("securityService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("psw").equals(req.getParameter("psw-repeat"))) {
            UUID uuid = UUID.randomUUID();
            User user = User.builder()
                    .firstName(req.getParameter("fname"))
                    .lastName(req.getParameter("lname"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("psw"))
                    .token(uuid.toString())
                    .build();
            try {
                securityService.signUp(user, req.getSession());
                resp.sendRedirect(req.getContextPath() + "/signIn");
                return;
            } catch (InvalidEmailException e) {
                req.setAttribute("message", "Неверный email");
            }
        } else {
            req.setAttribute("message", "Пароли не совпадают");
        }
        resp.sendRedirect(context.getContextPath()+"/signUp");
    }
}
