/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents an order to the copy shop. It goes out as a printed document to igeko.
 */
@Entity
@Table(name = "skriptorCopyShopOrder")
public class CopyShopOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    // TODO: Eventuell Fertigstellung bis und Uhrzeit noch als Attribute aufnehmen

    @OneToMany
    private Set<StudentOrder> studentOrders;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Set<StudentOrder> getStudentOrders() {
        return studentOrders;
    }

    public void setStudentOrders(Set<StudentOrder> studentOrders) {
        this.studentOrders = studentOrders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null) ? id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(final Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CopyShopOrder)) {
            return false;
        }
        CopyShopOrder other = (CopyShopOrder) object;
        if (((this.id == null) && (other.id != null)) || ((this.id != null) && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.hm.cs.fs.entityBeans.CopyShopOrder[ id=" + id + " ]";
    }

}
