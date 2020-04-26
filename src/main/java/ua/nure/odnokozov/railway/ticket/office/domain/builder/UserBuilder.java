package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import java.time.LocalDateTime;

import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;

public class UserBuilder {

    private User user;
    
    private UserBuilder() {
        this.user = new User();
    }
    
    public static UserBuilder getInstance() {
        return new UserBuilder();
    }
    
    public UserBuilder id(long id) {
        user.setId(id);
        return this;
    }
    
    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }
    
    public UserBuilder firstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }
    
    public UserBuilder lastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }
    
    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }
    
    public UserBuilder role(Role role) {
        user.setRole(role);
        return this;
    }
    
    public UserBuilder activationStatus(ActivationStatus status) {
        user.setActivationStatus(status);
        return this;
    }
    
    public UserBuilder registrationDate(LocalDateTime registrationDate) {
        user.setRegistrationDate(registrationDate);
        return this;
    }

    public User build() {
        return user;
    }  
}