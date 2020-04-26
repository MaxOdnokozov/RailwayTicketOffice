package ua.nure.odnokozov.railway.ticket.office.domain;

import java.time.LocalDateTime;

import ua.nure.odnokozov.railway.ticket.office.enums.ActivationStatus;
import ua.nure.odnokozov.railway.ticket.office.enums.Role;

public class User extends Entity {

    private static final long serialVersionUID = 8705765955964673143L;

    private String email;
    private String password;
    private Role role;
    private ActivationStatus activationStatus;
    private String firstName;
    private String lastName;
    private LocalDateTime registrationDate;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("User [id=").append(getId())
                .append(", email=").append(email)
                .append(", role=").append(role)
                .append(", activationStatus=")
                .append(activationStatus).append("]")
                .toString();
    }
}
