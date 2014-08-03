package edu.hm.cs.fs.scriptinat0r7.model.enums;

/**
 * The states at reviewing a script.
 */
public enum ReviewState {
    /** The script is locked. */
    LOCKED,
    /** The script is approved by an fs-student. */
    FACHSCHAFTLERAPPROVED,
    /** The script is approved by the owning professor. */
    PROFESSORAPPROVED,
    /** The script is deleted. */
    DELETED
}