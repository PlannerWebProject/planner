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
<link rel="stylesheet" href="${path}/resources/css/font-icons.css">

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
<title>Planiverse</title>

<style>
.button-inner {
	color: #333 !important;
}

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
	height: calc(100vh - 30px);
	margin: 20px auto 0 auto;
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
	height: 38.88px;
	margin: 0px auto 19px auto;
    width: 90%;
    justify-content: center;
    line-height: inherit;
	place-items: center;
}
 .calendarGroup{
    display: flex;
    padding-right: 10px;
	color: #333 !important;
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

#scheduleAcc {
	padding: 0px 10px 0px 10px;
}

/* #exampleModal, #editEventModal, #loginModal, #signupModal,
	#eventProduceModal {
	background-color: rgba(0, 0, 0, 0.4);
} */

.modal {
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

.button {
	margin: 0;
	-cnvs-btn-padding-x: 1rem;
	-cnvs-btn-padding-y: 0.5rem;
}

#scheduleSearchBox {
	display: flex;
}

#addMyCalendarBtn, #addShCalendarBtn {
	padding: 0px 48px;
	margin-bottom: 8px;
	border: 1px solid #D4E2D4;
    background: #D4E2D4;
}

.white {
	color: #FFF;
}

.bgclightgray {
	background-color: #eeeeee;
}

@media screen and (max-width: 1000px) {
	#datepickerDiv {
		display: none;
	}
	.WhitespaceBlock {
		display: none;
	}
	#addScheduleBtn {
		margin-bottom: 0;
	}
	#sidebarMain {
		display: block;
		width: 100%;
	}
	#slider {
		height: auto;
	}
}

#selected-color, #editSelColor {
	width: 50px;
	display: inline-block;
	height: 15px;
	border-radius: 5px;
}


.pointer {
	cursor: pointer;
	user-select: none;
}
.pointer:hover {
	opacity: 0.7;
}


.bi-person-circle::before {
    font-size: 30px;
}

@media (min-width: 1400px) {
    .container-xxl, .container-xl, .container-lg, .container-md, .container-sm, .container {
        max-width: inherit;
        width: 100%;

    }
}

.fc-toolbar-chunk > button {
	--fc-button-bg-color: #FFC7A4;
    --fc-button-border-color: #FFC7A4;
    --fc-button-hover-bg-color: #FFCACC;
    --fc-button-hover-border-color: #FFCACC;
}

