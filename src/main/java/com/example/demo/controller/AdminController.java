package com.example.demo.controller;


import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Employee;
import com.example.demo.model.Holiday;
import com.example.demo.model.Leave;
import com.example.demo.model.Project;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.HolidayRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.EmployeeLeavePolicyService;
import com.example.demo.service.PasswordGenerationService;
import com.example.demo.service.emailSender;


@Controller
public class AdminController{
	@Autowired
	EmployeeRepository emprepo;
	@Autowired
	HolidayRepository hrepo;
	@Autowired
	LeaveRepository lrepo;
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	private PasswordGenerationService pwdService;
	
	@Autowired
	private emailSender senderService;
	
	@RequestMapping("/Admindashboard")
	public String adminDashboard(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Leave> leave =  lrepo.findAllByEmpId(id);
		request.setAttribute("leave", leave);
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/Admin dashboard.jsp";
	}
	@GetMapping("/deleteHoliday")
	public ModelAndView deleteHoliday(HttpServletRequest request) {
//		int id = Integer.parseInt(request.getParameter("id"));
//		System.out.println("id"+id);
		int hid = Integer.parseInt(request.getParameter("hid"));
		System.out.println(hid);
		hrepo.deleteById(hid);
		return new ModelAndView("/viewHoliday");
	}
	

	@GetMapping("/viewHoliday")
	public ModelAndView viewHoliday(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		List<Holiday> holiday = hrepo.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view holiday admin.jsp");
		modelAndView.addObject("holiday", holiday);
		System.out.println(modelAndView);
		return modelAndView;
	}

	@RequestMapping("/addEmployee")
	public ModelAndView addEmployee(Employee emp,HttpServletRequest request) {
	  ModelAndView mv = new ModelAndView();
	  int id = Integer.parseInt(request.getParameter("eid"));
	  try {
	    EmployeeLeavePolicyService empservice = new EmployeeLeavePolicyService();
	   
	    
	    String password=pwdService.generatePassword();
	    System.out.println(password);
	    emp.setPassword(password);
	    String email = emp.getEmail();
	    
	    empservice.setLeaveDays(emp);
	    emprepo.save(emp);
	    senderService.sendEmail(email,"Username and Password from LMS","Your profile has been successfully created in Leave management portal.Your credentials to access the portal are : "+"\n"+"Username ="+email+"\n"+"Password ="+password);
	    return new ModelAndView("redirect:/viewEmployee?id="+id);
	  } catch (DataIntegrityViolationException e) {
		  RedirectView redirectView = new RedirectView("/viewAddEmployee");
		  redirectView.addStaticAttribute("id", id); 
		  System.out.println("id"+id);
		  mv.setView(redirectView);
		  
		  mv.addObject("errorMessage", "This email already exists. Please check the values and try again.");
		  return mv;
		

	  } catch (Exception e) {
	    mv.addObject("errorMessage", "An error occurred while adding the employee. Please try again.");
	    mv.setViewName("addEmployee.jsp");
	    return mv;
	  }
	}

	

