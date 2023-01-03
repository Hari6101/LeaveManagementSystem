<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/userstyle.css" />
    <link rel="stylesheet" href="css/userprofilestyle.css">
    <title>Manager Profile | LMS</title>
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
                                <i class="fas fa-user me-2"></i>employee
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                               
                                <li><a class="dropdown-item" href="/logout">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

<!-- start -->
	<div class="container1 shadow">
				<div class="col-sm-8 bg-white rounded-right">
					<h3 class="mt-3 text-center"></h3>
					<div class="row">
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Employee Id</b>
							</p>
							<h6 class=" text-muted">${employee.id}</h6>
						</div>

						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Name</b>
							</p>
							<h6 class="text-muted">${employee.name}</h6>
						</div>
					</div>
					<hr class="bg-dark">
					<div class="row">
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b> Email</b>
							</p>
							<h6 class=" text-muted">${employee.email}</h6>
						</div>
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Contact</b>
							</p>
							<h6 class="text-muted">+91 ${employee.contact}</h6>
						</div>
					</div>
					<!-- <h4 class="mt-3">Projects</h4> -->
					<hr class="bg-dark">
					<div class="row">
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Gender</b>
							</p>
							<h6 class="text-muted">${employee.gender}</h6>
						</div>
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Date of Birth</b>
							</p>
							<h6 class="text-muted">${employee.dob}</h6>
						</div>
					</div>

					<!-- other -->

					<hr class="bg-dark">
					<div class="row">
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Date of Joining</b>
							</p>
							<h6 class="text-muted">${employee.doj}</h6>
						</div>
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Employee Status</b>
							</p>
							<h6 class="text-muted">${employee.status}</h6>
						</div>
					</div>
					<!-- other -->
					<hr class="bg-dark">
					<div class="row">

						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Designation</b>
							</p>
							<h6 class="text-muted">${employee.designation}</h6>
						</div>
						<div class="col-sm-6">
							<p class="font-weight-bold">
								<b>Address</b>
							</p>
							<h6 class="text-muted">${employee.address}</h6>
						</div>
					</div>



				</div>


			</div>
		</div>
<!-- end -->
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