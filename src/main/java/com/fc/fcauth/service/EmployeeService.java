package com.fc.fcauth.service;

import com.fc.fcauth.model.Employee;
import com.fc.fcauth.repository.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  public final EmployeeRepository employeeRepository;

  public List<Employee> listEmployees() {
    return employeeRepository.findAll();
  }

  public Employee createEmployee(String firstName, String lastName, Long departmentId,
      String kakaoNickName) {

    if(employeeRepository.existsByKakaoNickName(kakaoNickName)){
      throw new DuplicateKeyException("같은 카카오 닉네임이 존재합니다.");
    }

    Employee employee = Employee.builder()
        .firstName(firstName)
        .lastName(lastName)
        .departmentId(departmentId)
        .kakaoNickName(kakaoNickName)
        .build();
    employeeRepository.save(employee);
    return employee;
  }
}
