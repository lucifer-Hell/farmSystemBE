package com.farmSystemBE.farmSystemBE.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class HtmlToPdfUtil {
    public byte[] generatePdfFromHtml(String html) {
        Document document=generateXHTMLFromHTML(html);
        try (OutputStream outputStream = new FileOutputStream("payment-report.pdf")) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
//            File file=new File("payment-report.pdf");
            return Files.readAllBytes(Path.of("payment-report.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Document generateXHTMLFromHTML(String html) {
        Document document= Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }
}
