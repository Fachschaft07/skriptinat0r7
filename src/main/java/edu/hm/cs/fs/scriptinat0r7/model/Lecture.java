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

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import edu.hm.cs.fs.scriptinat0r7.model.enums.StudyProgram;

/**
 * Represents a lecture of a professor in a specific semester.
 */
@Entity
@Table(name = "skriptorLecture")
public class Lecture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String name;

    @OneToOne
    private Professor readingProfessor;

    @Enumerated(EnumType.STRING)
    private StudyProgram studyProgram;

    @ManyToMany(mappedBy = "lectures")
    private Set<Script> usedScripts = new HashSet<Script>();

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

    public Professor getReadingProfessor() {
        return readingProfessor;
    }

    public void setReadingProfessor(final Professor readingProfessor) {
        this.readingProfessor = readingProfessor;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(final StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Set<Script> getUsedScripts() {
        return usedScripts;
    }

    public void setUsedScripts(final Set<Script> usedScripts) {
        this.usedScripts = usedScripts;
    }

    /**
     * Adds a {@code Script} to this {@code Lecture}.
     *
     * @param script
     *            the script to add.
     */
    public void addScript(final Script script) {
        if (usedScripts == null) {
            usedScripts = new HashSet<>();
        }
        usedScripts.add(script);
    }

    /**
     * Removes a {@code Script} from this {@code Lecture}.
     *
     * @param script
     *            the script to remove.
     */
    public void removeScript(final Script script) {
        usedScripts.remove(script);
    }

    public String getLectureAndProfessor() {
        return getName() + " / " + getReadingProfessor().getFullName();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(name, readingProfessor, studyProgram);
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Lecture)) {
            return false;
        }

        final Lecture other = (Lecture) obj;
        if (!Objects.equals(name, other.name)) {
            return false;
        }
        if (!Objects.equals(readingProfessor, other.readingProfessor)) {
            return false;
        }
        if (!Objects.equals(studyProgram, other.studyProgram)) {
            return false;
        }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity

    @Override
    public String toString() {
        return "Lecture [id=" + id + ", name=" + name + ", readingProfessor="
                + readingProfessor + "]";
    }
}
