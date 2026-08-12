package com.fc.fcauth.controller;

import com.fc.fcauth.model.Department;
import com.fc.fcauth.model.Employee;
import com.fc.fcauth.service.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Basics", description = "기본 관리 API")
public class DepartmentController {

  private final DepartmentService departmentService;

  @GetMapping(value = "/departments",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Department>> listAll(){
    return new ResponseEntity<>(departmentService.listDepartments(), HttpStatus.OK);
  }

}
