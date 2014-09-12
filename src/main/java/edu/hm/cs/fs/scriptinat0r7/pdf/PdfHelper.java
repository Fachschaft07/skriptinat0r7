package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class PdfHelper {

	protected PDDocument loadDocumentFromByteStream(byte[] pdfBytes) throws IOException{
		
		return PDDocument.load(new ByteInputStream(pdfBytes, 0));
		
	}
	
	protected byte[] convertPDDocumentToByte(PDDocument document){
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            document.save(out);
            document.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return out.toByteArray();
		
	}
	
	
}
