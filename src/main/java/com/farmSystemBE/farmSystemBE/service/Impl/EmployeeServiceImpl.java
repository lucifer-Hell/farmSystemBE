package com.farmSystemBE.farmSystemBE.service.Impl;

import com.farmSystemBE.farmSystemBE.DTO.EmployeeDto;
import com.farmSystemBE.farmSystemBE.entity.Employee;
import com.farmSystemBE.farmSystemBE.mapper.EmployeeMapper;
import com.farmSystemBE.farmSystemBE.repository.EmployeeRepository;
import com.farmSystemBE.farmSystemBE.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpRetryException;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public void addEmployee(EmployeeDto employeeDto) throws HttpRetryException {
        if(!employeeRepository.findEmployeesByName(employeeDto.getFirstName(),
                employeeDto.getLastName()).isEmpty())
            throw new HttpRetryException("Employee name already exists try with another employee name ",400);
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
