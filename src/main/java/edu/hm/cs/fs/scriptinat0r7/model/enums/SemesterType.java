package edu.hm.cs.fs.scriptinat0r7.model.enums;

/**
 * The semester types.
 */
public enum SemesterType {
    /** Semester in winter. */
    WS ("WS / Wintersemester"),
    /** Semester in summer. */
    SS ("SS / Sommersemester");

    private final String name;
    private SemesterType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}