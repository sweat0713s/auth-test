package com.fc.fcauth.controller;

import com.fc.fcauth.model.Employee;
import com.fc.fcauth.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping(value = "/employees",
  produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Employee>> listAll(){
    return new ResponseEntity<>(employeeService.listEmployees(), HttpStatus.OK);
  }
}
