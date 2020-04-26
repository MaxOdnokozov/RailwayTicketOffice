package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.UserBuilder;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.service.UserService;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class RegistrationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    private static final String REQUEST_EMAIL = "emailRegistration";
    private static final String REQUEST_PASSWORD = "password";
    private static final String REQUEST_REPEATED_PASSWORD = "repeatedPassword";
    private static final String REQUEST_LAST_NAME = "lastName";
    private static final String REQUEST_FIRST_NAME = "firstName";
    private static final String SESSION_USER = "user";

    private static final String REQUEST_ERROR_REGISTRATION_MESSAGE = "errorRegistrationMessage";
    
    private static final int MAX_NAME_LENGTH = 50;

    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("RegistrationCommand execute");
        String email = request.getParameter(REQUEST_EMAIL).toLowerCase().trim();
        String password = request.getParameter(REQUEST_PASSWORD).trim();
        String repeatedPassword = request.getParameter(REQUEST_REPEATED_PASSWORD).trim();
        String firstName = request.getParameter(REQUEST_FIRST_NAME).trim();
        String lastName = request.getParameter(REQUEST_LAST_NAME).trim();

        if (!isValidEmail(email, request) || !isValidPassword(password, repeatedPassword, request)
                || !isValidFirstAndLastName(firstName, lastName, request)) {
            request.setAttribute(REQUEST_EMAIL, email);
            request.setAttribute(REQUEST_FIRST_NAME, firstName);
            request.setAttribute(REQUEST_LAST_NAME, lastName);
            request.setAttribute(REQUEST_EMAIL, email);
            request.setAttribute(REQUEST_FIRST_NAME, firstName);
            request.setAttribute(REQUEST_LAST_NAME, lastName);
            return PagesConstants.WELCOME_PAGE;
        }

        User user = UserBuilder.getInstance()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        Optional<UserDTO> registratedUser = userService.registrateUser(user);
        
        if (registratedUser.isPresent()) {
            request.getSession().setAttribute(SESSION_USER, registratedUser.get());
            userService.sendActivationEmail(registratedUser.get());      
            LOG.debug("Redirect to activation email page");
            return PagesConstants.REDIRECT_ACTIVATION_EMAIL;
        }

        sendRequestErrorMessage("Cold not registrate a new user", request);
        request.setAttribute(REQUEST_EMAIL, email);
        request.setAttribute(REQUEST_FIRST_NAME, firstName);
        request.setAttribute(REQUEST_LAST_NAME, lastName);
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
        request.setAttribute(REQUEST_ERROR_REGISTRATION_MESSAGE, message);
    }

    private boolean isValidPassword(String password, String repeatedPassword, HttpServletRequest request) {
        if (!Validator.isPassword(password) || !Validator.isPassword(repeatedPassword)) {
            sendRequestErrorMessage("Password is incorrect", request);
            return false;
        }
        if (!password.equals(repeatedPassword)) {
            sendRequestErrorMessage("Passwords don't match", request);
            return false;
        }
        return true;
    }

    private boolean isValidFirstAndLastName(String firstName, String lastName, HttpServletRequest request) {
        if (!Validator.isFilled(firstName, MAX_NAME_LENGTH)
                || !Validator.isFilled(lastName, MAX_NAME_LENGTH)) {
            sendRequestErrorMessage("First or last name is invalid", request);
            return false;
        }
        return true;
    }
}
