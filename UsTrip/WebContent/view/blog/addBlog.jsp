<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<script type="text/javascript">
	$(function() {
		$('body').on('click' , '#addBlog', function() {
			self.location="/blog/addBlog?travelNo="+$('#travNo').val();
		});
		
	});
</script>
<%-- 
	 ${travel.travTitle} 
	<div class="col-md-8 col-xs-4">
		블로그를 시작하시겠습니까?
	</div>
	<div class="col-md-8 col-xs-4">
		<input type="button" id="addBlog" value="start BLOG" style="margin-left:50px;">
		<input type="hidden" id="travelNo" name="travelNo" value="${travel.travelNo}">
		<input type="hidden" id="travelNo" name="travelNo" value="${travel.travelNo}">
	</div>
	 --%>
	<div class="jumbotron" 
			style="margin-top:50px;
			 background-image:url('http://chulsa.kr/files/attach/images/73/766/397/66805457a235cc2c3a0f4782ebc49806.JPG' );
			 background-size: cover;
			 background-position: center center ;
			 height:400px;
			 opacity: 0.8;">
						 
	  <h3 style="color:white;" class="display-3">블로그를 시작하시겠습니까?</h3>
	  <p class="lead" style="text-align: center; margin-right:60px;"><br/><br/><br/>
	 <input type="button" id="addBlog" value="start BLOG" style="margin-left:50px;">
	 <input type="hidden" id="travelNo" name="travelNo" value="${travel.travelNo}" >
	 </p>
		</div>