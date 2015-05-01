package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;

/**
 * Test class for the {@code ScriptDocument}.
 */
public class ScriptDocumentTest {

    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(ScriptDocument.class).verify();
    }

    /**
     * Tests the getter for the file size.
     */
    @Test
    public void testGetFileSize() {
        final ScriptDocument document = new ScriptDocument();
        document.setFile(new byte[] {0x00, 0x01, 0x02, -0x80});
        final int expected = 4;

        final int actual = document.getFileSize();

        assertEquals("The size of the file is not the expected.", expected, actual);
    }

    /**
     * Tests the getter and setter for the file.
     */
    @Test
    public void testGetAndSetFile() {
        final ScriptDocument document = new ScriptDocument();
        final byte[] expected = new byte[] {0x00, 0x01, 0x02, -0x80};

        document.setFile(expected);
        final byte[] actual = document.getFile();

        assertArrayEquals("The files of the script document are not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter for the sortnumber.
     */
    @Test
    public void testGetAndSetSortnumber() {
        final ScriptDocument document = new ScriptDocument();
        final int expected = 1;

        document.setSortnumber(expected);
        final int actual = document.getSortnumber();

        assertEquals("The sortnumber of the script document is not equal.", expected, actual);
    }

    /**
     * Tests the getter and setter for the review state.
     */
    @Test
    public void testGetAndSetReviewState() {
        final ScriptDocument document = new ScriptDocument();
        final ReviewState expected = ReviewState.PROFESSORAPPROVED;

        document.setReviewState(expected);
        final ReviewState actual = document.getReviewState();

        assertSame("The review state of the script document is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter for the password.
     */
    @Test
    public void testGetAndSetPassword() {
        final ScriptDocument document = new ScriptDocument();
        final String expected = "top_secret";

        document.setPassword(expected);
        final String actual = document.getPassword();

        assertSame("The password of the script document is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter for the filename.
     */
    @Test
    public void testGetAndSetFilename() {
        final ScriptDocument document = new ScriptDocument();
        final String expected = "datenkommunikation.pdf";

        document.setFilename(expected);
        final String actual = document.getFilename();

        assertSame("The filename of the script document is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter for the note.
     */
    @Test
    public void testGetAndSetNote() {
        final ScriptDocument document = new ScriptDocument();
        final String expected = "some notes...";

        document.setNote(expected);
        final String actual = document.getNote();

        assertSame("The note of the script document is not the same.", expected, actual);
    }

    /**
     * Tests the getter and setter for the script.
     */
    @Test
    public void testGetAndSetScripts() {
        final ScriptDocument document = new ScriptDocument();
        final Set<Script> expected = Collections.emptySet();

        document.setScripts(expected);
        final Collection<Script> actual = document.getScripts();

        assertEquals("The parent scripts of the script document are not the same.", expected, actual);
    }

    /**
     * Tests the add method of the scripts.
     */
    @Test
    public void testAddScript() {
        final ScriptDocument document = new ScriptDocument();
        final Script script = new Script();

        document.addScript(script);

        assertEquals("The set of scripts has not 1 element.", 1, document.getScripts().size());
    }

    /**
     * Tests the add method of the scripts when there is already an existing set.
     */
    @Test
    public void testAddScriptExistingSet() {
        final ScriptDocument document = new ScriptDocument();
        document.setScripts(new HashSet<Script>());
        final Script script = new Script();

        document.addScript(script);

        assertEquals("The set of scripts has not 1 element.", 1, document.getScripts().size());
    }

    /**
     * Tests the getter and setter for the hash value.
     */
    @Test
    public void testGetAndSetHashvalue() {
        final ScriptDocument document = new ScriptDocument();
        final Long expected = 123456789L;

        document.setHashvalue(expected);
        final Long actual = document.getHashvalue();

        assertEquals("The hash value of the script document is not the same.", expected, actual);
    }

}
