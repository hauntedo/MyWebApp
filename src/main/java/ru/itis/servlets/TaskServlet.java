package ru.itis.servlets;

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
import java.util.List;
import java.util.Optional;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    private TaskService taskService;
    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        taskService = (TaskService) context.getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        List<Task> tasks = taskService.getAllTaskByEmployerId(u.getId());
        req.setAttribute("tasks", tasks);
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    resp.sendRedirect(context.getContextPath() + "/tasks/createTask");
                    return;
                case "update":
                    String id = req.getParameter("id");
                    Optional<Task> t = taskService.getTaskById(Long.valueOf(id));
                    if (t.isPresent()) {
                        Task task = t.get();
                        task.setTaskState(TaskState.valueOf(req.getParameter("state")));
                        taskService.updateTask(task);
                    }
                    resp.sendRedirect(context.getContextPath() + "/tasks");
                    return;
            }
        }
        context.getRequestDispatcher("/WEB-INF/views/task.jsp").forward(req, resp);
    }
}
