/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Represents a script.
 */
@Entity
@Table(name = "skriptorScript")
@NamedQuery(name = Script.QUERY_FIND_ALL, query = "SELECT e FROM Script e")
public class Script implements Serializable {

    /** Query to find all. */ //TODO: Prüfen, ob das weg kann.
    public static final String QUERY_FIND_ALL = "findAll";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String category;

    @Transient
    private HashSet<User> authors;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public HashSet<User> getAuthors() {
        return authors;
    }

    public void setAuthors(final HashSet<User> authors) {
        this.authors = authors;
    }

    /**
     * Adds an author to this {@code Script}.
     * 
     * @param author
     *            the author to add.
     */
    public void addAuthor(final User author) {
        if (authors == null) {
            authors = new HashSet<>();
        }
        authors.add(author);
    }

    /**
     * Removes an author from this {@code Script}.
     * 
     * @param author
     *            the author to remove.
     */
    public void removeAuthor(final User author) {
        if ((authors.size() == 1) && authors.contains(author)) {
            authors = null;
        }
        else {
            authors.remove(author);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null) ? id.hashCode() : 0;
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
        final Script other = (Script) obj;
        if (!Objects.equals(id, other.id)) {
            return false;
        }
        if (!Objects.equals(category, other.category)) {
            return false;
        }
        if (!Objects.equals(authors, other.authors)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.hm.cs.fs.entityBeans.Script[ id=" + id + " ]";
    }
}