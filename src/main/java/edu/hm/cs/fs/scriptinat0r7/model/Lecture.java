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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.hm.cs.fs.scriptinat0r7.model.enums.SemesterType;
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

    private String name;
    private Professor readingProfessor;

    @Enumerated(EnumType.STRING)
    private StudyProgram studyProgram;

    @Enumerated(EnumType.STRING)
    private SemesterType semesterType;

    private Integer semesterYear;

    @OneToMany
    private Set<Script> usedScripts;

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

    public SemesterType getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(final SemesterType semesterType) {
        this.semesterType = semesterType;
    }

    public Integer getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(final Integer semesterYear) {
        this.semesterYear = semesterYear;
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

    @Override
    public final int hashCode() {
        return Objects.hash(id, name, readingProfessor, studyProgram, semesterType, semesterYear, usedScripts);
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (!(obj instanceof Lecture)) { return false; }

        Lecture other = (Lecture) obj;
        if (!Objects.equals(this.id, other.id)) { return false; }
        if (!Objects.equals(this.name, other.name)) { return false; }
        if (!Objects.equals(this.readingProfessor, other.readingProfessor)) { return false; }
        if (!Objects.equals(this.studyProgram, other.studyProgram)) { return false; }
        if (!Objects.equals(this.semesterType, other.semesterType)) { return false; }
        if (!Objects.equals(this.semesterYear, other.semesterYear)) { return false; }
        if (!Objects.equals(this.usedScripts, other.usedScripts)) { return false; }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity
}
