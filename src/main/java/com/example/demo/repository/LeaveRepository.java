package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Leave;


public interface LeaveRepository extends JpaRepository<Leave,Integer> {

	//Leave findByEmail(String email);
	List<Leave> findAllById(int id);

	List<Leave> findAllByEmpId(int empId);

	List<Leave> findByRole(String role);

	List<Leave> findByRoleAndStatus(String role, String status);

	List<Leave> findByRoleInAndStatusAndReportingmanager(List<String> asList, String status, String name);

	List<Leave> findByRoleAndStatusAndReportingmanager(String role, String status, String reportingmanager);




	

	

}
