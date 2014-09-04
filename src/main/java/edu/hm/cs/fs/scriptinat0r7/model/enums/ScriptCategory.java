package edu.hm.cs.fs.scriptinat0r7.model.enums;

/**
 * Possible categories of a {@code Script}.
 */
public enum ScriptCategory {
    /** Official script to a lecture. */
    LECTURE_SCRIPT("Skript zur Vorlesung"),
    /** Exercises to a lecture. */
    EXERCISE("Übung"),
    /** Student notes to a lecture. */
    STUDENT_NOTE("Studentische Mitschrift"),
    /** Summary of the contents of a lecture. */
    SUMMARY("Zusammenfassung");

    private final String name;
    private ScriptCategory(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
