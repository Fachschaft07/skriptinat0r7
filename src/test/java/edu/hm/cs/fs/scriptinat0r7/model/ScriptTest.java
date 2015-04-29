package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ScriptCategory;
import edu.hm.cs.fs.scriptinat0r7.model.enums.SemesterType;

/**
 * Test class for the {@code Script}.
 */
public class ScriptTest {

    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Script.class).verify();
    }

    /**
     * Tests the getter and setter of the id.
     */
    @Test
    public void testGetAndSetId() {
        final Script script = new Script();
        final int expected = 1;

        script.setId(expected);
        final int actual = script.getId();

        assertSame("The id is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the name.
     */
    @Test
    public void testGetAndSetName() {
        final Script script = new Script();
        final String expected = "Grundkurs Datenkommunikation";

        script.setName(expected);
        final String actual = script.getName();

        assertSame("The name of the script is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the category.
     */
    @Test
    public void testGetAndSetCategory() {
        final Script script = new Script();
        final ScriptCategory expected = ScriptCategory.LECTURE_SCRIPT;

        script.setCategory(expected);
        final ScriptCategory actual = script.getCategory();

        assertSame("The category of the script is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the semester type.
     */
    @Test
    public void testGetAndSetSemesterType() {
        final Script script = new Script();
        final SemesterType expected = SemesterType.SS;

        script.setSemesterType(expected);
        final SemesterType actual = script.getSemesterType();

        assertSame("The semester type is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the semester year.
     */
    @Test
    public void testGetAndSetSemesterYear() {
        final Script script = new Script();
        final int expected = 2014;

        script.setSemesterYear(expected);
        final int actual = script.getSemesterYear();

        assertEquals("The semester year is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter of the lectures.
     */
    @Test
    public void testGetAndSetLectures() {
        final Script script = new Script();
        final Set<Lecture> expected = Collections.emptySet();

        script.setLectures(expected);
        final Collection<Lecture> actual = script.getLectures();

        assertEquals("The lectures of the script are not the same.", expected, actual);
    }


    /**
     * Tests the add method of the lectures.
     */
    @Test
    public void testAddLecture() {
        final Script script = new Script();
        final Lecture lecture = new Lecture();

        script.addLecture(lecture);

        assertEquals("The set of lectures has not 1 element.", 1, script.getLectures().size());
    }

    /**
     * Tests the add method of the lectures when there is already an existing set.
     */
    @Test
    public void testAddLectureExistingSet() {
        final Script script = new Script();
        final Lecture lecture = new Lecture();
        final Set<Lecture> lectures = new HashSet<Lecture>();
        script.setLectures(lectures);

        script.addLecture(lecture);

        assertEquals("The set of lectures has not 1 element.", 1, script.getLectures().size());
    }

    /**
     * Tests the remove method of the lectures.
     */
    @Test
    public void testRemoveLecture() {
        final Script script = new Script();
        final Lecture lecture = new Lecture();
        script.addLecture(lecture);
        assertEquals("The set of lectures has not 1 element.", 1, script.getLectures().size());

        script.removeLecture(lecture);
        assertEquals("The set of lectures has not 0 elements.", 0, script.getLectures().size());
    }

    /**
     * Tests the getter and setter of the script documents.
     */
    @Test
    public void testGetAndSetScriptDocuments() {
        final Script script = new Script();
        final Set<ScriptDocument> expected = Collections.emptySet();

        script.setScriptDocuments(expected);
        final Set<ScriptDocument> actual = script.getScriptDocuments();

        assertSame("The script documents of the script are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the script documents.
     */
    @Test
    public void testAddScriptDocument() {
        final Script script = new Script();
        final ScriptDocument document = new ScriptDocument();

        script.addScriptDocument(document);

        assertEquals("The set of script documents has not 1 element.", 1, script.getScriptDocuments().size());
    }

    /**
     * Tests the add method of the script documents when theres already an existing set.
     */
    @Test
    public void testAddScriptDocumentExistingSet() {
        final Script script = new Script();
        final ScriptDocument document = new ScriptDocument();
        final Set<ScriptDocument> scriptDocuments = new HashSet<ScriptDocument>();
        script.setScriptDocuments(scriptDocuments);

        script.addScriptDocument(document);

        assertEquals("The set of script documents has not 1 element.", 1, script.getScriptDocuments().size());
    }

    /**
     * Tests the remove method of the script documents.
     */
    @Test
    public void testRemoveScriptDocument() {
        final Script script = new Script();
        final ScriptDocument document = new ScriptDocument();
        script.addScriptDocument(document);
        assertEquals("The set of script documents has not 1 element.", 1, script.getScriptDocuments().size());

        script.removeScriptDocument(document);
        assertEquals("The set of script documents has not 0 elements.", 0, script.getScriptDocuments().size());
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        final Script script = new Script();
        script.setId(2);
        script.setName("Grundkurs Datenkommunikation");
        script.setCategory(ScriptCategory.LECTURE_SCRIPT);
        final String expected = "Script [id=2, name=Grundkurs Datenkommunikation, category=LECTURE_SCRIPT]";

        final String actual = script.toString();

        assertEquals("The toString does not return expected String.", expected, actual);
    }

}
