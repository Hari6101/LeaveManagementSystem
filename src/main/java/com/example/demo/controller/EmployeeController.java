package com.example.demo.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Employee;
import com.example.demo.model.Holiday;
import com.example.demo.model.Leave;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.HolidayRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.ProjectRepository;


@Controller
public class EmployeeController{
	@Autowired
	EmployeeRepository emprepo;
	@Autowired
	HolidayRepository hrepo;
	@Autowired
	LeaveRepository lrepo;
	@Autowired
	ProjectRepository proRepo;
	

	@GetMapping("/")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView("login.jsp");
		return modelAndView;
	}
	@RequestMapping("/employeeDashboard")
	public String employeeDashboard(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Leave> leave =  lrepo.findAllByEmpId(id);
		request.setAttribute("leave", leave);
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/user dashboard.jsp";
	}
	@RequestMapping("/viewLeaveformEmployee")
	public String viewLeaveFormEmployee(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/leaveform emp.jsp";
	}
	@GetMapping("/viewHolidayEmployee")
	public ModelAndView viewHolidayEmployee(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		List<Holiday> holiday = hrepo.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view holiday emp.jsp");
		modelAndView.addObject("holiday", holiday);
		System.out.println(modelAndView);
		return modelAndView;
	}
	 @RequestMapping("/viewProjectsEmployee")
	  public ModelAndView viewProjectEmployee(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		//List<Project> project = proRepo.findAll();
		List<Project> project = proRepo.findByAssignedTeam(employee.getReportingmanager());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewProjects emp.jsp");
		modelAndView.addObject("project", project);
		System.out.println(modelAndView);
		return modelAndView;
	}
	 @RequestMapping("/viewResetPasswordEmployee")
		public String viewEmployeeResetPassword(HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = emprepo.getReferenceById(id);
			request.setAttribute("employee", employee);
			return "/resetpassword emp.jsp";
		}
		@RequestMapping("/employeeProfile")
		public String viewEmployeeProfile(HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = emprepo.getReferenceById(id);
			request.setAttribute("employee", employee);
			return "/userProfile.jsp";
		}
		  @RequestMapping("/applyLeaveEmployee")
		  public ModelAndView applyEmployee(Leave leave,HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("ids"));
			Employee employee = emprepo.getReferenceById(id);
			request.setAttribute("employee", employee);
			  lrepo.save(leave);
			  System.out.println("Leave applied successfully");
			  return new ModelAndView("redirect:/employeeDashboard?id="+id);
		  } 

	@PostMapping("/login")
	public ModelAndView login(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session,Model model) {
		ModelAndView mv = new ModelAndView();

		System.out.println(email);
		System.out.println(password);

		Employee employee = emprepo.findByEmailAndPassword(email, password);
		if (employee==null) {
			System.out.println("failed to login");
			//mv.addObject("errorMessage", "Invalid email or password. Please try again.");
			//return new ModelAndView("login.jsp");
			  session.setAttribute("errorMessage", "Invalid email or password. Please try again.");
			mv.setViewName("login.jsp");
			return mv;
		}
		else if (employee.getRole().equals("manager")) {
				session.setAttribute("email", email);
				mv.setViewName("Manager dashboard.jsp");
				mv.addObject("employee",employee);
				return mv;
			}
			else if (employee.getRole().equals("admin")) {
				session.setAttribute("email", email);
				mv.setViewName("Admin dashboard.jsp");
				mv.addObject("employee",employee);
				return mv;
			}
			else if (employee.getRole().equals("employee")) {
				session.setAttribute("email", email);
				mv.setViewName("user dashboard.jsp");
				mv.addObject("employee",employee);
				return mv;
			}else {
				
				
				return new ModelAndView("login.jsp");
			}
		}
		

	@GetMapping("/fetchdata")
	public ResponseEntity<List<Date>> getData() {
		List<Holiday> holidays = hrepo.findAll();
		List<Date> dates=new ArrayList<>();
		for(Holiday h:holidays) {
			dates.add(h.getDates());
		}
		return ResponseEntity.ok(dates);
	}

	//fetch remaining leave count
//	@GetMapping("/fetchleavedata")
//	public ResponseEntity<Integer> getLeaveData(HttpServletRequest request) {
//		int id = Integer.parseInt(request.getParameter("id"));
//		Employee employee = emprepo.getReferenceById(id);
//		request.setAttribute("employee", employee);
//		int sickleave=employee.getSickleave();
//		return ResponseEntity.ok(sickleave);
//	}
}
