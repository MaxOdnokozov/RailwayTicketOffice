package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface EntityMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
