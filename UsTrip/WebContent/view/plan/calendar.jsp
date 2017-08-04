<meta charset='utf-8' />
<link rel="stylesheet" href="/css/bootstrap.vertical-tabs.css">
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

	<link href='/css/fullcalendar.min.css' rel='stylesheet' />
	<link href='/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
	<script src='/js/moment.min.js'></script>
	<script src='/js/fullcalendar.min.js'></script>
     <script src="/js/dateFormat.js"></script>
<script>
 	function setCalendar(date,data){
		//alert(JSON.stringify(data));
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,basicWeek,basicDay'
			},
			defaultDate: date,
			navLinks: true, // can click day/week names to navigate views
			editable: false,
			eventLimit: true, // allow "more" link when too many events
			events: data
		});
	} 
	$(document).ready(function() {
		var calendarNo = $('#travNo').val();
		console.log("cccccccccc " +calendarNo );
		var calendarEvent = [];
		var dDate;
		$.ajax( 
				{
					url : "/plan/getCalendarJSON/"+calendarNo,
					method : "GET" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(J , status) {	
						for(var i in J.listCity){
							console.log(" ??????!!!!!!!? :: " + new Date(J.listCity[i].stayEnd).format("yyyy-MM-dd"));
							 calendarEvent.push({
								title: J.listCity[i].city,
								start: new Date(J.listCity[i].stayStart).format("yyyy-MM-dd"),
								end: new Date(J.listCity[i].stayEnd).format("yyyy-MM-dd")								
							}); 
							 for(var j in J.listCity[i].listPlace){
								 console.log(" ??? :: " + new Date(J.listCity[i].stayStart).format("yyyy-MM-dd"));
								 console.log(" ??????? :: " + new Date(J.listCity[i].stayEnd).format("yyyy-MM-dd"));
								 calendarEvent.push({
										title: J.listCity[i].listPlace[j].place,
										start: new Date(J.listCity[i].listPlace[j].visitDate).format("yyyy-MM-dd")
							 });
							}
						}
						dDate = new Date(J.listCity[0].stayStart).format("yyyy-MM-dd");
						setCalendar(dDate,calendarEvent);
					}
				});			
	});
</script>
<style>
	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}
	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}
</style>
	<div id="calendar"></div>
	<!-- <div id="calendar"></div> -->
<input type="hidden" value="${calendarTravNo}" id="calendarTravNo"/>
