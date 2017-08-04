<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 
	<!-- Bootstrap -->
<!--     <link href="../../css/bootstrap.min.css" rel="stylesheet"> -->
<%-- 	<link href='<c:url value="/css/kfonts2.css" />' rel="stylesheet"> --%>
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
	
		$(function() {
			$( ".btn.btn-info" ).on("click" , function() {

				  $("form").attr("method" , "POST").attr("action" , "/user/findPwd").submit();  
			});
		});	
	</script>
	
	<style>
		.container{
			margin-top:100px;
		}
	</style>
 
<title>메일 보내기</title>
</head>
<body>
<jsp:include page="/common/toolbar.jsp"/>
<div class="container">
  <h4 align="center">메일 보내기</h4>
  <form >
    <div align="center"><!-- 받는 사람 이메일 -->
      <input type="text" id="userId" name="userId" size="120" style="width:50%" placeholder="상대의 이메일" class="form-control" >
    </div>     
    <!-- <div align="center">제목
      <input type="text" name="title" size="120" style="width:100%" placeholder="제목을 입력해주세요" class="form-control" >
    </div>
    <p>
    <div align="center">내용 
      <textarea name="content" cols="120" rows="12" style="width:100%; resize:none" placeholder="내용#" class="form-control"></textarea>
    </div> -->
    <p>
    <div align="center">
<!--       <input type="submit" id="submit" value="메일 보내기" class="btn btn-info"> -->
      <button type="submit" id="submit" class="btn btn-info">메일보내기</button>
    </div>
  </form>
</div>
</body>
</html> 