package ua.nure.odnokozov.railway.ticket.office.web.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;

public class AuthorizationFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthorizationFilter.class);

    private static final String SESSION_USER = "user";
    private static final String ERROR_403 = "403";
    private static final String ERROR_404 = "404";

    private final Set<String> accessibleCommands;
    private final Set<String> adminCommands;
    private final Set<String> clientCommands;
    private final Set<String> commonCommands;

    public AuthorizationFilter() {
        accessibleCommands = new HashSet<>();
        adminCommands = new HashSet<>();
        clientCommands = new HashSet<>();
        commonCommands = new HashSet<>();

        accessibleCommands.add("/find-routes");
        accessibleCommands.add("/show-route-stops");
        accessibleCommands.add("/show-route-carriages");
        accessibleCommands.add("/show-route-seats");
        accessibleCommands.add("/registration");
        accessibleCommands.add("/login");

        adminCommands.add("/admin-home-page");
        adminCommands.add("/admin-add-user-form");
        adminCommands.add("/admin-add-carriage-form");
        adminCommands.add("/admin-add-route-form");
        adminCommands.add("/admin-save-route-code");
        adminCommands.add("/admin-save-route-stop");
        adminCommands.add("/admin-save-route-carriage");
        adminCommands.add("/admin-add-carriage");
        adminCommands.add("/admin-add-route");
        adminCommands.add("/admin-view-all-carriages");
        adminCommands.add("/admin-view-all-stations");
        adminCommands.add("/admin-edit-carriage-form");
        adminCommands.add("/admin-delete-carriage");
        adminCommands.add("/admin-add-station-form");
        adminCommands.add("/admin-edit-station-form");
        adminCommands.add("/admin-delete-station");
        adminCommands.add("/admin-save-edit-station");
        adminCommands.add("/admin-add-station");
        adminCommands.add("/admin-cancel-station");
        adminCommands.add("/admin-view-routes");
        adminCommands.add("/admin-find-routes");
        adminCommands.add("/admin-delete-route");
        adminCommands.add("/admin-edit-route-form");
        adminCommands.add("/admin-save-edit-route");
        adminCommands.add("/admin-cancel-add-route");
        adminCommands.add("/admin-cancel-edit-route");
        adminCommands.add("/admin-delete-route-stop");
        adminCommands.add("/admin-delete-route-carriage");
        adminCommands.add("/admin-save-edit-carriage");
        adminCommands.add("/admin-find-tickets");
        adminCommands.add("/admin-delete-ticket");
        adminCommands.add("/admin-succes-create-route");

        clientCommands.add("/client-booking-seats");
        clientCommands.add("/client-tickets-payment");
        clientCommands.add("/client-pay-tickets");
        clientCommands.add("/client-successful-tickets-payment");
        clientCommands.add("/client-error-tickets-payment");
        clientCommands.add("/client-home-page");
        clientCommands.add("/client-check-activation-code");
        clientCommands.add("/client-activation-email-form");
        clientCommands.add("/client-check-activation-code");
        clientCommands.add("/client-successful-activation-email");

        commonCommands.add("/logout");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("AuthoriizationFilter is initializing");
        filterConfig.getServletContext().getNamedDispatcher("authorizationFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.debug("doFilter() :: is starting");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String command = req.getRequestURI().substring(req.getContextPath().length());
        LOG.debug("Command :: " + command);

        if (accessibleCommands.contains(command)) {
            LOG.trace("This command :: " + command + " - is available for all users");
            chain.doFilter(req, resp);
        } else {
            LOG.debug("This command :: " + command + " - is available only for logged users");
            HttpSession session = req.getSession();
            if (session == null || session.getAttribute(SESSION_USER) == null) {
                LOG.trace("Authorization error, user must be logged!");
                sendError(req, resp, ERROR_403);
            } else {
                ActivationStatus activetionStatus = ((UserDTO) session.getAttribute(SESSION_USER)).getActivationStatus();
                if (ActivationStatus.INACTIVE.equals(activetionStatus) && !commonCommands.contains(command)) {
                    request.getRequestDispatcher(PagesConstants.CLIENT_ACTIVATION_EMAIL_PAGE).forward(request, response);
                } else {
                    Role role = ((UserDTO) session.getAttribute(SESSION_USER)).getRole();
                    if (Role.CLIENT.equals(role)
                            && (clientCommands.contains(command) || commonCommands.contains(command))) {
                        LOG.trace("Client command");
                        chain.doFilter(req, resp);
                    } else if (Role.ADMIN.equals(role)
                            && (adminCommands.contains(command) || commonCommands.contains(command))) {
                        LOG.trace("Admin command");
                        chain.doFilter(req, resp);
                    } else if ((Role.CLIENT.equals(role) && (adminCommands.contains(command))
                            || (Role.ADMIN.equals(role) && (clientCommands.contains(command))))) {
                        LOG.trace("Authorization error, user = '" + role + "', trying get access to command = '"
                                + command + "' ! Error 403");
                        sendError(req, resp, ERROR_403);
                    } else {
                        LOG.trace("Command is invalid");
                        sendError(req, resp, ERROR_404);
                    }
                }
            }
        }
    }

    private void sendError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        request.setAttribute("error", error);
        request.getRequestDispatcher(PagesConstants.WELCOME_PAGE).forward(request, response);
    }

    @Override
    public void destroy() {
        LOG.trace("Destroy AuthorizationFilter");
    }
}
