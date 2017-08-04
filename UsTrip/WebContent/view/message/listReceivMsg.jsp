<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 	<script src="https://use.fontawesome.com/04438b50a5.js"></script>
	
	<script type="text/javascript">
	
		function fncGetAllList(currentPage) {
			$("#currentPage").val(currentPage);		
			$("form").attr("method" , "POST").attr("action" , "/message/listReceivMsg").submit();
		}
		
		$(function() {
			$( ".content" ).on("click" , function() {
				var msgNo = $(this).next().val();
				var receiver = $("#receiver").val();
				 self.location ="/message/getMsg?msgNo="+msgNo+"&receiver="+receiver;
			});
		});
		
		$(function() {
			$("#sendMsg").on("click", function() {
				self.location="/message/sendMsg";	
			});
		});
		
		$(function() {
			$("#listSend").on("click", function() {
				self.location="/message/listSendMsg";	
			});
		});
		
		$(function() {		
			$("#listReceive").on("click", function() {
				self.location="/message/listReceivMsg";	
			});
		});
		
		$(function() {
			$( "#delete" ).on("click" , function() {
				fncDeleteMessage();
			});
		});

		function fncDeleteMessage() {
			$("form").attr("method" , "POST").attr("action" , "/message/deleteMsg").submit();
		}
	
	</script>
	
	<style type="text/css">
		#accordion .glyphicon { margin-right:10px; }
		.panel-collapse>.list-group .list-group-item:first-child {border-top-right-radius: 0;border-top-left-radius: 0;}
		.panel-collapse>.list-group .list-group-item {border-width: 1px 0;}
		.panel-collapse>.list-group {margin-bottom: 0;}
		.panel-collapse .list-group-item {border-radius:0;}
		.content{
			overflow: hidden; 
			text-overflow: ellipsis;
			white-space: nowrap; 
			width: 60px;
			height: 20px;
			display: inline-block;
		}
		
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
						<li class="test" id="sendMsg">
							<a >
							<i class="fa fa-plane" aria-hidden="true" ></i>
							쪽지보내기 </a>
						</li>
						<li class="test"  id="listSend" >
							<a>
							<i class="fa fa-heart" aria-hidden="true"></i>
							보낸 쪽지함</a>
						</li >
						<li class="test active"  id="listReceive">
							<input type="hidden" id="userId" name="userId" value="${user.userId}">
							<a>
							<i class="fa fa-user-circle" aria-hidden="true" ></i>
							받은쪽지함 </a>
						</li>
					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		
<div class="col-sm-9 col-md-9">
<table class="table">
	<tr>
		<td colspan="13" >전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" value="" ></td>
		<td class="ct_list_b" width="150">보낸사람</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">글내용</td>
		<td class="ct_line02"></td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">받은날짜</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">읽은날짜</td>	
	</tr>

	<c:set var="i" value="0"/>
	<c:forEach var="message" items="${list}">
		<c:set var="i" value="${i+1}"/>
		
		<c:if test="${ empty message.readDate }">
		<tr class="ct_list_pop" style="font-weight:bold">
			<td align="center"><input type="checkbox" name="chbox" value="${message.msgNo}" /></td>
			<input type="hidden" id="receiver" name="receiver" value="${message.receiver}">
			<td align="left">${message.sender}</td>
			<td></td>
			<td align="left">
				<span class="content" >${message.msgContent}</span>
				<input type="hidden" id="msgNo" name="msgNo" value="${message.msgNo}">
			</td>
			<td></td>
			<td></td>
			<td align="left">${message.sendDate}</td>
			<td></td>
			<c:set var="readDate" value="${message.readDate}"/>
					<c:if test="${ empty message.readDate}">
						<td align="left">읽지않음</td>
					</c:if>
			 <c:if test="${!empty message.readDate}">
				<td align="left">${message.readDate}</td>
			 </c:if>
			<td align="center"></td>
		</tr>
		</c:if>
			<c:if test="${ not empty message.readDate}">
			<tr class="ct_list_pop" >
			<td align="center"><input type="checkbox" name="chbox" value="${message.msgNo}" /></td>
			<input type="hidden" id="receiver" name="receiver" value="${message.receiver}">
			<td align="left">${message.sender}</td>
			<td></td>
			<td align="left">
				<span class="content" >${message.msgContent}</span>
				<input type="hidden" id="msgNo" name="msgNo" value="${message.msgNo}">
			</td>
			<td></td>
			<td></td>
			<td align="left">${message.sendDate}</td>
			<td></td>
			<c:set var="readDate" value="${message.readDate}"/>
					<c:if test="${ empty message.readDate}">
						<td align="left">읽지않음</td>
					</c:if>
			 <c:if test="${!empty message.readDate}">
				<td align="left">${message.readDate}</td>
			 </c:if>
			<td align="center"></td>
		</tr>
		</c:if>
	</c:forEach>
</table>
	<div align="right">
    	<button type="button" class="btn fa fa-trash-o" id="delete">삭제하기</button>
   	</div>
</div>
		<tr>
			<td align="center">
				<input type="hidden" id="currentPage" name="currentPage" value="${resultPage.currentPage}"/>
					<jsp:include page="../../common/pageNavigator_new.jsp"/>	
	    	</td>
		</tr>
	</div>

</body>
</html>