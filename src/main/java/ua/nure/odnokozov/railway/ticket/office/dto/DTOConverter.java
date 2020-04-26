package ua.nure.odnokozov.railway.ticket.office.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.domain.Route;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Seat;
import ua.nure.odnokozov.railway.ticket.office.domain.SeatConditions;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.domain.Ticket;
import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;

public class DTOConverter {

    private static final Logger LOG = Logger.getLogger(DTOConverter.class);

    public static RouteDTO toRouteDTO(Route route, long departureStationId, long arrivalStationId) {
        LOG.trace("Converting Route to RouteDTO");
        RouteDTO routeDTO = RouteDTO.builder()
                .id(route.getId())
                .code(route.getCode())
                .isCanceled(route.isCanceled())
                .departureStop(findStopByStationId(route.getStops(), departureStationId))
                .arrivalStop(findStopByStationId(route.getStops(), arrivalStationId))
                .timeInWay(getTimeInWay(route.getStops(), departureStationId, arrivalStationId))
                .stops(toStopsDTO(route.getStops())).routeCarriages(toRouteCarriagesDTO(route.getRouteCarriages(),
                        route.getStops(), departureStationId, arrivalStationId))
                .build();

        routeDTO.setFreeSeats(getFreeSeats(routeDTO.getRouteCarriages()));
        routeDTO.setMinPrice(getMinPrice(routeDTO.getRouteCarriages()));
        return routeDTO;
    }
    
    private static StopDTO findStopByStationId(List<Stop> stops, long stationId) {
        return new StopDTO(stops.stream().filter(stop -> stationId == stop.getStationId()).findFirst().get());
    }

    private static LocalTime getTimeInWay(List<Stop> stops, long departureStationId, long arrivalStationId) {
        StopDTO departureStop = findStopByStationId(stops, departureStationId);
        StopDTO arrivalStop = findStopByStationId(stops, arrivalStationId);

        LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalStop.getArrivalDate(), arrivalStop.getArrivalTime());
        LocalDateTime departureDateTime = LocalDateTime.of(departureStop.getDepartureDate(),
                departureStop.getDepartureTime());

