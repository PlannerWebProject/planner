<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US">
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="x-ua-compatible" content="IE=edge">

<!-- Font Imports -->
<link rel="stylesheet" href="https://use.typekit.net/gmv6nzn.css">

<!-- Core Style -->
<link rel="stylesheet" href="${path}/resources/css/style.css">

<!-- Font Icons -->
<link rel="stylesheet" href="/plan/resources/css/font-icons.css">

<!-- Plugins/Components CSS -->
<link rel="stylesheet" href="${path}/resources/css/swiper.css">

<!-- Niche Demos -->
<link rel="stylesheet" href="${path}/resources/css/speaker.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="${path}/resources/css/custom.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Air Datepicker -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/air-datepicker/2.1.0/css/datepicker.min.css">

<!-- Document Title
	============================================= -->
<title>Demo Planner</title>

<style>
html, body {
	width: 100%;
	height: 100vh;
}

#calendar {
	width: 100%;
}

#calendar table {
	margin-bottom: 0;
}

#slider {
	height: calc(100vh - 70px);
	margin: 0 auto;
	margin-top: 0;
	justify-content: center;
	display: flex;
}

#sidebarMain {
	display: flex;
	flex-direction: column;
	position: relative;
	width: 250px;
}

#addScheduleBtn {
	position: relative;
}

#datepickerDiv {
	margin: 0 auto;
}

#datepicker {
	margin: 0 auto;
	padding-left: 0;
	padding-right: 0;
}

.sidebar {
	display: flex;
	flex-direction: column;
	width: 100%;
	padding: 0;
	border: 0;
}

#exampleModal, #editEventModal, #loginModal, #signupModal, #eventProduceModal {
	background-color: rgba(0, 0, 0, 0.4);
}

.color-circle {
	width: 20px;
	height: 20px;
	margin: 2px;
	display: inline-block;
	cursor: pointer;
	border-radius: 50%;
}

</style>

</head>

