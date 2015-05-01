/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.zip.CRC32;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;

/**
 * Represents the real script file.
 */
@Entity
@Table(name = "skriptorScriptDocument")
public class ScriptDocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long hashvalue;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] file;

    /**
     * FIXME: sortnumber should be in a relationship with exactly one script, but we got a N-N relationship here.
     * @deprecated see fixme
     */
    @Deprecated
    private int sortnumber;

    private ReviewState reviewState;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String filename;

    @Lob
    @Column(nullable = true)
    private String note;

    @ManyToMany
    @JoinTable
    private Collection<Script> scripts;

    @Column(nullable = false)
    private Integer fileSize;

    private boolean isPasswordMissing;

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(final Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSizeFormatted() {
        return FileUtils.byteCountToDisplaySize(fileSize);
    }

    public byte[] getFile() {
        return Arrays.copyOf(file, file.length);
    }

    /**
     * Sets the file.
     * @param file the file.
     */
    public void setFile(final byte[] file) {
        this.file = Arrays.copyOf(file, file.length);
        fileSize = file.length;
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

    public boolean isPublic() {
        return (reviewState == ReviewState.FACHSCHAFTLERAPPROVED) || (reviewState == ReviewState.PROFESSORAPPROVED);
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

    public Collection<Script> getScripts() {
        return scripts;
    }

    public void setScripts(final Collection<Script> scripts) {
        this.scripts = new HashSet<>(scripts);
    }

    /**
     * Adds a script to this {@code ScriptDocument}.
     *
     * @param script
     *            the script to add.
     */
    public void addScript(final Script script) {
        if (scripts == null) {
            scripts = new HashSet<Script>();
        }
        scripts.add(script);
    }

    public Long getHashvalue() {
        return hashvalue;
    }

    public void setHashvalue(final Long hashvalue) {
        this.hashvalue = hashvalue;
    }

    public boolean hasPassword() {
        return isPasswordMissing || !password.isEmpty();
    }

    public boolean isPasswordMissing() {
        return isPasswordMissing;
    }

    public void setPasswordMissing(final boolean isRightPassword) {
        isPasswordMissing = isRightPassword;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(hashvalue, Arrays.hashCode(file), sortnumber,
                reviewState, password, filename, note);
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
        if (!(obj instanceof ScriptDocument)) {
            return false;
        }

        final ScriptDocument other = (ScriptDocument) obj;
        if (!Objects.equals(hashvalue, other.hashvalue)) {
            return false;
        }
        if (!Arrays.equals(file, other.file)) {
            return false;
        }
        if (!Objects.equals(sortnumber, other.sortnumber)) {
            return false;
        }
        if (!Objects.equals(reviewState, other.reviewState)) {
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

        return true;
    }
    // CHECKSTYLE.ON: NPath Complexity

    /**
     * Computes the hash value of a script document.
     * @return the 32 bit hash value as a long.
     */
    public long computeHashvalue() {
        final CRC32 crc = new CRC32();
        crc.update(getFile());
        return crc.getValue();
    }

    public boolean isOnePasswordMatching(final Collection<String> passwords) {
        return StringUtils.isEmpty(getPassword()) || passwords.contains(getPassword());
    }
}
