package ru.itis.servlets;

import ru.itis.models.Task;
import ru.itis.repositories.TaskRepository;
import ru.itis.services.TaskService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet
public class TaskServlet extends HttpServlet {

    private TaskService taskService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        taskService = (TaskService) context.getAttribute("taskRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> tasks = taskService.getAllTaskByEmployeeId(Long.valueOf(req.getParameter("id")));
        req.getRequestDispatcher("/WEB-INF/views/task.jsp");
    }
}
