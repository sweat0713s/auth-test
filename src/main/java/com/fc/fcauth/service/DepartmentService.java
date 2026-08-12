package com.fc.fcauth.service;

import com.fc.fcauth.model.Department;
import com.fc.fcauth.model.Employee;
import com.fc.fcauth.repository.DepartmentRepository;
import com.fc.fcauth.repository.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  public final DepartmentRepository departmentRepository;

  public List<Department> listDepartments() {
    return departmentRepository.findAll();
  }
}