	  @RequestMapping("/applyLeaveAdmin")
	  public ModelAndView applyLeaveAdmin(Leave leave,HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("ids"));
		String leaveType=request.getParameter("leaveType");
		String total=request.getParameter("totalDays");
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		String email=employee.getEmail();
		System.out.println("email : " +email);
		 senderService.sendEmail(email,"Leave application acknowledgment from LMS","You have successfully applied for the"+leaveType+"for"+total+"days.");
		  lrepo.save(leave);
		  System.out.println("Leave applied successfully");
		  return new ModelAndView("redirect:/Admindashboard?id="+id);
	  } 
	
	@RequestMapping("/viewProjectAdmin")
	public String viewProjects(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/viewProjects.jsp";
	}
	@RequestMapping("/viewLeaveformAdmin")
	public String viewLeaveFormAdmin(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/leaveform.jsp";
	}
	@RequestMapping("/viewAddEmployee")
	public String viewAddEmployee(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Employee> manager=emprepo.findByRole("manager");
		request.setAttribute("manager", manager);
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/addEmployee.jsp";
	}
	@GetMapping("/viewEmployee")
	public ModelAndView viewEmployeeDetails(HttpServletRequest request) {
		//List<Employee> employee = emprepo.findAll();
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		List<Employee> employe = emprepo.findByStatus("active");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("view Employee details Admin.jsp");
		modelAndView.addObject("employe", employe);
		System.out.println(modelAndView);
		return modelAndView;
	}

	
	@RequestMapping("/viewApproveLeave")
	public String viewApproveLeaveManager(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		
		List<Leave> leave = lrepo.findByRoleAndStatusAndReportingmanager("manager", "pending", employee.getName());
		request.setAttribute("leave", leave);
		
		request.setAttribute("employee", employee);
		return "/Admin leave approve.jsp";
	}
	
	@RequestMapping("/viewAddHoliday")
	public String viewAddHoliday(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/addHoliday.jsp";
	}
	
	@PostMapping("/addHoliday")
	public ModelAndView newholiday(Holiday hld, HttpServletRequest request) {
	  ModelAndView mv = new ModelAndView();
	  int id = Integer.parseInt(request.getParameter("id"));
	  try {
	    hrepo.save(hld);
	    return new ModelAndView("redirect:/viewHoliday?id=" + id);
	  } catch (DataIntegrityViolationException e) {
		RedirectView redirectView = new RedirectView("/viewAddHoliday");
		redirectView.addStaticAttribute("id", id); 
		mv.addObject("errorMessage", "This holiday already exists. Please check the values and try again.");
	    mv.setView(redirectView);
	    return mv;
	  } catch (Exception e) {
	    mv.addObject("errorMessage", "An error occurred while adding the holiday. Please try again.");
	    mv.setViewName("addHoliday.jsp");
	    return mv;
	  }
	}
	// reset password 
	  @RequestMapping("/resetPassword")
		public ModelAndView resetPassword (HttpServletRequest request,HttpSession session) {
		  int id = Integer.parseInt(request.getParameter("id"));
		  Employee employee = emprepo.getReferenceById(id);
		  String oldpassword=request.getParameter("password");
		  String password=request.getParameter("newpassword");
		  ModelAndView mv =new ModelAndView();
			
			if (oldpassword.equals(employee.getPassword())) {
				employee.setPassword(password);
				emprepo.save(employee);
				mv.setViewName("login.jsp");
				mv.addObject("employee",employee);
				session.invalidate();
				return mv;
			}
			else {
				RedirectView redirectView = new RedirectView("/viewAdminResetPassword");
				redirectView.addStaticAttribute("id", id); 
				mv.addObject("errorMessage", "Please check the password and try again.");
				System.out.println("reset failed");
				mv.setView(redirectView);
				return mv;
			}
				
		}	
	
	@RequestMapping("/viewAddProject")
	public String viewAddProject(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Employee> manager=emprepo.findByRole("manager");
		request.setAttribute("manager", manager);
		Employee employee = emprepo.getReferenceById(id);
		
		request.setAttribute("employee", employee);
		return "/addProject.jsp";
	}
	


	 @RequestMapping("/viewProjects")
	  public ModelAndView viewProject(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		List<Project> project = proRepo.findAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewProjects.jsp");
		modelAndView.addObject("project", project);
		System.out.println(modelAndView);
		return modelAndView;
	}
	 @RequestMapping("/viewAdminResetPassword")
		public String viewAdminResetPassword(HttpServletRequest request) {
			int id = Integer.parseInt(request.getParameter("id"));
			Employee employee = emprepo.getReferenceById(id);
			request.setAttribute("employee", employee);
			return "/resetpassword.jsp";
		}

	
	
	@RequestMapping("/edit")
	public String editForm(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/updateform.jsp";
	}
	@RequestMapping("/adminProfile")
	public String viewAdminProfile(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		request.setAttribute("employee", employee);
		return "/adminProfile.jsp";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateEmployee(Employee emp,HttpServletRequest request) {
		Employee currentData = emprepo.getReferenceById(emp.getId());
		int id = Integer.parseInt(request.getParameter("id"));
		String pwd=currentData.getPassword();
		int sickleave=currentData.getSickleave();
		int casualleave=currentData.getCasualleave();
		int personalleave=currentData.getPersonalleave();
		int maternityleave=currentData.getMaternityleave();
		int paternityleave=currentData.getPaternityleave();
		int marriageleave=currentData.getMarriageleave();
		int adoptionleave=currentData.getAdoptionleave();
		
		emp.setSickleave(sickleave);
		emp.setCasualleave(casualleave);
		emp.setPersonalleave(personalleave);
		emp.setMaternityleave(maternityleave);
		emp.setPaternityleave(paternityleave);
		emp.setMarriageleave(marriageleave);
		emp.setAdoptionleave(adoptionleave);
		emp.setPassword(pwd);
		emprepo.save(emp);
		return new ModelAndView("redirect:/viewEmployee?id="+id);
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteEmployee(HttpServletRequest request,Employee emp) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee employee = emprepo.getReferenceById(id);
		employee.setStatus("inactive");
		emprepo.save(employee);
		return new ModelAndView("/viewEmployee");
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session !=null) {
			session.invalidate();
		}
		return "login.jsp";

	}
	

	
	// add project 
 	 @RequestMapping("/addProject")
	 public ModelAndView addProject(Project project) {
 	proRepo.save(project);	
    return new ModelAndView("/viewProjects");
  	 }
   
}
