package com.esdproject.esd_project.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        try {
            Department savedDepartment = departmentRepository.save(department);
            return ResponseEntity.ok("Department created with ID: " + savedDepartment.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create department: " + e.getMessage());
        }
    }
}
