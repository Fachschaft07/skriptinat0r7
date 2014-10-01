package edu.hm.cs.fs.scriptinat0r7.pdf;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Test class for {@code PdfHelper}.
 */
public class PdfHelperTest {

    /**
     * Test to decrypt a pdf with a known password.
     * @throws IOException should not happen
     */
    @Test
    public void tryToDecryptDocumentWithValidPassword() throws IOException {
        try (
            final InputStream resourceAsStream = getClass().getResourceAsStream("/samplePdfs/sample_encrypted_with_password_test.pdf");
        ) {
            final byte[] document = IOUtils.toByteArray(resourceAsStream);
            assertEquals("It should find the right password.", "test", PdfHelper.findCorrectPassword(document, Collections.singleton("test")));
        }
    }

    /**
     * Try to decrypt a pdf which has no password set. even if the empty password is not provided explicitly it should return it.
     * @throws IOException should not happen
     */
    @Test
    public void tryToDecryptNotEncryptedPassword() throws IOException {
        try (
            final InputStream resourceAsStream = getClass().getResourceAsStream("/samplePdfs/sample_not_encrypted.pdf");
        ) {
            final byte[] document = IOUtils.toByteArray(resourceAsStream);
            assertEquals("It should find the empty password.", "", PdfHelper.findCorrectPassword(document, Collections.singleton("test")));
        }
    }

    /**
     * If you try to find a password and do not provide the right one it should throw an IllegalArgumentException.
     * @throws IOException should not happen
     */
    @Test(expected = IllegalArgumentException.class)
    public void tryToDecryptDocumentWithoutRightPassword() throws IOException {
        try (
            final InputStream resourceAsStream = getClass().getResourceAsStream("/samplePdfs/sample_encrypted_with_password_test.pdf");
        ) {
            final byte[] document = IOUtils.toByteArray(resourceAsStream);
            PdfHelper.findCorrectPassword(document, Collections.singleton(""));
        }
    }

    /**
     * Tests if pdfs are recognized as pdfs and other files not.
     * @throws IOException should not happen
     */
    @Test
    public void testIsValidPdfOnValidPdf() throws IOException {
        try (
            final InputStream resourceAsStream = getClass().getResourceAsStream("/samplePdfs/sample_encrypted_with_password_test.pdf");
        ) {
            final byte[] document = IOUtils.toByteArray(resourceAsStream);
            assertTrue("a valid pdf document should be detected", PdfHelper.isValidPdf(document));
        }
    }

    /**
     * Tests if pdfs are recognized as pdfs and other files not.
     * @throws IOException should not happen
     */
    @Test
    public void testIsValidPdfOnInvalidPdf() throws IOException {
        try (
            final InputStream resourceAsStream = getClass().getResourceAsStream("/samplePdfs/not_a_pdf.txt");
        ) {
            final byte[] document = IOUtils.toByteArray(resourceAsStream);
            assertFalse("an invalid pdf document should be detected", PdfHelper.isValidPdf(document));
        }
    }

}
