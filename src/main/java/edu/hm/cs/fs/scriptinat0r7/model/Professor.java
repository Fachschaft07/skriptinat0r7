package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * Represents a professor.
 */
@Entity
public class Professor extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = true)
    private String title;

    @Override
    public final String getFullName() {
        return ((title == null) || (title.length() == 0)) ? super.getFullName() : title + " " + super.getFullName();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = (prime * result) + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        Professor other = (Professor) obj;
        if (!Objects.equals(this.title, other.title)) { return false; }

        return true;
    }

    @Override
    public String toString() {
        return "Professor [title=" + title + ", firstName=" + getFirstName() + ", lastName=" + getLastName()
                + "]";
    }
}
