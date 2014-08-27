package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.model.enums.SemesterType;
import edu.hm.cs.fs.scriptinat0r7.model.enums.StudyProgram;

/**
 * Test class for the {@code Lecture}.
 */
public class LectureTest {
    
    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Lecture.class).verify();
    }

    @Test
    public void testGetAndSetId() {
        Lecture lecture = new Lecture();
        int expected = 3;

        lecture.setId(expected);
        int actual = lecture.getId();

        assertSame("The id is not the same.", expected, actual);
    }

    @Test
    public void testGetAndSetName() {
        Lecture lecture = new Lecture();
        String expected = "Skript";

        lecture.setName(expected);
        String actual = lecture.getName();

        assertSame("The name is not the same.", expected, actual);
    }

    @Test
    public void testGetAndSetReadingProfessor() {
        Lecture lecture = new Lecture();
        Professor expected = new Professor();

        lecture.setReadingProfessor(expected);
        Professor actual = lecture.getReadingProfessor();

        assertSame("The professor is not the same.", expected, actual);
    }

    @Test
    public void testGetAndSetStudyProgram() {
        Lecture lecture = new Lecture();
        StudyProgram expected = StudyProgram.INFORMATIK;

        lecture.setStudyProgram(expected);
        StudyProgram actual = lecture.getStudyProgram();

        assertSame("The study program is not the same.", expected, actual);
    }

    @Test
    public void testGetAndSetSemesterType() {
        Lecture lecture = new Lecture();
        SemesterType expected = SemesterType.SS;

        lecture.setSemesterType(expected);
        SemesterType actual = lecture.getSemesterType();

        assertSame("The semester type is not the same.", expected, actual);
    }

    @Test
    public void testGetAndSetSemesterYear() {
        Lecture lecture = new Lecture();
        int expected = 2014;

        lecture.setSemesterYear(expected);
        int actual = lecture.getSemesterYear();

        assertEquals("The semester year is not equal.", expected, actual);
    }

    @Test
    public void testGetAndSetUsedScripts() {
        Lecture lecture = new Lecture();
        Set<Script> expected = Collections.emptySet();

        lecture.setUsedScripts(expected);
        Set<Script> actual = lecture.getUsedScripts();

        assertSame("The used scripts are not the same.", expected, actual);
    }

    @Test
    public void testAddScript() {
        Lecture lecture = new Lecture();
        Script script = new Script();

        lecture.addScript(script);

        assertEquals("The set of used scripts has not 1 element.", 1, lecture.getUsedScripts().size());
    }
    
    @Test
    public void testAddScriptExistingSet() {
        Lecture lecture = new Lecture();
        Script script = new Script();
        lecture.setUsedScripts(new HashSet<Script>());

        lecture.addScript(script);

        assertEquals("The set of used scripts has not 1 element.", 1, lecture.getUsedScripts().size());
    }
    
    @Test
    public void testRemoveScript() throws Exception {
        Lecture lecture = new Lecture();
        Script script = new Script();
        lecture.addScript(script);
        assertEquals("The set of scripts has not 1 element.", 1, lecture.getUsedScripts().size());

        lecture.removeScript(script);
        assertEquals("The set of scripts has not 0 elements.", 0, lecture.getUsedScripts().size());
    }
    
}