        return LocalTime.ofSecondOfDay(ChronoUnit.SECONDS.between(departureDateTime, arrivalDateTime));
    }

    public static List<StopDTO> toStopsDTO(List<Stop> stops) {
        return stops.stream().map(stop -> new StopDTO(stop)).collect(Collectors.toList());
    }

    public static List<RouteCarriageDTO> toRouteCarriagesDTO(List<RouteCarriage> routeCarriages, List<Stop> stops,
            long departureStationId, long arrivalStationId) {
        return routeCarriages.stream()
                .map(routeCarriage -> toRouteCarriageDTO(routeCarriage, stops, departureStationId, arrivalStationId))
                .collect(Collectors.toList());
    }


    public static RouteCarriageDTO toRouteCarriageDTO(RouteCarriage routeCarriage, List<Stop> stops,
            long departureStationId, long arrivalStationId) {
        RouteCarriageDTO routeCarriageDTO = RouteCarriageDTO.builder().carriageId(routeCarriage.getCarriage().getId())
                .comfortType(routeCarriage.getCarriage().getComfortType()).number(routeCarriage.getNumber())
                .seats(toSeatsDTO(routeCarriage.getSeats(), stops, departureStationId, arrivalStationId))
                .price(getPrice(routeCarriage, stops, departureStationId, arrivalStationId)).build();

        routeCarriageDTO.setTotalFreeSeats(getTotalFreeSeats(routeCarriageDTO.getSeats()));
        return routeCarriageDTO;
    }

    public static List<SeatDTO> toSeatsDTO(List<Seat> seats, List<Stop> stops, long departureStationId,
            long arrivalStationId) {
        return seats.stream().map(seat -> toSeatDTO(seat, stops, departureStationId, arrivalStationId))
                .collect(Collectors.toList());
    }

    public static SeatDTO toSeatDTO(Seat seat, List<Stop> stops, long departureStationId, long arrivalStationId) {
        return SeatDTO.builder().id(seat.getId()).number(seat.getNumber())
                .setFree(isFree(seat.getSeatConditions(), stops, departureStationId, arrivalStationId)).build();
    }

    private static boolean isFree(SeatConditions seatConditions, List<Stop> stops, long departureStationId,
            long arrivalStationId) {
        StopDTO departureStop = findStopByStationId(stops, departureStationId);
        StopDTO arrivalStop = findStopByStationId(stops, arrivalStationId);
        for (int i = departureStop.getStopNumber(); i < arrivalStop.getStopNumber(); i++) {
            if (!seatConditions.isFree(i)) {
                return false;
            }
        }
        return true;
    }

    private static int getTotalFreeSeats(List<SeatDTO> seats) {
        return seats.stream().filter(seat -> seat.isFree() == true).collect(Collectors.counting()).intValue();
    }

    private static BigDecimal getPrice(RouteCarriage routeCarriage, List<Stop> stops, long departureStationId,
            long arrivalStationId) {
        StopDTO departureStop = findStopByStationId(stops, departureStationId);
        StopDTO arrivalStop = findStopByStationId(stops, arrivalStationId);
        BigDecimal result = BigDecimal.ZERO;
        for (int i = departureStop.getStopNumber(); i < arrivalStop.getStopNumber(); i++) {
            result = result.add(routeCarriage.getPrice().getStopPrice(i));
        }
        return result;
    }

    private static BigDecimal getMinPrice(List<RouteCarriageDTO> routeCarriages) {
        return Collections.min(routeCarriages, Comparator.comparing(routeCarriage -> routeCarriage.getPrice()))
                .getPrice();
    }

    private static Map<ComfortType, Integer> getFreeSeats(List<RouteCarriageDTO> routeCarriages) {
        Map<ComfortType, Integer> freeSeats = new HashMap<>();
        ComfortType[] comfortTypes = ComfortType.class.getEnumConstants();
        for (ComfortType comfortType : comfortTypes) {
            int sumFreeSeats = 0;
            for (RouteCarriageDTO routeCarriage : routeCarriages) {
                if (comfortType == routeCarriage.getComfortType()) {
                    sumFreeSeats += routeCarriage.getTotalFreeSeats();
                }
            }
            freeSeats.put(comfortType, sumFreeSeats);
        }
        return freeSeats;
    }
    
    public static List<TicketDTO> toTicketsDTO(List<Ticket> tickets) {
        return tickets.stream().map(ticket -> toTicketDTO(ticket)).collect(Collectors.toList());
    }

    public static TicketDTO toTicketDTO(Ticket ticket) {
        StopDTO departureStop = findStopByStopId(ticket.getRoute().getStops(), ticket.getDepartureStopId());
        StopDTO arrivalStop = findStopByStopId(ticket.getRoute().getStops(), ticket.getArrivalStopId());
        
        return TicketDTO.builder()
                .id(ticket.getId())
                .route(toRouteDTOWithoutSeats(ticket.getRoute(), departureStop.getStationId(), arrivalStop.getStationId()))
                .user(toUserDTO(ticket.getUser())).seatId(ticket.getSeat().getId())
                .seatNumber(ticket.getSeat().getNumber())
                .routeCarriageId(ticket.getSeat().getRouteCarriageId())
                .carriageNumber(findCarriageNumber(ticket.getRoute().getRouteCarriages(),
                        ticket.getSeat().getRouteCarriageId()))
                .price(ticket.getPrice()).isPaid(ticket.isPaid()).createdDateTime(ticket.getCreatedDateTime()).build();
    }
    
    private static RouteDTO toRouteDTOWithoutSeats(Route route, long departureStationId, long arrivalStationId) {
        LOG.trace("Converting Route to RouteDTO");
        RouteDTO routeDTO = RouteDTO.builder()
                .id(route.getId())
                .code(route.getCode())
                .isCanceled(route.isCanceled())
                .departureStop(findStopByStationId(route.getStops(), departureStationId))
                .arrivalStop(findStopByStationId(route.getStops(), arrivalStationId))
                .timeInWay(getTimeInWay(route.getStops(), departureStationId, arrivalStationId))
                .stops(toStopsDTO(route.getStops())).routeCarriages(toRouteCarriagesDTOWithoutSeats(route.getRouteCarriages(),
                        route.getStops(), departureStationId, arrivalStationId))
                .build();

        routeDTO.setFreeSeats(getFreeSeats(routeDTO.getRouteCarriages()));
        routeDTO.setMinPrice(getMinPrice(routeDTO.getRouteCarriages()));
        return routeDTO;
    }
    
    private static List<RouteCarriageDTO> toRouteCarriagesDTOWithoutSeats(List<RouteCarriage> routeCarriages, List<Stop> stops,
            long departureStationId, long arrivalStationId) {
        return routeCarriages.stream()
                .map(routeCarriage -> toRouteCarriageDTOWithoutSeats(routeCarriage, stops, departureStationId, arrivalStationId))
                .collect(Collectors.toList());
    }
    
    public static RouteCarriageDTO toRouteCarriageDTOWithoutSeats(RouteCarriage routeCarriage, List<Stop> stops,
            long departureStationId, long arrivalStationId) {
        return RouteCarriageDTO.builder()
                .carriageId(routeCarriage.getCarriage().getId())
                .comfortType(routeCarriage.getCarriage().getComfortType())
                .number(routeCarriage.getNumber())
                .price(getPrice(routeCarriage, stops, departureStationId, arrivalStationId)).build();
    }

    
    private static StopDTO findStopByStopId(List<Stop> stops, long stopId) {
        return new StopDTO(stops.stream().filter(stop -> stopId == stop.getId()).findFirst().get());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user);
    }

    private static int findCarriageNumber(List<RouteCarriage> routeCarriages, long routeCarriageId) {
        return routeCarriages.stream().filter(routeCarriage -> routeCarriage.getId() == routeCarriageId).findFirst()
                .get().getNumber();
    }

    public static List<RouteDTO> toRoutesDTO(List<Route> routes, long departureStationId, long arrivalStationId) {
        return routes.stream().map(route -> DTOConverter.toRouteDTO(route, departureStationId, arrivalStationId))
                .collect(Collectors.toList());
    }
}