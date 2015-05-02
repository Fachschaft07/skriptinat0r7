/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Represents a student order. A {@code StudentOrder} is part of a {@code CopyShopOrder}.
 */
@Entity
@Table(name = "skriptorStudentOrder")
@NamedEntityGraph(name = "StudentOrder.scriptDocuments", attributeNodes = @NamedAttributeNode("scriptDocuments"))
public class StudentOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StudentOrderId", nullable = false)
    @Id
    private Integer id;

    @ManyToOne
    private CopyShopOrder copyShopOrder;

    @ManyToMany
    @JoinTable
    private Set<ScriptDocument> scriptDocuments;

    // TODO: datetime?
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date studentPickup;

    @Lob
    private String notes;

    @ManyToOne
    private User orderer;

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

    public void setScriptDocuments(final Collection<ScriptDocument> documentsToOrder) {
        setScriptDocuments(new HashSet<>(documentsToOrder));
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

    public User getOrderer() {
        return orderer;
    }

    public void setOrderer(final User orderer) {
        this.orderer = orderer;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, copyShopOrder, scriptDocuments, orderDate, studentPickup, notes, orderer);
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    @SuppressWarnings(value = { "PMD.ModifiedCyclomaticComplexity", "PMD.StdCyclomaticComplexity"})
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof StudentOrder)) {
            return false;
        }

        final StudentOrder other = (StudentOrder) obj;
        if (!Objects.equals(id, other.id)) {
            return false;
        }
        if (!Objects.equals(copyShopOrder, other.copyShopOrder)) {
            return false;
        }
        if (!Objects.equals(scriptDocuments, other.scriptDocuments)) {
            return false;
        }
        if (!Objects.equals(orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(studentPickup, other.studentPickup)) {
            return false;
        }
        if (!Objects.equals(notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(orderer, other.orderer)) {
            return false;
        }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity

    @Override
    public String toString() {
        return "StudentOrder [id=" + id + ", orderDate=" + orderDate + ", studentPickup=" + studentPickup + "]";
    }
}
