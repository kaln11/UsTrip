<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" media="all" type="text/css"/>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
  	
  	<link href="/css/star-rating.min.css" rel="stylesheet" media="all" type="text/css"/>
  	<script src="/js/star-rating.min.js" type="text/javascript"></script>
  	
    <link href="/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="/css/theme.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="/js/sortable.js" type="text/javascript"></script>
    <script src="/js/fileinput.js" type="text/javascript"></script>
    <script src="/js/theme.js" type="text/javascript"></script>
  	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.4/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.4/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>    

	<script type="text/javascript">
	
	$(function() {

		var blogNo=$('#blogNo').val();
		var travNo=$('#travNo').val();
	/* $(function() {
		$( "#add" ).on("click" , function() {
			 document.forms["updateBlog"].submit(); 
		});
	}); */	
		
		$('body').on('click' , '.fa-pencil', function() {
			
			$.ajax( 
					{
						url : "/blog/getJsonBlog/"+blogNo,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						context : this,
						success : function(serverData , status) {
							$("#dialog-form").attr('title',serverData.blog.place);
							if(serverData.blog.review!=null){
								$("#review").html(serverData.blog.review);
							}
							$("#score").val(serverData.blog.score);
							showDialog();
						}
					});
				
		});
		
		$('body').on('click' , '#tagPlus', function() {
			var addTag={hashTag:$('#hashTag').val(),
					blogNo:$('#blogNo').val() }
				
				$.post( "/blog/addJsonTag", addTag , function( serverData ) {
					var addTag='<span name="hashTag" class="text-primary">#'+serverData.hashTag.hashTag+'</span>'
  				  +'<i class="fa fa-times" aria-hidden="true" id="deleteTag"></i>'
  				  +'<input type="hidden" value="'+serverData.hashTag.tagNo+'"><br/>';
  				  
			$("#tagSpan").append(addTag);
			$('#hashTag').val("");
			timesEvent();
					}, "json" );  
			
		});
		
		$('body').on('click' , '#assetPlus', function() {
			
			var visitdate = new Date($('#visitDate').val());
			var postdata = {travNo:$("#travNo").val(),					
					blogNo:$("#blogNo").val(),
					assetCategory:$("#assetCategory").val(),	  
					usage:$("#usage").val(),	  		
					charge:$("#charge").val(),	 
					visitDate:	visitdate}
			 $.post( "/blog/addJsonAsset", postdata , function( serverData ) {
				var addAsset='<div class="row" id="'+serverData.asset.assetNo+'">'
				   +'<label class="col-md-1 col-sm-2 col-xs-2 control-label" for="textinput"><i class="fa fa-usd" aria-hidden="true"></i></i></label>'
				   +'<div class="col-md-3 col-sm-3 col-xs-3">'
				   +'<input type="text" class="form-control" name="category" value="'+serverData.asset.assetCategory+'" readonly/></div>'
				   +'<div class="col-md-4 col-sm-3 col-xs-3">'
				   +'<input type="text" class="form-control" name="usage" value="'+serverData.asset.usage+'" readonly/>'
				   +'</div><div class="col-md-3 col-sm-3 col-xs-3">'
				   +'<input type="text" class="form-control" name="charge" value="'+serverData.asset.charge+'" readonly/></div>'
				   +'<label class="col-md-1 col-sm-1 col-xs-1 control-label" for="textinput"><i class="fa fa-times" aria-hidden="true" id="deleteAsset"></i>'
				   +'<input type="hidden" value="'+serverData.asset.assetNo+'"></label></div>';
				   
		$(addAsset).insertBefore($("#assetBlog"));
		$('#charge').last().val("");
		$('#usage').last().val("");
		timesEvent();
				}, "json" );  
			
		});
		
		$('body').on('click' , '#deleteTag', function() {
			tagNo=$(this).next().val();
			
			$.ajax( 
					{
						url : "/blog/deleteJsonTag/"+tagNo,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						context : this,
						success : function(serverData , status) {
							$(this).prev('span').remove();
							$(this).remove();
						}
					});
		});
		
		$('body').on('click' , '#deleteAsset', function() {
			var assetNo=$(this).next().val();
			
			$.ajax( 
					{
						url : "/asset/deleteAssetJSON/"+assetNo,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						context : this,
						success : function(serverData , status) {
							$('#'+assetNo+'').remove();
							$(this).remove();
						}
					});
		});
		
		$('body').on('click' , '#updateScore', function() {
			var score=$("#score").val();
			
			if(0.1<=score&&score<=5){
				$.ajax( 
						{
							url : "/blog/updateJsonScore/"+score+"/"+blogNo,
							method : "GET" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							context : this,
							success : function(serverData , status) {							
								$("#score").val(score);
							}
						});
			}else{
				alert("점수는 0.1~5.0까지 가능합니다.")
			}
		});
		
		$('body').on('click' , '#updateReview', function() {
			var updateReview = { review:$("#review").val(), blogNo:$("#blogNo").val()};
			
			$.post( "/blog/updateJsonReview/", updateReview , function( serverData ) {
				$('#review').val($("#review").val());
				}, "json" );  
			
		});
		
		$('body').on('click' , '#deleteImage', function() {
			var imgNo=$(this).next().val();
			
			$.ajax( 
					{
						url : "/blog/deleteJsonImage/"+imgNo,
						method : "GET" ,
						dataType : "json" ,
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						context : this
					});
			$( "#"+imgNo+"" ).remove();
		});
		
		$('body').on('click' , '#confirm', function() {			
			self.location="/user/getTravel?travNo="+travNo
		});
		
		$('body').on('click' , '#cancle', function() {			
			location.reload();
		});
		
		 $('.kv-fa').rating({	   
			  hoverOnClear: false,
	            	filledStar: '<i class="fa fa-star"></i>',
	                emptyStar: '<i class="fa fa-star-o"></i>',
	                clearButton: '<i class="fa fa-lg fa-minus-circle"></i>'
	        });
		 
		 $('body').on('click' , '#uploadFile', function() {
			 var queryString = $("#blogFile").val() ;
			var fileForm = $('#fileForm')[0]
			 var sp = queryString.split(',');			
			 var formData = new FormData(fileForm);
			for(var i in sp){
				formData.append("blogFile[]", sp[i]);
			}		 
			$.ajax({
				    url: "/blog/addJSONImage/"+$('#blogNo').val()+"/"+$('#travNo').val(),
				    data: formData,
				    type: 'POST',
				    contentType: false, 
				    processData: false
				}); 
			
			window.setTimeout("rePicture()", 2000);	
			
		 });
		 
		 $('.fa-check').hover(function(){
				$(this).attr('class','fa fa-check text-danger');
			},function(){
				$(this).attr('class','fa fa-check text-default');
			}); 
		 
		 $('.fa-check').on('click',function(){
			 swal({
					title:'정상적으로 반영되었습니다.',
					text: "작성하신 내용이 정상적으로 입력/수정되었습니다.",
			   		  type: 'success'
			 })
			 });
					
	$('.fa-times').hover(function(){
		$(this).attr('class','fa fa-times text-danger');
	},function(){
		$(this).attr('class','fa fa-times text-default');
	}); 
 
 $('.fa-times').on('click',function(){
	 swal({
			title:'정상적으로 삭제되었습니다.',
			text: "작성하신 내용이 정상적으로 삭제되었습니다.",
	   		  type: 'success'
	 })
	 });
		
});
	
	function timesEvent(){
		$('.fa-times').hover(function(){
			$(this).attr('class','fa fa-times text-danger');
		},function(){
			$(this).attr('class','fa fa-times text-default');
		}); 
	 
	 $('.fa-times').on('click',function(){
		 swal({
				title:'정상적으로 삭제되었습니다.',
				text: "작성하신 내용이 정상적으로 삭제되었습니다.",
		   		  type: 'success'
		 })
		 });
	}
	
	function rePicture(){
		/* $('#divLoad').load("/blog/updateBlog?blogNo="+$('#blogNo').val()); */
		 location.reload();
	}

