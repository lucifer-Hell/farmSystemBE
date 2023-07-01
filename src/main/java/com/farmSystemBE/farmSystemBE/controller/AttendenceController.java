package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.DTO.Attendence;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {
    @PostMapping
    ResponseEntity markAttendence(Date date, Attendence attendence){
        return ResponseEntity.ok("attendence marked");
    }
}
