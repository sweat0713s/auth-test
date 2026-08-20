package com.fc.fcauth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String lastName;

  private Long departmentId;

  private String kakaoNickName;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "employee_role_mapping",
      joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Set<Role> roles;

  public static boolean isHR(Employee employee) {
    Set<Role> roles = employee.getRoles();
    return roles.stream()
        .anyMatch(role -> role.getName().equals("인사팀"));
  }
}