</script>
<style type="text/css">
  body{
  	font-family: "arial", dotum, "굴림", gulim, arial, helvetica, sans-serif;
  }
  </style>
</head>
	<body>
	
		<div class="row" >
			<div class="col-sm-5">				
						<input type="hidden"  id="blogNo" value="${blog.blogNo}">
						<input type="hidden"  id="travNo" value="${blog.travNo}">
						<input type="hidden"  id="visitDate" value="<fmt:formatDate value="${blog.visitDate}" pattern="yyyy/MM/dd" />">
						
			          	<h2 class="text-success"><i class="fa fa-map-marker" aria-hidden="true"></i> ${blog.place}</h2>
			          	</div>
			          	</div>
			          	<hr/>			
			          	<div class="row">
			            	<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></label>
			            	<div class="col-sm-10">
			              		<textarea class="form-control text-default" style="width:flex; height:150px;" name="review" id="review" onclick="this.value=''">${!empty blog.review? blog.review : "리뷰를 남겨주세요"}</textarea>
			            	</div>
			            	<label class="col-sm-1 control-label" for="textinput">
				              		<i class="fa fa-check" aria-hidden="true" id="updateReview"></i>
				            </label>
			          	</div>
		                <hr/>
						
			          	<div class="row">
			            	<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></label>
			            	<div class="col-sm-10">
			            	<input class="kv-fa rating-loading " dir="ltr" data-size="sm" name="score" id="score" value="${blog.score}">
			            	</div>
			            	
			            		<label class="col-sm-1 control-label" for="textinput" ><i class="fa fa-check" aria-hidden="true" id="updateScore" ></i></label>
			            	
			          	</div>
						<hr/>
						
						
							<div class="row">
				            	<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-camera" aria-hidden="true"></i></label>
				            	<div class="col-sm-11">
				            		<c:forEach items="${blog.images}" var="images" varStatus="status3">
					            		<span id="${images.imgNo}">
					                    	<img src="/images/upload/blog/${images.travNo}/${images.serverImgName}" style="width:300px; height:150px;">
							              	<i class="fa fa-times" aria-hidden="true" id="deleteImage"></i>
				                    		<input type="hidden" value="${images.imgNo}">
				                    		</span>
				                    	
			          				</c:forEach>
				            	</div>
				          	</div>
						<hr/>
			          	<form class="row"  id="fileForm">
			          <label class="col-sm-1 control-label" for="textinput"><i class="fa fa-camera" aria-hidden="true"></i></label>
			          <div class="col-sm-10">
			          			<span class="btn btn-info btn-file" style="width:668px;">
						    파일올리기 <input class="inputfile inputfile-1"  type="file" accept="image/png, image/jpeg, image/gif" id="blogFile" name="blogFile[]"  multiple />	
						</span>		          		        
			          </div>
       					<label class="col-sm-1 control-label" for="textinput" ><i class="fa fa-check" aria-hidden="true" id="uploadFile" ></i></label>
			            </form>
			          	<hr/>
	      			
			          	<c:forEach items="${blog.assets}" var="assets" varStatus="status">
		               		<div class="row" id="${assets.assetNo}">
			         	 		<label class="col-md-1 col-sm-2 col-xs-2 control-label" for="textinput"><i class="fa fa-usd" aria-hidden="true"></i></label>
				              	<div class="col-md-3 col-sm-3 col-xs-3">
				              		<input type="text" class="form-control" name="category" value="${assets.assetCategory}" readonly/>
				              	</div>
				         	  	<div class="col-md-4 col-sm-3 col-xs-3">
				              		<input type="text" class="form-control" name="usage" value="${assets.usage}" readonly/>
				              	</div>
				       	      	<div class="col-md-3 col-sm-3 col-xs-3">
				              		<input type="text" class="form-control" name="charge" value="${assets.charge}" readonly/>
				              	</div>
				            	<label class="col-md-2col-sm-1 col-xs-1 control-label" for="textinput">
				              		<i class="fa fa-times" aria-hidden="true" id="deleteAsset"></i>
				              		<input type="hidden" value="${assets.assetNo}">
				            	</label>
			         		</div>
		                </c:forEach>
			          	
			          	<div class="row" id="assetBlog">
			         	 	<label class="col-md-1 col-sm-2 col-xs-2 control-label" for="textinput"><i class="fa fa-usd" aria-hidden="true"></i></label>
			              	<div class="col-md-3 col-sm-3 col-xs-3">
			                  	<select class="form-control" name="assetCategory" id="assetCategory" >
									<option value="식비" selected="selected">식비</option>
									<option value="교통비">교통비</option>
									<option value="숙박비">숙박비</option>
									<option value="쇼핑">쇼핑</option>
									<option value="입장료">입장료</option>
									<option value="기타">기타</option>
							 	</select>
			              	</div>
			         	  	<div class="col-md-4 col-sm-3 col-xs-3">
			              		<input type="text" class="form-control" placeholder="사용처" id="usage" />
			              	</div>
			       	      	<div class="col-md-3 col-sm-3 col-xs-3">
			              		<input type="text" class="form-control" placeholder="사용금액" id="charge" />
			              	</div>
			            	<label class="col-md-1 col-sm-1 col-xs-1 control-label" for="textinput">
			              		<i class="fa fa-check" aria-hidden="true" id="assetPlus"></i>
			            	</label>
			         	</div>
			         	
		                <hr/>
			         	
			         	<div class="row">
			            	<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-hashtag" aria-hidden="true"></i></label>
			            	<div class="col-sm-11" id="tagSpan" >
			            		<c:forEach items="${blog.hashTags}" var="hashTags" varStatus="status2">
			                		<span class="text-primary"> #${hashTags.hashTag} </span>
			                		<i class="fa fa-times" aria-hidden="true" id="deleteTag"></i>
			                		<input type="hidden" value="${hashTags.tagNo}">
			                		<input type="hidden" value="${status2.index}" id="tagCount">
			                		<br/>
			                	</c:forEach>
		                	</div>
		                </div>
			
			          	<div class="row">
			            	<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-hashtag" aria-hidden="true"></i></label>
			            	<div class="col-sm-10">
			              		<input type="text" name="hashTag" id="hashTag" class="form-control" style="width: flex;" maxlength="10" id="hashTag" value="" >
			            	</div>   
			            	
			            		<label class="col-sm-1 control-label" for="textinput"><i class="fa fa-check" aria-hidden="true" id="tagPlus"></i></label>
			            	
						</div>
						
					    <br/>
		                <hr/>
		                <div class="row">
		                <div class="col-md-6"></div>
			         	<div class="col-md-6">
			         					         	
			            		<input type="button"  id="confirm" value="되돌아가기">
			                	<input type="button"  id="cancel" value="취소">		
			                	          	
			         	</div>	
			         	</div>        
	</body>
</html>