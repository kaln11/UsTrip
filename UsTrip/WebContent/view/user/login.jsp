<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- Bootstrap Core CSS -->
    <!-- <link href="../../css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="/css/main.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
     
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.scrolly.min.js"></script>
	<script src="/js/skel.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>
    
    <!-- ///////////////////////카카오 및 구글로그인 연동/////////////////////////////// -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	
 	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<script src="https://apis.google.com/js/api:client.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<!-- <script src="https://www.googleapis.com/plus/v1/activities/z12gtjhq3qn2xxl2o224exwiqruvtda0i?key=874013762845-1vbc3sib3cn2fapfgg734rjjj4suktt1.apps.googleusercontent.com"></script>
	 -->
	<!-- ///////////////////////// Modal popup  및 달력UI /////////////////////////////// -->
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
	// 카카오톡 로그인버튼 처리
	 Kakao.init('84c5f6e23594b89cadfb9500086ba376');
	 function loginWithKakao() {
	      // 로그인 창을 띄웁니다.
	      Kakao.Auth.login({
	        success: function(authObj) {
	          var accessToken = Kakao.Auth.getAccessToken();
	          Kakao.Auth.setAccessToken(accessToken);
	          
	          Kakao.API.request({
	        	  url: '/v1/user/me',
	        	  success: function(res) {
	        		  var userId = res.kaccount_email;
	        		  var tempId = userId.split(".");
	        		  console.log("userId :: " + userId);
	        		  console.log("tempId :: " + tempId);
	        		  /* 타계정 로그인 시 처음방문한 사람인지 확인 */
	        		  
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
			    				   				 
			    				 if( JSONData.result ) {
			    					 self.location="/view/user/login.jsp?kakao=0&tempId="+tempId;	
			    				 } else {
			    					 self.location="/user/getUser?userId="+userId;						 
			    				 }	
		    			 	}
		    		 });	
	        	  }	//success:(res) close	        	  
	          });
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	    };
//	client_id: '874013762845-1vbc3sib3cn2fapfgg734rjjj4suktt1.apps.googleusercontent.com',
//240419245572-40o3ev6lrtro935gb6aqvske3rv8toah.apps.googleusercontent.com

//google
		$( function() {
		    $(".buttonText").on("click" , function() {
				gapi.load('auth2', function() {
					auth2 = gapi.auth2.init({
					/* 	url: '/plus.login' */
					client_id: '240419245572-40o3ev6lrtro935gb6aqvske3rv8toah.apps.googleusercontent.com',
					cookiepolicy: 'single_host_origin',
					fetch_basic_profile: false,
					scope: 'profile'
		  		});
		
				// Sign the user in, and then retrieve their ID.
				auth2.signIn().then(function() {
				    console.log("dd :: " + auth2.currentUser.get().getId());
				    console.log("ss :: " + auth2.currentUser.get().getBasicProfile());
				    
				    var profile = auth2.currentUser.get().getBasicProfile();
				    console.log('Email: ' + profile.getEmail());
				    
				    var userId = profile.getEmail();
	        		var tempId = userId.split(".");
	        		
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
			    				   				 
			    				 if( JSONData.result ) {	//처음방문자일경우
									self.location="/view/user/login.jsp?google=1&tempId="+tempId;
			    				 } else {	//이미 등록된 회원일경우
			    					 self.location="/user/getUser?userId="+userId;					 
			    				 }	
		    			 	}
		    		 });	
				  });
				});
			});
		}); 
	    
		//로그인 Event처리
		$( function() {
			
			$("#userId").focus();
			
			$("#signin").on("click" , function() {
				var id=$("input:text").val();
				var pw=$("input:password").val();
				
				if(id == null || id.length <1) {
					alert('ID를 입력하지 않으셨습니다.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('패스워드를 입력하지 않으셨습니다.');
					$("#password").focus();
					return;
				} 
				
				$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
			});							
		});			
		
		// 회원원가입화면이동
		$( function() {
			$("#joinus").on("click" , function() {
				self.location = "/user/addUser"
			});
			
			$("#findPwd").on("click" , function() {
				self.location="/view/user/findPwd.jsp";
			});

		});
		
	</script>		
	
	<style>
	#extraUserInfo> .form-group{
			margin-bottom:-5px;
		}  

		#dialog-form { 
			display:none;

			overflow-x:hidden;
                            overflow-y:auto;
                            position:relative;
		 }  
    	input.text { width:60%; padding: .4em; }
		.validateTips { border: solid transparent; padding: 0.3em; color:red; }
		
		#customBtn {
			display: inline-block;
			background: white;
			color: #444;
			width: 180px;
			border-radius: 5px;
			border: thin solid #888;
			box-shadow: 1px 1px 1px grey;
			white-space: nowrap;
		}
		#customBtn:hover {
			cursor: pointer;
		}
		span.label {
			font-family: serif;
			font-weight: normal;
		}
		span.icon {
			background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat;
			display: inline-block;
			vertical-align: middle;
			width: 42px;
			height: 42px;
		}
		span.buttonText {
			display: inline-block;
			vertical-align: middle;
			padding-left: 25px;
			padding-right: 42px;
			font-size: 13px;
			font-weight: bold;
			/* Use the Roboto font that is loaded in the <head> */
			font-family: 'Roboto', sans-serif;
		}
