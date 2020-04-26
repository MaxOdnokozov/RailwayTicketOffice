package ua.nure.odnokozov.railway.ticket.office.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionFactory {

    private static ConnectionFactory instance;

    private static final Logger LOG = Logger.getLogger(ConnectionFactory.class);

    private  DataSource dataSource;

    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            return new ConnectionFactory();
        }
        return instance;
    }

    private ConnectionFactory() {
    }

    public Connection getConnection() {
        LOG.debug("Getting the connection");
        Connection connection = null;
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/railway_ticket_office_db");
            return dataSource.getConnection();
        } catch (NamingException e) {
            LOG.trace("Can not obtain data source", e);
        } catch (SQLException e) {
            LOG.trace("Can not obtain connection");
        }
        return connection;
    }
}