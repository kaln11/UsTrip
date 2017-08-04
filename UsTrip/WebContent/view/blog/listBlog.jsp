<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" media="all" type="text/css"/>
<!-- 	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>	 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	
	
  	
  	<link href="/css/star-rating.min.css" rel="stylesheet" media="all" type="text/css"/>
  	<script src="/js/star-rating.min.js" type="text/javascript"></script>
  	
  	<link href="/css/lightbox.css" rel="stylesheet" media="all" type="text/css"/>
  	<script src="/js/lightbox.min.js" type="text/javascript"></script>
  	
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.4/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.4/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
	
	<style>
@import url('https://fonts.googleapis.com/css?family=Lato:300,400,700,900');

* {
	pading: 0;
	margin: 0;
	font-family: "Lato", sans-serif;
	position: relative;
}

.timeline {
	padding: 40px 0px;
	width: 100%;
	 left: -62px; 
}

.timeline:before {
	content: "";
	position: absolute;
	top: 40px;
	left: 68px;
	width: 3px;
	height: 100% ;
	background: #c0392b;
}

.timeline .column {
	margin: 40px 40px 40px 120px;
}

.timeline .column .title h1 {
	font-size: 120px;
	font-weight:600;
	color: rgba(0,0,0,0.1);
	font-family: serif;
	letter-spacing: 3px;
}

.timeline .column .title h1:before {
	content: "";
	position: absolute;
	left: -61px;
	top: 86px;
	width: 10px;
	height: 10px;
	background: #fff;
	border: 10px solid #c0392b;
}

.timeline .column .title h2 {
	font-weight:600;
	margin-top: -60px;
	font-size: 60px;
}

.timeline .column .description p {
	font-size: 13px;
	line-height: 20px;
	margin-left: 20px;
	margin-top: 10px;
	font-family: serif;
}

/* .timeline .column .description {
	border-left: 1px solid #000;
} */

.main {
	width: 80%;
	margin-left: 10%;
	margin-top: 80px;
}

.main h1 {
	font-size: 80px;
	line-height: 60px;
}

.main p {
	font-size: 13px;
	line-height: 20px;
	font-family: serif;
	text-align: right;
}


</style>
  	
	<script type="text/javascript">
		
	$(function() {
				
		$('body').on('click' , '.fa-pencil', function() {
			var temp = $(this).attr('temp');
			swal({
				title:'블로그를 작성/수정합니까?',
				text: "블로그를 작성해보세요",
		   		  type: 'question',
		   		  showCancelButton: true,
		   		  confirmButtonColor: '#3085d6',
		   		  cancelButtonColor: '#d33',
		   		  confirmButtonText: '간다'
		   		}).then(function () {		   			
		   			//self.location="/blog/updateBlog?blogNo="+temp 
		   					var destination="/blog/updateBlog?blogNo="+temp ;
		   					$("#content3").load(destination);
		   		})
		});
		
		$('body').on('click' , '.glyphicon-remove', function() {
			var temp = $(this).attr('temp');
			swal({
				title:'블로그를 삭제합니까?',
				text: "삭제한 블로그와 내용은 복구할수 없습니다.",
		   		  type: 'warning',
		   		  showCancelButton: true,
		   		  confirmButtonColor: '#3085d6',
		   		  cancelButtonColor: '#d33',
		   		  confirmButtonText: '삭제'
		   		}).then(function () {		   			
		   			self.location="/blog/deleteBlog?blogNo="+temp+"&travNo="+$("#travNo").val();
		   		})
			
		});
		
		$('body').on('click' , '#listPicture', function() {
			self.location="/blog/listPicture?travelNo="+$("#travNo").val();
		});
		
		$('body').on('click' , '#travLike', function() {
			
			if($(this).val()=='좋아요취소'){
				$.ajax( 
						{
							url : "/blog/deleteJsonLike/"+$("#travNo").val(),
							method : "GET" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							context : this,
							success : function(serverData , status) {
								$(this).val('좋아요');
							}
						});
			}else{
				$.ajax( 
						{
							url : "/blog/addJsonLike/"+$("#travNo").val(),
							method : "GET" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							context : this,
							success : function(serverData , status) {
								$(this).val('좋아요취소');
							}
						});
			}
			
		});
		
		$('body').on('click' , '#addPlace', function() {
			self.location="/blog/updatePlace?travelNo="+$("#travNo").val()+"?"+$("#visitDate").val();
		});
		
		 $('.kv-fa').rating({	   			 
	            	filledStar: '<i class="fa fa-star"></i>',
	                emptyStar: '<i class="fa fa-star-o"></i>',
	                clearButton: '<i class="fa fa-lg fa-minus-circle"></i>',
	                displayOnly: true
	        });  
	        
		$('.fa-pencil').hover(function(){
			$(this).attr('class','fa fa-pencil text-danger');
		},function(){
			$(this).attr('class','fa fa-pencil text-default');
		}); 
	        
		$('.glyphicon-remove').hover(function(){
			$(this).attr('class','glyphicon glyphicon-remove text-danger');
		},function(){
			$(this).attr('class','glyphicon glyphicon-remove text-default');
		}); 
		 		 
	}); 
	</script>
