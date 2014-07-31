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
 *
 * @author usn1982e
 */
@Entity
@Table(name="skriptorOrder")
public class StudentOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StudentOrderId", nullable = false)
    @Id private Integer studentOrderid;
    
    @ManyToOne
    private CopyShopOrder copyShopOrder;
    
    @OneToMany
    Set<ScriptDocment> containsScriptDocuments;

    public Set<ScriptDocment> getContainsScriptDocuments() {
        return containsScriptDocuments;
    }

    public void setContainsScriptDocuments(Set<ScriptDocment> containsScriptDocuments) {
        this.containsScriptDocuments = containsScriptDocuments;
    }
      
   
    
    
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

    public Integer getStudentOrderid() {
        return studentOrderid;
    }

    public void setStudentOrderid(Integer studentOrderid) {
        this.studentOrderid = studentOrderid;
    }

    public CopyShopOrder getCopyShopOrder() {
        return copyShopOrder;
    }

    public void setCopyShopOrder(CopyShopOrder copyShopOrder) {
        this.copyShopOrder = copyShopOrder;
    }

    public Date getStudentCustomerOrderIssueDate() {
        return studentCustomerOrderIssueDate;
    }

    public void setStudentCustomerOrderIssueDate(Date studentCustomerOrderIssueDate) {
        this.studentCustomerOrderIssueDate = studentCustomerOrderIssueDate;
    }

    public Date getCopyShopOrderDate() {
        return copyShopOrderDate;
    }

    public void setCopyShopOrderDate(Date copyShopOrderDate) {
        this.copyShopOrderDate = copyShopOrderDate;
    }

    public Date getCopyShopOrderisDeliveredToFachschaftDate() {
        return copyShopOrderisDeliveredToFachschaftDate;
    }

    public void setCopyShopOrderisDeliveredToFachschaftDate(Date copyShopOrderisDeliveredToFachschaftDate) {
        this.copyShopOrderisDeliveredToFachschaftDate = copyShopOrderisDeliveredToFachschaftDate;
    }

    public Date getStudentCustomerPickupDate() {
        return studentCustomerPickupDate;
    }

    public void setStudentCustomerPickupDate(Date studentCustomerPickupDate) {
        this.studentCustomerPickupDate = studentCustomerPickupDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.studentOrderid);
        hash = 79 * hash + Objects.hashCode(this.copyShopOrder);
        hash = 79 * hash + Objects.hashCode(this.containsScriptDocuments);
        hash = 79 * hash + Objects.hashCode(this.studentCustomerOrderIssueDate);
        hash = 79 * hash + Objects.hashCode(this.copyShopOrderDate);
        hash = 79 * hash + Objects.hashCode(this.copyShopOrderisDeliveredToFachschaftDate);
        hash = 79 * hash + Objects.hashCode(this.studentCustomerPickupDate);
        hash = 79 * hash + Objects.hashCode(this.notes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentOrder other = (StudentOrder) obj;
        if (!Objects.equals(this.studentOrderid, other.studentOrderid)) {
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
        if (!Objects.equals(this.copyShopOrderisDeliveredToFachschaftDate, other.copyShopOrderisDeliveredToFachschaftDate)) {
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
