package ru.itis.servlets;

import ru.itis.models.Task;
import ru.itis.models.TaskState;
import ru.itis.services.TaskService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/tasks/update")
public class ChangeTaskServlet extends HttpServlet {

    private ServletContext context;
    private TaskService taskService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context.getRequestDispatcher("/WEB-INF/views/changeTask.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action!= null) {
            switch(action) {
                case "save":
                    String id = req.getParameter("id");
                    Optional<Task> t = taskService.getTaskById(Long.valueOf(id));
                    if (t.isPresent()) {
                        Task task = t.get();
                        task.setTitle(req.getParameter("title"));
                        task.setDescription(req.getParameter("desc"));
                        task.setTaskState(TaskState.valueOf(req.getParameter("states")));
                        taskService.updateTask(task);
                        req.setAttribute("task", task);
                        resp.sendRedirect(context.getContextPath()+"/tasks");
                        return;
                    }
                case "cancel":
                    resp.sendRedirect(context.getContextPath() + "/tasks");
                    return;
            }
        }
        context.getRequestDispatcher("/WEB-INF/views/changeTask.jsp").forward(req,resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
        taskService = (TaskService) context.getAttribute("taskService");
    }
}
