package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.model.Leave;
import com.example.demo.model.Manager;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.HolidayRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.ManagerRepository;

import com.example.demo.service.ManagerLeavePolicyService;

@Controller
public class ManagerController{
	@Autowired
	ManagerRepository mrepo;
	@Autowired
	HolidayRepository hrepo;
	@Autowired
	EmployeeRepository emprepo;
	@Autowired
	LeaveRepository lrepo;

	@RequestMapping("/viewApproveLeaveManager")
	public String viewApproveLeaveManager(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		//List<Leave> leave = lrepo.findByRole("admin");leave.addAll(lrepo.findByRole("employee"));
		List<Leave> leave = lrepo.findByRoleAndStatus("admin", "pending");leave.addAll(lrepo.findByRoleAndStatus("employee", "pending"));

		request.setAttribute("leave", leave);
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/viewLeave manager.jsp";
	}

	@GetMapping("/leaveRejectManager")
	public ModelAndView managerRejectLeave(HttpServletRequest request,Leave leave) {
		int id = Integer.parseInt(request.getParameter("id"));
		Leave lv = lrepo.getReferenceById(leave.getId());
		System.out.println(leave.getId());
		lv.setStatus("Rejected");
		lrepo.save(lv);
		return new ModelAndView("/viewApproveLeaveManager?id="+id);
	}
	@GetMapping("/leaveApproveManager")
	public ModelAndView managerApproveLeave(HttpServletRequest request,Leave leave,Employee emp) {
		int id = Integer.parseInt(request.getParameter("id"));
		Leave lv = lrepo.getReferenceById(leave.getId());
		System.out.println(leave.getId());
		lv.setStatus("Approved");
		lrepo.save(lv);
		//comment
		//leave calculation
		 String leaveType = lv.getLeaveType();
		  int days = Integer.parseInt(lv.getTotalDays());
		  System.out.println("emp id"+lv.getEmpId());
		  System.out.println("days "+days);
		  System.out.println("leaveType"+leaveType);
		  Employee employee = emprepo.findById(lv.getEmpId()).orElseThrow(EntityNotFoundException::new);
		  switch (leaveType) {
		    case "SickLeave":
		      employee.setSickleave(employee.getSickleave() - days);
		      break;
		    case "CasualLeave":
		      employee.setCasualleave(employee.getCasualleave() - days);
		      break;
		    case "PersonalLeave":
		      employee.setPersonalleave(employee.getPersonalleave() - days);
		      break;
		    case "MaternityLeave":
		      employee.setMaternityleave(employee.getMaternityleave() - days);
		      break;
		    case "PaternityLeave":
		      employee.setPaternityleave(employee.getPaternityleave() - days);
		      break;
		    case "MarriageLeave":
		      employee.setMarriageleave(employee.getMarriageleave() - days);
		      break;
		    case "AdoptionLeave":
		      employee.setAdoptionleave(employee.getAdoptionleave() - days);
		      break;
		    default:
		      throw new IllegalArgumentException("Invalid leave type: " + leaveType);
		  }
		  emprepo.save(employee);
		return new ModelAndView("/viewApproveLeaveManager?id="+id);
	}

}