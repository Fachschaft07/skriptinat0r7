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

@Entity
@Table(name = "skriptorUser")
@javax.persistence.Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static enum Role {

        Regular, Fachschaftler, Professor, Gesperrt, Author;
    };

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

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        return sb.toString();
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
