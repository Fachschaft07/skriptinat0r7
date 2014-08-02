/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author usn1982e
 */
@Entity
public class Student extends User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @OneToMany
    Set<StudentOrder> studentsOrders;

    public Set<StudentOrder> getStudentsOrders() {
        return studentsOrders;
    }

    public void setStudentsOrders(final Set<StudentOrder> studentsOrders) {
        this.studentsOrders = studentsOrders;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = (53 * hash) + Objects.hashCode(this.studentsOrders);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.studentsOrders, other.studentsOrders)) {
            return false;
        }
        return true;
    }

}
