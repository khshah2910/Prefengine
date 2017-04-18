<!DOCTYPE HTML>
<html>

<head>
<title>Welcome</title>


<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta name="keywords" content="Template, html, premium, themeforest" />
<meta name="description"
	content="Traveler - Premium template for travel companies">
<meta name="author" content="Tsoy">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- GOOGLE FONTS -->
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600'
	rel='stylesheet' type='text/css'>
<!-- /GOOGLE FONTS -->
<link rel="stylesheet" href="web/css/bootstrap.css">
<link rel="stylesheet" href="web/css/font-awesome.css">
<link rel="stylesheet" href="web/css/icomoon.css">
<link rel="stylesheet" href="web/css/styles.css">
<link rel="stylesheet" href="web/css/mystyles.css">
<script src="web/js/modernizr.js"></script>
<style>
.hidden {
	display: none;
}
</style>
<script type="text/javascript">
    	function showHide(){
    		//alert("here");
    		var checkbox = document.getElementById("show");
    		var hide = document.getElementById("hidden");
    		if(checkbox.checked){
    			hide.style.display="block";
    		}
    		else{
    			hide.style.display="none";
    		}
    		//alert("done");
    	}
    </script>


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
							<a class="logo" href="index.html"> <img
								src="web/img/new_logo_1.png" height="47px" width="150px"
								alt="Image Alternative text" title="Image Title" />
							</a>
						</div>

						<div class="col-md-9">
							<div class="top-user-area clearfix">
								<ul class="top-user-area-list list list-horizontal list-border">
									<li class="top-user-area-avatar"><a href=""> Hello, <%=session.getAttribute("name")%></a>
									</li>
									<li><a href="#">Sign Out</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>

		<div class="show-onload">
			<div class="bg-holder full">
				<div class="bg-mask"></div>
				<div class="bg-img"
					style="background-image: url(web/img/2048x1365.png);">
				</div>
				
				<div class="bg-content">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<div class="search-tabs search-tabs-bg mt50">

									<div class="tabbable">

										<div class="tab-content">

											<div class="tab-pane fade in active" id="tab-1">
												<h2>Search for Cheap Flights</h2>
												<form method="get" action="/Prefengine/SearchController"
													name="search">
													<div class="tabbable">
														<ul class="nav nav-pills nav-sm nav-no-br mb10"
															id="flightChooseTab">
															<li class="active"><a href="#flight-search-1"
																data-toggle="tab">Round Trip</a></li>
															<li><a href="#flight-search-2" data-toggle="tab">One
																	Way</a></li>
														</ul>
														<div class="tab-content">
															<div class="tab-pane fade in active" id="flight-search-1">
																<div class="row">
																	<div class="col-md-6">
																		<div
																			class="form-group form-group-lg form-group-icon-left">
																			<i class="fa fa-map-marker input-icon"></i> <label>From</label>
																			<input class="typeahead form-control"
																				placeholder="City, Airport, U.S. Zip" type="text"
																				value="BOS" name="departure" />
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div
																			class="form-group form-group-lg form-group-icon-left">
																			<i class="fa fa-map-marker input-icon"></i> <label>To</label>
																			<input class="typeahead form-control"
																				placeholder="City, Airport, U.S. Zip" type="text"
																				value="AMD" name="destination" />
																		</div>
																	</div>
																</div>
																<div class="input-daterange" data-date-format="M d, D">
																	<div class="row">
																		<div class="col-md-3">
																			<div
																				class="form-group form-group-lg form-group-icon-left">
																				<i
																					class="fa fa-calendar input-icon input-icon-highlight"></i>
																				<label>Departing</label> <input class="date-pick form-control" id="departureDate"
																					name="departureDate" data-date-format="M d, D"  type="text" />
																			</div>
																		</div>
																		<div class="col-md-3">
																			<div
																				class="form-group form-group-lg form-group-icon-left">
																				<i
																					class="fa fa-calendar input-icon input-icon-highlight"></i>
																				<label>Returning</label> <input class="date-pick form-control" id="returnDate"
																					name="returnDate" data-date-format="M d, D"  type="text" />
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div
																				class="form-group form-group-lg form-group-icon-left">
																				<label>Passngers</label>
																				<div class="btn-group btn-group-select-num"
																					data-toggle="buttons">
																					<input class="form-control" type="text"
																						name="numberOfPassengers" value=1>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<div class="tab-pane fade" id="flight-search-2">
																<div class="row">
																	<div class="col-md-6">
																		<div
																			class="form-group form-group-lg form-group-icon-left">
																			<i class="fa fa-map-marker input-icon"></i> <label>From</label>
																			<input class="typeahead form-control"
																				placeholder="City, Airport, U.S. Zip" type="text" />
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div
																			class="form-group form-group-lg form-group-icon-left">
																			<i class="fa fa-map-marker input-icon"></i> <label>To</label>
																			<input class="typeahead form-control"
																				placeholder="City, Airport, U.S. Zip" type="text" />
																		</div>
																	</div>
																</div>
																<div class="row">
																	<div class="col-md-3">
																		<div
																			class="form-group form-group-lg form-group-icon-left">
																			<i
																				class="fa fa-calendar input-icon input-icon-highlight"></i>
																			<label>Departing</label> <input
																				class="date-pick form-control"
																				data-date-format="M d, D" type="text" />
																		</div>
																	</div>
																	<div class="col-md-6">
																		<div
																			class="form-group form-group-lg form-group-icon-left">
																			<label>Passengers</label>
																			<div class="btn-group btn-group-select-num"
																				data-toggle="buttons">
																				<input class="form-control" type="text"
																					name="numberOfPassengers" value=1>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<input type="checkbox" name="checkbox"
																onclick="showHide()" id="show" />
															<lable for="show">Show more</lable>
															<br>
															<br>


															<div class="content hideContent" hidden="true"
																id="hidden">
																<div class="row">
																	<div class="col-md-2">
																		<h5 class="booking-filters-title">Stops</h5>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox"
																				value="0" name="stops" />Non-stop
																			</label>
																		</div>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox"
																				value="1" name="stops" />1 Stop
																			</label>
																		</div>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox"
																			value="2"	name="stops" />2+ Stops
																			</label>
																		</div>
																	</div>
																	<div class="col-md-1"></div>
																	<div class="col-md-2">
																		<h5 class="booking-filters-title">Price</h5>
																		<input type="text" id="price-slider" name="price">
																	</div>
																	<div class="col-md-1"></div>
																	<div class="col-md-2">
																		<h5 class="booking-filters-title">Flight Class</h5>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox"
																				name="economy" />Economy
																			</label>
																		</div>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox"
																				name="business" />Business
																			</label>
																		</div>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox"
																				name="first" />First
																			</label>
																		</div>
																	</div>
																	<div class="col-md-1"></div>
																	<div class="col-md-3">
																		<h5 class="booking-filters-title">Departure Time</h5>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox" />Morning
																				(5:00a - 11:59a)
																			</label>
																		</div>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox" />Afternoon
																				(12:00p - 5:59p)
																			</label>
																		</div>
																		<div class="checkbox">
																			<label> <input class="i-check" type="checkbox" />Evening
																				(6:00p - 11:59p)
																			</label>
																		</div>
																	</div>
																	<p></p>
																</div>
																<div id="nonfuncarea">
																	<div class="row">
																		<h3>Advanced Non-Functional Parameters</h3>
																		<div class="col-md-4">
																			<div class="form-group form-group-lg">
																				<label>Non-Functional Requirement</label>
																				<select name="req1" value="Price, stops, duration..." class="form-control">
																				  <option value="price">Price</option>
																				  <option value="stops">Stops</option>
																				  <option value="duration">Duration</option>
																				  <option value="mileage">Mileage</option>
																				</select>																	
																				
																			</div>
																		</div>
																		<div class="col-md-3">
																			<div class="form-group form-group-lg">
																				<label>Operator</label>
																				<input type="radio" name="operator1" value="and" checked> AND
																				<input type="radio" name="operator1" value="or"> OR
																				<input type="radio" name="operator1" value="compromise"> COMPROMISE
																			</div>
																		</div>
																		<div class="col-md-4">
																			<div class="form-group form-group-lg">
																				<label>Non-Functional Requirement</label>
																				<select name="req2" value="Price, stops, duration..." class="form-control">
																				  <option value="price">Price</option>
																				  <option value="stops">Stops</option>
																				  <option value="duration">Duration</option>
																				  <option value="mileage">Mileage</option>
																				</select>																	
																				
																			</div>
																		</div>
																		<div class="col-md-1">
																			<div class="form-group form-group-lg">
																				<label>Add</label>
																					<a href="#" onclick="return addNewRow();"><i class="fa fa-plus" aria-hidden="true"></i></a>
																				</label>
																			</div>
																		</div>
																	</div>
																	
																
															</div>
														</div>
													</div>
													<button class="btn btn-primary btn-lg" type="submit">Search
														for Flights</button>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			



		<footer id="main-footer">
			<div class="container">
				<div class="row row-wrap">
					
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
		<script
			src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
		<script src="web/js/typeahead.js"></script>
		<script src="web/js/card-payment.js"></script>
		<script src="web/js/magnific.js"></script>
		<script src="web/js/owl-carousel.js"></script>
		<script src="web/js/fitvids.js"></script>
		<script src="web/js/tweet.js"></script>
		<script src="web/js/countdown.js"></script>
		<script src="web/js/gridrotator.js"></script>
		<script src="web/js/custom.js"></script>
		
		<script type="text/javascript">
			var rowCount = 1;
			//Added code by Yinka
			function addNewRow(){
					rowCount += 1;
					$('#nonfuncarea').append("<div class='row'> <div class='col-md-4'> " +
							"<div class='form-group form-group-lg'> <label>Non-Functional Requirement</label> " +
							"<select name='req" + ((rowCount * 2)-1) + "' value='Price, stops, duration...' class='form-control'> " +
																				  "<option value='price'>Price</option> " +
																				  "<option value='stops'>Stops</option> " +
																				  "<option value='duration'>Duration</option> " +
																				  "<option value='mileage'>Mileage</option> " +
																				"</select> " +
																			"</div> " +
																		"</div> " +
																		"<div class='col-md-3'> " +
																		"	<div class='form-group form-group-lg'> " +
																		"		<label>Operator</label> " +
																		"		<input type='radio' name='operator" + rowCount + "' value='and' checked> AND " +
																		"		<input type='radio' name='operator" + rowCount + "' value='or'> OR " +
																		"		<input type='radio' name='operator" + rowCount + "' value='compromise'> COMPROMISE " +
																		"	</div> " +
																		"</div> " +
																		"<div class='col-md-4'> " +
																		"	<div class='form-group form-group-lg'> " +
																	"			<label>Non-Functional Requirement</label> " +
																	"			<select name='req" + (rowCount * 2) + "' value='Price, stops, duration...' class='form-control'> " +
																	"			  <option value='price'>Price</option> " +
																	"			  <option value='stops'>Stops</option> " +
																	"			  <option value='duration'>Duration</option> " +
																	"			  <option value='mileage'>Mileage</option>  " +
																	"			</select>	 " +																
																	"		</div>  " +
																	"	</div> " +
																	"</div> " 
																	
																	);
					return false;
				}
			$(document).ready(function() {
				var date = new Date();
				var today = new Date(date.getFullYear(), date.getMonth(), date.getDate());
				$('#departureDate').datepicker('setStartDate', 'today');
				$('#returnDate').datepicker('setStartDate', 'today');
		
				
				$('#departureDate').datepicker()
				  .on('changeDate', function(ev){
					  $("#returnDate").val($("#departureDate").datepicker('getFormattedDate'));
					  $('#returnDate').datepicker('setStartDate', val($("#departureDate").datepicker('getFormattedDate')))
					//if (ev.date.valueOf() < startDate.valueOf()){
					  //....
				  });
				  
				

			
			});
		</script>
	</div>
</body>

</html>


