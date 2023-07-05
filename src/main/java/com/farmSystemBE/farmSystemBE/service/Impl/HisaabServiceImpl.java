package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.*;
import com.farmSystemBE.farmSystemBE.constants.Shift;
import com.farmSystemBE.farmSystemBE.entity.Employee;
import com.farmSystemBE.farmSystemBE.service.AttendenceService;
import com.farmSystemBE.farmSystemBE.service.EmployeeService;
import com.farmSystemBE.farmSystemBE.service.HisaabService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;
@Service
@Slf4j
public class HisaabServiceImpl implements HisaabService {
    @Autowired
    AttendenceService attendenceService;
    @Autowired
    EmployeeService employeeService;

    @Override
    public PaymentReportDto generatePaymentReport(LocalDate fromDate, LocalDate toDate) {
        // get list of all employees worked between from date and to date
        Set<Long> employeeIds= findEmployeePresentInDateRange(fromDate,toDate);
        // for each employee find data generate data related to its payment
        List<PaymentReportRowDto> paymentReportRows= employeeIds.stream().map(id -> {
            EmployeeDto employee=employeeService.getEmployeeDetails(id);
            Map<LocalDate, Set<Shift>> dateShiftMap=new HashMap<>();
            fromDate.datesUntil(toDate.plusDays(1)).forEach(date -> {
                List<AttendenceDto> attendenceDtos=attendenceService.getAttendenceDetailByEmployeeIdAndDate(id,date);
                // generate dateShift map
                attendenceDtos.forEach(attendenceDto ->{
                    if(dateShiftMap.containsKey(attendenceDto.getDate())){
                        dateShiftMap.get(attendenceDto.getDate()).add(attendenceDto.getShift());
                    }else{
                        HashSet<Shift> shifts=new HashSet<>();
                        shifts.add(attendenceDto.getShift());
                        dateShiftMap.put(attendenceDto.getDate(),shifts);
                    }
                });
            });
            return PaymentReportRowDto.builder()
                    .employeeId(id)
                    .employeeName(employee.getFirstName()+" "+employee.getLastName())
                    .dateShiftMap(dateShiftMap)
                    .totalWage(employee.getSalary()*getTotalShifts(dateShiftMap))
                    .build();
        }).toList();
        return PaymentReportDto.builder()
                .from(fromDate)
                .to(toDate)
                .paymentReportRows(paymentReportRows)
                .build();
    }

    private int getTotalShifts(Map<LocalDate, Set<Shift>> dateShiftMap) {
        int totalShifts=0;
        for(LocalDate date: dateShiftMap.keySet())
            for(Shift shift:dateShiftMap.get(date))
                totalShifts++;
        return totalShifts;
    }

    private Set<Long> findEmployeePresentInDateRange(LocalDate fromDate, LocalDate toDate) {
        Set<Long> employeeIds=new HashSet<>();
        fromDate.datesUntil(toDate.plusDays(1)).forEach(date -> {
            log.info("curr date "+toDate);
            attendenceService.getAttendenceDetailByDate(date).forEach(attendenceDto -> {
                        employeeIds.add(attendenceDto.getEmployeeId());
                    });
        });
        return employeeIds;
    }
}
