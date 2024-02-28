package org.example.test_run.manager;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Manager {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String firstName;
    private String email;
    private LocalDate DateOfBirth;
    @Transient
    private Integer howOld;

    public Manager() {
    }

    public Manager(Long id,
                   String name,
                   String email,
                   LocalDate dob) {
        this.id = id;
        this.firstName = name;
        this.email = email;
        this.DateOfBirth = dob;
    }

    public Manager(String name,
                   String email,
                   LocalDate dob) {
        this.firstName = name;
        this.email = email;
        this.DateOfBirth = dob;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public Integer getHowOld() {
        return Period.between(this.DateOfBirth, LocalDate.now()).getYears();
    }

    public void setHowOld(Integer howOld) {
        this.howOld = howOld;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", howOld=" + howOld +
                '}';
    }
}