/* 
///// */

@media (min-width: 768px) {
    .omb_row-sm-offset-3 div:first-child[class*="col-"] {
        margin-left: 25%;
    }
}

.omb_login .omb_authTitle {
    text-align: center;
	line-height: 300%;
}
	
.omb_login .omb_socialButtons a {
	color: white; // In yourUse @body-bg 
	opacity:0.9;
}
.omb_login .omb_socialButtons a:hover {
    color: white;
	opacity:1;    	
}
.omb_login .omb_socialButtons .omb_btn-facebook {background: #3b5998;}
.omb_login .omb_socialButtons .omb_btn-twitter {background: #00aced;}
.omb_login .omb_socialButtons .omb_btn-google {background: #c32f10;}

.omb_login .omb_loginOr {
	position: relative;
	font-size: 1.5em;
	color: #aaa;
	margin-top: 1em;
	margin-bottom: 1em;
	padding-top: 0.5em;
	padding-bottom: 0.5em;
}
.omb_login .omb_loginOr .omb_hrOr {
	background-color: #cdcdcd;
	height: 1px;
	margin-top: 0px !important;
	margin-bottom: 0px !important;
}
.omb_login .omb_loginOr .omb_spanOr {
	display: block;
	position: absolute;
	left: 50%;
	top: -0.6em;
	margin-left: -1.5em;
	background-color: white;
	width: 3em;
	text-align: center;
}			

.omb_login .omb_loginForm .input-group.i {
	width: 2em;
}
.omb_login .omb_loginForm  .help-block {
    color: red;
}
	
@media (min-width: 768px) {
    .omb_login .omb_forgotPwd {
        text-align: right;
 	}		
}

	</style>
</head>
<body>
	<jsp:include page="/common/toolbar.jsp"/>
        <div class="top-content" align="center">
            <div class="inner-bg">
                <div class="container">
                
					<div class="omb_login">
						<div class="row omb_row-sm-offset-3 omb_socialButtons">
							<div class="col-xs-6 col-sm-6">
								<a id="kakao-login-btn" href="javascript:loginWithKakao()">
									<img src="/images/layout/kakao_account_login_btn_medium_narrow_ov.png" >
										<span class="hidden-xs"></span>
								</a>
							</div>
<!-- 	        
     	<div class="col-xs-4 col-xs-2" style="width:180px;">
		 </div>  -->
<!-- 		    
		    	 <div class="col-xs-4 col-sm-2">
				   	<div id="gSignInWrapper">
						<div id="customBtn" class="customGPlusSignIn" >
							<span class="icon"></span>
								<span class="buttonText" >구글로 로그인</span>
						</div>
					</div>
				<div id="name"></div>
	        </div> -->	
					</div>

					<div class="row omb_row-sm-offset-3 omb_loginOr">
						<div class="col-xs-12 col-sm-6">
							<hr class="omb_hrOr">
							<span class="omb_spanOr">or</span>
						</div>
					</div>

					<div class="row omb_row-sm-offset-3">
						<div class="col-xs-12 col-sm-6">	
						    <form class="omb_loginForm" action="" autocomplete="off" method="POST">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user"></i></span>
									<label class="sr-only" for="form-username">UserId</label>
			                 	<input type="text" name="userId" placeholder="UserId..." class="form-username form-control" id="userId">
								</div>
								<span class="help-block"></span>
													
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<label class="sr-only" for="form-password">Password</label>
			                 	<input type="password" name="password" placeholder="Password..." class="form-password form-control" id="password">
								</div>
			                    <span class="help-block"></span>
								
								<button type="submit" class="button" id="signin">로그인</button>
								<button type="button" class="button" id="joinus">회원가입</button>
							</form>
						</div>
					</div>
					<div class="row omb_row-sm-offset-3">
					<div class="col-xs-12 col-sm-3">
					</div>
					<div class="col-xs-12 col-sm-3">
						<p class="omb_forgotPwd">
							<a href="#"  id="findPwd">비밀번호찾기</a>
						</p>
					</div>
			</div>	  
		</div>
					<!-- <div class="row omb_row-sm-offset-3">
						<div class="col-xs-12 col-sm-3">
							<p class="omb_forgotPwd">
								<a href="#"  id="findPwd">비밀번호찾기</a>
							</p>
						</div>
					</div>	     -->	
				</div>
				<!--  ////////////////////// Modal Popup /////////////////////// -->
				<div id="dialog-form" title="추가정보 입력">
					<p class="validateTips">모든정보를 입력해주세요</p>											 
					<form id="extraUserInfo" action="/user/extraUserInfo" method="POST" class="form-horizontal">
						<div class="form-group">
							<label class="col-md-4 control-label" for="nickName" >닉네임</label>
							<div class="col-md-4">
								<input type="text" name="nickName" id="nickName" >
								<div id="checkNick" style="color:red; font-size:12px;">닉네임을 입력해주세요.</div>
							</div>
			    		</div><br>
			    		
		    			<div class="form-group">
			    			<label class="col-md-4 control-label" for="gender" >성별</label>
			    			<div class="col-md-3">
			    				남 <input type="radio" name="gender" id="gender" value="m" checked>
					      		여 <input type="radio" name="gender" id="gender" value="f" >
			    			</div>
			    		</div><br>										    											    		
			    		
			    		<div class="form-group">
			    			<label class="col-md-4 control-label" for="birthDate" >생년월일</label>
			    			<div class="col-md-3">
			    				<input type="text" name="birthDate" id="birthDate"  >
			    			</div>
			    		</div><hr>												  

						<div class="form-group">
							<label class="col-md-4 control-label" ></label>
							<div class="col-md-4">
								<input type="button" class="btn btn-info btn-sm" id="add" value="등록">
								<input type="button" class="btn btn-info btn-sm" id="cancle" value="취소">
							</div>
						</div>
						 	  
					 	 <input type="hidden" value="${param.tempId }" id="tempId" name="userId">
     					 <input type="hidden" value="${param.kakao}" id="kakao">
     					 <input type="hidden" value="${param.google}" id="google">
					  </form>
				</div>
                    <!--  ////////////////////// Modal Popup /////////////////////// -->
			</div>
		</div>
	</div>
        
</body>
</html>

<script type="text/javascript">
	
	var dialog, form;
	
	if($('#kakao').val() =="0" || $('#google').val() == '1' ) {
		var userId = $("#tempId").val();
		/* console.log("userId :: 가져오닝" + userId); */
		dialog = $('#dialog-form').dialog({			
			height: 400,
			width: 720, 
			modal: true,
			resizable:false
		});		
	}
	
	//가입 연결
	 $(function() {
		$( "#add" ).on("click" , function() {
			 document.forms["extraUserInfo"].submit(); 
		});
	});	
	
	//취소 연결
	$(function() {
		$("#cancle").on("click" , function() {
			$("form")[1].reset();
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
		    				   				 
		    				 if( JSONData.result ) {
		    					 $("#checkNick").html("사용가능한 닉네임입니다.").css('color','blue');
		    				 } else {
		    					 $("#checkNick").html("존재하는 닉네임입니다.").css('color','red');		    					 
		    				 }	
	    			 	}
	    		 });			
			});		
		});
	
	//달력 UI ///생년월일 입력에 맞게 년도 및 월 설정
	$(function() {
		$( "#birthDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: 'c-100:c+10',
			dateFormat: "yy-mm-dd"
		});
	});

</script>