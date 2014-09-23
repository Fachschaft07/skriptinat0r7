package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * Helper functions for pdf documents.
 */
public class PdfHelper {

    /**
     * Returns a document from a byte array.
     * @param pdfBytes the document as byte array.
     * @return the document.
     * @throws IOException thrown if io error happen.
     */
    protected PDDocument loadDocumentFromByteStream(final byte[] pdfBytes) throws IOException {
        return PDDocument.load(new ByteArrayInputStream(pdfBytes));
    }

    /**
     * Returns a byte array from a document.
     * @param document the document to serialize.
     * @return the byte array representing the document.
     * @throws IOException thrown if io error happen.
     * @throws COSVisitorException An exception that represents something gone wrong when visiting a PDF object.
     */
    protected byte[] convertPDDocumentToByte(final PDDocument document) throws COSVisitorException, IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        document.close();
        return out.toByteArray();
    }


}
