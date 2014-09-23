package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

/**
 * PDF modifier functions.
 */
public class PdfModifier {

    private final PdfHelper helper;

    /**
     * The constructor.
     */
    public PdfModifier() {
        helper = new PdfHelper();
    }

    /**
     * This method joins pdf documents. The document at index 0 is the first document.
     *
     * @param pdfsToBeMerged the ordered pdfs that shall be merged.
     * @return a new pdf document in byte representation.
     * @throws IOException thrown if io error happen.
     * @throws COSVisitorException thrown if a pdf is corrupt.
     */
    public byte[] mergePdfs(List<byte[]> pdfsToBeMerged) throws IOException, COSVisitorException {
        PDDocument mergedPdf = new PDDocument();
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        for (byte[] nextBPdf : pdfsToBeMerged) {
            PDDocument nextPdf = helper.loadDocumentFromByteStream(nextBPdf);
            mergerUtility.appendDocument(mergedPdf, nextPdf);
        }
        return helper.convertPDDocumentToByte(mergedPdf);
    }

}
