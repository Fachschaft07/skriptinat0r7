package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Represents a professor.
 */
@Entity
public class Professor extends User implements Serializable {

    @Column(nullable = true)
    private String title;

    private static final long serialVersionUID = 1L;

    /**
     * Returns the full name (with title if exists) of the professor.
     * 
     * @return the full name.
     */
    @Override
    public final String getFullName() {
        return (title == null || title.length() == 0) ? super.getFullName() : title + " " + super.getFullName();
    }

}
