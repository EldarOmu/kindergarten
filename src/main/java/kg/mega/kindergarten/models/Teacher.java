package kg.mega.kindergarten.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import kg.mega.kindergarten.enums.Position;
import org.hibernate.annotations.DialectOverride;

import java.util.Date;

@Entity
@Table(name = "teachers")

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please enter your first name!")
    private String firstName;
    @NotBlank(message = "Please enter your last name!")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Position position;
    @NotBlank(message = "Date of birth cannot be blank!")
    @Past
    private Date dateOfBirth;
    @OneToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
