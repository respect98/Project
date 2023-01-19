<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<head>

	
	<%@ include file="/WEB-INF/include/import.jsp"%>
	<style type="text/css">
		
		/* @font-face {
			font-family: 'netmarbleM';
			src: url('../assets/fonts/netmarbleM.ttf') format('truetype'); 
		} */
		
		.area {
			box-shadow: rgba(0, 0, 0, 0.08) 0px 20px 40px 0px;
			padding-top: 50px;
			padding-bottom: 40px;
			text-align: center;
			background-color: #fafafa;
			border-radius: 6px;	
		}
		 
		.title-area {
			text-align: center;
			margin-bottom: 80px;
		}
		
		ul {
			list-style: none;
			padding: 0;
		}
		
		#signUpList li {
			width: 300px;
			height: 50px;
			margin-bottom: 6px;
			border: 1px solid #e0e0e0;
			border-radius: 3px;
			font-size: 14px;
		}
		
		.span {
			box-sizing: border-box;
			width: 200px;
			height: 40px;
			padding-top: 3px;
			display: inline-block;
			padding-top: 13px;
		}
		
		#signUpList-wrapper {
			/* margin: 0 auto; */
			display: inline-block;
		}
		
		.h3-korean {
			/* font-family : 'netmarbleM', sans-serif; */
			font-family: 'Noto Sans KR', sans-serif;
			/* font-family: 'Poor Story', cursive; */
			/* font-family: 'Nanum Myeongjo', serif; */
		}
		
	</style>

</head>

<body>
	
	
	
	<div class="container">
		<div class="side_overlay">
			<div class="row">
				<div class="col-12 title-area">
					
				</div>
				<div class="col-2"></div>
				<div class="col-8 area">
					<div id="signUpList-wrapper">
						<ul id="signUpList">
							<a href="join"><li><span class="span">일반 회원가입하기</span></li></a>
							<a href="${naver_url}"><li style="border: 1px solid #19ce60"><span class="span">네이버로 가입하기</span></li></a>
							
						</ul>
						<br>
						<a href="login" style="font-size: 13px;">이미 계정이 있으신가요?</a>
					</div>
				</div>
				<div class="col-2"></div>
				<br><br><br><br><br><br><br>
			</div> <!-- /.row -->
		</div> <!-- /.side_overlay -->
	</div> <!-- /.container  -->

	<%@ include file="/WEB-INF/include/footer.jsp"%>

</body>

</html>