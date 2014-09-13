package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

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

    /**
     * Tests the getter and setter of the id.
     */
    @Test
    public void testGetAndSetId() {
        Lecture lecture = new Lecture();
        int expected = 3;

        lecture.setId(expected);
        int actual = lecture.getId();

        assertSame("The id is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the name.
     */
    @Test
    public void testGetAndSetName() {
        Lecture lecture = new Lecture();
        String expected = "Skript";

        lecture.setName(expected);
        String actual = lecture.getName();

        assertSame("The name is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the reading professor.
     */
    @Test
    public void testGetAndSetReadingProfessor() {
        Lecture lecture = new Lecture();
        Professor expected = new Professor();

        lecture.setReadingProfessor(expected);
        Professor actual = lecture.getReadingProfessor();

        assertSame("The professor is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the study program.
     */
    @Test
    public void testGetAndSetStudyProgram() {
        Lecture lecture = new Lecture();
        StudyProgram expected = StudyProgram.INFORMATIK;

        lecture.setStudyProgram(expected);
        StudyProgram actual = lecture.getStudyProgram();

        assertSame("The study program is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the used scripts.
     */
    @Test
    public void testGetAndSetUsedScripts() {
        Lecture lecture = new Lecture();
        Set<Script> expected = Collections.emptySet();

        lecture.setUsedScripts(expected);
        Set<Script> actual = lecture.getUsedScripts();

        assertSame("The used scripts are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the scripts.
     */
    @Test
    public void testAddScript() {
        Lecture lecture = new Lecture();
        Script script = new Script();

        lecture.addScript(script);

        assertEquals("The set of used scripts has not 1 element.", 1, lecture.getUsedScripts().size());
    }
    
    /**
     * Tests the add method of the scripts when there is already an existing set.
     */
    @Test
    public void testAddScriptExistingSet() {
        Lecture lecture = new Lecture();
        Script script = new Script();
        lecture.setUsedScripts(new HashSet<Script>());

        lecture.addScript(script);

        assertEquals("The set of used scripts has not 1 element.", 1, lecture.getUsedScripts().size());
    }
    
    /**
     * Tests the remove method of the scripts.
     */
    @Test
    public void testRemoveScript() {
        Lecture lecture = new Lecture();
        Script script = new Script();
        lecture.addScript(script);
        assertEquals("The set of scripts has not 1 element.", 1, lecture.getUsedScripts().size());

        lecture.removeScript(script);
        assertEquals("The set of scripts has not 0 elements.", 0, lecture.getUsedScripts().size());
    }
    
    /**
     * Tests if the name of the lecture and professor is properly returned. 
     */
    @Test
	public void testGetLectureAndProfessor() {
		Lecture lecture = new Lecture();
		lecture.setName("Softwareentwicklung II");
		Professor prof = new Professor();
		prof.setFirstName("Veronika");
		prof.setLastName("Thurner");
		prof.setTitle("Prof. Dr.");
		lecture.setReadingProfessor(prof);
		String expected = "Softwareentwicklung II / Prof. Dr. Thurner, Veronika";
		
		String actual = lecture.getLectureAndProfessor();
		
		assertEquals("The lecture and professor string ist not equal to expected.", expected, actual);
	}
    
}
