package ru.itis.servlets;

import ru.itis.models.Job;
import ru.itis.models.User;
import ru.itis.services.JobService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/jobs")
public class JobServlet extends HttpServlet {

    private JobService jobService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        jobService = (JobService) context.getAttribute("jobService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    resp.sendRedirect(context.getContextPath()+"/jobs/createJob");
                    return;
                case "respond":
                    User u = (User) req.getSession().getAttribute("user");
                    String employerId = req.getParameter("id");
                    try {
                        jobService.createRelation(u.getId(), Long.valueOf(employerId));
                    } catch (IllegalStateException e) {
                        throw new IllegalStateException("Ошибка " + e);
                    }
                    resp.sendRedirect(context.getContextPath()+"/jobs");
                    return;
            }
        }
        List<Job> jobs = jobService.getAllByActive(true);
        req.setAttribute("jobs", jobs);
        context.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(req,resp);
    }
}
