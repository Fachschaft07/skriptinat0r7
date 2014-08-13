/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Represents a student order. A {@code StudentOrder} is part of a {@code CopyShopOrder}.
 */
@Entity
@Table(name = "skriptorOrder")
public class StudentOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StudentOrderId", nullable = false)
    @Id
    private Integer studentOrderId;

    @ManyToOne
    private CopyShopOrder copyShopOrder;

    @OneToMany
    private Set<ScriptDocument> scriptDocuments;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date studentOrder;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date copyShopOrderDate; // TODO: Max: In CopyShopOrder verschieben

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date documentsIngoing; // TODO: Max: In CopyShopOrder verschieben

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date studentPickup;

    @Lob
    private String notes;

    public Integer getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(final Integer studentOrderid) {
        this.studentOrderId = studentOrderid;
    }

    public CopyShopOrder getCopyShopOrder() {
        return copyShopOrder;
    }

    public void setCopyShopOrder(final CopyShopOrder copyShopOrder) {
        this.copyShopOrder = copyShopOrder;
    }

    public Set<ScriptDocument> getContainsScriptDocuments() {
        return scriptDocuments;
    }

    public void setContainsScriptDocuments(final Set<ScriptDocument> containsScriptDocuments) {
        this.scriptDocuments = containsScriptDocuments;
    }

    public Date getStudentCustomerOrderIssueDate() {
        return (Date) ((Date) studentOrder == null ? null : studentOrder.clone());
    }

    public void setStudentCustomerOrderIssueDate(final Date studentCustomerOrderIssueDate) {
        this.studentOrder = (Date) (studentCustomerOrderIssueDate == null ? null : studentCustomerOrderIssueDate
                .clone());
    }

    public Date getCopyShopOrderDate() {
        return (Date) (copyShopOrderDate == null ? null : copyShopOrderDate.clone());
    }

    public void setCopyShopOrderDate(final Date copyShopOrderDate) {
        this.copyShopOrderDate = (Date) (copyShopOrderDate == null ? null : copyShopOrderDate.clone());
    }

    public Date getCopyShopOrderisDeliveredToFachschaftDate() {
        return documentsIngoing;
    }

    public void setDocumentsDeliveredToFachschaftDate(final Date documentsDeliveredToFachschaftDate) {
        this.documentsIngoing = documentsDeliveredToFachschaftDate;
    }
    
    // TODO: Max: Getter und Setter überarbeiten und Namen ändern.

    public Date getStudentCustomerPickupDate() {
        return studentPickup;
    }

    public void setStudentCustomerPickupDate(final Date studentCustomerPickupDate) {
        this.studentPickup = studentCustomerPickupDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = (79 * hash) + Objects.hashCode(this.studentOrderId);
        hash = (79 * hash) + Objects.hashCode(this.copyShopOrder);
        hash = (79 * hash) + Objects.hashCode(this.scriptDocuments);
        hash = (79 * hash) + Objects.hashCode(this.studentOrder);
        hash = (79 * hash) + Objects.hashCode(this.copyShopOrderDate);
        hash = (79 * hash) + Objects.hashCode(this.documentsIngoing);
        hash = (79 * hash) + Objects.hashCode(this.studentPickup);
        hash = (79 * hash) + Objects.hashCode(this.notes);
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
        final StudentOrder other = (StudentOrder) obj;
        if (!Objects.equals(this.studentOrderId, other.studentOrderId)) {
            return false;
        }
        if (!Objects.equals(this.copyShopOrder, other.copyShopOrder)) {
            return false;
        }
        if (!Objects.equals(this.scriptDocuments, other.scriptDocuments)) {
            return false;
        }
        if (!Objects.equals(this.studentOrder, other.studentOrder)) {
            return false;
        }
        if (!Objects.equals(this.copyShopOrderDate, other.copyShopOrderDate)) {
            return false;
        }
        if (!Objects.equals(this.documentsIngoing, other.documentsIngoing)) {
            return false;
        }
        if (!Objects.equals(this.studentPickup, other.studentPickup)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }

}
