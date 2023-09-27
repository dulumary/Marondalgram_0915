<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타임라인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>
	
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="contents d-flex justify-content-center">
			<!-- 타임라인  -->
			<div class="timeline">	
				<!-- 입력 상자 -->
				<div class="input-box border rounded">
					<textarea class="form-control border-0" rows="4" id="contentInput"></textarea>
					<div class="d-flex justify-content-between p-2">
						<input type="file" id="fileInput">
						<button type="button" class="btn btn-info btn-sm" id="addBtn">입력</button>
					</div>
				
				</div>		
				<!--  /입력상자 -->
				
				<!-- 카드 리스트 -->
				<div class="card-list">
					<c:forEach var="post" items="${postList }">
					<!-- 카드 -->
					<div class="card mt-4">
						<div class="d-flex justify-content-between p-2">
							<div>${post.loginId }</div>
							<i class="bi bi-three-dots-vertical"></i>
						</div>
						<div>
							<img width="100%" src="${post.imagePath }">
						</div>
						<div class="p-2">
							<i class="bi bi-heart"></i>
							좋아요 11개
						</div>
						<div class="p-2">
							<b>${post.loginId }</b> ${post.content }
						</div>
						
						<!-- 댓글 목록 -->
						<div class="comment-box">
							<div class="px-2">댓글</div>
							<div class="px-2">
								<div><b>marondal</b> 진짜 귀엽네요</div>
								<div><b>asdf</b> 우와 잘 찍었네요</div>
							</div>
							
							<div class="d-flex mt-3">
								<input type="text" class="form-control">
								<button type="button" class="btn btn-info">게시</button>
							</div>
						
						</div>
						<!-- /댓글 목록 -->
					</div>
					<!-- /카드 -->
					</c:forEach>
				
					
					
				</div>
				<!-- /카드 리스트 -->
					
					
				
			</div>
			<!-- /타임라인 -->
		
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" /> 
	
	</div>
	
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<script>
		$(document).ready(function() {
			$("#addBtn").on("click", function() {
				
				let content = $("#contentInput").val();
				let file = $("#fileInput")[0];
				
				if(content == "") {
					alert("내용을 입력하세요");
					return ;
				}
				
				// 파일에 대한 유효성 검사 
				if(file.files.length == 0) {
					alert("파일을 선택해 주세요");
					return ;
				}
				
				let formData = new FormData();
				formData.append("content", content);
				formData.append("imageFile", file.files[0]);
				
				$.ajax({
					type:"post"
					, url:"/post/create"
					, data:formData
					, enctype:"multipart/form-data"
					, processData:false
					, contentType:false
					, success:function(data) {
						if(data.result == "success") {
							location.reload();
						} else {
							alert("글쓰기 실패");
						}
						
					}
					, error:function() {
						alert("글쓰기 에러");
					}
				});
				
			});
		});
	
	</script>
	
</body>
</html>