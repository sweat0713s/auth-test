package com.fc.fcauth.controller;

import com.fc.fcauth.model.Employee;
import com.fc.fcauth.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Basics", description = "기본 관리 API")
public class EmployeeController {

  private final EmployeeService employeeService;

  @Operation(description = "전사 부서 조회")
  @GetMapping(value = "/employees",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Employee>> listAll() {
    return new ResponseEntity<>(employeeService.listEmployees(), HttpStatus.OK);
  }

  @PostMapping(value = "/admin/employees",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> create(@RequestParam String firstName,
      @RequestParam String lastName,
      @RequestParam Long departmentId,
      @RequestParam String kakaoNickName) {
    Employee employee = employeeService.createEmployee(firstName, lastName, departmentId,kakaoNickName);
    return new ResponseEntity<>(employee, HttpStatus.CREATED);
  }
}
