package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;

public interface UserDao {

    User getById(long id);

    boolean create(User user);

    boolean update(User user);

    boolean delete(long id);

    User getByEmail(String email);

    List<User> getAll();

    String getActivationCodeByUserId(long id);

    boolean updateActivationStatusByUserId(long userId, ActivationStatus status);
}
