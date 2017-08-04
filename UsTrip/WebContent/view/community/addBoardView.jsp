<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
	 <link rel="stylesheet" href="/css/bootstrap.vertical-tabs.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.4/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.4/sweetalert2.min.css">
	
	<script src="/js/dateFormat.js"></script>
	
	<link href="/css/jquery.contextMenu.css" rel="stylesheet" type="text/css" />
    <script src="/js/jquery.contextMenu.js" type="text/javascript"></script>
    <script src="/js/jquery.ui.position.min.js" type="text/javascript"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
    <link href="/css/main.css" rel="stylesheet" type="text/css"/>    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.scrolly.min.js"></script>
	<script src="/js/skel.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>
	
	<script>
	
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("#searchForm").attr("method" , "POST").attr("action" , "/community/listCommunity").submit();
	}
	
	$(function () {			
		
		$('#write').on('click',function(){
			//self.location="/community/addBoardForm?userId="+${user.userId}
		});
			
		$('#men').on('click',function(){
			$('#searchKeyword').val(null);
        	$('#searchCondition').val(null);
			fncGetList(1);
        });
        
        $('#part').on('click',function(){
        	$('#searchKeyword').val(null);
        	$('#searchCondition').val(null);
        	$('#boardCategory').val(1);
        	fncGetList(1);
        }); 
        
        $('#okay').on('click',function(){
        	$("#boardForm").attr("method" , "POST").attr("action" , "/community/addBoard").submit();
        })
        
        $('#nope').on('click',function(){
        	$("#boardForm")[0].reset();
        })
        
});
	
	
  </script>
  <style type="text/css">
  body{font-family: "arial", dotum, "굴림", gulim, arial, helvetica, sans-serif;}
  </style>
  </head>
<body>
<jsp:include page="/common/toolbar.jsp"/>

<form class="form-inline" name="detailForm" id="searchForm">
	    <input type="hidden" class="form-control" name="searchCondition"  id="searchCondition" style="width:130px;">	
		<input type="hidden" style="width:250px;" type="text" class="form-control" id="searchKeyword" name="searchKeyword" 
		 placeholder="검색조건을 선택하고 검색하세요." value="${! empty search.searchKeyword ? search.searchKeyword : '' }" >
	    <input type="hidden" id="currentPage" name="currentPage" value=""/>  
	    <input type="hidden" id="boardCategory" name="boardCategory" value="1"/>
	    <input type="hidden" id="order" name="order" value=""/>
		</form>
	<div class="container" >
	
		<div class="jumbotron" 
			style="margin-top:50px;
			 background-image:url('http://cfile5.uf.tistory.com/image/2642ED4753AAD6872B11B9' );
			 background-size: cover;
			 background-position: center center ;
			 height:400px;
			 opacity: 0.8;">
						 
	  <h1 style="color:white;">게시글 작성</h1>
	  <p style="color:white; margin-top:130px;">게시글을 작성하여 동행을 구하거나 여행계획을 물어보세요!</p>
		</div> 
	</div>

	<div class="row" >
		<div class="col-md-2" style="margin-left:60px; margin-top:34px;">        
			<h2 id="men"> 멘토링</h2>
				<h2 id="part" > 동행구하기</h2>
				<c:if test="${ !empty user }">
				<h2 id="write">게시글작성</h2> 
				</c:if>      
		</div>
		<!-- <div class="col-md-1" style="margin-left:10px; margin-top:34px;"></div> -->
    <div class="col-md-8" style="margin-left:10px; margin-top:34px;">
    
   
    <form class="panel panel-warning" id="boardForm">
  <!-- Default panel contents -->
  <div class="panel-heading"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 게시글작성</div>
  <div class="panel-body" class="form-group">
  <div class="row">
  <div class="col-md-6" >
    게시판 선택<br/><select name="boardCategory" class="form-control" style="margin-top:5px;">
    <option>선택하세요.</option>
    <option value="0">멘토링 게시판</option>
    <option value="1">동행구하기 게시판</option>
    </select>
    </div>
    <div class="col-md-6" >
    
여행 선택<select name="travNo" class="form-control" style="margin-top:5px;">
<option>선택하세요.</option>
<c:forEach items="${travels}" var="travel" varStatus="status">
<option value="${travel.travelNo}">${travel.travTitle} :: <fmt:formatDate value="${travel.startDate}" pattern="yyyy/MM/dd"/></option>
</c:forEach>
</select>
</div>
    </div>
<hr/>
게시글제목<input type="text" name="boardTitle"><hr/>
게시글내용<textarea name="boardContent" style="height:200px;"></textarea><hr/>
<input type="hidden" value="${user.nickName}" name="nickName">
<button id="nope" type="button" style="float: right; margin-left:10px;">리 셋</button>
   <button id="okay" type="button" style="float: right;">확 인</button>
  </div>   
  
</form>  
    
	</div>
	<div class="col-md-1" style="margin-left:10px; margin-top:34px;"></div>
	</div>
	
</body>
</html>