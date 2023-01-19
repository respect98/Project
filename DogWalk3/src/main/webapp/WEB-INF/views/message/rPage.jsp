<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
								<li class="nav-item"><a class="nav-link active show"
									onclick="location.href='rPage.bit'" data-toggle="tab"> <!-- <i class="material-icons">face</i> -->
										<i class="material-icons">email</i> 받은 쪽지함
										</a></li>
								<li class="nav-item"><a class="nav-link" onclick="location.href='sPage.bit'"
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
							<!---------- 받은쪽지함 ------------------>

							<div class="table-responsive">
								 <form action="delete.bit" method="get">
								<table class="table">
								    <thead class="text-primary">
								   	 	<tr>
								             <th class="checkbox"><input type="checkbox" id="ck_all">전체선택</th>
								        </tr>
								        <tr>
								   	 		<th class="text-center">체크박스</th>
											<th class="text-center">쪽지번호</th>
											<th class="text-center">보낸사람</th>
											<th class="text-center">내용</th>
											<th class="text-center">받은 날짜</th>
											<th class="text-center">읽은 날짜</th>
										</tr>
								        
								    </thead>
								     <c:forEach var="message" items="${messageList}" >
								    <tbody id="message">
								        
								       <tr data-tr_value="${message.msindex}"> 
								            <td><input type="checkbox" name="msindexes" value="${message.msindex}"></td>
								            <td class="text-center">${message.msindex}</td>
											<td class="text-center">${message.suserid}</td>
											  <td class="text-center" style="cursor:pointer" onclick="location.href='detail.bit?msindex=${message.msindex}'">${message.content}</td> 
											<!--  <td  data-toggle="modal" data-target="#deleteModal" >${message.content}</td> -->
											<!-- <td data-toggle="modal" data-target="#deleteModal" onClick="$('#createFormId').modal('show')">${message.content}</td> --> 
											<td class="text-center">${message.sendtime}</td>
											<td class="text-center">${message.readtime!=null?message.readtime:'읽지않음'}</td>
								        </tr>
								         
								        
								    </tbody>
								    </c:forEach> 
								</table>
								<button type="submit" class="btn btn-primary" style="padding: 10px 20px"><b>삭제</b></button>
 								<!--  
 								<label for="bmd-label-static">삭제</label> 			
								<input type="button" id="delete" placeholder="삭제">-->
								</form>
								
								
								<!-- 페이징 -->
			<div class="pagination justify-content-center">
			<!-- <nav aria-label="Page navigation example" style="display: none;" id="pagingNav"> -->
					<ul class="pagination" id="pagingview">
						<c:if test="${cpage > 1}">
							<li class="page-item"><a class="page-link"
								href="rPage.bit?cp=${cpage-1}&ps=${pageSize}"
								cp="${cpage-1}" ps="${pageSize}" aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
						</c:if>

						<c:forEach var="i" begin="1" end="${pageCount}" step="1">
							<c:choose>
								<c:when test="${cpage==i }">
									<li class="page-item active"><a class="page-link"
										href="rPage.bit?cp=${i}&ps=${pageSize}" cp="${i}"
										ps="${pageSize}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="rPage.bit?cp=${i}&ps=${pageSize}" cp="${i}"
										ps="${pageSize}">${i}</a></li>
								</c:otherwise>
							</c:choose>

						</c:forEach>

						<c:if test="${cpage < pageCount}">

							<li class="page-item">
								<a class="page-link" href="rPage.bit?cp=${cpage+1}&ps=${pageSize}"
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
	
	
	<!-- Modal -->

<!-- Modal -->
	

	<!-- side_overlay end -->
	<%@ include file="/WEB-INF/include/footer.jsp"%>

<script>
<!-- Modal에서 삭제 -->



<!-- Modal에서 삭제 --> 

$(document).ready(function(){
    //체크박스 전체 선탣&해제
    $('#ck_all').click(function(){
         if($("#ck_all").prop("checked")){
            $("input[type=checkbox]").prop("checked",true); 
        }else{
            $("input[type=checkbox]").prop("checked",false); 
        }
    });
});	
	

$('#delete').click(function(){
	
	let con = confirm("정말로 삭제하시겠습니까?");
	if(con){
		return location.href='delete.bit?msindex=${message.msindex}';
	}else{
		return;
	}
});


</script>

</body>
</html>

