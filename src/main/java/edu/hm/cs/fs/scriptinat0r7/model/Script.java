/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ScriptCategory;

/**
 * Represents a script.
 */
@Entity
@Table(name = "skriptorScript")
public class Script implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private ScriptCategory category;

    @ManyToMany
    private Set<User> authors;

    @ManyToMany
    @NotEmpty
    @JoinTable
    private Set<Lecture> lectures;

    @OneToMany
    private Set<ScriptDocument> scriptDocuments;

    @OneToOne
    private User submitter;

    private boolean submittedCompletely;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ScriptCategory getCategory() {
        return category;
    }

    public void setCategory(final ScriptCategory category) {
        this.category = category;
    }

    public Set<User> getAuthors() {
        return authors;
    }

    public void setAuthors(final Set<User> authors) {
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
        authors.remove(author);
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(final Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    /**
     * Adds a lecture to this {@code Script}.
     *
     * @param lecture
     *            the lecture to add.
     */
    public void addLecture(final Lecture lecture) {
        if (lectures == null) {
            lectures = new HashSet<>();
        }
        lectures.add(lecture);
    }

    /**
     * Removes a lecture from this {@code Script}.
     *
     * @param lecture
     *            the lecture to remove.
     */
    public void removeLecture(final Lecture lecture) {
        lectures.remove(lecture);
    }

    public Set<ScriptDocument> getScriptDocuments() {
        return scriptDocuments;
    }

    public void setScriptDocuments(final Set<ScriptDocument> scriptDocuments) {
        this.scriptDocuments = scriptDocuments;
    }

    /**
     * Adds a {@code ScriptDocument} to this {@code Script}.
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
     * Removes a {@code ScriptDocument} from this {@code Script}.
     *
     * @param scriptDocument
     *            the scriptDocument to remove.
     */
    public void removeScriptDocument(final ScriptDocument scriptDocument) {
        scriptDocuments.remove(scriptDocument);
    }

    public User getSubmitter() {
        return submitter;
    }

    public void setSubmitter(final User submitter) {
        this.submitter = submitter;
    }

    public boolean isSubmittedCompletely() {
        return submittedCompletely;
    }

    public void setSubmittedCompletely(final boolean isSubmittedCompletely) {
        this.submittedCompletely = isSubmittedCompletely;
    }

    /**
     * returns if all script documents are allowed to be seen.
     * @return true if all script documents are allowed to be seen.
     */
    public boolean areAllScriptsApproved() {
        for (ScriptDocument document : getScriptDocuments()) {
            if (document.getReviewState() == ReviewState.DELETED || document.getReviewState() == ReviewState.LOCKED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, name, category, submitter, submittedCompletely);
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (!(obj instanceof Script)) { return false; }

        Script other = (Script) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        if (!Objects.equals(this.name, other.name)) { return false; }
        if (!Objects.equals(this.category, other.category)) { return false; }
        if (!Objects.equals(this.submitter, other.submitter)) { return false; }
        if (!Objects.equals(this.submittedCompletely, other.submittedCompletely)) { return false; }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity

    @Override
    public String toString() {
        return "Script [id=" + id + ", name=" + name + ", category=" + category + "]";
    }

}