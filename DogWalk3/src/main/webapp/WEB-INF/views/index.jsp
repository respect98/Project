<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- basic -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- mobile metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<!-- site metas -->
<title>팀 사랑해요~</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<!-- bootstrap css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!-- style css -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- Responsive-->
<link rel="stylesheet" href="css/responsive.css">
<!-- fevicon -->
<link rel="icon" href="images/fevicon.png" type="image/gif" />
<!-- Scrollbar Custom CSS -->
<link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
<!-- Tweaks for older IEs-->
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<!-- owl stylesheets -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css"
	media="screen">

</head>
<body>
	<c:set var="myctx" value="${pageContext.request.contextPath}" />
	<!-- section banner start -->
	<div class="header_section">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4">
					<div>
						<a href="index.html"><img src="images/logo.png"></a>
					</div>
				</div>
				<div class="col-md-8">
					<div class="menu_text">
						<ul>
							<c:if test="${member eq null }">
								<li class="last"><a href="${myctx}/member/login">로그인</a> /
									<a href="${myctx}/member/register">회원가입</a>
							</c:if>
							<c:if test="${ member ne null }">
								<li class="last"><a href="${myctx}/member/logout">${member.userid}님 로그아웃</a>
									/ <a href="${myctx}/member/memberUpdate">마이페이지</a> / <a
									href="${myctx}/message/msgRePage.bit">나의 메세지함</a> 
									<c:if test="${ member.status eq 99 }">
					        	/ <a href="${myctx}/admin/main">관리자 페이지</a>
									</c:if></li>
							</c:if>
							<li class="last"><a href="#"><img
									src="images/search-icon.png"></a></li>
							<li class="active">
								<div id="myNav" class="overlay">
									<a href="javascript:void(0)" class="closebtn"
										onclick="closeNav()">&times;</a>
									<div class="overlay-content">
										<a href="/index">Home</a> <a href="${myctx}/NewFile1">병원지도</a> 
										<!-- <a href="gallery.html">산책</a> -->
											<a href="${myctx}/comanimal/animal_boardlist">돌봄</a> <a
											href="${myctx}/management/main.bit">반려동물 등록</a> <a
											href="${myctx}/chatList.bit">채팅</a> <a
											href="${myctx}/qna/main">QNA</a> 
											<%-- <a href="${myctx}/second-hand">중고 게시판</a> <a
											href="${myctx}/animal-care">양육 게시판</a> <a
											href="${myctx}/board/list">문의 게시판</a> --%>
									</div>
								</div> <span style="font-size: 30px; cursor: pointer"
								onclick="openNav()">&#9776; 메뉴</span>
					</div>
					</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="banner_main">
			<div class="container">
				<div class="ram">
					<div class="row">
						<div class="col-sm-12">
							<h1 class="taital">Team Love~ Project</h1>
							<p class="consectetur_text">1인가구 반려동물 매칭플랫폼</p>
							<div class="banner_bt">
								
							</div>
						</div>
					</div>
				</div>
				<div class="box">
					<h1></h1>
				</div>
			</div>
		</div>
	</div>
	<!-- section footer start -->
	<div class="section_footer">
		<div class="container">
			<div class="mail_section">
				<ul>
					<li class="footer-logo"><img src="images/logo.png"></li>
					<li class="footer-logo"><img src="images/map-icon.png"><span
						class="map_text">㈜멀티캠퍼스 : 서울특별시 강남구 언주로 508 14층(역삼동, 서울상록빌딩)</span></li>
					<li class="footer-logo"><img src="images/call-icon.png"><span
						class="map_text">(+82) 9876543210</span></li>
					<li class="footer-logo"><img src="images/email-icon.png"><span
						class="map_text">Dog@gmail.com</span></li>
				</ul>
			</div>
			<div class="social-icon">
				<div class="row">
					<div class="col-sm-12">
						<div class="icons">
							<ul>
								<li><a href="#"><img src="images/fb-icon.png"></a></li>
								<li><a href="#"><img src="images/twitter-icon.png"></a></li>
								<li><a href="#"><img src="images/google-icon.png"></a></li>
								<li><a href="#"><img src="images/linkedin-icon.png"></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="copyright">
				2023 Happy New Year! <a href="https://html.design">
					Team Love Project</a>
			</div>
		</div>
	</div>
	</div>
	<!-- section footer end -->

	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/plugin.js"></script>
	<!-- sidebar -->
	<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
	<script src="js/custom.js"></script>
	<!-- javascript -->
	<script src="js/owl.carousel.js"></script>
	<script
		src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".fancybox").fancybox({
				openEffect : "none",
				closeEffect : "none"
			});

			$(".zoom").hover(function() {

				$(this).addClass('transition');
			}, function() {

				$(this).removeClass('transition');
			});
		});
	</script>


	<script>
		function openNav() {
			document.getElementById("myNav").style.width = "100%";
		}

		function closeNav() {
			document.getElementById("myNav").style.width = "0%";
		}
	</script>

</body>
</html>