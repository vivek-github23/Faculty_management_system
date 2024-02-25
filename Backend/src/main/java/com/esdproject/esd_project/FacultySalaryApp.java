package com.esdproject.esd_project;

import com.esdproject.esd_project.auth.AuthenticationService;
import com.esdproject.esd_project.auth.RegisterRequest;
import com.esdproject.esd_project.department.Department;
import com.esdproject.esd_project.department.DepartmentRepository;
import com.esdproject.esd_project.employee.Employee;
import com.esdproject.esd_project.employee.EmployeeRepository;
import com.esdproject.esd_project.employee.EmployeeSalary;
import com.esdproject.esd_project.employee.EmployeeSalaryRepository;
import com.esdproject.esd_project.user.Role;
import com.esdproject.esd_project.user.User;
import com.esdproject.esd_project.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class FacultySalaryApp {

	public static void main(String[] args) {
		SpringApplication.run(FacultySalaryApp.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			UserRepository userRepository,
			DepartmentRepository departmentRepository,
			EmployeeSalaryRepository employeeSalaryRepository,
			EmployeeRepository employeeRepository,
			AuthenticationService authService
	) {

		return args -> {

			try {

				//Create departments
				Department department1 = Department.builder().name("CSE").capacity(50).build();
				Department department2 = Department.builder().name("ESE").capacity(30).build();

				departmentRepository.save(department1);
				departmentRepository.save(department2);

				// Create users
				var user1 = RegisterRequest.builder()
						.firstname("Vivek")
						.lastname("Kumar")
						.email("vivek@gmail.com")
						.password("pass")
						.role(Role.EMPLOYEE).build();

				var user2 = RegisterRequest.builder()
						.firstname("Akhil")
						.lastname("Asati")
						.email("akhil@gmail.com")
						.password("pass")
						.role(Role.EMPLOYEE).build();

				authService.register(user1);
				authService.register(user2);
				User user3 = userRepository.findById(1).orElse(null);
				User user4 = userRepository.findById(2).orElse(null);
				if(user3 != null && user4 != null) {
					// Create employees and associate with departments and users
					Employee employee1 = Employee.builder().title("Professor").photographPath("/path/to/photo1.jpg").user(user3).department(department1).build();
//					employee1.setDepartment(department1);
					Employee employee2 = Employee.builder().title("Assistant Professor").photographPath("/path/to/photo2.jpg").user(user4).department(department2).build();
//					employee2.setDepartment(department2);

					employeeRepository.save(employee1);
					employeeRepository.save(employee2);

					// Example employeeSalary creation
					EmployeeSalary salary1 = EmployeeSalary.builder().paymentDate(java.sql.Date.valueOf(LocalDate.parse("2023-11-01"))).amount(BigDecimal.valueOf(15000))
							.description("Monthly salary").employee(employee1).salarySlip("https://drive.google.com/file/d/1vYnz3lo4FM9nWT66OxP1dwcxsCrodDyj/view?usp=sharing").build();
					EmployeeSalary salary3 = EmployeeSalary.builder().paymentDate(java.sql.Date.valueOf(LocalDate.parse("2023-10-01"))).amount(BigDecimal.valueOf(10000))
							.description("Monthly salary").employee(employee1).salarySlip("https://drive.google.com/file/d/1vYnz3lo4FM9nWT66OxP1dwcxsCrodDyj/view?usp=sharing").build();
					EmployeeSalary salary4 = EmployeeSalary.builder().paymentDate(java.sql.Date.valueOf(LocalDate.parse("2023-09-01"))).amount(BigDecimal.valueOf(5000))
							.description("Monthly salary").employee(employee1).salarySlip("https://drive.google.com/file/d/1vYnz3lo4FM9nWT66OxP1dwcxsCrodDyj/view?usp=sharing").build();
					EmployeeSalary salary2 = EmployeeSalary.builder().paymentDate(new Date()).amount(BigDecimal.valueOf(6000))
							.description("Monthly salary").employee(employee2).salarySlip("https://drive.google.com/file/d/1vYnz3lo4FM9nWT66OxP1dwcxsCrodDyj/view?usp=sharing").build();

					// Save employee salaries
					employeeSalaryRepository.save(salary1);
					employeeSalaryRepository.save(salary3);
					employeeSalaryRepository.save(salary4);
					employeeSalaryRepository.save(salary2);
				}

				// Save employees
				// (Remember to save employeeSalary entries if needed)


			}
			catch (Exception ex) {
				ex.printStackTrace();
			}


		};
	}
}
