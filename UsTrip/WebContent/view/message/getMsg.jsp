<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
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
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script src="https://use.fontawesome.com/04438b50a5.js"></script>
	
	<script type="text/javascript">
	
		//답장보내기
		$( function() {
			$(".btn.btn-info").bind("click", function() {
				var msgNo = $("#msgNo").val();
				alert("msgNo:: " + msgNo);
				self.location = "/message/sendMsg?msgNo="+msgNo; 
			});
			
			$("#confirm").bind("click", function() {
				history.go(-1);
			});
			
			$("#sendMsg").on("click", function() {
				self.location="/message/sendMsg";	
			});
			
			$("#listSend").on("click", function() {
				self.location="/message/listSendMsg";	
			});
			
			$("#listReceive").on("click", function() {
				self.location="/message/listReceivMsg";	
			});
			
		});
		
		$( function() {
			var msgContent =$("#hiddenVal").val();
			console.log("dd LL "  + msgContent);
			
		if( msgContent != null ){
				var mes = '${message.msgContent}'.replace("\r\n","<br/>");
				$("#msgContent").val(mes);
			};  
		});
	
	</script>
	
	<style>
		html, body {
			width: 100%;
			height:100%;
		}
	
		/* Profile container */
		.profile {
		  margin: 20px 0;
		}
		/* Profile sidebar */
		.profile-sidebar {
		  padding: 20px 0 10px 0;
		  background: #F1F3FA;
		  border-radius : 4px;
		  /* background: #fff; */
		}
		.profile-userpic img {
		  float: none;
		  margin: 0 auto;
		  width: 50%;
		  height: 120px;
		  -webkit-border-radius: 50% !important;
		  -moz-border-radius: 50% !important;
		  border-radius: 50% !important;
		}
		.profile-usertitle {
		  text-align: center;
		  margin-top: 20px;
		}
		.profile-usertitle-name {
		  color: #5a7391;
		  font-size: 16px;
		  font-weight: 600;
		  margin-bottom: 7px;
		}
		.profile-usertitle-job {
		  text-transform: uppercase;
		  color: #5b9bd1;
		  font-size: 12px;
		  font-weight: 600;
		  margin-bottom: 15px;
		}
		.profile-userbuttons {
		  text-align: center;
		  margin-top: 10px;
		}
		.profile-userbuttons .btn {
		  text-transform: uppercase;
		  font-size: 11px;
		  font-weight: 600;
		  padding: 6px 15px;
		  margin-right: 5px;
		}
		.profile-userbuttons .btn:last-child {
		  margin-right: 0px;
		}
		.profile-usermenu {
		  margin-top: 30px;
		}
		.profile-usermenu ul li {
		  border-bottom: 1px solid #f0f4f7;
		}
		.profile-usermenu ul li:last-child {
		  border-bottom: none;
		}
		.profile-usermenu ul li a {
		  color: #93a3b5;
		  font-size: 14px;
		  font-weight: 400;
		}
		.profile-usermenu ul li a i {
		  margin-right: 8px;
		  font-size: 14px;
		}
		.profile-usermenu ul li a:hover {
		  background-color: #fafcfd;
		  color: #5b9bd1;
		}
		.profile-usermenu ul li.active {
		  border-bottom: none;
		}
		.profile-usermenu ul li.active a {
		  color: #5b9bd1;
		  background-color: #f6f9fb;
		  border-left: 2px solid #5b9bd1;
		  margin-left: -2px;
		}
		
		button {
			color : white;
		}
	
	</style>
</head>

<body>

<jsp:include page="/common/toolbar.jsp"/>
<div class="container">
		<div class="col-md-3">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC -->
					<div class="profile-userpic">

						<c:if test="${ user.profileImage != null}">
							<img src="/images/upload/profile/${user.profileImage}" class="img-responsive" alt="">
						</c:if>
						<c:if test="${ user.profileImage == null}">
							<img class="img-responsive" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
						 </c:if> 
					</div>
					<!-- END SIDEBAR USERPIC -->
					<!-- SIDEBAR USER TITLE -->
					<div class="profile-usertitle">
						<div class="profile-usertitle-name">
							${user.nickName}
						</div>
						<div class="profile-usertitle-job">
							${user.userId}
						</div>
					</div>
				<!-- END SIDEBAR USER TITLE -->
				
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
					<ul class="nav">
						<li class="test active" id="sendMsg">
							<a>
							<i class="fa fa-plane" aria-hidden="true" ></i>
							쪽지보내기 </a>
						</li>
						<li class="test"  id="listSend" >
							<a>
							<i class="fa fa-heart" aria-hidden="true"></i>
							보낸 쪽지함</a>
						</li >
						<li class="test"  id="listReceive">
							<input type="hidden" id="userId" name="userId" value="${user.userId}">
							<a >
							<i class="fa fa-user-circle" aria-hidden="true" ></i>
							받은쪽지함 </a>
						</li>
					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		
		<div class="col-sm-9 col-md-9">
			
			<h3 >쪽지조회</h3><br>
			<form class="form-horizontal"> 
				<input type="hidden" id="msgNo" name="msgNo" value="${message.msgNo}"/>
					<div class="form-group">
					  <label class="ccol-md-3 col-lg-3 " align="center" for="sender">보낸사람</label>  
					  <div class="col-md-4">${message.sender}</div>
					</div>
		
					<!-- Text input-->
					<div class="form-group">
					  <label class="ccol-md-3 col-lg-3 " align="center" for="receiver">받는사람</label>  
					  <div class="col-md-4">${message.receiver}</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="ccol-md-3 col-lg-3 " align="center" for="sendDate">보낸날짜</label>  
					  <div class="col-md-4">${message.sendDate}</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group">
					  <label class="ccol-md-3 col-lg-3 " align="center" for="readDate">읽은날짜</label>  
					  <div class="col-md-4">${message.readDate}</div>
					</div>
					
					<!-- Text input-->
					<div class="form-group" style="height:180px;">
					  <label class="ccol-md-3 col-lg-3 " align="center" for="msgContent">글내용</label>  
					  <div class="col-md-4" id="msgContent" name="msgContent" > 
					  <input type="hidden" id="hiddenVal" value="${message.msgContent}">
					 	 ${message.msgContent }
					  </div>
					</div>
		
					<!-- Button -->
					<div class="form-group" align="center">
						<div class="col-md-8">	
							<input type="button" class="btn btn-info"  value="답장보내기">
							<input type="button" class="btn btn-info btn" id="confirm" value="확인">
						</div>
					</div>
				</form>
		</div>
</div>
</body>
</html>
