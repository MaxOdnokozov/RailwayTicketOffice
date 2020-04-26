package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.SeatConditionsDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.SeatConditionsDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteDTO;

public class SeatService {

    private static final Logger LOG = Logger.getLogger(SeatService.class);

    private SeatConditionsDao seatConditionsDao = new SeatConditionsDaoImpl();

    public boolean bookSeats(RouteDTO route, List<Long> seatsIds) {
        LOG.info("Booking seats in route by seats ids :: " + seatsIds);
        int departureStopNumber = route.getDepartureStop().getStopNumber();
        int arrivalStopNumber = route.getArrivalStop().getStopNumber();
        return seatConditionsDao.updateBySeatsIdsAndStopNumbers(seatsIds, departureStopNumber, arrivalStopNumber, false);
    }

    public void cancelBookSeats(RouteDTO route, List<Long> seatsIds) {
        LOG.info("Unbooking seats in route by seats ids :: " + seatsIds);
        int departureStopNumber = route.getDepartureStop().getStopNumber();
        int arrivalStopNumber = route.getArrivalStop().getStopNumber();
        seatConditionsDao.updateBySeatsIdsAndStopNumbers(seatsIds, departureStopNumber, arrivalStopNumber, true);      
    }
}
