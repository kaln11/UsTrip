<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Intensify by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html>
	<head>
	<meta charset="UTF-8">
		<title>USTRIP</title>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="/css/main.css" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		
		 <link href="/css/fullscreenDemo.css" rel="stylesheet" type="text/css">
		 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>

		<script type="text/javascript">
/*  			$(window).scroll(function() {
				if ($(document).scrollTop() > 150) {
			         alert('hi');
					$('.logo').height(200);
				} 	else {
					$('.logo').height(100);
				}
			}); */
			
		 $(function(){
		          $('body').vidbg({
		              'mp4': '/images/media/mp4_video.mp4',
		              /*   	           		   'webm': '/images/media/webm_video.webm',
		              'poster': '/images/media/fallback.jpg',  */
		          }, {
		            // Options
		            muted: true,
		            loop: true,
		  			overlay: true,
		            overlayColor: '#000',
		            overlayAlpha: '0.2',
		            muted: true
		          });
		      }); 
			
		</script>
		
	</head>
	<body >
	<jsp:include page="/common/toolbar.jsp" />
	<!-- //////////툴바 및 메뉴바/////////// -->

	<div class="block-container">
		<div class="block">
			<h3>당신의 아름다운 대한민국을 여행하세요</h3>
			<br>
			<ul class="actions">
			</ul>
		</div>
	</div>

	<!-- Banner -->
	<section id="banner">
		<div class="content"></div>
	</section>

	<!-- Scripts -->
	<script src="/js/jquery.min.js"></script>
	<script src="/js/jquery.scrolly.min.js"></script>
	<script src="/js/skel.min.js"></script>
	<script src="/js/util.js"></script>
	<script src="/js/main.js"></script>
	<script src="/js/vidbg.min.js"></script>

</body>
</html>