package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	Employee findByEmailAndPassword(String email,String password);
	
	Employee getRefrencedByPassword(String password);
	Employee getRefrencedByEmail(String email);

	List<Employee> findByRole(String role);

	List<Employee> findByStatus(String status);

	List<Employee> findAllByReportingmanagerAndStatus(String reportingmanager, String status);

	List<Employee> findAllByNameAndStatus(String name, String string);

	Employee findByEmail(String email);

	
}
