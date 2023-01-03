<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/managerstyle.css" />
    <title>Manager | Approve Leave | LMS</title>
</head>

<body>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar -->
        <div class="bg-white" id="sidebar-wrapper">
            <div class="list-group list-group-flush my-3 mt-5">
               <a href="/managerDashboard?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text active me-2"><i
                        class="fas fa-tachometer-alt "></i> Manager Dashboard</a>

                        
                <a href="/viewLeaveformManager?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-house-add"></i> Apply Leave</a>


                <a href="/viewHolidayManager?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-calendar-day"></i> View Holidays</a>


                <a href="/viewEmployeeManager?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-person-plus"></i>
                     View Employee </a>


               <a href="/viewApproveLeaveManager?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-check2-circle"></i> Approve Leave</a>


                <a href="/viewProjectsManager?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-card-list "></i> View Projects</a>


                <a href="/viewResetPasswordManager?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-key-fill"></i> Reset Password</a>

            </div>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <nav class="navbar navbar-expand-lg navbar-light bg-transparent py-4 px-4">
                <div class="d-flex align-items-center">
                    <i class="fas fa-align-left primary-text fs-4 me-3" id="menu-toggle"></i>
                    <h2 class="fs-2 m-0">Leave Management</h2>
                </div>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle second-text fw-bold" href="#" id="navbarDropdown"
                                role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user me-2"></i>Manager
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item"
									href="ManagerProfile?id=${employee.id}">Profile</a></li>
								<li><a class="dropdown-item" href="/logout">Logout</a></li>
							</ul>
                        </li>
                    </ul>
                </div>
            </nav>



            <div class="container-fluid px-4">

                <div class="row my-5">
                    <h3 class="fs-4 mb-3">Leave Requests</h3>
                    <%-- <c:out value="${leave}"/> --%>
                    <div class="col">
                        <table class="table bg-white rounded shadow-sm  table-hover">
                            <thead>
                                <tr>
                               <th scope="col">ID</th>
                               		<th scope="col">Email</th>
                                    <th scope="col">Leave Type</th>
                                    <th scope="col">Start Date</th>
                                    <th scope="col">End Date</th>
                                    <th scope="col">Days Taken</th>
                                    <th scope="col">Reason</th>
                                    <th scope="col">Status</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <c:forEach items="${leave}" var="leave">
                            <tbody>
                                <tr>
                                <th scope="row">${leave.id}</th>
                                	 <th scope="row">${leave.email}</th>
                                    
                                    <td>${leave.leaveType}</td>
                                    <td>${leave.fromDate}</td>
                                 <td>${leave.endDate}</td>
                            	 <td>${leave.totalDays}</td> 
                                    <td>${leave.reason}</td>
                                    <td>${leave.status}</td>
                                <%--     <td><a href="leaveRejectManager?id=<c:out value="${leave.id}"/>"><button
											type="button"
											onclick="return confirm('Do you really want to reject the leave request?');"
											class="btn btn-danger">REJECT</button></a></td>
								<td><a href="leaveApproveManager?id=<c:out value="${leave.id}"/>"><button
											type="button" class="btn btn-primary">APPROVE</button></a></td> --%>
                               
                               
                               <td><form action="leaveRejectManager?id=${leave.id}">
							  <input type="hidden" name="id" value="${leave.id}">
							   <input type="hidden" name="eid" value="${employee.id}">
							  <button type="submit"
							         onclick="return confirm('Do you really want to reject the leave request?');"
							          class="btn btn-danger">REJECT</button>
									</form>
										</td>
										 <td><form action="leaveApproveManager?id=${leave.id}">
							  <input type="hidden" name="id" value="${leave.id}">
							   <input type="hidden" name="eid" value="${employee.id}">
							  <button type="submit"
							         
							          class="btn btn-primary">APPROVE</button>
									</form>
										</td>
                               

                            </tbody>
                            </c:forEach>
                        </table>
                    </div>

                </div>

            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        var el = document.getElementById("wrapper");
        var toggleButton = document.getElementById("menu-toggle");

        toggleButton.onclick = function () {
            el.classList.toggle("toggled");
        };
    </script>
</body>

</html>