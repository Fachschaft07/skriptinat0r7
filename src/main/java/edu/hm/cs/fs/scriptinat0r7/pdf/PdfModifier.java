package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class PdfModifier {

	PdfHelper helper;
	
	
	public PdfModifier(){
		
		helper = new PdfHelper();
	}
	
	/**
	 * 
	 * Diese Methode fügt alle in der Liste enthaltenen Pdf Files zusammen.
	 * Dabei steht das Pdf an Index 0 an erster Stelle.
	 * 
	 * @param pdfsToBeMerged
	 * @return
	 * @throws IOException 
	 */	
	public byte[] mergePdfs(List<byte[]> pdfsToBeMerged) throws IOException{
		
		PDDocument mergedPdf = new PDDocument();
		PDFMergerUtility mergerUtility = new PDFMergerUtility();
				
		for(byte[] nextBPdf : pdfsToBeMerged){
			
			PDDocument nextPdf = helper.loadDocumentFromByteStream(nextBPdf);
			
			mergerUtility.appendDocument(mergedPdf, nextPdf);
			
		}
		
		return helper.convertPDDocumentToByte(mergedPdf);
			
	}
	
}
