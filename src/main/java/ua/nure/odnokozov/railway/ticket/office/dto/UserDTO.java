package ua.nure.odnokozov.railway.ticket.office.dto;

import java.time.LocalDateTime;

import ua.nure.odnokozov.railway.ticket.office.domain.User;
import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;

public class UserDTO {

    private long id;
    private String email;
    private Role role;
    private ActivationStatus activationStatus;
    private String firstName;
    private String lastName;
    private LocalDateTime registrationDate;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.activationStatus = user.getActivationStatus();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.registrationDate = user.getRegistrationDate();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ActivationStatus getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(ActivationStatus activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
