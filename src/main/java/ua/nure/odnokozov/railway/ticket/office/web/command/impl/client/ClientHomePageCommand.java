package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.TicketDTO;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.service.TicketService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientHomePageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ClientHomePageCommand.class);
    
    private TicketService ticketService = new TicketService(); 
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start ClientHomePageCommand");
        
        long userId = ((UserDTO) request.getSession().getAttribute("user")).getId();
        List<TicketDTO> tickets = ticketService.getAllActualsByUserId(userId);
        
        request.setAttribute("tickets", tickets);
        return PagesConstants.CLIENT_HOME_PAGE;
    }
}
