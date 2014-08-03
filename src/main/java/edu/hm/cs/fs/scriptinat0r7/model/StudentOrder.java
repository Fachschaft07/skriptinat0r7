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
 * Represents a student order.
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
    private Set<ScriptDocment> containsScriptDocuments; //TODO: Warum ScriptDocument und nicht Script?

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date studentCustomerOrderIssueDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date copyShopOrderDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date copyShopOrderisDeliveredToFachschaftDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date studentCustomerPickupDate;

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

    public Set<ScriptDocment> getContainsScriptDocuments() {
        return containsScriptDocuments;
    }

    public void setContainsScriptDocuments(final Set<ScriptDocment> containsScriptDocuments) {
        this.containsScriptDocuments = containsScriptDocuments;
    }

    public Date getStudentCustomerOrderIssueDate() {
        return (Date) ((Date) studentCustomerOrderIssueDate == null ? null : studentCustomerOrderIssueDate.clone());
    }

    public void setStudentCustomerOrderIssueDate(final Date studentCustomerOrderIssueDate) {
        this.studentCustomerOrderIssueDate = (Date) (studentCustomerOrderIssueDate == null ? null
                : studentCustomerOrderIssueDate.clone());
    }

    public Date getCopyShopOrderDate() {
        return (Date) (copyShopOrderDate == null ? null : copyShopOrderDate.clone());
    }

    public void setCopyShopOrderDate(final Date copyShopOrderDate) {
        this.copyShopOrderDate = (Date) (copyShopOrderDate == null ? null : copyShopOrderDate.clone());
    }

    public Date getCopyShopOrderisDeliveredToFachschaftDate() {
        return copyShopOrderisDeliveredToFachschaftDate;
    }

    public void setCopyShopOrderisDeliveredToFachschaftDate(final Date copyShopOrderisDeliveredToFachschaftDate) {
        this.copyShopOrderisDeliveredToFachschaftDate = copyShopOrderisDeliveredToFachschaftDate;
    }

    public Date getStudentCustomerPickupDate() {
        return studentCustomerPickupDate;
    }

    public void setStudentCustomerPickupDate(final Date studentCustomerPickupDate) {
        this.studentCustomerPickupDate = studentCustomerPickupDate;
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
        hash = (79 * hash) + Objects.hashCode(this.containsScriptDocuments);
        hash = (79 * hash) + Objects.hashCode(this.studentCustomerOrderIssueDate);
        hash = (79 * hash) + Objects.hashCode(this.copyShopOrderDate);
        hash = (79 * hash) + Objects.hashCode(this.copyShopOrderisDeliveredToFachschaftDate);
        hash = (79 * hash) + Objects.hashCode(this.studentCustomerPickupDate);
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
        if (!Objects.equals(this.containsScriptDocuments, other.containsScriptDocuments)) {
            return false;
        }
        if (!Objects.equals(this.studentCustomerOrderIssueDate, other.studentCustomerOrderIssueDate)) {
            return false;
        }
        if (!Objects.equals(this.copyShopOrderDate, other.copyShopOrderDate)) {
            return false;
        }
        if (!Objects.equals(this.copyShopOrderisDeliveredToFachschaftDate,
                other.copyShopOrderisDeliveredToFachschaftDate)) {
            return false;
        }
        if (!Objects.equals(this.studentCustomerPickupDate, other.studentCustomerPickupDate)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }

}
