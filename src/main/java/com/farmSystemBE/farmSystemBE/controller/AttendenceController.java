package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.DTO.AttendenceDto;
import com.farmSystemBE.farmSystemBE.service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {
    @Autowired
    AttendenceService attendenceService;
    @PostMapping("/update")
    ResponseEntity<String> markAttendence(@RequestParam("date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestBody List<AttendenceDto> attendence){
        attendenceService.markAttendence(date,attendence);
        return ResponseEntity.ok("attendence marked");
    }

    List<AttendenceDto> getAttendenceDetail(LocalDate date){
        return attendenceService.getAttendenceDetailByDate(date);
    }
}
