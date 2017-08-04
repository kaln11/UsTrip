<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="navbar  navbar-inverse navbar-fixed-top">

	<div class="container">

		<a class="navbar-brand navbar-left"><i
			class="glyphicon glyphicon-gift"></i> Model2 MVC Shop</a>

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<!-- 로그인을 하지 않았을경우  -->
		<c:if test="${ empty user }">
			<div id="navbar" class="navbar-collapse collapse">

				<ul class="nav navbar-form navbar-nav navbar-right">
					<li>
						<form class="navbar-form">
							<input type="text" placeholder="ID" class="form-control" />
						</form>
					</li>
					<li><form class="navbar-form">
							<input type="password" placeholder="Password"
								class="form-control" />
						</form></li>

					<li id="login"><a>Log in</a></li>
					<li id=""><a>Sign up</a></li>

				</ul>

			</div>
			<!--/.navbar-collapse -->
		</c:if>
		<!-- ******************************************************************************************************** -->

		<!--로그인을 했을경우  -->
		<c:if test="${ !empty user }">

			<div class="collapse navbar-collapse" id="navbar">

				<ul class="nav navbar-nav navbar-left navbar-form"
					data-toggle="dropdown" data-hover="dropdown">

					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown">개인정보 관리<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a>개인정보 조회</a></li>
							<%--  <c:if test="${user.role == 'admin' }">        
            <li class="divider"></li>           
            <li><a>회원정보 조회</a></li>
            </c:if>  --%>
						</ul></li>

					<c:if test="${sessionScope.user.role == 'admin'}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false"> <span>판매상품관리</span>
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#">판매상품등록</a></li>
								<li class="divider"></li>
								<li><a href="#">판매상품관리</a></li>
								<li class="divider"></li>
								<li><a href="#">구매목록조회</a></li>

							</ul></li>
					</c:if>

					<!-- 구매관리 DrowDown -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false"> <span>상
								품 메 뉴</span> <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">상 품 검 색</a></li>
							<c:if test="${user.role == 'user' }">
								<li class="divider"></li>
								<li><a href="#">구매상품조회</a></li>
							</c:if>
						</ul></li>

				</ul>

				<ul class="nav navbar-nav navbar-right navbar-form">

					<c:if test="${user.role == 'admin' }">
						<li><a id="ment">관리자:: ${user.userName}님 환영합니다</a></li>
					</c:if>

					<c:if test="${user.role == 'user' }">
						<li><a id="ment">${user.userName}님 환영합니다</a></li>
					</c:if>

					<li><a>Log out</a></li>
				</ul>

			</div>
		</c:if>
	</div>
	<!--/.navbar-collapse -->

</div>
<style>
</style>
<script type="text/javascript">
$(function() {
	
	$('#ment').css('color','white').css('font-size','130%');
	
	$('.navbar-brand').css('font-size','220%');
	
	$('.navbar-brand').on("click" , function() {
 		$(window.parent.document.location).attr("href","../index.jsp");
 	}); 
	
	/* 로그인 및 벨리데이션체크 */
	$('li:contains("Log in")').on("click" , function() {
		
		var id=$('input[placeholder="ID"]').val();
		var pw=$('input[placeholder="Password"]').val();
		
		if(id == null || id.length <1) {
			alert('ID 를 입력하지 않으셨습니다.');
			$("input:text").focus();
			return;
		}
		
		if(pw == null || pw.length <1) {
			alert('패스워드를 입력하지 않으셨습니다.');
			$("input:password").focus();
			return;
		}
		$.ajax( 
				{
					url : "/user/jsonLogin",
					method : "POST" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					data : JSON.stringify({
						userId : id,
						password : pw
					}),
					success : function(JSONData , status) {
						if( JSONData.user != null ){
							$(window.parent.document.location).attr("href","/index.jsp");
						}else{
							alert("아이디 , 패스워드를 확인하시고 다시 로그인...");
						}
					}
			}); 
	});
	/* 로그인및 벨리데이션체크 끝  */
	
		$('li:contains("Sign up")').on("click" , function() {
			self.location = "/user/addUser"
		});

		$('li:contains("Log out")').on("click", function(){
			$(window.parent.document.location).attr("href","/user/logout");
		});
			 
		 $("a:contains('회원정보 조회')").on("click" , function() {
			
				self.location = "/user/listUser"
			}); 
		 
			$( "a:contains('개인정보 조회')" ).on("click" , function() {
		 
				$(self.location).attr("href","/user/getUser?userId=${sessionScope.user.userId}");
			});
			
			$( "a:contains('판매상품등록')").on("click" , function() {
				$(self.location).attr("href","../product/addProductView.jsp;");
		 	}); 
			
			$( "a:contains('판매상품관리')").on("click" , function() {
				$(self.location).attr("href","/Product/listProduct?menu=manage");
		 	}); 
			
			$( "a:contains('상 품 검 색')").on("click" , function() {
				$(self.location).attr("href","/Product/listProduct?menu=search");
		 	}); 
			
			$( "a:contains('구매목록조회')").on("click" , function() {
				$(self.location).attr("href","/Purchase/listSale");
		 	}); 
			
			$( "a:contains('구매상품조회')").on("click" , function() {
				$(self.location).attr("href","/Purchase/listPurchase?userId=${sessionScope.user.userId}");
		 	}); 
		
	
});	
</script>

