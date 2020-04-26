package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;
import ua.nure.odnokozov.railway.ticket.office.dao.UserDao;
import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.UserBuilder;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email=? LIMIT 1";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users WHERE role!='ADMIN'";
    private static final String SQL_INSERT_USER = "INSERT INTO users(email, password_hash, first_name, last_name, "
            + "date_registration) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE id=? LIMIT 1";
    private static final String SQL_UPDATE_USER = "UPDATE users SET email=?, password_hash=?, first_name=?, "
            + "last_name=?, WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_ACTIVATION_CODE_BY_USER_ID = "SELECT activation_code FROM activation_codes "
            + "WHERE user_id=? LIMIT 1";
    private static final String SQL_UPDATE_USER_ACTIVATION_STATUS_BY_USER_ID = "UPDATE users SET activation_status=? "
            + "WHERE id=? LIMIT 1";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD_HASH = "password_hash";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_ACTIVATION_STATUS = "activation_status";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_REGISTRATION_DATE = "date_registration";
    private static final String COLUMN_ACTIVATION_CODE = "activation_code";

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public User getById(long id) {
        LOG.trace("Obtain user by id from the database");
        return getByParameter(SQL_SELECT_USER_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    public EntityMapper<User> getMapper() {
        return resultSet -> UserBuilder.getInstance().id(resultSet.getLong(COLUMN_ID))
                .email(resultSet.getString(COLUMN_EMAIL)).password(resultSet.getString(COLUMN_PASSWORD_HASH))
                .role(Role.valueOf(resultSet.getString(COLUMN_ROLE)))
                .activationStatus(ActivationStatus.valueOf(resultSet.getString(COLUMN_ACTIVATION_STATUS)))
                .firstName(resultSet.getString(COLUMN_FIRST_NAME)).lastName(resultSet.getString(COLUMN_LAST_NAME))
                .registrationDate(LocalDateTime.parse(resultSet.getString(COLUMN_REGISTRATION_DATE),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    @Override
    public User getByEmail(String email) {
        LOG.trace("Getting user by email from the database");
        return getByParameter(SQL_SELECT_USER_BY_EMAIL, ps -> ps.setString(1, email), getMapper());
    }

    @Override
    public List<User> getAll() {
        LOG.trace("Getting all users from database");
        return getAll(SQL_SELECT_ALL_USERS, getMapper());
    }

    @Override
    public boolean create(User entity) {
        LOG.trace("Creating a new user into the database");
        long generatedId = create(SQL_INSERT_USER, ps -> {
            int count = 1;
            ps.setString(count++, entity.getEmail());
            ps.setString(count++, entity.getPassword());
            ps.setString(count++, entity.getFirstName());
            ps.setString(count++, entity.getLastName());
            ps.setString(count, LocalDateTime.now().toString());
        });
        return generatedId > 0;
    }

    @Override
    public boolean update(User entity) {
        LOG.trace("Update user in the database");
        int updatedRow = updateDelete(SQL_UPDATE_USER, ps -> {
            int count = 1;
            ps.setString(count++, entity.getEmail());
            ps.setString(count++, entity.getPassword());
            ps.setString(count++, entity.getFirstName());
            ps.setString(count++, entity.getLastName());
            ps.setLong(count, entity.getId());
        });
        return updatedRow == 1;
    }

    @Override
    public boolean delete(long id) {
        LOG.trace("Delete user from the database");
        int updatedRow = updateDelete(SQL_DELETE_USER, ps -> ps.setLong(1, id));
        return updatedRow == 1;
    }

    @Override
    public String getActivationCodeByUserId(long id) {
        LOG.trace("Get activation code from datebase by user id");
        String result = null;
        try (Connection connection = connectionFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ACTIVATION_CODE_BY_USER_ID)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result = resultSet.getString(COLUMN_ACTIVATION_CODE);
                    }
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception when getting aactivation code by user id", e);
        }
        return result;
    }

    @Override
    public boolean updateActivationStatusByUserId(long userId, ActivationStatus status) {
        LOG.trace("Update user activation status in the database");
        int updatedRow = updateDelete(SQL_UPDATE_USER_ACTIVATION_STATUS_BY_USER_ID, ps -> {
            int count = 1;
            ps.setString(count++, status.getName());
            ps.setLong(count, userId);
        });
        return updatedRow == 1;
    }
}