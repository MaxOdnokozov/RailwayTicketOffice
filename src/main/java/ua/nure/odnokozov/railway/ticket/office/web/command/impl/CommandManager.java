package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.web.command.Command;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.AdminAddUserFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.AdminHomePageCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage.AdminAddCarriageCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage.AdminAddCarriageFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage.AdminDeleteCarriageCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage.AdminEditCarriageFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage.AdminViewAllCarriagesCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminAddRouteCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminAddRouteFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminCancelAddRouteCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminDeleteRouteCarriageCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminDeleteRouteStopCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminDeleteRoutesCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminEditRouteFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminFindRouteCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminSaveRouteCarriageCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminSaveRouteCodeCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminSaveRouteStopCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route.AdminViewRoutesCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminAddStationCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminAddStationFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminCancelEditStationCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminDeleteStationCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminEditStationFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminSaveStationCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station.AdminViewAllStationsCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientActivationEmailFormCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientBookingSeatsCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientCheckActivationCodeCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientErrorTicketsPaymentCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientHomePageCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientPayTicketsCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientSuccessfulActivationEmailCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientSuccessfulTicketsPaymentCommand;
import ua.nure.odnokozov.railway.ticket.office.web.command.impl.client.ClientTicketsPaymentCommand;

public class CommandManager {
    
    private static final Logger LOG = Logger.getLogger(CommandManager.class);
    
    private static Map<String, Command> commands = new TreeMap<>();
    
    static {        
        commands.put("/find-routes", new FindRouteCommand());
        commands.put("/show-route-stops", new ShowRouteStopsCommand());
        commands.put("/show-route-carriages", new ShowRouteCarriagesCommand());
        commands.put("/show-route-seats", new ShowRouteSeatsCommand());        
        commands.put("/login", new LoginCommand());
        commands.put("/logout", new LogoutCommand());
        commands.put("/registration" , new RegistrationCommand());
        
        commands.put("/client-activation-email-form" , new ClientActivationEmailFormCommand());
        commands.put("/client-check-activation-code" , new ClientCheckActivationCodeCommand());
        commands.put("/client-successful-activation-email" , new ClientSuccessfulActivationEmailCommand());
        commands.put("/client-home-page", new ClientHomePageCommand());
        commands.put("/client-booking-seats", new ClientBookingSeatsCommand());
        commands.put("/client-tickets-payment", new ClientTicketsPaymentCommand());
        commands.put("/client-pay-tickets", new ClientPayTicketsCommand());
        commands.put("/client-successful-tickets-payment", new ClientSuccessfulTicketsPaymentCommand());
        commands.put("/client-error-tickets-payment", new ClientErrorTicketsPaymentCommand());
        
        commands.put("/admin-home-page", new AdminHomePageCommand());
        commands.put("/admin-find-routes", new AdminFindRouteCommand());
        commands.put("/admin-add-user-form", new AdminAddUserFormCommand());
        commands.put("/admin-add-carraige-form", new AdminAddCarriageFormCommand());
        commands.put("/admin-add-route-form", new AdminAddRouteFormCommand());        
        commands.put("/admin-add-carriage", new AdminAddCarriageCommand());
        commands.put("/admin-edit-carriage-form", new AdminEditCarriageFormCommand());
        commands.put("/admin-delete-carriage", new AdminDeleteCarriageCommand());
        commands.put("/admin-save-route-code", new AdminSaveRouteCodeCommand());
        commands.put("/admin-save-route-stop", new AdminSaveRouteStopCommand());
        commands.put("/admin-save-route-carriage", new AdminSaveRouteCarriageCommand());
        commands.put("/admin-add-route", new AdminAddRouteCommand());        
        commands.put("/admin-view-all-carriages", new AdminViewAllCarriagesCommand());
        commands.put("/admin-view-all-stations", new AdminViewAllStationsCommand());
        commands.put("/admin-add-station-form", new AdminAddStationFormCommand());
        commands.put("/admin-edit-station-form", new AdminEditStationFormCommand());
        commands.put("/admin-delete-station", new AdminDeleteStationCommand());
        commands.put("/admin-save-edit-station", new AdminSaveStationCommand());
        commands.put("/admin-add-station", new AdminAddStationCommand());
        commands.put("/admin-cancel-station", new AdminCancelEditStationCommand());
        commands.put("/admin-view-routes", new AdminViewRoutesCommand());
        commands.put("/admin-delete-route", new AdminDeleteRoutesCommand());
        commands.put("/admin-edit-route-form", new AdminEditRouteFormCommand());
        commands.put("/admin-save-edit-route", new AdminSaveEditRouteCommand());
        commands.put("/admin-cancel-add-route", new AdminCancelAddRouteCommand());
        commands.put("/admin-cancel-edit-route", new AdminCancelEditRouteCommand());
        commands.put("/admin-delete-route-stop", new AdminDeleteRouteStopCommand());
        commands.put("/admin-delete-route-carriage", new AdminDeleteRouteCarriageCommand());
        
        commands.put("/no-command" , new NoCommand());        
    }
    
    public static Command getCommand(String command) {
        if(command == null || !commands.containsKey(command)) {
            LOG.warn("Command = '" + command + "' was not found");
            return commands.get("/no-command");
        }
        return commands.get(command);
    }
}
