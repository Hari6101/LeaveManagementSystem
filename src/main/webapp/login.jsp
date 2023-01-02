<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login | LMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <style>
        body {
            background: #eee;
        }
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
   
  }
   #errorMessage {
 	 color: red;
	}
    </style>
    <script>
    window.addEventListener('load', function() {
    	  document.querySelector('.page-loader').remove();
    	});

    </script>
</head>

<body>
    <!--  -->
    <div class="page-loader">
  <div class="d-flex align-items-center">
    <strong>Loading...</strong>
    <div class="spinner-border ml-auto" role="status" aria-hidden="true"></div>
  </div>
</div>
    <section class="vh-100">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col col-xl-10">
                    <div class="card rounded-4">
                        <div class="row g-0">
                            <div class="col-md-6 col-lg-5 d-none d-md-block rounded-4 shadow"
                                style="background-color: antiquewhite;">



                                <!-- <img src="./images/login4.jpg"alt="login form"  style="border-radius: 1rem 0 0 1rem;height: 510px;width: 480px;" />  -->
                                <img src="./images/login6.png" alt="login form" class="img-fluid rounded-4" />
                            </div>
                            <div class="col-md-6 col-lg-7 d-flex align-items-center shadow">
                                <div class="card-body p-4 p-lg-5 text-black">

                                   <!--  <form action="login " onsubmit="return validateForm()"> -->
                                    <form action="login" method="POST">


                                        <div class="d-flex align-items-center mb-3 pb-1">

                                            <span class="h1 fw-bold mb-0">LMS Portal</span>
                                        </div>
                                        <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your
                                            account</h5>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="email">Email</label>
                                            <input type="email" id="email"  name="email"class="form-control form-control-lg" required/>
                                               

                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="password">Password</label>
                                            <input type="password" id="password" name="password"class="form-control form-control-lg" required/>
                                             <%
											  String errorMessage = (String) session.getAttribute("errorMessage");
											  if (errorMessage != null) {
											%>
																						
											    <div id="errorMessage">
												      <%= errorMessage %>
												    </div>
												<%
												    session.removeAttribute("errorMessage");
												  }
												%>
                                        </div>
                                        
                                        
										
											
											
											
											
                                        <div class="pt-1 mb-4">
                                            <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>
                                        </div>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <!--  -->
 
    <!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
        integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
        crossorigin="anonymous"></script>  -->

       
</body>

</html>