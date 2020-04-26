package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementMapper<T> {
    void map(PreparedStatement statement) throws SQLException;
}
