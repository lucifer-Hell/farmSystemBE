package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;
import com.farmSystemBE.farmSystemBE.service.HisaabService;
import com.farmSystemBE.farmSystemBE.templates.html.PaymentReportHtmlGenerator;
import com.farmSystemBE.farmSystemBE.utils.HtmlToPdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    HisaabService hisaabService;
    @Autowired
    PaymentReportHtmlGenerator paymentReportHtmlGenerator;
    @Autowired
    HtmlToPdfUtil htmlToPdfUtil;
    @GetMapping("/generateReport")
    PaymentReportDto generatePaymentReport(@RequestParam("from")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                           @RequestParam("to")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to ){

        return hisaabService.generatePaymentReport(from,to);
    }

    @GetMapping(value = "/generatePdfReport",produces = MediaType.APPLICATION_PDF_VALUE)
    ResponseEntity<InputStreamResource> generatePaymentReportPdf(@RequestParam("from")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                                 @RequestParam("to")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to ){

        String html=paymentReportHtmlGenerator.generateHtmlTemplate(
                hisaabService.generatePaymentReport(from,to)
        );
        String paymentReportTitle="PaymentReport_"+from.toString()+"__"+to.toString()+".pdf";
        byte[] pdfBytes = htmlToPdfUtil.generatePdfFromHtml(html);
        // Create an input stream from the PDF bytes
        ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);
        // Set response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+paymentReportTitle);

        // Create the response entity with input stream resource
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
