package edu.hm.cs.fs.scriptinat0r7.pdf;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class PdfConverter {

	PdfHelper helper;
	
	public PdfConverter (){
		
		helper = new PdfHelper();
		
	}
	
	
	public BufferedImage getFirstPageAsImage(byte[] pdfByteStream, String imageFormat) throws IOException{
		
		PDDocument document = helper.loadDocumentFromByteStream(pdfByteStream);
		
		BufferedImage image = null;
		
		if(document.getNumberOfPages() >= 1){
			
			List<PDPage> pages = document.getDocumentCatalog().getAllPages();
			
			Iterator<PDPage> iterator = pages.iterator();
			
			PDPage page = iterator.next();
			
			image = page.convertToImage();
			
		}
		
		return image;
		
	}
}
