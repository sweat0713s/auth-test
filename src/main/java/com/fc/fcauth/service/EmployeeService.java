package com.fc.fcauth.service;

import com.fc.fcauth.model.Employee;
import com.fc.fcauth.repository.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  public final EmployeeRepository employeeRepository;

  public List<Employee> listEmployees() {
    return employeeRepository.findAll();
  }
}
