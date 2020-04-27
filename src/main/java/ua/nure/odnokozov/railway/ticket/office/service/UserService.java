package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.Optional;

import org.apache.log4j.Logger;

import com.lambdaworks.crypto.SCryptUtil;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.dao.UserDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.UserDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.dto.DTOConverter;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.util.EmailSender;

public class UserService {
    
    private static final Logger LOG = Logger.getLogger(UserService.class);
    
    private UserDao userDao = new UserDaoImpl();

    public Optional<UserDTO> registrateUser(User user) {
        LOG.info("Registrating new user");
        String passwordHash = getPasswordHash(user.getPassword());
        user.setPassword(passwordHash);
        boolean isRegistrated = userDao.create(user);
        if(isRegistrated) {
            LOG.trace("Registration of new user was success");
            return Optional.of(DTOConverter.toUserDTO(userDao.getByEmail(user.getEmail())));
        }
        return Optional.empty();
    }
    
    private String getPasswordHash(String password) {
        return SCryptUtil.scrypt((password), ApplicationConstants.SCRYPT_CPU_COST,
                ApplicationConstants.SCRYPT_MEMEORY_COST, ApplicationConstants.SCRYPT_PARALLELIZATION);
    }
    
    public void sendActivationEmail(UserDTO user) {
        LOG.info("Registrating new user");
        String activationCode = userDao.getActivationCodeByUserId(user.getId()); 
        new EmailSender().sendActivationCode(user.getEmail(), activationCode);    
    }

    public boolean activateUser(UserDTO user, String activationCode) {
        LOG.info("Trying activate user profile, user id" + user.getId());
        String realActivationCode = userDao.getActivationCodeByUserId(user.getId());
        if(activationCode.equals(realActivationCode)) {
            LOG.trace("ActivationCode is valid");
            return userDao.updateActivationStatusByUserId(user.getId(), ActivationStatus.ACTIVETED);
        }
        return false;
    }

    public boolean isRegistratedUser(String email) {
        LOG.info("Checking registration user by email");
        return userDao.getByEmail(email) != null;
    }

    public Optional<UserDTO> validateUser(String email, String password) {
        Optional<User> user = Optional.ofNullable(userDao.getByEmail(email));
        if (user.isPresent() && SCryptUtil.check(password, user.get().getPassword())) {
            return Optional.of(DTOConverter.toUserDTO(user.get()));
        }
        return Optional.empty();
    }

    public boolean checkActivationCode(UserDTO user, String activationCode) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
