package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;
import ua.nure.odnokozov.railway.ticket.office.service.UserService;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class LoginCommand implements Command {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private static final String REQUEST_EMAIL = "email";
    private static final String REQUEST_PASSWORD = "password";
    private static final String REQUEST_ERROR_AUTHENTICATION = "errorAuthentication";
    private static final String SESSION_USER = "user";
    
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start LoginCommand");
        String email = request.getParameter(REQUEST_EMAIL).toLowerCase().trim();
        LOG.trace("email :: " + email);
        String password = request.getParameter(REQUEST_PASSWORD).trim();

        if (!isValidEmail(email, request) || !isValidPassword(password, request)) {
            LOG.info("Email or password is not valid");
            request.setAttribute(REQUEST_EMAIL, email);
            return PagesConstants.WELCOME_PAGE;
        }
        
        boolean isRegistratedUser = userService.isRegistratedUser(email);
        if (isRegistratedUser) {
            Optional<UserDTO> user = userService.validateUser(email, password);
            if (user.isPresent()) {
                LOG.debug("User was find");
                request.getSession().setAttribute(SESSION_USER, user.get());
                if (Role.ADMIN.equals(user.get().getRole())) {
                    LOG.debug("Role of the user - ADMIN");
                    return PagesConstants.REDIRECT_ADMIN_HOME;
                } else {
                    LOG.debug("Role of the user - CLIENT");
                    return ActivationStatus.ACTIVETED.equals(user.get().getActivationStatus())
                            ? PagesConstants.REDIRECT_CLIENT_HOME
                            : PagesConstants.REDIRECT_ACTIVATION_EMAIL;
                }
            } else {
                sendRequestErrorMessage("Password is incorrect", request);
                request.setAttribute(REQUEST_EMAIL, email);
                return PagesConstants.WELCOME_PAGE;
            }
        }
        sendRequestErrorMessage("User not found", request);
        request.setAttribute(REQUEST_EMAIL, email);
        return PagesConstants.WELCOME_PAGE;

    }

    private boolean isValidEmail(String email, HttpServletRequest request) {
        if (!Validator.isEmailAddress(email)) {
            sendRequestErrorMessage("Email is incorrect", request);
            return false;
        }
        return true;
    }
    
    private void sendRequestErrorMessage(String message, HttpServletRequest request) {
        LOG.debug(message);
        request.setAttribute(REQUEST_ERROR_AUTHENTICATION, message);
    }

    private boolean isValidPassword(String password, HttpServletRequest request) {
        if (!Validator.isPassword(password)) {
            sendRequestErrorMessage("Password is incorrect", request);
            return false;
        }
        return true;
    }
}
