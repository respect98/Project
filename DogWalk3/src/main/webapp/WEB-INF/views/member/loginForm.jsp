<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>	

    <title>๋ก๊ทธ์ธ</title>
    <%@ include file="/WEB-INF/include/import.jsp"%>
     
    <style type="text/css">

		#area {
			/* box-shadow: rgba(0, 0, 0, 0.08) 0px 20px 40px 0px; */
			padding-top: 30px;
			padding-bottom: 40px;
			text-align: center;
			/* background-color: #fafafa; */
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

		/* #signUpList {
			text-align: center;
		}
		 */
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
			margin-top: 16px;
		}

		#login-btn {
			padding: 19px 0 26px 19px;
		}

		#socialLoginBox {
			text-align: center;
		}

		
	</style> 
     
</head>

<body>

	<%@ include file="/WEB-INF/include/headerAndNavi.jsp"%>

	<div class="container">
		<div class="side_overlay">
			<div class="row">
				<div class="col-4"></div>
				<div class="col-4" style="margin-top: 50px;">
					<div class="card card-login">
						<form class="form" id="loginform" method="post" action="${pageContext.request.contextPath}/login">
							<div class="card-header card-header-primary text-center" style="margin-bottom: 52px;">
								<h4 class="card-title">Login</h4>
							</div>
							<div class="card-body">
								<span class="bmd-form-group">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">
												<i class="material-icons">mail</i>
											</span>
										</div>
										<input type="text" class="form-control" placeholder="์์ด๋(์ด๋ฉ์ผ ์ฃผ์)" name="userid">
									</div>
								</span>
								<span class="bmd-form-group">
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">
												<i class="material-icons">lock_outline</i>
											</span>
										</div>
					                  	<input type="password" class="form-control" placeholder="๋น๋ฐ๋ฒํธ" name="pwd"/>
					                </div>
								</span>
								<!-- ๋ก๊ทธ์ธ ์คํจํ์ ๋ ๋จ๋ ์๋ด๋ฉ์์ง >> ์๋ธ-->
								<c:if test="${failedLogin != null}">
									<p style="color:red;">์์ด๋ ๋๋ ๋น๋ฐ๋ฒํธ๊ฐ ์๋ชป๋์์ต๋๋ค.</p><!-- ์คํ์ผ ๋์ค์ ์์?ํ? ๊ฒ -->
								</c:if>
								<div class="text-center" id="login-btn">
									<a href="../index"><button type="submit" class="btn btn-primary">๋ก๊ทธ์ธ</button></a>
							   </div>
							</div>
							<div id="socialLoginBox">
								<h6>๋๋</h6>
								<!-- <div id="area" class=""> -->
									<div id="signUpList-wrapper">
										<ul id="signUpList">
											<a href="${naver_url}"><li style="border: 1px solid #19ce60"><span class="span">๋ค์ด๋ฒ ์์ด๋๋ก ๋ก๊ทธ์ธ</span></li></a>
											<a href="${facebook_url}"><li style="border: 1px solid #385898"><span class="span">ํ์ด์ค๋ถ ์์ด๋๋ก ๋ก๊ทธ์ธ</span></li></a>
											<a href="${google_url}"><li style="border: 1px solid #2962ff"><span class="span">๊ตฌ๊ธ ์์ด๋๋ก ๋ก๊ทธ์ธ</span></li></a>
										</ul>
										<br>
									</div>
								<!-- </div> -->
							</div>
							
							
						</form>
									
					</div>
				</div>
				<div class="col-4"></div>
			</div> <!-- /.row -->
		</div> <!-- /.side_overlay -->
	</div> <!-- /.container -->


	<%@ include file="/WEB-INF/include/footer.jsp"%>
</body>
</html>