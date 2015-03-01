package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Represents a professor.
 */
@Entity
public class Professor extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = true)
    private String title;

    @OneToMany(mappedBy = "readingProfessor")
    private Set<Lecture> lectures;

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public final String getFullName() {
        return (title == null) || (title.length() == 0) ? super.getFullName() : title + " " + super.getFullName();
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(final Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(title, super.hashCode());
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (!(obj instanceof Professor)) { return false; }

        final Professor other = (Professor) obj;
        if (!Objects.equals(title, other.title)) { return false; }
        if (!super.equals(other)) { return false; }

        return true;
    }

    @Override
    public boolean canEqual(final Object obj) {
        return obj instanceof Professor;
    }

    @Override
    public String toString() {
        return "Professor [title=" + title + ", firstName=" + getFirstName() + ", lastName=" + getLastName()
                + "]";
    }
}
