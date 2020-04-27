package ua.nure.odnokozov.railway.ticket.office.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import ua.nure.odnokozov.railway.ticket.office.dao.UserDao;
import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.UserBuilder;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    
    private static final String USER_EMAIL = "email@gmail.com";
    private static final String VALID_PASSWORD = "asdfasdf";
    private static final String FIRST_NAME = "Chack";
    private static final String LAST_NAME = "McGeaL";
    private static final String USER_PASSWORD_HASH = "$s0$41010$VqYUp+7Hp3VdAvQwRlwvqQ==$uBjbAbxBR6VsgsfFdFzcdvPvVddfTkiKJhBk2/xGlPE=";
    private static final String INVALID_PASSWORD = "invalid password";
    private static final String INVALID_EMAIL = "invalid email";
    private static final String REGISTRATION_DATE = "2020-12-02T12:34";
    private static final Long ID = 1L;
    private static final String VALID_ACTIVATION_CODE = "1234546dfg";
    private static final String INVALID_ACTIVATION_CODE = "1234546qwerdfg";

    @InjectMocks
    private UserService instance;
    
    @Mock
    private UserDao userDao;
    
    private User user;
    
    private UserDTO userDTO;
    
    @Before
    public void setUp() {
        
        user = UserBuilder.getInstance()
                .id(ID)
                .password(USER_PASSWORD_HASH)
                .email(USER_EMAIL)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .activationStatus(ActivationStatus.INACTIVE)
                .registrationDate(LocalDateTime.parse(REGISTRATION_DATE))
                .role(Role.CLIENT)
                .build();
        
        userDTO = new UserDTO(user);
        
        when(userDao.create(user)).thenReturn(true);
        when(userDao.getByEmail(USER_EMAIL)).thenReturn(user);
        when(userDao.getActivationCodeByUserId(ID)).thenReturn(VALID_ACTIVATION_CODE);
        when(userDao.updateActivationStatusByUserId(ID, ActivationStatus.ACTIVETED)).thenReturn(true);
    }
    
    @Test
    public void shouldReturnUserDTOIfEmailAndPasswordValid() {
        Optional<UserDTO> result = instance.validateUser(USER_EMAIL, VALID_PASSWORD);    
        
        assertThat(result).isNotEmpty();
    }
    
    @Test
    public void shouldReturnEmptyWhenInvalidPassword() {
        Optional<UserDTO> result = instance.validateUser(USER_EMAIL, INVALID_PASSWORD);

        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnEmptyWhenInvalidEmail() {
        Optional<UserDTO> result = instance.validateUser(INVALID_EMAIL, INVALID_PASSWORD);
        
        assertThat(result).isEmpty();
    }
    
    @Test
    public void shouldReturnTrueIfUserWillRigistrated() {
        Optional<UserDTO> result = instance.registrateUser(user);
        
        assertThat(result).isNotEmpty();
        assertThat(result.get().getEmail()).isEqualTo(userDTO.getEmail());
    }
    
    @Test
    public void shouldReturnTrueIfValidActivationCode() {
        Boolean result = instance.activateUser(userDTO, VALID_ACTIVATION_CODE);;
        
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfInvalidActivationCode() {
        Boolean result = instance.activateUser(userDTO, INVALID_ACTIVATION_CODE);;
        
        assertThat(result).isFalse();
    }
}