<body class="stretched scroll-detect">

	<!-- Document Wrapper
	============================================= -->
	<div id="wrapper">

		<!-- Modal -->
		<div class="modal d-none animated fadeInUpSmall faster"
			id="speakerModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div
				class="modal-dialog modal-dialog-centered ms-md-4 me-md-auto align-items-end"
				style="max-width: 48rem;"></div>
		</div>


		<!-- Header
		============================================= -->
		<header id="header" class="header-size-sm" data-sticky-shrink="false">
			<div id="header-wrap" class="border-bottom-0">
				<div class="container">
					<div class="header-row justify-content-lg-between">

						<!-- Logo
						============================================= -->
						<div id="logo" class="me-lg-0">
							<div>
								<button name="sidebarFoldingBtn" id="sidebarFoldingBtn">접기</button>
							</div>

							<a href="index.html"><img
								src="/plan/resources/images/plannerLogo.webp" alt=""></a>

						</div>
						<!-- #logo end -->

						<div class="header-misc">

							<!-- Top Account
							============================================= -->
							<div class="header-misc-icon top-account">
								<a href="#" data-bs-toggle="dropdown" data-bs-offset="0,20"
									data-bs-auto-close="true" aria-haspopup="true"
									aria-expanded="false"><i class="bi-person-circle"></i></a>
								<div class="dropdown-menu dropdown-menu-end px-2 m-0">
									<a
										class="dropdown-item py-2 fw-medium h-bg-tranparent font-primary"
										href="#" id="login-action"><i class="bi-bag-check me-2"></i>Login</a>
									<a
										class="dropdown-item py-2 fw-medium h-bg-tranparent font-primary"
										href="#"><i class="bi-person me-2"></i>Your Profile</a> <a
										class="dropdown-item py-2 fw-medium h-bg-tranparent font-primary"
										href="#"><i class="bi-box-arrow-right me-2"></i>Log Out</a>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="header-wrap-clone"></div>
		</header>


		<!--Hero
		============================================= -->
		<section id="slider" class="slider-element">
			<div id="sidebarMain" class="sidebar">
				<div id="addSchedule" class="sidebar">
					<button id="addScheduleBtn" class="sidebar">일정생성</button>
				</div>
				<div id="datepickerDiv" class="sidebar">
					<div id="datepicker"></div>
				</div>
				<div id="scheduleSearchBox" class="sidebar">

					<input type="text" placeholder="일정 검색" class="sidebar">
				</div>
				<div id="scheduleAcc" class="sidebar">
					<button type="button" class="collapsible sidebar">내 달력</button>
					<div class="calendarGroup" class="sidebar">

						<div class="calendarItem">
							<label class="calendarCheckbox"><input
								class='calendarFilter' type="checkbox" value="정연" checked>달력1</label>
						</div>
						<div class="calendarItem">
							<label class="calendarCheckbox"><input
								class='calendarFilter' type="checkbox" value="정연" checked>달력2</label>
						</div>
						<div class="calendarItem">
							<label class="calendarCheckbox"><input
								class='calendarFilter' type="checkbox" value="정연" checked>달력3</label>
						</div>
					</div>

					<button type="button" class="collapsible sidebar">공유받은 달력</button>
					<div class="calendarGroup" class="sidebar">

						<div class="calendarItem">
							<label class="calendarCheckbox"><input
								class='calendarFilter' type="checkbox" value="정연" checked>달력1</label>
						</div>
						<div class="calendarItem">
							<label class="calendarCheckbox"><input
								class='calendarFilter' type="checkbox" value="정연" checked>달력2</label>
						</div>
						<div class="calendarItem">
							<label class="calendarCheckbox"><input
								class='calendarFilter' type="checkbox" value="정연" checked>달력3</label>
						</div>
					</div>
				</div>
			</div>

			<!-- Content
			============================================= -->
			<div id='calendar'></div>
		</section>

	</div>
	<!-- #wrapper end -->

	<!-- Content
		============================================= -->
	<div class="modal fade" id="eventProduceModal" tabindex="-1"
		aria-labelledby="eventProduceModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="eventProduceModalLabel">일정 추가</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form>
							<div class="mb-3">
								<div class="container">
									<div id="color-selector">
										<div class="color-circle" style="background-color: #FF8080"></div>
										<div class="color-circle" style="background-color: #FFCF96"></div>
										<div class="color-circle" style="background-color: #F6FDC3"></div>
										<div class="color-circle" style="background-color: #CDFAD5"></div>
									</div>
									<p>
										선택한 색: <span id="selected-color"></span>
									</p>
								</div>
							</div>
							<div class="mb-3">
								<label for="eventModalTitle" class="col-form-label">제목</label> <input
									type="text" class="form-control" id="eventModalTitle">
							</div>
							<div class="mb-3">
								<label for="eventModalStart" class="col-form-label">일정
									시작</label> <input type="datetime-local" id="eventModalStart"
									class="form-control" placeholder="datetime-local input">
							</div>
							<div class="mb-3">
								<label for="eventModalEnd" class="col-form-label">일정 종료</label>
								<input type="datetime-local" id="eventModalEnd"
									class="form-control" placeholder="datetime-local input"
									onchange="validateEndDate()">
							</div>
							<div class="mb-3">
								<label for="eventModalSelect" class="col-form-label">카테고리</label>
								<select class="form-select" aria-label="Default select example"
									id="eventModalSelect">
									<option selected>카테고리</option>
									<option value="1">개인일정</option>
									<option value="2">공유일정</option>
								</select>
							</div>
							<div class="mb-3">
								<label for="eventModalIoc" class="col-form-label">장소</label> <input
									type="text" class="form-control" id="eventModalIoc">
							</div>
							<div class="mb-3">
								<label for="eventModalContent" class="col-form-label">내용:</label>
								<textarea class="form-control" id="eventModalContent"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="btnEventProduce">일정
							생성</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- loginSTart -->

	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-body">
						<div class="card-body p-5">
							<form id="login-form" name="login-form" class="mb-0" action="#"
								method="post">
								<h1 class="fs-4 fw-semibold text-center mb-0">Sign In to
									Canvas Account</h1>
								<h2 class="fs-5 text-center fw-medium mb-5 mt-1">
									<span class="op-06 nocolor">New?</span> <a href="#"
										id="signup-action">Create Account</a>
								</h2>

								<div class="row">
									<div class="col-12 form-group mb-4">
										<label for="login-form-username">Email</label> <input
											type="text" id="login-form-username"
											name="login-form-username" value=""
											class="form-control not-dark">
									</div>

									<div class="col-12 form-group mb-4">
										<div class="d-flex justify-content-between">
											<label for="login-form-password">Password</label> <a href="#"
												class="fw-semibold text-smaller">Forgot Password?</a>
										</div>
										<input type="password" id="login-form-password"
											name="login-form-password" value=""
											class="form-control not-dark">
									</div>

									<div class="col-12 form-group mb-0">
										<button
											class="btn btn-lg text-white bg-black h-bg-color d-block w-100 m-0"
											id="login-form-submit" name="login-form-submit" value="login">Continue</button>
									</div>
								</div>
							</form>

							<div class="divider divider-center">
								<div class="divider-text text-dark lh-base">or Login with</div>
							</div>

							<div class="text-center">
								<a href="#"
									class="btn d-block mx-0 mb-3 btn-light border d-flex align-items-center justify-content-center"><img
									src="https://cdn.cdnlogo.com/logos/g/35/google-icon.svg"
									alt="Google Logo" class="d-inline-block me-2 square square-xs">Sign
									In with Google</a> <a href="#"
									class="btn d-block mx-0 mb-3 btn-light border d-flex align-items-center justify-content-center"><img
									src="https://cdn.cdnlogo.com/logos/f/91/facebook-icon.svg"
									alt="Facebook Logo"
									class="d-inline-block me-2 square square-xs">Sign In with
									Facebook</a> <a href="#"
									class="btn d-block mx-0 mb-3 btn-light border d-flex align-items-center justify-content-center"><img
									src="https://cdn.cdnlogo.com/logos/t/39/twitter.svg"
									alt="Twitter Logo" class="d-inline-block me-2 square square-xs">Sign
									In with Twitter</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- login end -->
	<!-- registerStart -->

	<div class="modal fade" id="signupModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-body">
						<div class="tab-pane" id="tab-register" role="tabpanel"
							aria-labelledby="canvas-tab-register-tab" tabindex="0">
							<div class="card-body" style="padding: 40px;">
								<h3>Register for an Account</h3>

								<form id="register-form" name="register-form" class="row mb-0"
									action="#" method="post">

									<div class="col-12 form-group">
										<label for="register-form-name">Name:</label> <input
											type="text" id="register-form-name" name="register-form-name"
											value="" class="form-control">
									</div>

									<div class="col-12 form-group">
										<label for="register-form-email">Email Address:</label> <input
											type="text" id="register-form-email"
											name="register-form-email" value="" class="form-control">
									</div>

									<div class="col-12 form-group">
										<label for="register-form-username">Choose a Username:</label>
										<input type="text" id="register-form-username"
											name="register-form-username" value="" class="form-control">
									</div>

									<div class="col-12 form-group">
										<label for="register-form-phone">Phone:</label> <input
											type="text" id="register-form-phone"
											name="register-form-phone" value="" class="form-control">
									</div>

									<div class="col-12 form-group">
										<label for="register-form-password">Choose Password:</label> <input
											type="password" id="register-form-password"
											name="register-form-password" value="" class="form-control">
									</div>

									<div class="col-12 form-group">
										<label for="register-form-repassword">Re-enter
											Password:</label> <input type="password"
											id="register-form-repassword" name="register-form-repassword"
											value="" class="form-control">
									</div>

									<div class="col-12 form-group">
										<button class="button button-3d button-black m-0"
											id="register-form-submit" name="register-form-submit"
											value="register">Register Now</button>
									</div>

								</form>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- registerEnd -->

	<!-- editEventModal -->
	<div class="modal fade" id="editEventModal" tabindex="-1"
		aria-labelledby="editModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editEventModalHeader">일정 수정</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">제목</label> <input
									type="text" class="form-control" id="editEventModalTitle">
							</div>

							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">일정 시작</label>
								<input type="datetime-local" id="editEventModalStart"
									class="form-control" placeholder="datetime-local input">
							</div>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">일정 종료</label>
								<input type="datetime-local" id="editEventModalEnd"
									class="form-control" placeholder="datetime-local input">
							</div>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">카테고리</label>
								<select class="form-select" aria-label="Default select example">
									<option selected>카테고리</option>
									<option value="1">개인일정</option>
									<option value="2">공유일정</option>
								</select>
							</div>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">장소</label> <input
									type="text" class="form-control" id="editEventModalLoc">
							</div>
							<div class="mb-3">
								<label for="message-text" class="col-form-label">내용:</label>
								<textarea class="form-control" id="editEventModalContent"></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="deleteEventBtn">일정
							삭제</button>
						<button type="button" class="btn btn-primary" id="editEventBtn">일정
							수정</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- JavaScripts
	============================================= -->

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/air-datepicker/2.1.0/js/datepicker.min.js"></script>
	<script src="${path}/resources/js/plugins.min.js"></script>
	<script src="${path}/resources/js/functions.bundle.js"></script>
	<script src='${path}/resources/js/index.global.js'></script>

	<script>
		
		var coll = document.getElementsByClassName("collapsible");
		var sidebar = document.getElementsByClassName("sidebar");
		var addScheduleBtn = document.getElementById("addScheduleBtn");
		var i;
		
		// Accordian
		for (var i = 0; i < coll.length; i++) {
			var initialContent = coll[i].nextElementSibling;
			initialContent.style.display = "block";
		}

		for (var i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function () {
				this.classList.toggle("active");
				var content = this.nextElementSibling;
				if (content.style.display === "block") {
					content.style.display = "none";
				} else {
					content.style.display = "block";
				}
			});
		}

		// datepicker
		document.addEventListener('DOMContentLoaded', function () {
			$('#datepicker').datepicker({
				inline: true
			});
		});
		
		$('#datepicker').datepicker({
        language: {
            daysMin: ['일', '월', '화', '수', '목', '금', '토'],
            months: [
                '1월', '2월', '3월', '4월', '5월', '6월',
                '7월', '8월', '9월', '10월', '11월', '12월'
            ],
            today: '오늘',
            clear: '지우기',
            dateFormat: 'yyyy-mm-dd',
            firstDay: 0
        },
        minDate: new Date(),  // 최소 선택 가능한 날짜를 현재 날짜로 설정
        dateFormat: 'yyyy-mm-dd',  // 날짜 형식 설정 (년-월-일)
		onSelect: function (formattedDate, date, picker) {
			if (date) {
				$('#selected-date').text('선택한 날짜: ' + formattedDate);

				var dayOfWeek = date.getDay();
				var days = ['일', '월', '화', '수', '목', '금', '토'];
				$('#day-of-week').text('요일: ' + days[dayOfWeek]);
			} else {
				$('#selected-date').text('');
				$('#day-of-week').text('');
			}
		}
    });
    
		window.addEventListener( 'load', function() {
			var swiperThumb = new Swiper(".swiper-thumb", {
				spaceBetween: 10,
				slidesPerView: 4,
				allowTouchMove: true,
				freeMode: true,
				watchSlidesProgress: true,
			});

			var swiperContainer = new Swiper(".swiper", {
				spaceBetween: 10,
				allowTouchMove: true,
				navigation: {
					nextEl: ".swiper-button-next",
					prevEl: ".swiper-button-prev",
				},
				thumbs: {
					swiper: swiperThumb,
				},
			});
		});

		window.onload = function() {
			var sidebarStatus = true;
			sidebarFoldingBtn.onclick = function () {
				var sidebarMain = document.getElementById("sidebarMain");
				var addSchedule = document.getElementById("addSchedule");


				sidebarMain.style.width = sidebarStatus ? "0" : "250px";
				Array.from(sidebarMain.children).forEach(child => {
					if (child !== addSchedule) {
						child.style.display = sidebarStatus ? "none" : "block";
						child.style.width = sidebarStatus ? "0" : "100%";
					}
				});
				sidebarStatus = !sidebarStatus;
			};
		};
		
		
	document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	var addScheduleBtn = document.getElementById('addScheduleBtn');

	var loginBtn = document.getElementById('login-action');
	var signupBtn = document.getElementById('signup-action');

    	
	var exampleModal = document.getElementById('event');
	addScheduleBtn.addEventListener('click', function() {
		 var modal = new bootstrap.Modal(eventProduceModal);
		 modal.show();
		 var start = document.getElementById('eventstart')
	});

	var loginModal = document.getElementById('loginModal');
		loginBtn.addEventListener('click',function(){
		var modal = new bootstrap.Modal(loginModal);
		modal.show();
		var signupModal = document.getElementById('signupModal');
		signupBtn.addEventListener('click',function(){
			modal.hide();
			var modal1 = new bootstrap.Modal(signupModal);
			modal1.show();
		})
	})

	
	
    var calendar = new FullCalendar.Calendar(calendarEl, {
		eventClick: function(info) {
			var container = document.getElementById("editEventModal");
			var modal = new bootstrap.Modal(container);
			$('#editEventModalTitle').val(info.event.title);
			$('#editEventModalStart').val(moment(info.event.start).format('YYYY-MM-DDTHH:mm'));
			$('#editEventModalEnd').val(moment(info.event.end).format('YYYY-MM-DDTHH:mm'));
			$('#editEventModalColor').val(info.event.backgroundColor);
			$('#editEventModalLoc').val(info.event.extendedProps.loc);
			$('#editEventModalContent').val(info.event.extendedProps.content);
        	modal.show();

			$('#deleteEventBtn').on('click', function() {
				if(window.confirm('일정을 삭제하시겠습니까?'))
				info.event.remove();
				modal.hide();
			});
			
			$('#editEventBtn').on('click', function() {
				if(confirm('일정을 수정하시겠습니까?')){
					$.ajax({
				  		type: "post",
				   		url: "/plan/event/change.do",
				   		data: {
				   			eventSeq: info.event.extendedProps.eventSeq,
				   			allDay: info.event.allDay,
				   			title: $('#editEventModalTitle').val(),
				   			start: moment($('#editEventModalStart').val()).format('YYYY/MM/DD HH:mm'), 
				   			end: moment($('#editEventModalEnd').val()).format('YYYY/MM/DD HH:mm'), 
				   			color: $('#editEventModalColor').val(),
				   			loc: $('#editEventModalLoc').val(),
				   			content: $('#editEventModalContent').val()
				   	    },
				   	    success: function (response) {
				   	    	info.event.setProp('title', $('#editEventModalTitle').val());
				   	    	info.event.setAllDay(false);
				   	    	info.event.setStart($('#editEventModalStart').val());
				   	    	info.event.setEnd($('#editEventModalEnd').val());
				   	    	info.event.setProp('color', $('#editEventModalColor').val());
				   	    	info.event.setExtendedProp('loc', $('#editEventModalLoc').val());
				   	    	info.event.setExtendedProp('content', $('#editEventModalContent').val());
				   	    	modal.hide();
				   	    },
				   	    error: function(a,b,c){
				   			console.log(a,b,c);
				   		}
				   	});
				}
			});
			
			$("#btnEventProduce").on('click', function(event) {
				var start = $('#eventModalStart').val();
				var end = $('#eventModalEnd').val();
				alert();
			});
		},
		
		eventDidMount: function (info) {
			var popover = new bootstrap.Popover(info.el, {
				title: $('<div />', {
					text: info.event.title
				}).css({
					'color': (info.event.backgroundColor != null ? info.event.backgroundColor : '#3788D8'),
					'font-weight': 'bold',
					'font-size': '20px'
				}),
			content: $('<div />', {
				class: 'popoverInfoEvent'
        		}).append('<strong>카테고리:</strong> ' + '<br>')
        .append('<strong>시간:</strong> ' + getDisplayEventDate(info.event) + '<br>')
        .append('<strong>내용:</strong> ' + info.event.extendedProps.content),
			trigger: 'hover',
			placement: 'top',
			html: true,
			container: 'body'
			});
		},
		dateClick: function(info) {
        var container = document.getElementById("eventProduceModal");//
        var modal = new bootstrap.Modal(container);
        modal.show();
      },
      select: function(info) {
        
      },
	  locale: 'ko',
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: '2023-01-12',
      navLinks: true, // can click day/week names to navigate views
    //   businessHours: true, // display business hours
      editable: true,
      selectable: true,
      events: [
   		   $.ajax({
   			type: 'get',
   			url: '/plan/event/list.do',
   			dataType: 'json',
   			success: function(result){
   				result.forEach(obj =>{
   					calendar.addEvent({
   						title: obj.title,
   						start: obj.start,
   						end: obj.end,
   						color: obj.colSeq,
   						extendedProps: {
   							eventSeq: obj.eventSeq,
			   				loc: obj.loc,
			   				content: obj.content
   						}
   					})
   				})
  			},
   			error: function(a,b,c){
   				console.log(a,b,c);
   			}
   		  }) 
   	  ],
   	  eventDrop: function(info){
   		  if(confirm('일정을 수정하시겠습니까?')){
   			$.ajax({
   	      		type: "post",
   	      		url: "/plan/event/dropchange.do",
   	      		data: {
   	      			eventSeq: info.event.extendedProps.eventSeq,
   	      			allDay: info.event.allDay,
   	      			start: moment(info.event.start).format('YYYY/MM/DD HH:mm'),
   	      			end: moment(info.event.end).format('YYYY/MM/DD HH:mm')
   	      		},
   	      		success: function (response) {
   	        		alert('수정 완료');
   	      		},
   	      		error: function(a,b,c){
   					console.log(a,b,c);
   				}
   	    	});
   		  } else {
   			  info.revert();
   		  }
   	  }
    });
    calendar.render();

  });
		
	function getDisplayEventDate(event) {
			var displayEventDate;

			if(event.end == null) {
				displayEventDate = moment(event.start).format('HH:mm');
			} else if (moment(event.start).format('MM-DD')!=moment(event.end).format('MM-DD')) {
			  var startEventInfo = moment(event.start).format('MM/DD');
			  var endEventInfo = moment(event.end).format('MM/DD');
			  displayEventDate = startEventInfo + " - " + endEventInfo;
			} else if (moment(event.start).format('MM-DD')==moment(event.end).format('MM-DD')) {
			  var startTimeEventInfo = moment(event.start).format('HH:mm');
			  var endTimeEventInfo = moment(event.end).format('HH:mm');
			  displayEventDate = startTimeEventInfo + " - " + endTimeEventInfo;
			} else {	
			  displayEventDate = "하루종일";
			}

			return displayEventDate;
			}
			function validateEndDate() {
			    var startDate = document.getElementById("eventModalStart").value;
			    var endDate = document.getElementById("eventModalEnd").value;

			    if (startDate && endDate) {
			        if (startDate > endDate) {
			            alert("일정 종료일은 시작일 이후여야 합니다.");
			            document.getElementById("eventModalEnd").value = startDate;
			        }
			    }
			}
	</script>
</body>
</html>
