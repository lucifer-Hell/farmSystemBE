package com.farmSystemBE.farmSystemBE.service;

import com.farmSystemBE.farmSystemBE.DTO.EmployeeDto;
import com.farmSystemBE.farmSystemBE.entity.Employee;
import com.farmSystemBE.farmSystemBE.mapper.EmployeeMapper;
import com.farmSystemBE.farmSystemBE.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        Employee employee=employeeMapper.employeeDtoToEmployee(employeeDto);
        employeeRepository.save(employee);
        log.info("employee added sucessfully");
    }

    @Override
    public EmployeeDto updateEmployee(long employeeId,EmployeeDto employeeDto) {
        if(employeeRepository.existsById(employeeId)){
            employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto));
        }
        log.info("employee updated sucessfully");
        return null;
    }

    @Override
    public void deleteEmployeeById(long employeeId) {
        log.info("employee deleted sucessfully");
    }

    @Override
    public EmployeeDto getEmployeeDetails(long employeeId) {
        log.info("employee details found");
        return new EmployeeDto();
    }
}
