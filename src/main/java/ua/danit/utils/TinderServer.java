package ua.danit.utils;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.*;
import ua.danit.controller.filter.LoginFilter;

import javax.servlet.DispatcherType;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class TinderServer {
    public void tinderStart() throws Exception {
        Server server = new Server(8010);
        ServletContextHandler handler = new ServletContextHandler();

        ServletHolder userHolder = new ServletHolder(new UserServlet());
        handler.addServlet(userHolder, "/users");

        ServletHolder likedHolder = new ServletHolder(new LikedServlet());
        handler.addServlet(likedHolder, "/liked");

        ServletHolder messegeHolder = new ServletHolder(new MessegeIdSevlet());
        handler.addServlet(messegeHolder, "/messeges/*");

        ServletHolder staticHolder = new ServletHolder(new StaticServlet());
        handler.addServlet(staticHolder, "/css/*");

        ServletHolder loginHolder = new ServletHolder(new LoginServlet());
        handler.addServlet(loginHolder, "/login");

        ServletHolder loginoutHolder = new ServletHolder(new LogoutServlet());
        handler.addServlet(loginoutHolder, "/logout");

        String[] urls = {"/users", "/messeges/*", "/liked"};
        List<String> list = Arrays.asList(urls);

        for (String path : list) {
            handler.addFilter(LoginFilter.class,path, EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        }

        server.setHandler(handler);

        server.start();
        server.join();
    }
}
