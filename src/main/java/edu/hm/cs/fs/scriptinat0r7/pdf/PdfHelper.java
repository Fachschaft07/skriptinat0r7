package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;

/**
 * Helper functions for pdf documents.
 */
public final class PdfHelper {

    private PdfHelper() { }

    /**
     * Returns a document from a byte array.
     * @param pdfBytes the document as byte array.
     * @return the document.
     * @throws IOException thrown if io error happen.
     */
    protected static PDDocument loadDocumentFromByteStream(final byte[] pdfBytes) throws IOException {
        return PDDocument.load(new ByteArrayInputStream(pdfBytes));
    }

    /**
     * Returns a byte array from a document.
     * @param document the document to serialize.
     * @return the byte array representing the document.
     * @throws IOException thrown if io error happen.
     * @throws COSVisitorException An exception that represents something gone wrong when visiting a PDF object.
     */
    protected static byte[] convertPDDocumentToByte(final PDDocument document) throws COSVisitorException, IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        return out.toByteArray();
    }

    /**
     * Tries to decrypt a document with the given passwords.
     * @param possiblePasswords to try.
     * @param document the document which shall be decrypted.
     * @return the password (can be an empty string)
     * @throws IOException can happen while interacting with the document.
     * @throws IllegalArgumentException thrown, if no password is matching.
     */
    public static String findCorrectPassword(final byte[] document, final Collection<String> possiblePasswords) throws IllegalArgumentException, IOException {
        PDFParser pdf = null;
        try {
            final ByteArrayInputStream bytes = new ByteArrayInputStream(document);
            pdf = new PDFParser(bytes);
            pdf.parse();
            final PDDocument pdfDocument = pdf.getPDDocument();

            if (pdfDocument.isEncrypted()) {
                for (String password : possiblePasswords) {
                    try {
                        StandardDecryptionMaterial passwordDecrypter = new StandardDecryptionMaterial(password);
                        pdfDocument.openProtection(passwordDecrypter);
                        return password;
                    } catch (BadSecurityHandlerException | IOException | CryptographyException e) {
                        // was wrong password, try next...
                    }
                }
            } else {
                return StringUtils.EMPTY;
            }
        } finally {
            if (pdf != null) {
                IOUtils.closeQuietly(pdf.getDocument());
                pdf.clearResources();
            }
        }

        throw new IllegalArgumentException("no password is able to decrypt the document");
    }

}
