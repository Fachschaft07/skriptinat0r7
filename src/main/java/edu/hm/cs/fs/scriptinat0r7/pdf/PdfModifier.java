package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

/**
 * PDF modifier functions.
 */
public final class PdfModifier {

    private PdfModifier() { }

    /**
     * This method joins pdf documents. The document at index 0 is the first document.
     *
     * @param pdfsToBeMerged the ordered pdfs that shall be merged.
     * @return a new pdf document in byte representation.
     * @throws IOException thrown if io error happen.
     * @throws COSVisitorException thrown if a pdf is corrupt.
     */
    public static byte[] mergePdfs(final List<byte[]> pdfsToBeMerged) throws IOException, COSVisitorException {
        final PDDocument mergedPdf = new PDDocument();
        final PDFMergerUtility mergerUtility = new PDFMergerUtility();
        for (byte[] nextBPdf : pdfsToBeMerged) {
            final PDDocument nextPdf = PdfHelper.loadDocumentFromByteStream(nextBPdf);
            mergerUtility.appendDocument(mergedPdf, nextPdf);
        }
        return PdfHelper.convertPDDocumentToByte(mergedPdf);
    }

}
