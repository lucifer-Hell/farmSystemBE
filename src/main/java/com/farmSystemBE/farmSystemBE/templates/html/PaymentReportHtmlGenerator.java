package com.farmSystemBE.farmSystemBE.templates.html;

import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;
import com.farmSystemBE.farmSystemBE.constants.Shift;
import org.joda.time.Days;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Component
public class PaymentReportHtmlGenerator {
    public String generateHtmlTemplate(PaymentReportDto paymentReportDto){
        /* String generated using https://www.textfixer.com/html/compress-html-compression.php */
        String htmlString = "<!DOCTYPE html><head> <title>Payment Report</title> <style>table { border-collapse: collapse; width: 80%; } th, td { padding: 8px; text-align: center; border: 1px solid #000; } th { background-color: #f2f2f2; } tbody tr:nth-child(even) { background-color: #f9f9f9; } tbody tr:hover { background-color: #eaf2ff; } </style></head><body> <table border=\"3\">" +
                generateTableHeading(paymentReportDto) +
                generateRecordRows(paymentReportDto) +
                "</table></body></html>";
        return htmlString;
    }

    private String generateRecordRows(PaymentReportDto paymentReportDto) {
        StringBuilder recordHtml=new StringBuilder("");
        paymentReportDto.getPaymentReportRows().forEach(paymentReportRowDto -> {
            recordHtml
                    .append("<tr>")
                    .append("<td>").append(paymentReportRowDto.getEmployeeId()).append("</td>")
                    .append("<td>").append(paymentReportRowDto.getEmployeeName()).append("</td>");
            paymentReportDto.getFrom().datesUntil(paymentReportDto.getTo().plusDays(1)).forEach(date -> {
                if(paymentReportRowDto.getDateShiftMap()!=null && paymentReportRowDto.getDateShiftMap().get(date)!=null){
                     Set<Shift> shifts=paymentReportRowDto.getDateShiftMap().get(date);
                     recordHtml
                             .append("<td>").append(shifts.contains(Shift.MORNING)?"P":"A").append("</td>")
                             .append("<td>").append(shifts.contains(Shift.AFTERNOON)?"P":"A").append("</td>");
                }else {
                    recordHtml
                            // for morning shift
                            .append("<td>").append("A").append("</td>")
                            // for afternoon shift
                            .append("<td>").append("A").append("</td>");
                }
            });
            recordHtml.append("<td>").append(paymentReportRowDto.getTotalWage()).append("</td>")
                    .append("</tr>");
        });
        return recordHtml.toString();
    }

    private String generateTableHeading(PaymentReportDto paymentReportDto) {
        long attendenceColSpan= ChronoUnit.DAYS.between(paymentReportDto.getFrom(),paymentReportDto.getTo().plusDays(1))*2 ;
        StringBuilder headingBuilder=new StringBuilder("<tr> <td rowspan=\"3\">Emp Id</td> <td rowspan=\"3\">Emp Name</td> <td colspan=\""+attendenceColSpan+"\">Attendence</td> <td rowspan=\"3\">Total Payment</td> </tr>");
        return headingBuilder
                .append(generateDateColoumns(paymentReportDto))
                .append(generateShiftColoumns(paymentReportDto))
                .toString();

    }

    private String generateShiftColoumns(PaymentReportDto paymentReportDto) {
        StringBuilder shiftColoumHtml=new StringBuilder("<tr>");
        paymentReportDto.getFrom().datesUntil(paymentReportDto.getTo().plusDays(1)).forEach(date -> {
            shiftColoumHtml.append("<td>Morning</td>").append("<td>Afternoon</td>");
        });
        return shiftColoumHtml.append("</tr>").toString();
    }

    private String generateDateColoumns(PaymentReportDto paymentReportDto) {
        StringBuilder dateColoumHtml=new StringBuilder("<tr>");
        paymentReportDto.getFrom().datesUntil(paymentReportDto.getTo().plusDays(1))
                .forEach(date -> {
                    dateColoumHtml.append("<td colspan=\"2\">").append(date.toString()).append("</td>");
                });
        return dateColoumHtml.append("</tr>").toString();
    }
}
