package edu.hm.cs.fs.scriptinat0r7.model;

import static org.junit.Assert.*;
import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.Test;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;

public class ScriptDocumentTest {
    
    /**
     * Tests the equals contract for equals() and hashCode().
     */
    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(ScriptDocument.class).verify();
    }

    /**
     * 
     */
    @Test
    public void testGetFileSize() {
        ScriptDocument document = new ScriptDocument();
        document.setFile(new byte[] {0x00, 0x01, 0x02, -0x80});
        int expected = 4;
        
        int actual = document.getFileSize();
        
        assertEquals("The size of the file is not the expected.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetFile() {
        ScriptDocument document = new ScriptDocument();
        byte[] expected = new byte[] {0x00, 0x01, 0x02, -0x80};
        
        document.setFile(expected);
        byte[] actual = document.getFile();
        
        assertArrayEquals("The files of the script document are not equal.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetSortnumber() {
        ScriptDocument document = new ScriptDocument();
        int expected = 1;
        
        document.setSortnumber(expected);
        int actual = document.getSortnumber();
        
        assertEquals("The sortnumber of the script document is not equal.", expected, actual);
    }
    
    /**
     * 
     */
    @Test
    public void testGetAndSetReviewState() {
        ScriptDocument document = new ScriptDocument();
        ReviewState expected = ReviewState.PROFESSORAPPROVED;
        
        document.setReviewState(expected);
        ReviewState actual = document.getReviewState();
        
        assertSame("The review state of the script document is not the same.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetPassword() {
        ScriptDocument document = new ScriptDocument();
        String expected = "top_secret";
        
        document.setPassword(expected);
        String actual = document.getPassword();
        
        assertSame("The password of the script document is not the same.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetFilename() {
        ScriptDocument document = new ScriptDocument();
        String expected = "datenkommunikation.pdf";
        
        document.setFilename(expected);
        String actual = document.getFilename();
        
        assertSame("The filename of the script document is not the same.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetNote() {
        ScriptDocument document = new ScriptDocument();
        String expected = "some notes...";
        
        document.setNote(expected);
        String actual = document.getNote();
        
        assertSame("The note of the script document is not the same.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetScript() {
        ScriptDocument document = new ScriptDocument();
        Script expected = new Script();
        
        document.setScript(expected);
        Script actual = document.getScript();
        
        assertSame("The note of the script document is not the same.", expected, actual);
    }

    /**
     * 
     */
    @Test
    public void testGetAndSetHashvalue() {
        ScriptDocument document = new ScriptDocument();
        int expected = 123456789;
        
        document.setHashvalue(expected);
        int actual = document.getHashvalue();
        
        assertEquals("The hash value of the script document is not the same.", expected, actual);
    }

}