<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

	<!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="/css/main.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
     
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.scrolly.min.js"></script>
	<script src="/js/skel.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    <script type="text/javascript">
		
		//회원정보수정 Event 처리
		 $(function() {
			 $( ".btn.btn-info" ).on("click" , function() {
					self.location = "/user/updateUser?userId=${user.userId}"
			  });
		 });
		 $(function() {
			 $( ".btn.btn-info.btn" ).on("click" , function() {
				 $(self.location).attr("href","/message/sendMsg");
					/* self.location = "/message/sendMsg" */
			  });
		 });
		 
		 $(function() {
			 $( "#list" ).on("click" , function() {
				 self.location = "/message/listSendMsg"; 
			  });
		 });
		 
		 $(function() {
			 $( "#listUser" ).on("click" , function() {
				 $("form").attr("method" , "POST").attr("action" , "/user/getUserList").submit();
			  });
		 });
		 
		 $(function() {
			 $( "#withdrawUser" ).on("click" , function() {
				 var userId = $(this).val();
				self.location="/user/withdrawUser?userId="+userId;
			  });
		 });
	</script>
	
    <style>

    	.col-sm-8.col-md-8{
    		height:40px;
    	}
    	.profile-content {
    		min-height: 500px !important;
    	}
    	.col-md-9 {
    		min-height: 35px !important;
    		/* 안먹힌당 */
    	}
    	.pull-right {
    		margin-top: -29px;
    		margin-right: 10px;
    	}
    </style>
       
</head>
<body>
<jsp:include page="/common/toolbar.jsp"/>
<div class="container">
    <div class="row profile">
		<jsp:include page="/view/user/getProfile.jsp"/>
			<div class="col-md-9">
				<div class="profile-content">
					<div class="row">
						<div>
							<div class="profile-sidebar">
								<form class="form-horizontal">
									
									<div class="col-sm-offset-3 col-sm-8 ">
										<label class="col-md-3" for="userId">아이디</label>
										<div class="col-md-4">${user.userId}</div>
									</div>

									<div class="col-sm-offset-3 col-sm-8 ">
										<label class="col-md-3" for="nickName">닉네임</label>
										<div class="col-md-4">${user.nickName}</div>
									</div>

									<div class="col-sm-offset-3 col-sm-8 ">
										<label class="col-md-3" for="gender">성별</label>
										<div class="col-md-4">
											<%-- ${user.gender } --%>
											${user.gender == 'm' ? '남자' : '여자' }
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8 ">
											<label class="col-md-3" for="birthDate">생년월일</label>
											<div class="col-md-4">${user.birthDate}</div>
										</div>
									</div>
									<button type="button" id="withdrawUser"
										class="btn-sm pull-right" value="${user.userId }">회원탈퇴</button>	
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</div>

</body>
</html>