package ru.itis.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        System.out.println(servletContext);
        try {
            properties. load(servletContext.getResourceAsStream("WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver"));
        hikariConfig.setUsername(properties.getProperty("db.user"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.max-pool-size")));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        servletContext.setAttribute("dataSource", dataSource);

        UserRepository userRepository = new UserRepositoryImpl(dataSource);
        JobRepository jobRepository = new JobRepositoryImpl(dataSource);
        SecurityService securityService = new SecurityServiceImpl(userRepository);
        TaskRepository taskRepository = new TaskRepositoryImpl(dataSource);

        UserService userService = new UserServiceImpl(userRepository);
        TaskService taskService = new TaskServiceImpl(taskRepository);
        JobService jobService = new JobServiceImpl(jobRepository);

        servletContext.setAttribute("taskService", taskService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("jobService", jobService);
        servletContext.setAttribute("securityService", securityService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        HikariDataSource hikariDataSource = (HikariDataSource) servletContext.getAttribute("dataSource");
        hikariDataSource.close();
    }
}