.item {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.label-container {
    flex-grow: 1;
}

.icons-container {
    display: flex;
    gap: 10px;
}

.checkbox-inline.pointer {
    margin-right: 10px;
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
		<!-- <header id="header" class="header-size-sm" data-sticky-shrink="false">
			<div id="header-wrap" class="border-bottom-0">
				<div class="container">
					<div class="header-row justify-content-lg-between">

						<div class="header-misc">

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
										class="dropdown-item py-2 fw-medium h-bg-tranparent font-primary api-btn"
										href="#" onclick="logOut()"><i
										class="bi-box-arrow-right me-2"></i>Log Out</a>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="header-wrap-clone"></div>
		</header> -->


		<!--Hero
		============================================= -->
		<section id="slider" class="slider-element">
			<div id="sidebarMain" class="sidebar">
				<div id="addSchedule" class="sidebar">
					<a href="#" id="addScheduleBtn"
						class="bg-primary sidebar button button-rounded px-5 button-text-effect button-text-flip-x">
						<div class="button-inner">
							<span><i class="bi-plus-circle"></i> 일정생성</span><span><i
								class="bi-plus-circle-fill"></i> 일정생성</span>
						</div>
					</a>
				</div>
				<div id="datepickerDiv" class="sidebar">
					<div id="datepicker"></div>
				</div>
				<div class="WhitespaceBlock">&nbsp;</div>
				<div id="scheduleSearchBox" class="input-group"
					style="display: flex">
					<input type="text" name="q" class="form-control" value=""
						placeholder="일정 검색">
					<div class="input-group-text">
						<i class="uil uil-search"></i>
					</div>
				</div>
				<div class="WhitespaceBlock">&nbsp;</div>
				<div id="scheduleAcc" class="sidebar">
					<nav class="nav-tree mb-0">
						<ul>
							<li><a href="#" class="calendarGroup">내 달력</a></i>
								<ul id="myCalGroup">
									<li><a href="#" id="addMyCalendarBtn"
										class="sidebar button button-rounded px-5 button-border button-text-effect button-text-flip-x">
											<div class="button-inner">
												<span><i class="bi-plus-circle"></i>달력추가</span> <span
													class=""><i class="bi-plus-circle-fill"></i>달력추가</span>
											</div>
									</a></li>
									<c:choose>
									    <c:when test="${empty sessionScope.id}">
									        <li><label class="checkbox-inline pointer">
									            <input class="filter" type="checkbox" value="달력1" checked>기본
									        </label></li>
									    </c:when>
									    <c:otherwise>
									        <c:forEach items="${sessionScope.calDTO}" var="dto">
									            <li class="item">
									            <div class="label-container">
										            <label class="checkbox-inline pointer">
										                <input class="filter" type="checkbox" value="${dto.calSeq}" checked name="${dto.calListSeq}"><span>${dto.name}</span>
										            </label>
									            </div>
									            <div class="icons-container">
											        <i class="fa-solid fa-pen-to-square pointer editMyCalendar"></i>
											        <i class="fa-solid fa-trash pointer delMyCalendar"></i>
											    </div>
										            <input type="hidden" name="token" value="${dto.token}">
											    </li>
									        </c:forEach>
									    </c:otherwise>
									</c:choose>
								</ul></li>
						</ul>
					</nav>
					<div class="WhitespaceBlock">&nbsp;</div>
					<nav class="nav-tree mb-0">
						<ul id="shareCalGroup">
							<li><a href="#" class="calendarGroup">공유받은 달력</a>
								<ul id="shareCalGroup">
									<li><a href="#" id="addShCalendarBtn"
										class="sidebar button button-rounded px-5 button-border button-text-effect button-text-flip-x">
											<div class="button-inner">
												<span><i class="bi-plus-circle"></i>달력추가</span> <span
													class=""><i class="bi-plus-circle-fill"></i>달력추가</span>
											</div>
									</a></li>
									<c:forEach items="${sessionScope.shareCalDTO}" var="dto">
							            <li class="item">
							            <div class="label-container">
								            <label class="checkbox-inline pointer">
								                <input class="filter" type="checkbox" value="${dto.calSeq}" checked name="${dto.calListSeq}"><span>${dto.name}</span>
								            </label>
							            </div>
							            <div class="icons-container">
									        <i class="fa-solid fa-trash pointer delShareCalendar"></i>
									    </div>
									    </li>
							        </c:forEach>
								</ul></li>
						</ul>
					</nav>
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
									<div id="color">
										<div class="color-circle" style="background-color: #F9B8D1"
											value="#F9B8D1"></div>
										<div class="color-circle" style="background-color: #FFCF81"
											value="#FFCF81"></div>
										<div class="color-circle" style="background-color: #FDFFAB"
											value="#FDFFAB"></div>
										<div class="color-circle" style="background-color: #A8D8EA"
											value="#A8D8EA"></div>
										<div class="color-circle" style="background-color: #DBC4F0"
											value="#DBC4F0"></div>
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
								<div class="form-check form-switch" id="allDayBox">
									<input class="form-check-input" type="checkbox"
										id="allDayCheck" checked> <label
										class="form-check-label" for="allDayCheck">하루종일</label>
								</div>
							</div>
							<div class="mb-3">
								<label for="eventModalStart" class="col-form-label">일정
									시작</label> <input type="datetime-local" id="eventModalStart"
									class="form-control" placeholder="datetime-local input"
									disabled onchange="validateEndDate('eventModal')">
							</div>
							<div class="mb-3">
								<label for="eventModalEnd" class="col-form-label">일정 종료</label>
								<input type="datetime-local" id="eventModalEnd"
									class="form-control" placeholder="datetime-local input"
									disabled>
							</div>
							<div class="mb-3">
								<label for="eventModalSelect" class="col-form-label">카테고리</label>
								<select class="form-select" aria-label="Default select example"
									id="eventModalSelect">
									<c:choose>
									    <c:when test="${empty sessionScope.id}">
									    <option selected>카테고리</option>
									    	</c:when>
									    <c:otherwise>
									        <c:forEach items="${sessionScope.calDTO}" var="dto">
									        	<option value="${dto.calSeq}">${dto.name}</option>
									        </c:forEach>
									    </c:otherwise>
									</c:choose>
								</select>
							</div>
							<div class="mb-3">
								<label for="eventModalLoc" class="col-form-label">장소</label> <input
									type="text" class="form-control" id="eventModalLoc">
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


	<!-- loginStart -->

	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-body">
						<div class="card-body p-5">
							<form id="login-form" name="login-form" class="mb-0" action="/plan/user/login.do"
								method="post">
								<h1 class="fs-4 fw-semibold text-center mb-0">Sign In to	Planiverse</h1>
								<h2 class="fs-5 text-center fw-medium mt-1">
									<span class="op-06 nocolor">New?</span> <a href="#"
										id="signup-action">회원가입</a>
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
												class="fw-semibold text-smaller">아이디/비밀번호 찾기</a>
										</div>
										<input type="password" id="login-form-password"
											name="login-form-password" value=""
											class="form-control not-dark">
									</div>

									<div class="col-12 form-group mb-0">
										<button
											class="btn btn-lg text-white bg-primary h-bg-color d-block w-100 m-0"
											id="login-form-submit" name="login-form-submit" value="login">Login</button>
									</div>
								</div>
							</form>

							<div class="divider divider-center">
								<div class="divider-text text-dark lh-base">or Login with</div>
							</div>

							<div class="text-center">
								<a href="#"
									class="btn d-block mx-0 mb-3 btn-light border d-flex align-items-center justify-content-center" id="googleLoginNow"><img
									src="https://cdn.cdnlogo.com/logos/g/35/google-icon.svg"
									alt="Google Logo" class="d-inline-block me-2 square square-xs">Sign
									In with Google</a> <a href="javascript:loginWithKakao()"
									class="btn d-block mx-0 mb-3 btn-light border d-flex align-items-center justify-content-center" id="kakaoLoginA"><img
									id="kakao-login-btn"
									src="https://oopy.lazyrockets.com/api/v2/notion/image?src=https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Ff5d7b9d3-6faa-4fbd-92fb-abc13883f4ac%2Fkakao.png&blockId=845a0760-d543-46ae-965d-018c4289eb32&width=256"
									class="d-inline-block me-2 square square-xs">Sign In with
									Kakao</a> 
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
								<h3>회원가입</h3>

								<form id="register-form" name="register-form" class="row mb-0"
									action="/plan/user/register.do" method="post">

									<div class="col-12 form-group">
										<label for="register-form-email">Email(ID):</label> <input
											type="text" id="id" name="id" class="form-control" required>
										<br>
										<button class="btn text-white bg-primary h-bg-color d-block w-25 m-0" id="idCheck"
											name="idCheck" disabled>중복 검사</button>
									</div>

									<div class="col-12 form-group">
										<label for="register-form-password">비밀번호:</label> <input
											type="password" id="pw" name="pw" class="form-control"
											required disabled>
									</div>

									<div class="col-12 form-group">
										<label for="register-form-repassword">비밀번호 확인:</label> <input type="password" id="repw" name="repw"
											class="form-control" required disabled>
									</div>

									<div class="col-12 form-group">
										<label for="register-form-name">이름:</label> <input
											type="text" id="name" name="name" class="form-control"
											required>
									</div>

									<div class="col-12 form-group">
										<button class="btn text-white bg-secondary h-bg-color d-block w-25 m-0"
											type="submit" id="registerBtn" name="registerBtn"
											value="register" disabled>회원가입</button>
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
								<div class="container">
									<div id="editColor">
										<div class="color-circle" style="background-color: #F9B8D1"
											value="#F9B8D1"></div>
										<div class="color-circle" style="background-color: #F1932E"
											value="#F1932E"></div>
										<div class="color-circle" style="background-color: #FFFFD2"
											value="#FFFFD2"></div>
										<div class="color-circle" style="background-color: #A8D8EA"
											value="#A8D8EA"></div>
										<div class="color-circle" style="background-color: #AA96DA"
											value="#AA96DA"></div>
									</div>
									<p>
										선택한 색: <span id="editSelColor"></span>
									</p>
								</div>
							</div>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">제목</label> <input
									type="text" class="form-control" id="editEventModalTitle">
							</div>

							<div class="mb-3">
								<label class="form-check-label" for="editEventModalAllDay">
									<input class="form-check-input" type="checkbox"
									id="editEventModalAllDay" name="editEventModalAllDay">
									하루종일
								</label>
							</div>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">일정 시작</label>
								<input type="datetime-local" id="editEventModalStart"
									class="form-control" placeholder="datetime-local input"
									onchange="validateEndDate('editEventModal')">
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

	<!-- CategoryModal -->
	<div class="modal fade" id="CategoryModal" tabindex="-1"
		aria-labelledby="editModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="addCategoryModalHeader">달력</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">달력 이름</label> <input
									type="text" class="form-control" id="CategoryModalTitle">
							</div>
							<div class="mb-3" id="ShareTokenDiv">
								<label for="recipient-name" class="col-form-label">공유용 토큰</label> <input
									type="text" class="form-control" id="ShareTokenOutput" readonly>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"	data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="deleteCategoryBtn">달력삭제</button>
						<button type="button" class="btn btn-primary" id="editCategoryBtn">달력수정</button>
						<button type="button" class="btn btn-primary" id="addCategoryBtn">달력생성</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- ShareModal -->
	<div class="modal fade" id="ShareModal" tabindex="-1"
		aria-labelledby="editModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modalBackground">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="ShareModalHeader">일정 공유받기</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form>
							<div class="mb-3">
								<label for="recipient-name" class="col-form-label">공유용 토큰</label> <input
									type="text" class="form-control" id="ShareTokenInput" placeholder="토큰을 입력해주세요.">
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"	data-bs-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="ShareBtn">공유받기</button>
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
		<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.9/index.global.min.js"></script>
	<script src="${path}/resources/js/plugins.min.js"></script>
	<script src="${path}/resources/js/functions.bundle.js"></script>
	<script src='${path}/resources/js/index.global.js'></script>
	<script src='${path}/resources/js/index.global.min.js'></script>
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.1/kakao.min.js"
		integrity="sha384-kDljxUXHaJ9xAb2AzRd59KxjrFjzHa5TAoFQ6GbYTCAG0bjM55XohjjDT7tDDC01"
		crossorigin="anonymous"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script>
	
		
		var coll = document.getElementsByClassName("collapsible");
		var sidebar = document.getElementsByClassName("sidebar");
		var addScheduleBtn = document.getElementById("addScheduleBtn");
		var i;
		var calendar;
		// ajax 중복 방지
		var delRequest = null;
		var addRequest = null;
		var editRequest = null;
		var sendRequest = null;
		
		Kakao.init('264eac61bfebe8b0add3dd5814946507'); // 사용하려는 앱의 JavaScript 키 입력
		Kakao.isInitialized();
		console.log(Kakao.isInitialized());
		
		//필터 함수
		function checkFilters() {
		    $('.filter').each(function() {
		        var seq = $(this).val();
		        if ($(this).is(':checked')) {
		            $('.' + seq).parent('div').show();
		        } else {
		            $('.' + seq).parent('div').hide();
		        }
		    });
		}
		$(document).on('change', '.filter', function () {
		    checkFilters();
		});
		$(document).on('click', 'button', function () {
			checkFilters();
		});

		
		function loginWithKakao() {
			Kakao.Auth.login({
				success: function (authObj) {
				  console.log(authObj); //access토큰 값
				  getInfo();
				},
				fail: function (err) {
				  console.log(err);
				},
			  });
			}
		function getInfo() {
	        Kakao.API.request({
	          url: "/v2/user/me",
	          success: function (res) {
	            console.log(res);
	            var id = res.id;
	            var name = res.kakao_account.profile.nickname;
				var email = res.kakao_account.email;
				
				sendUserInfo(name, email);
				
	            localStorage.setItem("name", name);
	            localStorage.setItem("id", id);
	            localStorage.setItem("email", email);
	            console.log(name);
	            console.log(id);
				console.log(email);
		
	          },
	          fail: function (error) {
	            alert("카카오 로그인 실패" + JSON.stringify(error));
	          },
	        });
	      }
		


	  
		function getCookie(name) {
		  var parts = document.cookie.split(name + '=');
		  if (parts.length === 2) { return parts[1].split(';')[0]; }
		}
		
		
	 	 function logout() {
		    Kakao.API.request({
		        url: '/v1/user/unlink',
		    })
		    .then(function (response) {
		        console.log(response); 
		    })
		    .catch(function (error) {
		        console.log(error); 
		    });

		    $.ajax({
		        url: '/plan/user/logout.do',
		        type: 'POST',
		        success: function() {
		            sessionStorage.clear(); 
		            localStorage.clear(); 
					location.reload(true);

		            //location.href = '/plan/planiverse.do';
		        },
		        error: function(xhr, status, error) {
		            console.error('Error:', status, error); 
		        }
		    });
		}  

		
		
			 

			  function deleteCookie() {
			    document.cookie = 'authorize-access-token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
			   
			  }
		
		
		//사이드바 토글
		var sidebarStatus = true;
		var sidebarMain = document.getElementById("sidebarMain");
		var addSchedule = document.getElementById("addSchedule");
	    var sidebarFoldingBtn = document.getElementById('sidebarFoldingBtn');

		//카테고리 모달
		const categoryModal = new bootstrap.Modal(document.getElementById('CategoryModal'));

		//공유 모달
		const shareModal = new bootstrap.Modal(document.getElementById('ShareModal'));

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

		document.getElementById('addCategoryBtn').addEventListener('click', function() {
		    const calendarName = $('#CategoryModalTitle').val();
		    console.log(calendarName);
		    if (calendarName == '') return;
		    const filters = document.querySelectorAll('#myCalGroup .filter');
		    const eventModalSelect = document.getElementById('eventModalSelect');
		    const option = document.createElement('option');
		    let listSeq = '';
		    for (let i = 0; i < filters.length; i++) {
		        const parentLabel = filters[i].parentElement;
		        if (filters[i].getAttribute('name') != null) {
		            listSeq = filters[i].getAttribute('name');
		        }
		        if (parentLabel.textContent.trim() == calendarName) {
		            alert('이미 존재하는 이름입니다.');
		            event.preventDefault();
		            return;
		        }
		    }
		    $.ajax({
		        type: "post",
		        url: "/plan/calendar/add.do",
		        data: {
		            name: calendarName,
		            listSeq: listSeq
		        },
		        dataType: 'json',
		        success: function (response) {
		            const newListItem = document.createElement('li');
		            newListItem.classList.add('item');
		            const labelContainer = document.createElement('div');
		            labelContainer.classList.add('label-container');
		            const newLabel = document.createElement('label');
		            newLabel.classList.add('checkbox-inline', 'pointer');
		            const newCheckbox = document.createElement('input');
		            newCheckbox.classList.add('filter');
		            const newSpan = document.createElement('span');
		            newSpan.appendChild(document.createTextNode(calendarName));
		            newCheckbox.type = 'checkbox';
		            newCheckbox.value = response.calSeq;
		            newCheckbox.name = listSeq;
		            newCheckbox.checked = true;
		            newLabel.appendChild(newCheckbox);
		            newLabel.appendChild(newSpan);
		            labelContainer.appendChild(newLabel);
		            const iconsContainer = document.createElement('div');
		            iconsContainer.classList.add('icons-container');
		            const editIcon = document.createElement('i');
		            editIcon.classList.add('fa-solid', 'fa-pen-to-square', 'pointer', 'editMyCalendar');
		            const deleteIcon = document.createElement('i');
		            deleteIcon.classList.add('fa-solid', 'fa-trash', 'pointer', 'delMyCalendar');
		            iconsContainer.appendChild(editIcon);
		            iconsContainer.appendChild(deleteIcon);
		            newListItem.appendChild(labelContainer);
		            newListItem.appendChild(iconsContainer);
		            const token = document.createElement('input');
		            token.type = 'hidden';
		            token.name = 'token';
		            token.value = response.token;
                    newListItem.appendChild(token);
		            document.getElementById('myCalGroup').appendChild(newListItem);
		            option.value = response.calSeq;
		            option.appendChild(document.createTextNode(calendarName));
		            eventModalSelect.append(option);
		        },
		        error: function(a, b, c) {
		            console.log(a, b, c);
		        }
		    });

		    categoryModal.hide();
		    $('#CategoryModalTitle').val();
		});
		
		document.getElementById('ShareBtn').addEventListener('click', function() {
		    const token = document.getElementById('ShareTokenInput').value;
		    if (token == '') return;
		    $.ajax({
		        type: "post",
		        url: "/plan/calendar/shareadd.do",
		        data: {
		        	token: token
		        },
		        dataType: 'json',
		        success: function (response) {
		            
		        },
		        error: function(a, b, c) {
		            console.log(a, b, c);
		        }
		    });
		    window.location.href = 'planiverse.do';
		});

		
		
	document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	var addScheduleBtn = document.getElementById('addScheduleBtn');

	var loginBtn = document.getElementById('login-action');
	var signupBtn = document.getElementById('signup-action');

	var modalElement = document.querySelector('.modal');
	var exampleModal = document.getElementById('event');
	
	var googlelogin = document.getElementById('googleLoginNow');
	
	googlelogin.onclick = function(){
		
		signIn();
	}
	
	addScheduleBtn.addEventListener('click', function() {
		 var modal = new bootstrap.Modal(eventProduceModal);
		 //allDayBox.style.display = 'none';
		 document.getElementById('eventModalStart').removeAttribute('disabled');
		 document.getElementById('eventModalEnd').removeAttribute('disabled');  
		 $('#eventProduceModal input, textarea').val('');
		 $('#selected-color').css("background-color", "transparent");
		 modal.show();
		 $("#btnEventProduce").off('click').click(function () {
			addEvent();
		 	modal.hide();
		 });
		 //var start = document.getElementById('eventstart')
	});

	var loginModal = document.getElementById('loginModal');
	function login() {
		var modal = new bootstrap.Modal(loginModal);
		modal.show();
		var signupModal = document.getElementById('signupModal');
		var kakaoModalClose = document.getElementById('kakaoLoginA');
		signupBtn.addEventListener('click',function(){
			modal.hide();
			var modal1 = new bootstrap.Modal(signupModal);
			modal1.show();
		})
		 kakaoModalClose.addEventListener('click', function(){
			modal.hide();
		}) 
	} 	
	// 윈도우 크기 변경, 사이드바 토글 이벤트 리스너 추가
	// 사이드바 및 내부 요소 조정 함수
    function adjustSidebar() {
    	// 브라우저 내부 사이즈 저장
        const windowWidth = window.innerWidth;
    	// 사이드바 너비 저장
        var sidebarWidth;
    	// 사이드바 높이 저장
        const sliderHeight = windowWidth < 1000 ? "auto" : "calc(100vh - 30px)";
    	// 사이드바 토글시의 버튼 간격(일정 생성과 이전달버튼) 저장
        const marginLeft = sidebarStatus ? "0" : "100px";
		
    	// 사이드바 너비 적용
        if (sidebarStatus) {
            sidebarWidth = (windowWidth >= 1000) ? "250px" : "100%";
        } else {
            sidebarWidth = (windowWidth >= 1000) ? "0" : "100%";
        }

        $('#sidebarMain').css({
            display: (windowWidth < 1000) ? "block" : "",
            width: sidebarWidth
        });
    	// 사이드바 높이 적용
        $('#slider').css("height", sliderHeight);
    	// 버튼 간격 적용
        $('.fc-toolbar-chunk').css("margin-left", marginLeft);
		
    	// 사이드바 토글시 내부 요소들 감추기/보이기
        $('#sidebarMain').children().not('#addSchedule').css("display", sidebarStatus ? "" : "none");
    }
	
    // 사이드바 토글 함수
    function toggleSidebar() {
        sidebarStatus = !sidebarStatus;
        adjustSidebar();
        calendar.render();checkFilters();
    }
	
    // 브라우저 크기 변경시 사이드바 조정함수 실행
    window.addEventListener('resize', adjustSidebar);
    // 사이드바 토글버튼 클릭시 토글 함수 실행
    $('#sidebarFoldingBtn').on('click', toggleSidebar);
	
    // 사이트 로딩 직후 사이드바 크기 조정
    adjustSidebar();
    // 사이트 로딩 후 css 후처리
    $('.calendarGroup').css({
        display: "flex",
        paddingRight: "10px"
    }).children().css("marginLeft", "auto");

    /* $('.button-border').css({
        border: "0",
        background: "none"
    }); */
    $('#addShCalendarBtn').click(function () {
		$('#ShareTokenInput').val('');
		shareModal.show();
	});
	
	$('#addMyCalendarBtn').click(function () {
		$('#CategoryModalTitle').val('');
		$('#addCategoryBtn').show();
		$('#ShareTokenDiv').hide()
		$('#deleteCategoryBtn').hide();
		$('#editCategoryBtn').hide();
		categoryModal.show();
	});
	
	$(document).on('click', '.editMyCalendar', function () {
		
		$('#editCategoryBtn').show();
		$('#addCategoryBtn').hide();
		$('#deleteCategoryBtn').hide();
		$('#ShareTokenDiv').show();
		
		var item = $(this).closest('.item');
		var calSeq = item.find('input.filter').val();
		var name = item.find('span').text().trim();
		var token = item.find('input[name="token"]').val();
		console.log(token);
		
		$('#CategoryModalTitle').val(name);
		$('#ShareTokenOutput').val(token);
		categoryModal.show();
		
		$('#editCategoryBtn').click(function () {
			name = $('#CategoryModalTitle').val();
			$.ajax({
                url: '/plan/calendar/edit.do',
                type: 'POST',
                data: {
                	calSeq: calSeq,
                	name: name
                },
                success: function(response) {
                	$('#CategoryModalTitle').val();
                	$('#eventModalSelect option[value="' + calSeq + '"]').text(name);
                	item.find('span').text(name);
					categoryModal.hide();
                },
                error: function(xhr, status, error) {
                }
            });
		});
		
	});
	
	$(document).on('click', '.delMyCalendar', function () {
		var item = $(this).closest('.item');
		var calSeq = item.find('input.filter').val();
		if (confirm('삭제하시겠습니까?')) {
            $.ajax({
                url: '/plan/calendar/del.do',
                type: 'POST',
                data: { calSeq: calSeq },
                success: function(response) {
                    item.remove();
                    $('#eventModalSelect option[value="' + calSeq + '"]').remove();
                },
                error: function(xhr, status, error) {
                }
            });
        }
	});
	
	$(document).on('click', '.delShareCalendar', function () {
		var item = $(this).closest('.item');
		var calSeq = item.find('input.filter').val();
		if (confirm('삭제하시겠습니까?')) {
            $.ajax({
                url: '/plan/calendar/sharedel.do',
                type: 'POST',
                data: { calSeq: calSeq },
                success: function(response) {
                    item.remove();
                    $("."+calSeq).remove();
                },
                error: function(xhr, status, error) {
                }
            });
        }
	});
	
 $("#login-form-submit").on('click', function() {
	 	var modal = new bootstrap.Modal(loginModal);

			modal.hide();
}); 

	//수정 모달 하루종일 버튼 제어
	$('#editEventModalAllDay').change(()=>{
		if($('#editEventModalAllDay').is(':checked')){
			var clickedDate = $('#editEventModalStart').val();
            var momentClickedDate = moment(clickedDate);
			$('#editEventModalStart').val(momentClickedDate.format('YYYY-MM-DDT00:00'));
			$('#editEventModalEnd').val(momentClickedDate.add(24, 'hours').format('YYYY-MM-DDT00:00'));
			$('#editEventModalStart').attr("disabled",true); 
			$('#editEventModalEnd').attr("disabled",true); 
		} else {
			$('#editEventModalStart').attr("disabled",false); 
			$('#editEventModalEnd').attr("disabled",false); 
		}		
	});
		
	//생성 모달 하루종일 버튼 제어
	$('#allDayCheck').change(()=>{
		if($('#allDayCheck').is(':checked')){
			var clickedDate = $('#eventModalStart').val();
            var momentClickedDate = moment(clickedDate);
			$('#eventModalStart').val(momentClickedDate.format('YYYY-MM-DDT00:00'));
			$('#eventModalEnd').val(momentClickedDate.add(24, 'hours').format('YYYY-MM-DDT00:00'));
			$('#eventModalStart').attr("disabled",true); 
			$('#eventModalEnd').attr("disabled",true); 
		} else {
			$('#eventModalStart').attr("disabled",false); 
			$('#eventModalEnd').attr("disabled",false); 
		}		
	});
    calendar = new FullCalendar.Calendar(calendarEl, {
    	
    	googleCalendarApiKey: 'AIzaSyCYi2s4BmKlnYWFKvDq1yfl7oUFmXSxiHc',

    	eventSources: [

    	{
    		googleCalendarId: 'ko.south_korea#holiday@group.v.calendar.google.com'
    	}
    	],
			
    	//이벤트 클릭시 수정 모달 생성
		eventClick: function(info) {
			info.jsEvent.cancelBubble = true;
			info.jsEvent.preventDefault();
			
			var container = document.getElementById("editEventModal");
			var modal = new bootstrap.Modal(container);

			if(info.event.allDay ==true){
				$('#editEventModalAllDay').prop('checked',true);
				$('#editEventModalStart').attr("disabled",true); 
				$('#editEventModalEnd').attr("disabled",true); 
			} else {
				$('#editEventModalAllDay').prop('checked',false);
				$('#editEventModalStart').attr("disabled",false); 
				$('#editEventModalEnd').attr("disabled",false); 
			}
			$('#editEventModalStart').val(moment(info.event.start).format('YYYY-MM-DDTHH:mm'));
			$('#editEventModalEnd').val(moment(info.event.end).format('YYYY-MM-DDTHH:mm'));
			$('#editEventModalTitle').val(info.event.title);
			$('#editSelColor').css("background-color", "transparent");
			$('#editSelColor').css("background-color", info.event.backgroundColor);
			$('#editEventModalLoc').val(info.event.extendedProps.loc);
			$('#editEventModalContent').val(info.event.extendedProps.content);
			
        	modal.show();

			$('#deleteEventBtn').off('click').click(function() {
				if(window.confirm('일정을 삭제하시겠습니까?')){
					// 중복 실행 방지
					if (delRequest !== null) {
					    delRequest.abort();
					}

					  // ajax 요청 생성
					delRequest = $.ajax({
		   	      		type: "post",
		   	      		url: "/plan/event/delete.do",
		   	      		data: {
		   	      			eventSeq: info.event.extendedProps.eventSeq
		   	      		},
		   	      		dataType: 'json',
		   	      		success: function (response) {
		   	      			if(response.result ==1){
								info.event.remove();
		   	      			} else if (response.result ==0){
				   	    		alert('로그인 후 이용 가능합니다.');
				   	    	}
		   	      		},
		   	      		error: function(a,b,c){
		   					console.log(a,b,c);
		   				}
		   	    	});
				} 
				modal.hide();
			});
			
			
			$('#editEventBtn').off('click').click(function() {
				if(confirm('일정을 수정하시겠습니까?')){
					// 중복 실행 방지
					if (editRequest !== null) {
						editRequest.abort();
					}
					var titleValue =  $('#editEventModalTitle').val();
					var locValue = $('#editEventModalLoc').val();
		   			var contentValue = $('#editEventModalContent').val();
				
					  // ajax 요청 생성
					editRequest =  $.ajax({
				  		type: "post",
				   		url: "/plan/event/change.do",
				   		data: {
				   			eventSeq: info.event.extendedProps.eventSeq,
				   			allDay: $('#editEventModalAllDay').is(':checked'),
				   			title: titleValue,
				   			start: moment($('#editEventModalStart').val()).format('YYYY/MM/DD HH:mm'), 
				   			end: moment($('#editEventModalEnd').val()).format('YYYY/MM/DD HH:mm'), 
				   			color: $('#editColor').attr("value"),
				   			loc: locValue,
				   			content: contentValue
				   	    },
				   	    dataType: 'json',
				   	    success: function (response) {
				   	    	if(response.result ==1){
				   	    	console.log(titleValue);
				   	    	info.event.setProp('title', titleValue);
				   	    	info.event.setAllDay($('#editEventModalAllDay').is(':checked'));
				   	    	info.event.setStart($('#editEventModalStart').val());
				   	    	info.event.setEnd($('#editEventModalEnd').val());
				   	    	info.event.setProp('color', $('#editColor').attr("value"));
				   	    	info.event.setExtendedProp('loc', locValue);
				   	    	info.event.setExtendedProp('content', contentValue);
				   	    	} else if (response.result ==0){
				   	    		alert('로그인 후 이용 가능합니다.');
				   	    	}
				   	    },
				   	    error: function(a,b,c){
				   			console.log(a,b,c);
				   		}
				   	});
				}
				$('#editEventModal input, textarea').val('');
				modal.hide();
			});

		},
		//이벤트 드롭으로 일정 수정
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
	   	      		dataType: 'json',
	   	      		success: function (response) {
	   	      			if(response.result ==1){
	   	        			alert('수정 완료');
	   	      			} else if (response.result ==0){
			   	    		alert('로그인 후 이용 가능합니다.');
			   	    	}
	   	      		},
	   	      		error: function(a,b,c){
	   					console.log(a,b,c);
	   				}
	   	    	});
	   		  } else {
	   			  info.revert();
	   		  }
	   	  },
	   	//이벤트 hover 시 팝오버창 생성
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
        		}).append('<strong>시간:</strong> ' + getDisplayEventDate(info.event) + '<br>')
        .append('<strong>내용:</strong> ' + info.event.extendedProps.content+ '<br>')
        .append('<strong>장소:</strong> ' + info.event.extendedProps.loc),
			trigger: 'hover',
			placement: 'top',
			html: true,
			container: 'body'
			});
		},
		
		//날짜 선택 시 일정 추가 모달 생성 + 이벤트 등록
		dateClick: function(info) {
			var clickedDate = info.date;
			var momentClickedDate = moment(clickedDate);
			var container = document.getElementById("eventProduceModal");
			var modal = new bootstrap.Modal(container);
			var allday = document.getElementById("allDayCheck");

			// 설정된 날짜 및 시간 설정
			var formattedDateTimeStart = momentClickedDate.format('YYYY-MM-DD HH:mm:ss');
			var formattedDateTimeEnd = moment(momentClickedDate).add(24, 'hours').format('YYYY-MM-DD HH:mm:ss');
			document.getElementById("eventModalStart").value = formattedDateTimeStart;
			document.getElementById("eventModalEnd").value = formattedDateTimeEnd;
			$('#eventProduceModal input[type=text], textarea').val('');
			$('#selected-color').css("background-color", "transparent");
			modal.show();
			$("#btnEventProduce").off('click').click(function () {
				addEvent();
			 	modal.hide();
			 });
		},
		
	  eventAdd: function (addInfo) {
	      console.log("eventAdd");
	  }, 
      select: function(info) {
        
      },
	  locale: 'ko',
      headerToolbar: {
        left: 'toggleButton prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay loginButton'
      },
      customButtons: {
    	    loginButton: {
    	    	<c:if test="${empty id}">
    	      text: 'Login',
    	      click: function() {
    	        login();
    	      }
			</c:if>
			<c:if test="${not empty id}">
		      text: 'Logout',
		      click: function() {
		        logout();
		      }
			</c:if>
    	    },
    	    toggleButton: {
    	      icon: 'chevrons-left',
    	      hint: '사이드바 접기',
    	      click: function() {
    	    	toggleSidebar();
    	      }
    	    }
    },
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      selectable: true,
      dayMaxEvents: true,
      events: [
		<c:if test="${not empty id}">
    	  $.ajax({
     			type: 'get',
     			url: '/plan/event/list.do',
     			dataType: 'json',
     			success: function(result){
     				result.forEach(obj =>{
     					calendar.addEvent({
     						classNames: obj.calSeq,
     						title: obj.title,
     						allDay: (obj.allDay == 'y'? true: false),
     						start: obj.start,
     						end: obj.end,
     						color: obj.color,
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
		</c:if>
      ] 
    });
    calendar.render();checkFilters();
  });
	
	function getDisplayEventDate(event) {
		var displayEventDate;
		
		if(event.end == null) {
			displayEventDate = moment(event.start).format('HH:mm');
		} else if (moment(event.start).format('MM-DD')==moment(event.end).format('MM-DD')) {
			  var startTimeEventInfo = moment(event.start).format('HH:mm');
			  var endTimeEventInfo = moment(event.end).format('HH:mm');
			  displayEventDate = startTimeEventInfo + " - " + endTimeEventInfo;
		} else if ((moment(event.start).format('MM-DD')==moment(event.end-1).format('MM-DD'))&& event.allDay==true) {
			displayEventDate = "하루종일";
		} else if (moment(event.start).format('MM-DD')!=moment(event.end).format('MM-DD')) {
		  var startEventInfo = moment(event.start).format('MM/DD');
		  var endEventInfo = moment(event.end).format('MM/DD');
		  displayEventDate = startEventInfo + " - " + endEventInfo;
		} 
		return displayEventDate;
		}
	
		document.getElementById('allDayCheck').addEventListener('change', function() {
	    
	    	if(this.checked){
	        	document.getElementById("eventModalStart").disabled = true;
	        	document.getElementById("eventModalEnd").disabled = true;
	    	}else{
	        	document.getElementById("eventModalStart").disabled = false;
	        	document.getElementById("eventModalEnd").disabled = false;
	    	}
		});
	
		function validateEndDate(modal) {
		    var startDate = document.getElementById(modal+"Start").value;
			document.getElementById(modal+"End").setAttribute("min", startDate);
		    /* var endDate = document.getElementById("eventModalEnd").value;

			if (startDate && endDate) {
			    if (startDate > endDate) {
			        alert("일정 종료일은 시작일 이후여야 합니다."); }}*/
		}
		
				
		function addEvent() {
			// 중복 실행 방지
			if (addRequest) {
				addRequest.abort();
			}

			// ajax 요청 생성
			console.log($('#eventModalSelect').val());
			addRequest = $.ajax({
				type: 'post',
				url: '/plan/event/add.do',
				data: {
					allDay: $('#allDayCheck').is(':checked'),
					title: $('#eventModalTitle').val(),
					start: $('#eventModalStart').val(), 
					end: $('#eventModalEnd').val(),
					color: $('#color').attr("value"),
					loc: $('#eventModalLoc').val(),
					content: $('#eventModalContent').val(),
					calSeq: $('#eventModalSelect').val()
				},
				dataType: 'json',
				success: function(result){
					calendar.addEvent({
						title: $('#eventModalTitle').val(),
						allDay: $('#allDayCheck').is(':checked'),
						start: $('#eventModalStart').val(),
						end: $('#eventModalEnd').val(),
						color: $('#color').attr("value"),
						extendedProps: {
							eventSeq: result.eventSeq,
							loc: $('#eventModalLoc').val(),
							content: $('#eventModalContent').val()
						}
					});
					calendar.render();checkFilters();
				},
				error: function(a, b, c){
					console.log(a, b, c);
				}
			});
		}
		
		//회원가입창 생성시 input 값 비우기
		$('#signup-action').click(function() {
			$('#signupModal input').val('');
		});
		
		//비밀번호 동일한지 검사
		$('#repw').keyup(function () {
			if($('#pw').val()!=$('#repw').val()){
				$('#registerBtn').attr("disabled",true); 
			} else if ($('#pw').val()==$('#repw').val()){
				$('#registerBtn').attr("disabled",false); 
			}
		});
		
		//id 다시 수정하면 블록
		$('#id').keyup(function () {
			$('#pw').attr("disabled",true); 
			$('#repw').attr("disabled",true);
			$('#registerBtn').attr("disabled",true); 
			$('#idCheck').attr("disabled",false); 
		});
		
		//id 중복검사
		$('#idCheck').click(function () {
			$.ajax({
				type: 'post',
				url: '/plan/user/idcheck.do',
				data: {
					id: $('#id').val()
				},
				success: function(result){
					if(result==0){
						alert('사용 가능한 Email(ID)입니다.');
						$('#pw').attr("disabled",false); 
						$('#repw').attr("disabled",false); 
					} else {
						alert('이미 사용중인 Email(ID)입니다.');
						$('#id').val('').focus();
					}
				},
				error: function(a, b, c){
					console.log(a, b, c);
				}
			});
		});
		
		//선택한 색 표시
		$('#color > div').click(function(){
			$('#selected-color').css("background-color", $(event.target).attr("value"));
			$('#color').attr("value", $(event.target).attr("value"));
		});
		
		$('#editColor > div').click(function(){
			$('#editSelColor').css("background-color", $(event.target).attr("value"));
			$('#editColor').attr("value", $(event.target).attr("value"));
		});
		
		function signIn() {
            let oauth2Endpoint = "https://accounts.google.com/o/oauth2/v2/auth";
            let form = document.createElement('form');
            form.setAttribute('method', 'POST'); 
            form.setAttribute('action', oauth2Endpoint);

            let params = {
                "client_id": "76388159425-rve73ghc872aj4c9qfh6pqbohg14inr8.apps.googleusercontent.com",
                "redirect_uri": "http://localhost:8090/plan/planiverse.do",
                "response_type": "token",
                "scope": "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/calendar https://www.googleapis.com/auth/calendar.events https://www.googleapis.com/auth/calendar.events.readonly https://www.googleapis.com/auth/calendar.readonly https://www.googleapis.com/auth/calendar.settings.readonly",
                "include_granted_scopes": 'true',
                "state": 'pass-through value'
            };

            for (var p in params) {
                let input = document.createElement('input');
                input.setAttribute('type', 'hidden');
                input.setAttribute('name', p);
                input.setAttribute('value', params[p]);
                form.appendChild(input);
            }

            document.body.appendChild(form);
            form.submit();
            localStorage.setItem('checkAuthPending', 'true');
        }
		
		window.addEventListener('load', function() {
		    // 로컬 스토리지 값을 체크하여 checkAuth 호출
		    if (localStorage.getItem('checkAuthPending') === 'true') {
		        checkAuth();
		        localStorage.removeItem('checkAuthPending'); // 실행 후 값 제거
		    }
		});

		function checkAuth() {
            let params = {};
            let regex = /([^&=]+)=([^&]*)/g, m;
            while (m = regex.exec(location.href)) {
                params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
            };

            if (Object.keys(params).length > 0) {
                localStorage.setItem('authInfo', JSON.stringify(params));
               window.history.pushState({}, document.title, "/" + "plan/planiverse.do");
                showProfile();
            } else {
                let storedInfo = localStorage.getItem('authInfo');
                if (storedInfo) {
                    showProfile();
                }
            }
        }

        
        const colors = [
        	  "#FEBE8C",
        	  "#B1BD71",
        	  "#21A796",
        	  "#8AD7A5",
        	  "#6DC5D1"
        	];
        
        var colorIndex = 0;

        
        function showProfile() {
    		let info = JSON.parse(localStorage.getItem('authInfo'));
    		console.log(info['access_token']);
    		console.log(info['expires_in'])
    		fetch("https://www.googleapis.com/oauth2/v3/userinfo", {
    			headers: {
    				"Authorization": `Bearer \${info['access_token']}`
    			}
    		})
    		.then((data) => data.json())
    		.then((info) => {
    			console.log(info.email)
    			sendUserInfo(info.name, info.email);
    		});
    	
    		fetch("https://www.googleapis.com/calendar/v3/users/me/calendarList/", {
    			headers: {
    				"Authorization": `Bearer \${info['access_token']}`
    			}
    		})
    		.then((data) => data.json())
    		.then((info) => {
    			calendarNow = info.items.map(item => {
    				console.log(item.description)
    				if(item.id != 'ko.south_korea#holiday@group.v.calendar.google.com'){
    					calendar.addEventSource({
        	                googleCalendarId: item.id, 
        	                color: colors[colorIndex], 
        	                classNames: [item.summary, item.description]
        	            });
    					 colorIndex = (colorIndex + 1); 
        	            calendar.render();checkFilters();
    				}
    	            
    	        });
    			
    		});
    	}

       /*  function logout() {
            let info = JSON.parse(localStorage.getItem('authInfo'));
            fetch("https://oauth2.googleapis.com/revoke?token=" + info['access_token'], {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            })
            .then((data) => {
                localStorage.removeItem('authInfo');
                location.href = "http://localhost:8090/plan/planiverse.do";
            });
        } */

        
         
        function sendUserInfo(name, email) {
        	// 중복 실행 방지
			if (sendRequest) {
				sendRequest.abort();
			}

			// ajax 요청 생성
			sendRequest = $.ajax({
      	    type: 'POST',
      	    url: '/plan/user/socialLogin.do', // Change this to your actual server endpoint
      	    data: ({
      	      name: name,
      	      email: email
      	    }),
      	    success: function(response) {
      	      console.log("로그인 성공");
      	    location.reload();
      	      checkFilters(); 
      	    },
      	    error: function(xhr, status, error) {
      	      console.error("Failed to send user info:", error);
      	    }
      	  });
			
      	}
		
	</script>
</body>
</html>
