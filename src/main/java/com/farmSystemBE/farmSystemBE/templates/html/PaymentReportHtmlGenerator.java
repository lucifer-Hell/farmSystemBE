package com.farmSystemBE.farmSystemBE.templates.html;

import com.farmSystemBE.farmSystemBE.DTO.PaymentReportDto;
import com.farmSystemBE.farmSystemBE.constants.Gender;
import com.farmSystemBE.farmSystemBE.constants.Shift;
import com.farmSystemBE.farmSystemBE.service.TranslatorService;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Component
public class PaymentReportHtmlGenerator {

    @Autowired
    TranslatorService translatorService;
    public String generateHtmlTemplate(PaymentReportDto paymentReportDto){
        // 0th total payment for male , 1st total payment for female , 2 total male count , 3 total female count
        long metaData[]=new long[]{0,0,0,0};
        /* String generated using https://www.textfixer.com/html/compress-html-compression.php */
        String htmlString = "<!DOCTYPE html><head> <title>Payment Report</title> <style>table { border-collapse: collapse; width: 80%; } th, td { padding: 8px; text-align: center; border: 1px solid #000; } th { background-color: #f2f2f2; } tbody tr:nth-child(even) { background-color: #f9f9f9; } tbody tr:hover { background-color: #eaf2ff; } </style></head><body> <table border=\"3\">" +
                generateTableHeading(paymentReportDto) +
                generateMaleHeader()+
                generateRecordRows(paymentReportDto, Gender.MALE,metaData) +
                generateTotalPayment(metaData[2],metaData[0]) +
                generateFemaleHeader()+
                generateRecordRows(paymentReportDto,Gender.FEMALE,metaData)+
                generateTotalPayment(metaData[3],metaData[1]) +
                "</table></body></html>";
        return htmlString;
    }

    private String generateMaleHeader(){
        return "<tr><td colspan=\"5\"><b>MALE</b></td></tr>";
    }
    private String generateTotalPayment(long personCount ,long totalPayment){
        return "<tr><td colspan=\"4\"><b>Total Payment ( "+personCount+" ) </b></td><td colspan=\"1\"><b>"+totalPayment+"</b></td></tr>";

    }

    private String generateFemaleHeader(){
        return "<tr><td colspan=\"5\"><b>FEMALE</b></td></tr>";
    }

    private String generateRecordRows(PaymentReportDto paymentReportDto,Gender gender,long [] metaData) {
        int paymentIndex=(gender==Gender.MALE)?0:1;
        int countIndex=(gender==Gender.MALE)?2:3;
        StringBuilder recordHtml=new StringBuilder("");
        paymentReportDto.getPaymentReportRows()
                .stream()
                .filter(paymentReportRowDto -> paymentReportRowDto.getGender()==gender)
                .forEach(paymentReportRowDto -> {
            recordHtml
                    .append("<tr>")
                    .append("<td>").append(paymentReportRowDto.getEmployeeId()).append("</td>")
                    .append("<td>")
                    .append(paymentReportRowDto.getEmployeeName())
                    .append(" ( ")
                    .append(translatorService.translateText(paymentReportRowDto.getEmployeeName(),"en","hi"))
                    .append(" ) ")
                    .append("</td>");
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
            recordHtml
                    .append("<td>").append(paymentReportRowDto.getTotalWage()).append("</td>")
                    .append("</tr>");
            metaData[paymentIndex]+=paymentReportRowDto.getTotalWage();
            metaData[countIndex]++;
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
