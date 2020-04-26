package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;

public abstract class AbstractDao<T> {

    private static final Logger LOG = Logger.getLogger(AbstractDao.class);

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    public T getByParameter(String query, StatementMapper<T> statementMapper, EntityMapper<T> entityMapper) {
        T result = null;
        try (Connection connection = connectionFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statementMapper.map(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = entityMapper.map(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception when getting an entity by id", e);
        }
        return result;
    }

    public List<T> getAll(String query, EntityMapper<T> mapper) {
        List<T> result = new LinkedList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T entity = mapper.map(resultSet);
                    result.add(entity);
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception on getting all entities", e);
        }
        return result;
    }

    public List<T> getAllByParameter(String query, StatementMapper<T> statementMapper, EntityMapper<T> entityMapper) {
        List<T> result = new LinkedList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statementMapper.map(statement);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        T entity = entityMapper.map(resultSet);
                        result.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception when getting all entities by id", e);
        }
        return result;
    }

    public long create(String query, StatementMapper<T> statementMapper) {
        long generatedId = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
                    statementMapper.map(statement);
                    statement.executeUpdate();
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = resultSet.getLong(1);
                            connection.commit();
                        }
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback of all changes due to an exception when creating an entity", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to create entity", e);
        }
        return generatedId;
    }

    public long create(Connection connection, String query, StatementMapper<T> statementMapper) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statementMapper.map(statement);
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    return generatedId;
                }
                throw new SQLException("ResultSet is empty after creating an entity");
            }
        }
    }

    public List<Long> createAll(Connection connection, String query, StatementMapper<T> statementMapper)
            throws SQLException {
        List<Long> generatedIds = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statementMapper.map(statement);
            statement.executeBatch();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    generatedIds.add(resultSet.getLong(1));
                }
                return generatedIds;
            }
        }
    }

    public List<Long> createAll(String query, StatementMapper<T> statementMapper) {
        List<Long> generatedIds = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
                    statementMapper.map(statement);
                    statement.executeBatch();
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        while (resultSet.next()) {
                            generatedIds.add(resultSet.getLong(1));
                        }
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback of all changes due to an exception when creating an entity", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to create entity", e);
        }
        return generatedIds;
    }

    public int updateDelete(String query, StatementMapper<T> statementMapper) {
        int result = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statementMapper.map(statement);
                    result = statement.executeUpdate();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback of all changes due to an exception when updating/deleting an entity", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to update/delete entity", e);
        }
        return result;
    }
}