/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;

import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;

/**
 * Represents a user of the system.
 */
@Entity
@Table(name = "skriptorUser")
@javax.persistence.Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Role role;

    @Column(columnDefinition = "varchar(254)")
    private InternetAddress email;

    private String firstName;
    private String lastName;
    private String facultyID;

    /**
     * Returns the full name (firstname and lastname) of the {@code User}.
     * 
     * @return the full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public InternetAddress getEmail() {
        return email;
    }

    public void setEmail(final InternetAddress email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(final String facultyID) {
        this.facultyID = facultyID;
    }

}
