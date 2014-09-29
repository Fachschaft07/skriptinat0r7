/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;

/**
 * Represents a user of the system.
 */
@Entity
@Table(name = "skriptorUser")
@javax.persistence.Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Role role;

    @Column(columnDefinition = "varchar(254)")
    @Email
    private String email;

    private String firstName;
    private String lastName;
    private String facultyID;

    @OneToMany
    private Set<StudentOrder> studentOrders;

    @Transient
    private String password;

    /**
     * Returns the full name (firstname and lastname) of the {@code User}.
     *
     * @return the full name.
     */
    public String getFullName() {
        return lastName + ", " + firstName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
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

    public Set<StudentOrder> getStudentOrders() {
        return studentOrders;
    }

    public void setStudentOrders(final Set<StudentOrder> studentOrders) {
        this.studentOrders = studentOrders;
    }

    /**
     * Adds a {@code StudentOrder} to this {@code User}.
     *
     * @param studentOrder
     *            the studentOrder to add.
     */
    public void addStudentOrder(final StudentOrder studentOrder) {
        if (studentOrders == null) {
            studentOrders = new HashSet<>();
        }
        studentOrders.add(studentOrder);
    }

    /**
     * Removes a {@code StudentOrder} from this {@code User}.
     *
     * @param studentOrder
     *            the studentOrder to remove.
     */
    public void removeStudentOrder(final StudentOrder studentOrder) {
        studentOrders.remove(studentOrder);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(getRole().asAuthority());
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return getFacultyID();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, email, firstName, lastName, facultyID);
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (!(obj instanceof User)) { return false; }

        final User other = (User) obj;
        if (!(other.canEqual(this))) { return false; }
        if (!Objects.equals(role, other.role)) { return false; }
        if (!Objects.equals(email, other.email)) { return false; }
        if (!Objects.equals(firstName, other.firstName)) { return false; }
        if (!Objects.equals(lastName, other.lastName)) { return false; }
        if (!Objects.equals(facultyID, other.facultyID)) { return false; }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity

    /**
     * Checks if the specified Object is able to be equal to this {@code User}.
     *
     * @param obj
     *            the {@code Object} to check.
     * @return {@code true} if the specified Object can equal {@code this}, otherwise {@code false}.
     */
    public boolean canEqual(final Object obj) {
        return obj instanceof User;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", role=" + role + ", email=" + email + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }

}
