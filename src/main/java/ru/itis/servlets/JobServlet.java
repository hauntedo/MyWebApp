package ru.itis.servlets;

import ru.itis.models.Job;
import ru.itis.services.JobService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet
public class JobServlet extends HttpServlet {

    private JobService jobService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        jobService = (JobService) context.getAttribute("jobRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Job> jobs = jobService.getAllJobs();
        req.setAttribute("jobs", jobs);
        req.getRequestDispatcher("/WEB-INF/views/job.jsp").forward(req,resp);
    }
}
