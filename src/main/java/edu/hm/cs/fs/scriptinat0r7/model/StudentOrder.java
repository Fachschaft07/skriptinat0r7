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
@Table(name = "skriptorStudentOrder")
public class StudentOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StudentOrderId", nullable = false)
    @Id
    private Integer id;

    @ManyToOne
    private CopyShopOrder copyShopOrder;

    @OneToMany
    private Set<ScriptDocument> scriptDocuments;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date studentPickup;

    @Lob
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public CopyShopOrder getCopyShopOrder() {
        return copyShopOrder;
    }

    public void setCopyShopOrder(final CopyShopOrder copyShopOrder) {
        this.copyShopOrder = copyShopOrder;
    }

    public Set<ScriptDocument> getScriptDocuments() {
        return scriptDocuments;
    }

    public void setScriptDocuments(final Set<ScriptDocument> scriptDocuments) {
        this.scriptDocuments = scriptDocuments;
    }

    /**
     * Adds a {@code ScriptDocument} to this {@code StudentOrder}.
     *
     * @param scriptDocument
     *            the scriptDocument to add.
     */
    public void addScriptDocument(final ScriptDocument scriptDocument) {
        if (scriptDocuments == null) {
            scriptDocuments = new HashSet<>();
        }
        scriptDocuments.add(scriptDocument);
    }

    /**
     * Removes a {@code ScriptDocument} from this {@code StudentOrder}.
     *
     * @param scriptDocument
     *            the scriptDocument to remove.
     */
    public void removeScriptDocument(final ScriptDocument scriptDocument) {
        scriptDocuments.remove(scriptDocument);
    }

    public Date getOrderDate() {
        return (Date) (orderDate == null ? null : orderDate.clone());
    }

    public void setOrderDate(final Date orderDate) {
        this.orderDate = (Date) (orderDate == null ? null : orderDate.clone());
    }

    public Date getStudentPickup() {
        return (Date) (studentPickup == null ? null : studentPickup.clone());
    }

    public void setStudentPickup(final Date studentPickup) {
        this.studentPickup = (Date) (studentPickup == null ? null : studentPickup.clone());
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
        hash = (79 * hash) + Objects.hashCode(this.id);
        hash = (79 * hash) + Objects.hashCode(this.copyShopOrder);
        hash = (79 * hash) + Objects.hashCode(this.scriptDocuments);
        hash = (79 * hash) + Objects.hashCode(this.orderDate);
        hash = (79 * hash) + Objects.hashCode(this.studentPickup);
        hash = (79 * hash) + Objects.hashCode(this.notes);
        return hash;
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        StudentOrder other = (StudentOrder) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        if (!Objects.equals(this.copyShopOrder, other.copyShopOrder)) { return false; }
        if (!Objects.equals(this.scriptDocuments, other.scriptDocuments)) { return false; }
        if (!Objects.equals(this.orderDate, other.orderDate)) { return false; }
        if (!Objects.equals(this.studentPickup, other.studentPickup)) { return false; }
        if (!Objects.equals(this.notes, other.notes)) { return false; }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity

    @Override
    public String toString() {
        return "StudentOrder [id=" + id + ", orderDate=" + orderDate + ", studentPickup=" + studentPickup + "]";
    }
}
