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
    <link rel="stylesheet" href="css/adminstyle.css" />
	<style>
	.page-loader {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(255, 255, 255, 0.5);
    z-index: 1040;
    display: flex;
    align-items: center;
    justify-content: center;
	</style>
<script>
    window.addEventListener('load', function() {
    	  document.querySelector('.page-loader').remove();
    	});

    </script>
    <title>Admin Dashboard | LMS</title>
</head>

<body>
   <div class="page-loader">
  <div class="d-flex align-items-center">
    <strong>Loading...</strong>
    <div class="spinner-border ml-auto" role="status" aria-hidden="true"></div>
  </div>
</div>
    <div class="d-flex" id="wrapper">
        <!-- Sidebar -->
       <div class="bg-white" id="sidebar-wrapper">

            <div class="list-group list-group-flush my-3 mt-5">
                <a  href="/Admindashboard?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text active me-2"><i
                        class="fas fa-tachometer-alt "></i> Admin Dashboard</a>


                <a href="/viewLeaveformAdmin?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-house-add"></i> Apply Leave</a>


                <a href="/viewHoliday?id=${employee.id}"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-calendar-day"></i> View Holidays</a>


                <a href="/viewAddEmployee?id=<c:out value="${employee.id}"/>"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-person-fill-add"></i> Add Employee</a>


                <a href="/viewEmployee?id=<c:out value="${employee.id}"/>"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-person-plus"></i>
                    View Employee </a>


                <a href="/viewApproveLeave?id=<c:out value="${employee.id}"/>"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-check2-circle"></i> Approve Leave</a>


                <a href="/viewAddHoliday?id=<c:out value="${employee.id}"/>"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-building-fill-add"></i> Add Holiday</a>


                  <a href="/viewAddProject?id=<c:out value="${employee.id}"/>"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-card-checklist "></i> Add Project</a>


                 <a href="/viewProjects?id=<c:out value="${employee.id}"/>"
                    class="list-group-item list-group-item-action bg-transparent second-text fw-bold me-2"><i
                        class="bi bi-card-list "></i> View Projects </a>


                <a href="/viewAdminResetPassword?id=<c:out value="${employee.id}"/>"
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
                                <i class="fas fa-user me-2"></i>Admin : <c:out value="${employee.name}"/>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="adminProfile?id=${employee.id}">Profile</a></li>
                                <li><a class="dropdown-item" href="/logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>



            <div class="container-fluid px-4">
                <div class="row g-3 my-2">
                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>

                                <p class="fs-5">Sick Leave</p>
                                <p class="fs-5">Available :${employee.sickleave}</p>
                            </div>
                            <i class="bi bi-shop fa-3x primary-text border rounded secondary-bg p-3"></i>

                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>

                                <p class="fs-5">Casual Leave</p>
                                <p class="fs-5">Available :${employee.casualleave}</p>
                            </div>
                            <i class="bi bi-briefcase fa-3x primary-text border rounded secondary-bg p-3"></i>

                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <p class="fs-5">Personal Leave</p>
                                <p class="fs-5">Available :${employee.personalleave}</p>
                            </div>
                            <i class="bi bi-calendar-plus fa-3x primary-text border rounded secondary-bg p-3"></i>

                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <p class="fs-5">Maternity Leave</p>
                                <p class="fs-5">Available :${employee.maternityleave}</p>
                            </div>
                            <i class="bi bi-heart fa-3x primary-text border rounded secondary-bg p-3"></i>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <p class="fs-5">Paternity Leave</p>
                                <p class="fs-5">Available :${employee.paternityleave}</p>
                            </div>

                            <i class="bi bi-heart-pulse fa-3x primary-text border rounded secondary-bg p-3"></i>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <p class="fs-5">Marriage Leave</p>
                                <p class="fs-5">Available :${employee.marriageleave}</p>
                            </div>

                            <i class="bi bi-people fa-3x primary-text border rounded secondary-bg p-3"></i>


                        </div>
                        

                    </div>
                    <div class="col-md-3">
                        <div class="p-3 bg-white shadow-sm d-flex justify-content-around align-items-center rounded">
                            <div>
                                <p class="fs-5">Adoption Leave</p>
                                <p class="fs-5">Available :${employee.adoptionleave}</p>
                            </div>
                            <i class="bi bi-person-plus fa-3x primary-text border rounded secondary-bg p-3"></i>

                        </div>
                    </div>
                </div>
              <%--   <c:out value="${leave}"/>  --%>
                <div class="row my-5">
                    <h3 class="fs-4 mb-3">Leave History</h3>
                    <div class="col">
                        <table class="table bg-white rounded shadow-sm  table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Leave Type</th>
                                    <th scope="col">Start Date</th>
                                    <th scope="col">End Date</th>
                                    <th scope="col">Total Days</th>
                                    <th scope="col">Reason</th>
                                    <th scope="col">Status</th>
                                </tr>
                            </thead>
                            <c:forEach items="${leave}" var="leave">
                            <tbody>
                                <tr>
                                
                                    <th scope="row">${leave.id}</th>
                                    <td>${leave.leaveType}</td>
                                    <td>${leave.fromDate}</td>
                                 <td>${leave.endDate}</td>
                            	 <td>${leave.totalDays}</td> 
                                    <td>${leave.reason}</td>
                                    <td>${leave.status}</td>
                                    
                                </tr>
                               
								
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