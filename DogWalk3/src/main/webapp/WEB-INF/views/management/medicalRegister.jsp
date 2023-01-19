<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>

	<title>병원이용 기록 등록</title>
	
	<%@ include file="/WEB-INF/include/import.jsp"%>
	<!-- 타임피커 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-material-datetimepicker/2.7.1/css/bootstrap-material-datetimepicker.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	
	<style>
		@font-face {
			font-family: 'netmarbleM';
			src: url('../assets/fonts/netmarbleM.ttf') format('truetype'); 
		}


		h1, h2, h3 {
			/* font-family : 'netmarbleM', sans-serif; */
			font-family: 'Noto Sans KR', sans-serif;
			/* color: #9c27b0; */
		}
				
		#joinForm-wrapper {
			display: inline-block;
			width: 428px;
		}

		#joinForm {
			margin-bottom: 0;
			text-align: center;
			margin-top: 25px;
		}
	</style>
	
	
	
</head>

<body>

	
	<div class="container">
		<div class="side_overlay">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8 area">
			<div class="card card-nav-tabs">
			<div class="card-body">
			<br>
			<div class="col-12 title-area">
					<h3 style=" text-align: center; margin-bottom:70px">병원이용이력 등록<i class="fas fa-paw"></i></h3>
					</div>
					
					
				<form id="joinForm" action="medicalRegister.bit" method="post" enctype="multipart/form-data">
				<div id="joinForm-wrapper">
								
								<div class="form-group bmd-form-group">
									<label for="bmd-label-static">보호자</label> 
									<input type="hidden" name="userid" class="form-control"  value="${sessionScope.member.userid}" readonly> 
									<input type="text" name="nick" class="form-control"  value="${sessionScope.member.nick}" readonly> 
								</div>
		
								<div class="form-group bmd-form-group text-left">
									<label for="bmd-label-static" for="petname" style="margin-bottom:15px">나의 반려동물</label>	
										<select class="custom-select"  name="petindex"> <!-- ex) ?mcategory=dog -->
											<c:forEach var="petName" items="${petNameList}">
											<option  value="${petName.petindex}">${petName.petname}</option>							
											</c:forEach>
										</select>		
								</div>
																	
								<div class="form-group bmd-form-group">
									<label for="bmd-label-static">병원 방문 시간</label>
									<fmt:parseDate var="parseTime" value="${mrecord.vdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									<fmt:formatDate var="vdate" value="${parseTime}" pattern="yyyy-MM-dd"/> 
									<input type="text" id="vdate" name="vdate" class="form-control" value=""> 
								</div>								
								<div class="form-group bmd-form-group">
								<label for="bmd-label-static" style="text-align: left;" >병원 방문 목적</label> 											
								<input type="text" name="vreason" class="form-control" value="">
								</div>	
																							
								<!-- <div id="donateColl"> -->															
								<div class="form-group bmd-form-group">
								<label for="bmd-label-static">방문병원 이름</label> 
								<input type="text" name="hname" class="form-control" value=""> 
								</div>
								<div style="margin:30px 0">					
									<button type="submit" class="btn btn-primary">등록</button>
								<!-- 	<button type="reset" class="btn btn-outline-primary">다시쓰기</button> -->
									<button type="reset" class="btn btn-outline-primary" onclick="location.href='main.bit'">취소</button>
								</div>
						</div>
					</form>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>

	<%@ include file="/WEB-INF/include/footer.jsp"%>
</body>
<script>
	
	$(function() {
		
		getMainCategory();
		makeAgeYear();
		makeAgeMonth();

		$('#mcategory').change(function() {
			if($('#mcategory').val() == '1') { // 1=개
				getSubCategory('dog');
			} else { // 2=고양이
				getSubCategory('cat');
			}
		});

		/* 타임피커  */
		//datetimepicker
		$("#vdate").bootstrapMaterialDatePicker({
			format: 'YYYY-MM-DD HH:mm',
			lang: 'ko',
			okText: '확인',
			cancelText: '취소'
		});

	});

	function getMainCategory() {

		$.ajax({
			type: "get",
			url: "getMainCategory.bit",
			dataType: 'json',
			cache:false,
			success: function(data) {
				var option = "<option disabled selected>-----선택하기-----</option>";
				$.each(data, function(i, item) {
					option += "<option value='" + item.mcategory + "'>" + item.mcaname + "</option>";
				});
				$('#mcategory').append(option);
			}
		});

	}

	function getSubCategory(item) {

		$.ajax({
			type: "get",
			url: "getSubCategory_" + item + ".bit",
			dataType: 'json',
			cache:false,
			success: function(data) {
				var option = "";
				$.each(data, function(i, item) {
					option += "<option value='" + item.scategory + "'>" + item.scaname + "</option>";
				});
				$('#scategory').empty().append(option);
			}
		});

	}
	
	function makeAgeYear() {

		var year = "<option disabled selected>-----선택하기-----</option>";
		for(var i=0; i<=30; i++) {
			year += "<option value='" + i + "'>" + i + "</option>";
		}
		$('#age_year').empty().append(year);
		
	}

	function makeAgeMonth() {
		
		var month = "<option disabled selected>-----선택하기-----</option>";
		for(var i=0; i<12; i++) {
			month += "<option value='" + i + "'>" + i + "</option>";
		}
		$('#age_month').empty().append(month);
	}

	
</script>
</html>