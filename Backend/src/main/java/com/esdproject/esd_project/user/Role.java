package com.esdproject.esd_project.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.esdproject.esd_project.user.Permission.STUDENT_CREATE;
import static com.esdproject.esd_project.user.Permission.STUDENT_DELETE;
import static com.esdproject.esd_project.user.Permission.STUDENT_READ;
import static com.esdproject.esd_project.user.Permission.STUDENT_UPDATE;
import static com.esdproject.esd_project.user.Permission.EMPLOYEE_CREATE;
import static com.esdproject.esd_project.user.Permission.EMPLOYEE_DELETE;
import static com.esdproject.esd_project.user.Permission.EMPLOYEE_READ;
import static com.esdproject.esd_project.user.Permission.EMPLOYEE_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  STUDENT(
          Set.of(
                  STUDENT_READ,
                  STUDENT_UPDATE,
                  STUDENT_DELETE,
                  STUDENT_CREATE
          )
  ),
  EMPLOYEE(
          Set.of(
                  EMPLOYEE_READ,
                  EMPLOYEE_UPDATE,
                  EMPLOYEE_DELETE,
                  EMPLOYEE_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
