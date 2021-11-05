package ru.itis.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.repositories.UserRepository;
import ru.itis.repositories.UserRepositoryImpl;
import ru.itis.services.SecurityService;
import ru.itis.services.SecurityServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("src\\resources\\application.properties"));
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
        SecurityService securityService = new SecurityServiceImpl(userRepository);

        servletContext.setAttribute("securityService", securityService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
