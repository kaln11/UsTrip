<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

	<!-- Bootstrap Core CSS -->
<!--     <link href="../../css/bootstrap.min.css" rel="stylesheet"> -->
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
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	
    <script type="text/javascript">
		//회원정보수정 Event 처리
		 $(function() {
			 $( ".btn.btn-info" ).on("click" , function() {
					self.location = "/user/withdrawUser?userId=${user.userId}";
			  });
		 });    
    </script>
	
	<style>
		.container{
			margin-top: 50px;
		}
	</style>
	
</head>
<body>
	<jsp:include page="/common/toolbar.jsp"/>
<div class="container">
	<h2 align="center">회원탈퇴</h2><br>
		<!-- <form class="form-horizontal" enctype="multipart/form-data"> -->
		<form class="form-horizontal">

		<div class="form-group">
		  <label class="col-md-4 control-label" for="userId">I D</label>  
		  <div class="col-md-4">
		  		${user.userId}
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="password">비밀번호</label>  
		  <div class="col-md-4">
		  	<input type="password" id="password" name="password" class="form-control input-md">
		  </div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-4 text-center">
				<button type="button" class="btn btn-info btn">회원탈퇴</button>
				<button type="button" class="btn btn-info" href="#">취소</button>
			</div>	
		</div>

		</form>
</div>

</body>
</html>