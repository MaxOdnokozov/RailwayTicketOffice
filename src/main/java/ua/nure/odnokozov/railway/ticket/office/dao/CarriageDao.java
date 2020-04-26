package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;

public interface CarriageDao {

    Carriage getById(long id);

    Carriage create(Carriage carriage);

    boolean update(Carriage carriage);

    boolean delete(long id);

    List<Carriage> getAll();
}