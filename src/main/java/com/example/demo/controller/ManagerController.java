package com.example.demo.controller;


import java.util.Arrays;
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
import com.example.demo.model.Holiday;
import com.example.demo.model.Leave;
import com.example.demo.model.Manager;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.HolidayRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ManagerLeavePolicyService;
import com.example.demo.service.emailSender;

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
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	private emailSender senderService;
	
	@RequestMapping("/managerDashboard")
	public String managerDashboard(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Leave> leave =  lrepo.findAllByEmpId(id);
		request.setAttribute("leave", leave);
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/Manager dashboard.jsp";
	}
	@RequestMapping("/viewLeaveformManager")
	public String viewLeaveFormAdmin(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/leaveform manager.jsp";
	}
	@GetMapping("/viewHolidayManager")
	public ModelAndView viewHolidayManager(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		List<Holiday> holiday = hrepo.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view holiday manager.jsp");
		modelAndView.addObject("holiday", holiday);
		System.out.println(modelAndView);
		return modelAndView;
	}
	@GetMapping("/viewEmployeeManager")
	public ModelAndView viewEmployeeDetailsManager(HttpServletRequest request,Employee emp) {
		//List<Employee> employee = emprepo.findAll();
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		System.out.println("reporting manager"+employee.getReportingmanager());
		request.setAttribute("employee", employee);
		//List<Employee> employe = emprepo.findByStatus("active");
		List<Employee> employe = emprepo.findAllByReportingmanagerAndStatus(employee.getName(),"active");
//		String reportingperson=employee.getName();
		//List<Employee> employe = emprepo.findAllByNameAndStatus(employee.getName(),"active");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view Employee details Manager.jsp");
		modelAndView.addObject("employe", employe);
		System.out.println(modelAndView);
		return modelAndView;
	}
	 @RequestMapping("/viewProjectsManager")
	  public ModelAndView viewProjectManager(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		//List<Project> project = proRepo.findAll();
		List<Project> project = proRepo.findByAssignedTeam(employee.getName());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewProjects manager.jsp");
		modelAndView.addObject("project", project);
		System.out.println(modelAndView);
		return modelAndView;
	}
	 @RequestMapping("/viewResetPasswordManager")
		public String viewAdminResetPassword(HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = emprepo.getReferenceById(id);
			request.setAttribute("employee", employee);
			return "/resetpassword manager.jsp";
		}
	  @RequestMapping("/applyLeaveManager")
	  public ModelAndView applyLeaveManager(Leave leave,HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("ids"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		  lrepo.save(leave);
		  System.out.println("Leave applied successfully");
		  return new ModelAndView("redirect:/managerDashboard?id="+id);
	  } 
	  @RequestMapping("/ManagerProfile")
		public String viewManagerProfile(HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = emprepo.getReferenceById(id);
			request.setAttribute("employee", employee);
			return "/managerProfile.jsp";
		}
	
	@RequestMapping("/viewApproveLeaveManager")
	public String viewApproveLeaveManager(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		
		List<Leave> leave = lrepo.findByRoleInAndStatusAndReportingmanager(Arrays.asList("admin", "employee"), "pending", employee.getName());

		request.setAttribute("leave", leave);
		
		request.setAttribute("employee", employee);
		return "/viewLeave manager.jsp";
	}

	@GetMapping("/leaveRejectManager")
	public ModelAndView managerRejectLeave(HttpServletRequest request,Leave leave) {
		int id = Integer.parseInt(request.getParameter("eid"));
		Employee employee = emprepo.getReferenceById(id);
		String email = employee.getEmail();
		
		Leave lv = lrepo.getReferenceById(leave.getId());
		String leaveType=lv.getLeaveType();
		System.out.println(leaveType);
		String total = lv.getTotalDays();
		System.out.println(total);
		System.out.println(leave.getId());
		lv.setStatus("Rejected");
		lrepo.save(lv);
		senderService.sendEmail(email,"Leave rejected","Your application for "+leaveType+"for"+total+"days is rejected.");
		return new ModelAndView("/viewApproveLeaveManager?id="+id);
	}
	@GetMapping("/leaveApproveManager")
	public ModelAndView managerApproveLeave(HttpServletRequest request,Leave leave,Employee emp) {
		int id = Integer.parseInt(request.getParameter("eid"));
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
		    case "Sick Leave":
		      employee.setSickleave(employee.getSickleave() - days);
		      break;
		    case "Casual Leave":
		      employee.setCasualleave(employee.getCasualleave() - days);
		      break;
		    case "Personal Leave":
		      employee.setPersonalleave(employee.getPersonalleave() - days);
		      break;
		    case "Maternity Leave":
		      employee.setMaternityleave(employee.getMaternityleave() - days);
		      break;
		    case "Paternity Leave":
		      employee.setPaternityleave(employee.getPaternityleave() - days);
		      break;
		    case "Marriage Leave":
		      employee.setMarriageleave(employee.getMarriageleave() - days);
		      break;
		    case "Adoption Leave":
		      employee.setAdoptionleave(employee.getAdoptionleave() - days);
		      break;
		    default:
		      throw new IllegalArgumentException("Invalid leave type: " + leaveType);
		  }
		  String email = employee.getEmail();
		  System.out.println("email : "+email);
		  senderService.sendEmail(email,"Leave approved","Your application for "+leaveType+"for"+days+"days is approved.");
		  emprepo.save(employee);
		return new ModelAndView("/viewApproveLeaveManager?id="+id);
	}

}