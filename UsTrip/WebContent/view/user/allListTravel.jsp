<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
	 $(function() {
			$( ".btn.btn-warning.bnt-action" ).on("click" , function() {
				var travNo = $(this).next().val();
				var userId = $(this).next().next().val();
					console.log("travNo :: " + travNo);
					/* alert("userId " + userId	); */
				/* self.location="/user/getTravel?travelNo="+travelNo; */
				self.location="/user/getTravel?travNo="+travNo+"&userId="+userId;
			});
	 });
	</script>
	
	<style>
		html, body {
			width: 100%;
			height:100%;
		}
		
		.title{
		    text-shadow: 2px 2px 0px rgba(0, 0, 0, 0.4) !important;    
		}
		
		.divider-title{
		    border:1px solid #dddddd;
		}
		
		.project {
		    margin-bottom: 30px;
		    vertical-align: top;
		    margin-right: 30px;
		    float: left;
		    cursor: pointer;
		    width:100%;
		}
		
		.project figure {
		    position: relative;
		    display: inline-block;
		    width: 283px;
		    -moz-box-shadow: 0 3px 0 #e2e2e2,transparent 0 0 0;
		    -webkit-box-shadow: 0 3px 0 #e2e2e2,transparent 0 0 0;
		    box-shadow: 0 3px 0 #e2e2e2,transparent 0 0 0;
		    -webkit-box-shadow: 0 3px 0 #e2e2e2;
		    -moz-box-shadow: 0 3px 0 #e2e2e2;
		    box-shadow: 0 3px 0 #e2e2e2;
		}
		
		.project figure figcaption {
		    position: relative;
		    z-index: 10;
		    padding: 10px 10px 10px;
		    background: #fff;
		    -ms-transition: all .2s ease-out;
		    -webkit-transition: all .2s ease-out;
		    -moz-transition: all .2s ease-out;
		    -o-transition: all .2s ease-out;
		    transition: all .2s ease-out;
		    text-align: left;
		    color: #555;
		}
		
		.project figure:hover .actions {
		    opacity: 1;
		}
		
		.project figure .actions {
		    display: block;
		    position: absolute;
		    top: 0;
		    z-index: 1;
		    width: 100%;
		    height: 100%;
		    opacity: 0;
		    background-color: rgba(29,29,29,.7);
		    -ms-transition: all .2s ease-out;
		    -webkit-transition: all .2s ease-out;
		    -moz-transition: all .2s ease-out;
		    -o-transition: all .2s ease-out;
		    transition: all .2s ease-out;
		}
		
		.project figure img {
		    border: 0;
		    width: 100%;
		    height: 200px;
		}
		
		.btn-warning bnt-action{
		    margin: 0% 0% auto;    
		}
		
		figcaption .project-details {
		    display: block;
		    font-size: 16px;
		    line-height: 34px;
		    color: #0093d0;
		    height: 27px;
		    width: 85%;
		    margin-bottom: 5px;
		    overflow: hidden;
			width:145px;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		
		figcaption .project-price {
		    position: absolute;
		    right: 15px;
		    top: 12px;
		    font-size: 22px;
		    text-align: right;
		    /* margin-top: 8px; */
		    letter-spacing: -1px;
		    -webkit-font-smoothing: antialiased;
		}
		
		figcaption .project-creator {
		    font-size: 13px;
		    color: #545454;
		    display: block;
		    padding-top:5px;
		}
		
		.project figure .actions button {
		    padding: 13px 20px;
		    font-size: 16px;
		    top: 32%;
		    position: absolute;
		    left: 50%;
		    width: 90%;
		    margin-left: -45%;
		    line-height: 18px;
		    letter-spacing: 1px;
		}     

	</style>
</head>
<body>
<jsp:include page="/common/toolbar.jsp"/>
<div class="container">
	  
						<div>
								 <c:forEach var="travel" items="${travel}">				
										<div class="col-ms-10 col-md-4">
									        <div class="project">
									            <figure class="img-responsive">
									                <img src="/images/upload/blog/${travel.thumbNail}">
									                <figcaption>
									                	<%-- <input type="hidden" class="travelNo" id="travelNo" value="${travel.travelNo}"> --%>
									                    <span class="project-details">${travel.travTitle }</span>
									                    <span class="project-price"><strong>${travel.nickName }</strong></span>
									                    <span class="project-creator">
							        					<fmt:formatDate var="newFormattedDateString" value="${travel.startDate}" pattern="yyyy-MM-dd"/>  
							         					출발일 ${newFormattedDateString} | 여행일수 ${travel.totalDate }일</span>									                    
									                </figcaption>
									                <span class="actions">
									                	<button class="btn btn-warning bnt-action" type="button" >상세보기</button>
									                    <input type="hidden" class="travelNo" id="travelNo" value="${travel.travelNo}">
									                    <input type="hidden" class="travUserId" id="travUserId" value="${travel.userId}">
									                </span>
									            </figure>
									       </div>
									    </div>
									</c:forEach>		
					
						</div>
				</div> 
</body>
</html>
