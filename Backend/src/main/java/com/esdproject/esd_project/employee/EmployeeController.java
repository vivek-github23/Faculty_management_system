package com.esdproject.esd_project.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @GetMapping("/salary/{facultyId}")
    public Optional<List<EmployeeSalary>> getSalaryDetails(@PathVariable Integer facultyId) {
//        System.out.println(facultyId);
        // Extract faculty ID from token
        //Integer facultyId = TokenUtil.extractFacultyId(token);
        // Fetch salary details for the faculty
        Optional<List<EmployeeSalary>> details = employeeService.getSalaryDetails(facultyId);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return details;
    }

//    @GetMapping("/salary/history/{facultyId}")
//    public ResponseEntity<Optional<List<EmployeeSalary>>> getSalaryHistory(@PathVariable Integer facultyId) {
//        // Extract faculty ID from token
//        // Fetch salary history for the faculty
//        Optional<List<EmployeeSalary>> history = employeeService.getSalaryHistory(facultyId);
//        return ResponseEntity.ok(history);
//    }
}
