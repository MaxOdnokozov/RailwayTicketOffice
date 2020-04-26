package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.service.UserService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientCheckActivationCodeCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ClientCheckActivationCodeCommand.class);

    private static final String SESSION_USER = "user";
    private static final String REQUEST_ACTIVATION_CODE = "activationCode";
    private static final String REQUEST_ERROR_ACTIVATION_MESSAGE = "errorActivation";

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start CheckActivationCodeCommand");
        HttpSession session = request.getSession();

        UserDTO user = (UserDTO) session.getAttribute(SESSION_USER);
        if (user.getActivationStatus().equals(ActivationStatus.ACTIVETED)) {
            LOG.debug("User profile already activeted, do redirect");
            return PagesConstants.REDIRECT_SUCCESSFUL_ACTIVATION_EMAIL;
        }

        String activationCode = request.getParameter(REQUEST_ACTIVATION_CODE);
        boolean isActiveted = userService.activateUser(user, activationCode);

        if (isActiveted) {
            user.setActivationStatus(ActivationStatus.ACTIVETED);
            session.setAttribute(SESSION_USER, user);
            LOG.debug("Activation of user profile was success, do riderect");
            return PagesConstants.REDIRECT_SUCCESSFUL_ACTIVATION_EMAIL;
        }
        
        sendActivationError("Activation code is invalid", request);
        return PagesConstants.REDIRECT_ACTIVATION_EMAIL;
    }

    private void sendActivationError(String message, HttpServletRequest request) {
        LOG.debug(message);
        request.setAttribute(REQUEST_ERROR_ACTIVATION_MESSAGE, message);
    }
}