</head>
<body id="listB">
	
		<%-- <input type="button" class="btn btn-default" id="listPicture" value="사진첩">
			<input type="button" class="btn btn-default" id="addPlace" value="장소추가">
			<c:if test='${isLiked == 1}'>
			  	<input type="button" class="btn" id="travLike" value="좋아요취소" >
			</c:if>
			<c:if test='${isLiked == 0}'>
			  	<input type="button" class="btn" id="travLike" value="좋아요" >
			</c:if>
	 --%>
	<c:set var="i" value="0" />
	<c:forEach items="${list}" var="blog" varStatus="status">
		<input type="hidden" name="travNo" id="travNo" value="${blog.travNo}">
		<input type="hidden" name="visitDate" id="visitDate" value="${blog.visitDate}">
		<input type="hidden" id="blogNo" name="blogNo" value="${blog.blogNo}"/>
		<div class="timeline">
			<div class="column">
			
				<div class="title">
				<hr/>
					<h1> <fmt:formatDate value="${blog.visitDate}" pattern="yyyy/MM/dd"/> </h1>
					<h2>  ${blog.place}</h2>			
				</div>
				<div class="row">
    				<div class="col-md-6 text-left">
						<input class="kv-fa rating-loading " data-size="sm" id="score" value="${blog.score}">
					</div>
					<div class="col-md-6 text-right" style="font-size:20px;">
					<c:if test="${ !empty user }">
					<i class="fa fa-pencil" aria-hidden="true" style="margin-right:10px" temp="${blog.blogNo}"></i> /<i class="glyphicon glyphicon-remove" aria-hidden="true" temp="${blog.blogNo}"></i>	
					</c:if>			                    
					</div>
				</div>
		<hr/>
				
           
				<c:forEach items="${blog.images}" var="images" varStatus="status3">
		        	<a class="image-link" href="/images/upload/blog/${images.travNo}/${images.serverImgName}" data-lightbox="set" data-title="${images.originalName}">
		        	<img class="image" src="/images/upload/blog/${images.travNo}/${images.serverImgName}" alt="${images.originalName}" style="width:150px; height:100px;"></a>
		        	
		        </c:forEach><hr/>
						<div class="row">
			            	<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></label>
			            	<div class="col-sm-11">
			              		&nbsp;${!empty blog.review? blog.review:""}
			            	</div>
			          	</div>
		 			
		 			<hr/>	 		
		                    <div class="panel panel-danger">
  
  <div class="panel-heading text-default">사용총액 :: ${blog.sumCharge} 원</div>  
  <table class="table">
   <thead> <tr> <th>사용분류</th> <th>사용내용</th> <th>사용금액</th>  </tr> </thead>
    <tbody> <c:forEach items="${blog.assets}" var="assets" varStatus="status4">
    			<tr> <th scope=row>${assets.assetCategory}</th> <td>${assets.usage}</td> <td>${assets.charge} 원</td> </tr> 
    			</c:forEach>
    </tbody>
  </table>
</div><hr/>
               <c:forEach items="${blog.hashTags}" var="hashTags" varStatus="status2">
                	&nbsp;#<span class="text-primary">&nbsp;${hashTags.hashTag}&nbsp;</span>
                </c:forEach><hr/>
		           &nbsp;${blog.memo}
		                     
				
				
			</div>
		</div>
</c:forEach>
		
</body>
</html>