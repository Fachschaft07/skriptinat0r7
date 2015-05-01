/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

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

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date printoutDelivery;

    @OneToMany
    private Set<StudentOrder> studentOrders;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return (Date) (orderDate == null ? null : orderDate.clone());
    }

    public void setOrderDate(final Date orderDate) {
        this.orderDate = (Date) (orderDate == null ? null : orderDate.clone());
    }

    public Date getPrintoutDelivery() {
        return (Date) (printoutDelivery == null ? null : printoutDelivery.clone());
    }

    public void setPrintoutDelivery(final Date printoutDelivery) {
        this.printoutDelivery = (Date) (printoutDelivery == null ? null : printoutDelivery.clone());
    }

    public Set<StudentOrder> getStudentOrders() {
        return studentOrders;
    }

    public void setStudentOrders(final Set<StudentOrder> studentOrders) {
        this.studentOrders = studentOrders;
    }

    /**
     * Adds a {@code StudentOrder} to this {@code CopyShopOrder}.
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
     * Removes a {@code StudentOrder} from this {@code CopyShopOrder}.
     *
     * @param studentOrder
     *            the studentOrder to remove.
     */
    public void removeStudentOrder(final StudentOrder studentOrder) {
        studentOrders.remove(studentOrder);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, orderDate, printoutDelivery, studentOrders);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CopyShopOrder)) {
            return false;
        }

        final CopyShopOrder other = (CopyShopOrder) obj;
        if (!Objects.equals(id, other.id)) {
            return false;
        }
        if (!Objects.equals(orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(printoutDelivery, other.printoutDelivery)) {
            return false;
        }
        if (!Objects.equals(studentOrders, other.studentOrders)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "CopyShopOrder [id=" + id + ", orderDate=" + orderDate + ", printoutDelivery=" + printoutDelivery + "]";
    }
}
