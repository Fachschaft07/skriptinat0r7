package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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
        Script script = new Script();
        int expected = 1;
        
        script.setId(expected);
        int actual = script.getId();
        
        assertSame("The id is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the name.
     */
    @Test
    public void testGetAndSetName() {
        Script script = new Script();
        String expected = "Grundkurs Datenkommunikation";
        
        script.setName(expected);
        String actual = script.getName();
        
        assertSame("The name of the script is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the category.
     */
    @Test
    public void testGetAndSetCategory() {
        Script script = new Script();
        ScriptCategory expected = ScriptCategory.LECTURE_SCRIPT;
        
        script.setCategory(expected);
        ScriptCategory actual = script.getCategory();
        
        assertSame("The category of the script is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter of the authors.
     */
    @Test
    public void testGetAndSetAuthors() {
        Script script = new Script();
        Set<User> expected = Collections.emptySet();
        
        script.setAuthors(expected);
        Set<User> actual = script.getAuthors();
        
        assertSame("The authors of the script are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the authors.
     */
    @Test
    public void testAddAuthor() {
        Script script = new Script();
        User author = new User();
        
        script.addAuthor(author);
        
        assertEquals("The set of authors has not 1 element.", 1, script.getAuthors().size());
    }
    
    /**
     * Tests the add method of the authors when there is already an existing set.
     */
    @Test
    public void testAddAuthorExistingSet() {
        Script script = new Script();
        User author = new User();
        Set<User> authors = new HashSet<User>();
        script.setAuthors(authors);
        
        script.addAuthor(author);
        
        assertEquals("The set of authors has not 1 element.", 1, script.getAuthors().size());
    }

    /**
     * Tests the remove method of the authors.
     */
    @Test
    public void testRemoveAuthor() {
        Script script = new Script();
        User author = new User();
        script.addAuthor(author);
        assertEquals("The set of authors has not 1 element.", 1, script.getAuthors().size());

        script.removeAuthor(author);
        assertEquals("The set of student orders has not 0 elements.", 0, script.getAuthors().size());
    }
    
    /**
     * Tests the getter and setter of the semester type.
     */
    @Test
    public void testGetAndSetSemesterType() {
    	Script script = new Script();
    	SemesterType expected = SemesterType.SS;
    	
    	script.setSemesterType(expected);
    	SemesterType actual = script.getSemesterType();
    	
    	assertSame("The semester type is not the same.", expected, actual);
    }
    
    /**
     * Tests the getter and setter of the semester year.
     */
    @Test
    public void testGetAndSetSemesterYear() {
    	Script script = new Script();
    	int expected = 2014;
    	
    	script.setSemesterYear(expected);
    	int actual = script.getSemesterYear();
    	
    	assertEquals("The semester year is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter of the lectures.
     */
    @Test
    public void testGetAndSetLectures() {
        Script script = new Script();
        Set<Lecture> expected = Collections.emptySet();
        
        script.setLectures(expected);
        Set<Lecture> actual = script.getLectures();
        
        assertSame("The lectures of the script are not the same.", expected, actual);
    }
    

    /**
     * Tests the add method of the lectures.
     */
    @Test
    public void testAddLecture() {
        Script script = new Script();
        Lecture lecture = new Lecture();
        
        script.addLecture(lecture);
        
        assertEquals("The set of lectures has not 1 element.", 1, script.getLectures().size());
    }
    
    /**
     * Tests the add method of the lectures when there is already an existing set.
     */
    @Test
    public void testAddLectureExistingSet() {
        Script script = new Script();
        Lecture lecture = new Lecture();
        Set<Lecture> lectures = new HashSet<Lecture>();
        script.setLectures(lectures);
        
        script.addLecture(lecture);
        
        assertEquals("The set of lectures has not 1 element.", 1, script.getLectures().size());
    }

    /**
     * Tests the remove method of the lectures.
     */
    @Test
    public void testRemoveLecture() {
        Script script = new Script();
        Lecture lecture = new Lecture();
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
        Script script = new Script();
        Set<ScriptDocument> expected = Collections.emptySet();
        
        script.setScriptDocuments(expected);
        Set<ScriptDocument> actual = script.getScriptDocuments();
        
        assertSame("The script documents of the script are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the script documents.
     */
    @Test
    public void testAddScriptDocument() {
        Script script = new Script();
        ScriptDocument document = new ScriptDocument();
        
        script.addScriptDocument(document);
        
        assertEquals("The set of script documents has not 1 element.", 1, script.getScriptDocuments().size());
    }
    
    /**
     * Tests the add method of the script documents when theres already an existing set.
     */
    @Test
    public void testAddScriptDocumentExistingSet() {
        Script script = new Script();
        ScriptDocument document = new ScriptDocument();
        Set<ScriptDocument> scriptDocuments = new HashSet<ScriptDocument>();
        script.setScriptDocuments(scriptDocuments);
        
        script.addScriptDocument(document);
        
        assertEquals("The set of script documents has not 1 element.", 1, script.getScriptDocuments().size());
    }

    /**
     * Tests the remove method of the script documents.
     */
    @Test
    public void testRemoveScriptDocument() {
        Script script = new Script();
        ScriptDocument document = new ScriptDocument();
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
        Script script = new Script();
        script.setId(2);
        script.setName("Grundkurs Datenkommunikation");
        script.setCategory(ScriptCategory.LECTURE_SCRIPT);
        String expected = "Script [id=2, name=Grundkurs Datenkommunikation, category=LECTURE_SCRIPT]";
        
        String actual = script.toString();
        
        assertEquals("The toString does not return expected String.", expected, actual);
    }

}
