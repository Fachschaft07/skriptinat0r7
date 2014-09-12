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
import javax.persistence.Table;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;

/**
 * Represents the real script file.
 */
@Entity
@Table(name = "skriptorScriptDocument")
public class ScriptDocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    private Integer hashvalue;

    @Lob
    @Column(nullable = false)
    private byte[] file;
  
    private int sortnumber;
    private ReviewState reviewState;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String filename;

    @Lob
    @Column(nullable = true)
    private String note;

    @ManyToOne
    private Script script;


	/**
     * Returns the size of the file.
     *
     * @return the size of the file.
     */
    public int getFileSize() {
        return file.length;
    }
        
    
    public byte[] getFile() {
        return Arrays.copyOf(this.file, this.file.length);
    }

    public void setFile(final byte[] file) {
        this.file = Arrays.copyOf(file, file.length);
    }

    public int getSortnumber() {
        return sortnumber;
    }

    public void setSortnumber(final int sortnumber) {
        this.sortnumber = sortnumber;
    }

    public ReviewState getReviewState() {
        return reviewState;
    }

    public void setReviewState(final ReviewState reviewState) {
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

    public Integer getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(final Integer hashvalue) {
        this.hashvalue = hashvalue;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(hashvalue, Arrays.hashCode(file), sortnumber, 
                reviewState, password, filename, note, script);
    }

    // CHECKSTYLE.OFF: NPath Complexity of generated equals
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (!(obj instanceof ScriptDocument)) { return false; }

        ScriptDocument other = (ScriptDocument) obj;
        if (!Objects.equals(this.hashvalue, other.hashvalue)) { return false; }
        if (!Arrays.equals(this.file, other.file)) { return false; }
        if (!Objects.equals(this.sortnumber, other.sortnumber)) { return false; }
        if (!Objects.equals(this.reviewState, other.reviewState)) { return false; }
        if (!Objects.equals(this.password, other.password)) { return false; }
        if (!Objects.equals(this.filename, other.filename)) { return false; }
        if (!Objects.equals(this.note, other.note)) { return false; }
        if (!Objects.equals(this.script, other.script)) { return false; }

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity
}
