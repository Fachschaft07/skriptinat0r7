/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
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

/**
 *
 * @author usn1982e
 */
@Entity
@Table(name="skriptorLecture")
public class Lecture implements Serializable {
    public enum StudyProgram {

	MASTER,
	INFORMATIK,
	WIRTSCHAFTSINFORMATIK,
	GEOTELEMATIK,
	SCIENTIFIC_COMPUTING
    }

    public enum SemesterType{WS,SS; }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Professor readingProfessor;
    @Enumerated(EnumType.STRING)
    private StudyProgram studyprogram;
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

    public StudyProgram getStudyprogram() {
        return studyprogram;
    }

    public void setStudyprogram(final StudyProgram studyprogram) {
        this.studyprogram = studyprogram;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = (53 * hash) + Objects.hashCode(id);
        hash = (53 * hash) + Objects.hashCode(name);
        hash = (53 * hash) + Objects.hashCode(readingProfessor);
        hash = (53 * hash) + Objects.hashCode(studyprogram);
        hash = (53 * hash) + Objects.hashCode(semesterType);
        hash = (53 * hash) + Objects.hashCode(semesterYear);
        hash = (53 * hash) + Objects.hashCode(usedScripts);
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
        final Lecture other = (Lecture) obj;
        if (!Objects.equals(id, other.id)) {
            return false;
        }
        if (!Objects.equals(name, other.name)) {
            return false;
        }
        if (!Objects.equals(readingProfessor, other.readingProfessor)) {
            return false;
        }
        if (studyprogram != other.studyprogram) {
            return false;
        }
        if (semesterType != other.semesterType) {
            return false;
        }
        if (!Objects.equals(semesterYear, other.semesterYear)) {
            return false;
        }
        if (!Objects.equals(usedScripts, other.usedScripts)) {
            return false;
        }
        return true;
    }







}
