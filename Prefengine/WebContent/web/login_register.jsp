<!DOCTYPE HTML>
<html>

<head>
    <title>Traveler - Login/Register on Traveler</title>


    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta name="keywords" content="Template, html, premium, themeforest" />
    <meta name="description" content="Traveler - Premium template for travel companies">
    <meta name="author" content="Tsoy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- GOOGLE FONTS -->
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600' rel='stylesheet' type='text/css'>
    <!-- /GOOGLE FONTS -->
    <link rel="stylesheet" href="web/css/bootstrap.css">
    <link rel="stylesheet" href="web/css/font-awesome.css">
    <link rel="stylesheet" href="web/css/icomoon.css">
    <link rel="stylesheet" href="web/css/styles.css">
    <link rel="stylesheet" href="web/css/mystyles.css">
    <script src="web/js/modernizr.js"></script>


</head>

<body>

    <!-- FACEBOOK WIDGET -->
    <div id="fb-root"></div>
    <script>
        (function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s);
            js.id = id;
            js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));
    </script>
    <!-- /FACEBOOK WIDGET -->
    <div class="global-wrap">
        <header id="main-header">
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <a class="logo" href="index.html">
                                <img src="web/img/new_logo_1.png" height="47px" width="150px" alt="Image Alternative text" title="Image Title" />
                            </a>
                        </div>
                
                    </div>
                </div>
            </div>
            <div class="container">
            </div>
        </header>

        <div class="container">
            <h1 class="page-title">Login/Register on Prefengine</h1>
        </div>

        <div class="gap"></div>


        <div class="container">
            <div class="row" data-gutter="60">
                <div class="col-md-4">
                    <h3>Welcome to Prefengine</h3>
                    <p>Prefengine is basically preference based airline search-engine which gives you best flight results based on your preferences.</p>
                </div>
                <div class="col-md-4">
                   <!-- <center>
                    <a  href="404.html">
                                <img src="img/signGoogle.png" style="width:307px; padding-bottom:10px" alt="Image Alternative text" title="Login with google" />
                    </a>
                    <br>
                    
                    <a  href="404.html">
                                <img src="img/fblogin.png" style="width:295px"  alt="Image Alternative text" title="Login with google"/>
                    </a>
                        </center>
                    <br>

                    <br>
                    <center>
                        or-->
                        <h3>Login</h3></center>
                    <form method="post" action="/Prefengine/Welcome" name="login">
                     <label style="color: RED;"> ${error }</label>
                        <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon input-icon-show"></i>
                            <label>Username or email</label>
                            <input class="form-control" placeholder="e.g. johndoe@gmail.com" type="text" name="username" required/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon input-icon-show"></i>
                            <label>Password</label>
                            <input class="form-control" type="password" placeholder="my secret password" name="password" required/>
                        </div>
                        <input class="btn btn-primary" type="submit" value="Sign in" />&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </form>
                    <br><br>
                </div>
                <div class="col-md-4">
                    <h3>New To Prefengine?</h3>
                    <form method="post" action="/Prefengine/UserController" name="addUser">
                    <!--    <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon input-icon-show "></i>
                           
                                <label>First Name</label>
                                <input class="form-control" placeholder="e.g. John Doe" type="text" />
                                <label>Last Name</label>
                                <input class="form-control" placeholder="e.g. John Doe" type="text" />
                   		  </div>
                     <form method="post" action="/Prefengine/UserController" name="addUser">
							<h2>Prefengine Registration </h2>
							<table>
								<tr>
									<td> <label>Enter Preferred Email id:</label></td>
									<td> <input type="text" name="email"  ></td>
								</tr>
								<tr>
									<td> <label>Re-enter Email id:</label></td>
									<td> <input type="text"  name="email" ></td>
								</tr>
								<tr>
									<td> <label>Enter Username :</label></td>
									<td> <input type="text"  name = "userName" ></td>
								</tr>
								<tr>
									<td> <label>Enter Password :</label></td>
									<td> <input type="password" name="password" ></td>
								</tr>
								<tr>
									<td> <label>Confirm Password :</label></td>
									<td> <input type="password" name="pass" ></td>
								</tr>
								<tr>
									<td> <label>Security Question :</label></td>
									<td> <input type="text" name="s_que"></td>
								</tr>
								<tr>
									<td> <label>Answer :</label></td>
									<td> <input type="text" name="s_ans"></td>
								</tr>
							</table>
							<input type="submit" name="Register" value="Submit">
						</form>           
                                
                       -->
                        <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                            <label>Username</label>
                            <input class="form-control" placeholder="e.g. johndoe@gmail.com" type="text" name = "userName" required/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                            <label>Emai</label>
                            <input class="form-control" placeholder="e.g. johndoe@gmail.com" type="text" name="email" required/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon input-icon-show"></i>
                            <label>Confirm Email</label>
                            <input class="form-control" placeholder="e.g. johndoe@gmail.com" type="text" name="email" required/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon input-icon-show" ></i>
                            <label>Password</label>
                            <input class="form-control" type="password" placeholder="my secret password" name="password" required/>
                        </div>
                        <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon input-icon-show"></i>
                            <label>Confirm Password</label>
                            <input class="form-control" type="password" placeholder="my secret password" name="password" required/>
                        </div>
                        <input class="btn btn-primary" type="submit" value="Sign up" />
                    </form>
                </div>
                
                
            </div>
        </div>



        <div class="gap"></div>
        <footer id="main-footer">
            <div class="container">
                <div class="row row-wrap">
                   <!--  <div class="col-md-3">
                        <a class="logo" href="index.html">
                            <img src="img/logo-invert.png" alt="Image Alternative text" title="Image Title" />
                        </a>
                        <p class="mb20">Booking, reviews and advices on hotels, resorts, flights, vacation rentals, travel packages, and lots more!</p>
                       <ul class="list list-horizontal list-space">
                            <li>
                                <a class="fa fa-facebook box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-twitter box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-google-plus box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-linkedin box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-pinterest box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                        </ul>
                    </div>
                        -->
                     <!-- 
                    <div class="col-md-2">
                        <ul class="list list-footer">
                            <li><a href="#">About US</a>
                            </li>
                          <li><a href="#">Press Centre</a>
                            </li>
                            <li><a href="#">Best Price Guarantee</a>
                            </li>
                            <li><a href="#">Travel News</a>
                            </li>
                            <li><a href="#">Jobs</a>
                            </li>
                            <li><a href="#">Privacy Policy</a>
                            </li>
                            <li><a href="#">Terms of Use</a>
                            </li>
                            <li><a href="#">Feedback</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <h4>Have Questions?</h4>
                        <h4 class="text-color">+1-202-555-0173</h4>
                        <h4><a href="#" class="text-color">support@traveler.com</a></h4>
                        <p>24/7 Dedicated Customer Support</p>
                    </div>
                    -->
                </div>
            </div>
        </footer>

        <script src="web/js/jquery.js"></script>
        <script src="web/js/bootstrap.js"></script>
        <script src="web/js/slimmenu.js"></script>
        <script src="web/js/bootstrap-datepicker.js"></script>
        <script src="web/js/bootstrap-timepicker.js"></script>
        <script src="web/js/nicescroll.js"></script>
        <script src="web/js/dropit.js"></script>
        <script src="web/js/ionrangeslider.js"></script>
        <script src="web/js/icheck.js"></script>
        <script src="web/js/fotorama.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="web/js/typeahead.js"></script>
        <script src="web/js/card-payment.js"></script>
        <script src="web/js/magnific.js"></script>
        <script src="web/js/owl-carousel.js"></script>
        <script src="web/js/fitvids.js"></script>
        <script src="web/js/tweet.js"></script>
        <script src="web/js/countdown.js"></script>
        <script src="web/js/gridrotator.js"></script>
        <script src="web/js/custom.js"></script>
    </div>
</body>

</html>


