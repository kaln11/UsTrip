<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

	<!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="/css/main.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
<!-- 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script> -->
      
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.scrolly.min.js"></script>
	<script src="/js/skel.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="/js/bootstrap-imageupload.js"></script>
	<link href="/css/bootstrap-imageupload.css" rel="stylesheet">
	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- 	<script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
	
	 $(function() {
		//가입 연결
		$( ".btn.btn-info" ).on("click" , function() {
			fncAddUser();
		});
		
		//취소 연결
		$("a[href='#' ]").on("click" , function() {
			$("form")[0].reset();
		});
		
	    var $imageupload = $('.imageupload');
	    $imageupload.imageupload();
	
	    $('#imageupload-disable').on('click', function() {
	        $imageupload.imageupload('disable');
	        $(this).blur();
	    })
	
	    $('#imageupload-enable').on('click', function() {
	        $imageupload.imageupload('enable');
	        $(this).blur();
	    })
	
	    $('#imageupload-reset').on('click', function() {
	        $imageupload.imageupload('reset');
	        $(this).blur();
	    }); 
	});	
	
	//달력 UI ///생년월일 입력에 맞게 년도 및 월 설정
	$(function() {
		$( "#birthDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: 'c-100:c+10',
			dateFormat: "yy-mm-dd",
			 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		        dayNames: ['일','월','화','수','목','금','토'],
		        dayNamesShort: ['일','월','화','수','목','금','토'],
		        dayNamesMin: ['일','월','화','수','목','금','토'],
		        showMonthAfterYear: true
		});
	});
	
	function fncAddUser() {
					
		var id=$("input[name='userId']").val();
		var nickname=$("input[name='nickName']").val();
		var pw=$("input[name='password']").val();
		var pw_confirm=$("input[name='password2']").val();
				
		//ID 및 닉네임 중복체크 처리해야함
		if(id == null || id.length <1){
			//아이디를 입력하지 않았을 경우
			alert("아이디는 반드시 입력하셔야 합니다.");
			return;
		}
		
		if(nickname == null || nickname.length <1){
			alert("닉네임은  반드시 입력하셔야 합니다.");
			return;
		}
		
		if(pw == null || pw.length <1){
			alert("비밀번호는  반드시 입력하셔야 합니다.");
			return;
		}
		if(pw_confirm == null || pw_confirm.length <1){
			alert("비밀번호 확인은  반드시 입력하셔야 합니다.");
			return;
		}

		$("form").attr("method" , "POST").attr("action" , "/user/addUser").submit();
	}
	
	//아이디 중복체크
	$(function(){
		
		$("#userId").on("keyup", function(){
			
			var userId = $("#userId").val();
			if(userId != "" && (userId.indexOf('@') < 1 || userId.indexOf('.') == -1) ){
				 $("#checkId").html("이메일형식이 아닙니다.");
			    	return;
			 }	
			var tempId = "";
			if(userId.indexOf('.') >= 0) {
				tempId = userId.split(".");
			}
						
			 $.ajax(
					 {
		    			 url : '/user/checkUserId/'+tempId,
		    			 method : "GET",
		    			 dataType : "json",
		    			 headers : {
		    				 "Accept" : "application/json",
		    				 "Content-Type" : "application/json"
	    				 },
		    			 context : this,
		    			 success : function(JSONData, status) {	
		    				  	    				 
		    				 if(! JSONData.result) {
		    					 $("#checkId").html("존재하는 아이디입니다.").css('color', 'red');
		    				 } else {
		    					 $("#checkId").html("사용가능한 아이디입니다.").css('color', 'blue');
		    				 }	
	    			 	}
	    		 });			
			});		
		});
	
	//닉네임 중복체크
	$(function(){
		
		$("#nickName").on("keyup", function(){
			
			var nickName = $("#nickName").val();
				
			 $.ajax(
					 {
		    			 url : '/user/checkNickName/'+nickName,
		    			 method : "GET",
		    			 dataType : "json",
		    			 headers : {
		    				 "Accept" : "application/json",
		    				 "Content-Type" : "application/json"
	    				 },
		    			 context : this,
		    			 success : function(JSONData, status) {		    				 
		    				   				 
		    				 if(! JSONData.result) {
		    					 $("#checkNick").html("존재하는 닉네임입니다.").css('color', 'red');;
		    				 } else {
		    					 $("#checkNick").html("사용가능한 닉네임입니다.").css('color', 'blue');;
		    				 }	
	    			 	}
	    		 });			
			});		
		});
	
	//비밀번호/ 비밀번호확인 입력 동일한지 체크
	$(function(){		
		
		$("#password").keyup( function(){
			$("#checkpw").text('');
		});
		
		$("#password2").keyup( function() {
			if( $("#password").val() != $("#password2").val() ) {
				$("#checkpw").text('');
				$("#checkpw").html("비밀번호가 일치하지 않습니다.").css('color', 'red');;
			} else {
				$("#checkpw").text('');
				$("#checkpw").html("비밀번호가 일치합니다.").css('color', 'blue');;
			}
		});
	});
	
	</script>

	<style type="text/css">
		.container{
			margin-top:75px;
		}
		.btn-primary.pull-right {
			padding: 3px 8px;
			height: 30px;
		}
		.ui-datepicker{ font-size: 12px; width: 300px; }
		.ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 12px; }
		.ui-datepicker select.ui-datepicker-year { width: 49%;}
	</style>

