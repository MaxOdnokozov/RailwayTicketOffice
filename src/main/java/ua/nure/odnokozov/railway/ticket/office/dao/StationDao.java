package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Station;

public interface StationDao {

    Station getById(long id);

    boolean create(Station station);

    boolean update(Station station);

    boolean delete(long id);

    List<Station> getAll();
}
