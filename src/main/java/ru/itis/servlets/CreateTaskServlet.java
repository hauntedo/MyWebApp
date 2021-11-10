package ru.itis.servlets;

import ru.itis.models.Job;
import ru.itis.models.Task;
import ru.itis.models.TaskState;
import ru.itis.models.User;
import ru.itis.services.TaskService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tasks/createTask")
public class CreateTaskServlet extends HttpServlet {

    private ServletContext context;
    private TaskService taskService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context.getRequestDispatcher("/WEB-INF/views/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action!=null) {
            switch (action) {
                case "save":
                    User u = (User) req.getSession().getAttribute("user");
                    Task task = Task.builder()
                            .title(req.getParameter("title"))
                            .description(req.getParameter("desc"))
                            .taskState(TaskState.OPEN)
                            .userId(u.getId())
                            .build();
                    taskService.createTask(task);
                    resp.sendRedirect(context.getContextPath()+"/tasks");
                    return;

                case "cancel":
                    resp.sendRedirect(context.getContextPath()+"/tasks");
                    return;
            }
        }
        context.getRequestDispatcher("/WEB-INF/views/createTask.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        taskService = (TaskService) context.getAttribute("taskService");
    }
}
