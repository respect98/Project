<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>

<title>쪽지</title>

<%@ include file="/WEB-INF/include/import.jsp"%>


</head>

<body>




	<div class="side_overlay">
		<div class="container">


			<button class="btn btn-primary btn-round"
				onclick="location.href='rPage.bit'">쪽지</button>


			
			<div class="card card-nav-tabs">
				<div class="card-header card-header-primary">
					<!-- colors: "header-primary", "header-info", "header-success", "header-warning", "header-danger" -->
					<div class="nav-tabs-navigation">
						<div class="nav-tabs-wrapper">
							<ul class="nav nav-tabs" data-tabs="tabs">
								<li class="nav-item"><a class="nav-link "
									onclick="location.href='rPage.bit'" data-toggle="tab"> <!-- <i class="material-icons">face</i> -->
										<i class="material-icons">email</i> 받은 쪽지함
										</a></li>
								<li class="nav-item"><a class="nav-link active show" onclick="location.href='sPage.bit'"
									data-toggle="tab"> <!-- <i class="material-icons">chat</i> -->
										<i class="material-icons">email</i> 보낸 쪽지함
										</a></li>
								<li class="nav-item"><a class="nav-link" onclick="location.href='write.bit'"
									data-toggle="tab"> <!-- <i class="material-icons">build</i> -->
										<i class="material-icons">email</i> 쪽지 쓰기
										</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="card-body">
					<div class="tab-content text-center">
						<div class="tab-pane active show" id="">
							<!---------- 보낸쪽지함 ------------------>

							<div class="table-responsive">
								<table class="table">
									<thead class="text-primary">
										<tr>
											<th class="text-center">받은사람</th>
											<th class="text-center">내용</th>
											<th class="text-center">보낸 날짜</th>
											<th class="text-center">읽은 날짜</th>
										</tr>
									</thead>
									 <c:forEach var="message" items="${messageList}" >
									<tbody>
									
										<tr>
											<td class="text-center">${message.ruserid}</td>
											<td class="text-center">${message.content}</td>
											<td class="text-center">${message.sendtime}</td>
											<td class="text-center">${message.readtime!=null?message.readtime:'읽지않음'}</td>

										</tr>
										</tbody>
										</c:forEach>
								</table>
								
								<!-- 페이징 -->
			<div class="pagination justify-content-center">
			<!-- <nav aria-label="Page navigation example" style="display: none;" id="pagingNav"> -->
					<ul class="pagination" id="pagingview">
						<c:if test="${cpage > 1}">
							<li class="page-item"><a class="page-link"
								href="sPage.bit?cp=${cpage-1}&ps=${pageSize}"
								cp="${cpage-1}" ps="${pageSize}" aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
						</c:if>

						<c:forEach var="i" begin="1" end="${pageCount}" step="1">
							<c:choose>
								<c:when test="${cpage==i }">
									<li class="page-item active"><a class="page-link"
										href="sPage.bit?cp=${i}&ps=${pageSize}" cp="${i}"
										ps="${pageSize}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="sPage.bit?cp=${i}&ps=${pageSize}" cp="${i}"
										ps="${pageSize}">${i}</a></li>
								</c:otherwise>
							</c:choose>

						</c:forEach>

						<c:if test="${cpage < pageCount}">

							<li class="page-item">
								<a class="page-link" href="sPage.bit?cp=${cpage+1}&ps=${pageSize}"
									cp="${cpage+1}" ps="${pageSize}" aria-label="Next"> 
									<span aria-hidden="true">&raquo;</span>
									<span class="sr-only">Next</span>
								</a>
							</li>
						</c:if>
					</ul>
			<!-- </nav> -->
			</div>
			<!-- 여기까지 페이징처리 -->
							</div>

						</div>


						<!-------------- 끝 ---------------->


					</div>
				</div>
			</div>

		</div>
		<!-- container end -->
	</div>
	<!-- side_overlay end -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>

</body>
</html>

