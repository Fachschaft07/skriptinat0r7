package edu.hm.cs.fs.scriptinat0r7.model.enums;

/**
 * The different study programs in department 07.
 */
public enum StudyProgram {
    /** All master programs. */
    MASTER("Master"),
    /** Computer Science program. */
    INFORMATIK("Informatik"),
    /** Information Systems and Management program. */
    WIRTSCHAFTSINFORMATIK("Wirtschaftsinformatik"),
    /** Geotelematics and Navigation program. */
    GEOTELEMATIK("Geotelematik"),
    /** Scientific Computing program. */
    SCIENTIFIC_COMPUTING("Scientific Computing");

    private final String name;
    private StudyProgram(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}