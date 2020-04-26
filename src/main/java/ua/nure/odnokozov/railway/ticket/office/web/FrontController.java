package ua.nure.odnokozov.railway.ticket.office.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.web.command.Command;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.CommandManager;

@MultipartConfig
public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -3817332622332738100L;
    private static final Logger LOG = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccesRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        proccesRequest(request, response);
    }

    private void proccesRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOG.debug("Start processing request in FrontController");
        
        String commandName = request.getRequestURI().substring(request.getContextPath().length());
        LOG.debug("Command = " + commandName);
        Command command = CommandManager.getCommand(commandName);
        
        String path = command.execute(request, response);
        
        if(path.contains("redirect:")) {
            LOG.debug("Path contains '" + path + "', do redirect");
            response.sendRedirect(path.replace("redirect:", ""));
        } else {
            LOG.debug("Path contain '" + path + "', do forward");
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
