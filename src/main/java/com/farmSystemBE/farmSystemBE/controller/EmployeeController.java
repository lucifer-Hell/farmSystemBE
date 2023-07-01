package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @PostMapping
    ResponseEntity<String> addEmployee(Employee employee){
        return ResponseEntity.ok("Employee added sucesfully");
    }
}
