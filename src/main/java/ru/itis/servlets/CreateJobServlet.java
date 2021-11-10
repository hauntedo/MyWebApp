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

@WebServlet("/jobs/createJob")
public class CreateJobServlet extends HttpServlet {

    private JobService jobService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        jobService = (JobService) context.getAttribute("jobService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context.getRequestDispatcher("/WEB-INF/views/createJobs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action!=null) {
            switch (action) {
                case "save":
                    User u = (User) req.getSession().getAttribute("user");
                    Job job = Job.builder()
                            .title(req.getParameter("title"))
                            .description(req.getParameter("desc"))
                            .employerId(u.getId())
                            .active(true)
                            .build();
                    jobService.createJob(job);
                    req.setAttribute("userId", u.getId());
                    resp.sendRedirect(context.getContextPath()+"/jobs");
                    return;

                case "cancel":
                    resp.sendRedirect(context.getContextPath()+"/jobs");
                    return;
            }
        }
        context.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(req, resp);
    }
}
