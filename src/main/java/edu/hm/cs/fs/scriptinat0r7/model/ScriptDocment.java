/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author usn1982e
 */
@Entity
@Table(name="skriptorScriptDocument")
public class ScriptDocment implements Serializable {
    public static enum ReviewState{LOCKED, FACHSCHAFTLERAPPROVED, PROFESSORAPPROVED, DELETED}


    private static final long serialVersionUID = 1L;
    @Id
    @Column(nullable = false)
    private Integer hashvalue;

    @Lob
    @Column(nullable = false)
    private byte[] file;
    private int sortnumber;
    private int reviewState;
    @Column(nullable = true)
    private String password;
    @Column(nullable = false)
    private String filename;
    @Lob
    @Column(nullable = true)
    private String note;

    public Script getIsPartOfScript() {
        return isPartOfScript;
    }

    public void setIsPartOfScript(final Script isPartOfScript) {
        this.isPartOfScript = isPartOfScript;
    }
    @OneToOne
    Script isPartOfScript;


    public Integer getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(final Integer hashvalue) {
        this.hashvalue = hashvalue;
    }


    @ManyToOne
    private Script script;

    int fileSize(){
        return file.length;
    }


    public byte[] getFile() {
        return file;
    }

    public void setFile(final byte[] file) {
        this.file = file;
    }

    public int getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(final int sortnumber) {
        this.sortnumber = sortnumber;
    }

    public int getReviewState() {
        return reviewState;
    }

    public void setReviewState(final int reviewState) {
        this.reviewState = reviewState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(final Script script) {
        this.script = script;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = (97 * hash) + Objects.hashCode(hashvalue);
        hash = (97 * hash) + Arrays.hashCode(file);
        hash = (97 * hash) + sortnumber;
        hash = (97 * hash) + reviewState;
        hash = (97 * hash) + Objects.hashCode(password);
        hash = (97 * hash) + Objects.hashCode(filename);
        hash = (97 * hash) + Objects.hashCode(note);
        hash = (97 * hash) + Objects.hashCode(script);
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
        final ScriptDocment other = (ScriptDocment) obj;
        if (!Objects.equals(hashvalue, other.hashvalue)) {
            return false;
        }
        if (!Arrays.equals(file, other.file)) {
            return false;
        }
        if (sortnumber != other.sortnumber) {
            return false;
        }
        if (reviewState != other.reviewState) {
            return false;
        }
        if (!Objects.equals(password, other.password)) {
            return false;
        }
        if (!Objects.equals(filename, other.filename)) {
            return false;
        }
        if (!Objects.equals(note, other.note)) {
            return false;
        }
        if (!Objects.equals(script, other.script)) {
            return false;
        }
        return true;
    }



}
