package com.farmSystemBE.farmSystemBE.controller;

import com.farmSystemBE.farmSystemBE.DTO.EmployeeDto;
import com.farmSystemBE.farmSystemBE.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpRetryException;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    // GET EMPLOYEE DETAILS
    @GetMapping
    EmployeeDto getEmployeeDetails(@RequestParam("empId") long employeeId){
      return employeeService.getEmployeeDetails(employeeId);
    }

    // ADD EMPLOYEE
    @PostMapping
    ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employee) throws HttpRetryException {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("Employee added sucesfully");
    }

    // UPDATE EMPLOYEE DETAIL
    @PutMapping
    EmployeeDto updateEmployeeDetails(@RequestParam("empId") long empId,@RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(empId,employeeDto);
    }

    // DELETE EMPLOYEE DETAIL
    @DeleteMapping
    void deleteEmployee(@RequestParam("empId") long empId){
        employeeService.deleteEmployeeById(empId);
    }
}
