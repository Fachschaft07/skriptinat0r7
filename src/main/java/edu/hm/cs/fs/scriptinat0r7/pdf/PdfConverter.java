package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 * Cconverter functions for pdf documents.
 */
public final class PdfConverter {

    private PdfConverter() { }

    /**
     * retrieves the first page as an image.
     * @param pdfByteStream the pdf to read from.
     * @return the BufferedImage representing the first page.
     * @throws IOException thrown if io errors happen.
     */
    @SuppressWarnings("unchecked")
    public static BufferedImage getFirstPageAsImage(final byte[] pdfByteStream) throws IOException {
        try (final PDDocument document = PdfHelper.loadDocumentFromByteStream(pdfByteStream)) {
            List<PDPage> pages = document.getDocumentCatalog().getAllPages();
            Iterator<PDPage> iterator = pages.iterator();
            PDPage page = iterator.next();
            return page.convertToImage();
        }
    }
}
