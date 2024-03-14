package org.example.test_run.manager;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @SequenceGenerator(
            name = "manager_sequence",
            sequenceName = "manager_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "manager_sequence"
    )
    private Long id;
    private String firstName;
    private String email;
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Franchise> franchises = new HashSet<>();

    private Integer howOld;


    public Set<Franchise> getFranchises() {
        return franchises;
    }

    public void setFranchises(Set<Franchise> franchises) {
        this.franchises = franchises;
    }

    public Manager() {
    }

    public Manager(Long id,
                   String name,
                   String email,
                   LocalDate dob) {
        this.id = id;
        this.firstName = name;
        this.email = email;
        this.dateOfBirth = dob;
    }

    public Manager(String name,
                   String email,
                   LocalDate dob) {
        this.firstName = name;
        this.email = email;
        this.dateOfBirth = dob;
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
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getHowOld() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
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
                ", dateOfBirth=" + dateOfBirth +
                ", howOld=" + howOld +
                '}';
    }
}
