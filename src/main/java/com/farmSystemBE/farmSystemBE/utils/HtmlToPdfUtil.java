package com.farmSystemBE.farmSystemBE.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class HtmlToPdfUtil {
    public byte[] generatePdfFromHtml(String html) {
        try{
            PdfWriter pdfWriter=new PdfWriter("payment-report.pdf");
            PdfDocument pdfDocument=new PdfDocument(pdfWriter);
            pdfWriter.flush();
            PageSize pg=PageSize.A4.rotate();
            pg.setHeight(5000);
            pg.setWidth(5000);
            pdfDocument.setDefaultPageSize(pg);
            HtmlConverter.convertToPdf(html,pdfWriter);
            pdfWriter.flush();
            pdfWriter.close();
            return Files.readAllBytes(Path.of("payment-report.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
