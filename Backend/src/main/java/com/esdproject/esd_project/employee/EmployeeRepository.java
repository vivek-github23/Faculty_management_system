package com.esdproject.esd_project.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface
EmployeeRepository extends JpaRepository<Employee, Integer> {
}