</head>
<body>
	<jsp:include page="/common/toolbar.jsp"/>
		<div class="container">
			<!-- <div class="row"> -->
				<form class="form-horizontal" enctype="multipart/form-data">
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="userId">아 이 디</label>	
						<div class="col-sm-4">		
							<input class="form-control input-md" type="text" id="userId" name="userId">
								<div id="checkId" style="color:red; font-size:12px;">이메일주소로 입력해주세요.</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="nickName">닉네임</label>
						<div class="col-md-4">				
							<input class="form-control" type="text" id="nickName" name="nickName">
								<div id="checkNick" style="color:red; font-size:12px;">닉네임을 입력해주세요.</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="password">비밀번호</label>	
						<div class="col-md-4">				
							<input class="form-control input-md" type="password" id="password" name="password">
						</div>
					</div>		
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="password2">비밀번호확인</label>	
						<div class="col-md-4">				
							<input class="form-control input-md" type="password" id="password2" name="password2">
								<div id="checkpw" style="color:red; font-size:12px;"></div>
						</div>
					</div>		
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="gender">성별</label>		
						<div class="col-md-4">			
							<input type="radio" id="gender" name="gender" value="m">남
							<input type="radio" id="gender" name="gender" value="f">여
						</div>
					</div>		
					
					<div class="form-group">
						<label class="col-md-4 control-label" for="birthDate">생년월일</label>	
						<div class="col-md-4">					
							<input class="form-control input-md" type="text" id="birthDate" name="birthDate" readonly>
						</div>		
					</div>	
					
					<!-- /////////////  프로필이미지등록  ////////////////////// -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="profileImage" >프로필이미지</label>	
						<div class="col-md-4">	  	
							<div class="imageupload panel panel-default">   		
								<div class="file-tab panel-body" align="center">                
   									<label class="btn btn-primary btn-file pull-right">                    
										<span>Browse</span>
										<input type="file" name="file">                                   
                    				</label>
                   					<button type="button" class="btn btn-primary pull-right">Remove</button><br/>                   
                				</div>
		            		</div>
					   	</div>
					</div>		
				
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-3 text-center">
							<input type="button" class="btn"  value="등록">
							<input type="button" class="btn" href="#" value="취소">
					</div>	
				</div>
										
			</form>
		<!-- </div> -->
	</div>
	
</body>
</html>