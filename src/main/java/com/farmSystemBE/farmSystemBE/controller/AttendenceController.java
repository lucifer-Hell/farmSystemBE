package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.DTO.EmployeeShiftDto;
import com.farmSystemBE.farmSystemBE.service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/attendence")
public class AttendenceController {
    @Autowired
    AttendenceService attendenceService;
    @PutMapping("/updateInBulk")
    ResponseEntity<String> updateAttendenceInBulk(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody List<EmployeeShiftDto> employeeShiftList){
        attendenceService.updateAttendenceInBulk(date, employeeShiftList);
        return ResponseEntity.ok("attendence marked");
    }
    @GetMapping
    List<AttendenceDto> getAttendenceDetail(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return attendenceService.getAttendenceDetailByDate(date);
    }
}
