package edu.hm.cs.fs.scriptinat0r7.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: person
 *
 */
@Entity
public class Professor extends User implements Serializable {

    @Column(nullable = true)
    private String title;

    private static final long serialVersionUID = 1L;

    public Professor() {
        super();
    }

    @Override
    public final String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (title.length() > 0) {
            sb.append(title);
            sb.append(" ");
        }
        sb.append(super.getFullName());
        return sb.toString();
    }

}
