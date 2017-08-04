function graph(J){
		

		var foodData = []
    	 var trafficData = []
    	 var entranceData = []
    	 var shoppingData = []
    	 var roomData = []
    	 var etcData = []
    	 var roundData = []
    	 var total = J.sum;
									
		for(var i in J.food){
			foodData.push({
		          y: J.food[i].charge,
		          x: new Date(J.food[i].visitDate),
		          usage: J.food[i].usage,
		          temp : J.food[i].assetNo,
		          exploded: true
		        });	}
		
		for(var i in J.traffic){
			trafficData.push({
		          y: J.traffic[i].charge,
		          x: new Date(J.traffic[i].visitDate),
		          usage: J.traffic[i].usage,
		          temp : J.traffic[i].assetNo,
		          exploded: true
		        });	}
		
		for(var i in J.entrance){
			entranceData.push({
		          y: J.entrance[i].charge,
		          x: new Date(J.entrance[i].visitDate),
		          usage: J.entrance[i].usage,
		          temp : J.entrance[i].assetNo,
		          exploded: true
		        });	}
		
		for(var i in J.shopping){
			shoppingData.push({
		          y: J.shopping[i].charge,
		          x: new Date(J.shopping[i].visitDate),
		          usage: J.shopping[i].usage,
		          temp : J.shopping[i].assetNo,
		          exploded: true
		        });	}
		
		for(var i in J.room){
			roomData.push({
		          y: J.room[i].charge,
		          x: new Date(J.room[i].visitDate),
		          usage: J.room[i].usage,
		          temp : J.room[i].assetNo,
		          exploded: true
		        });	}
		
		for(var i in J.etc){
			etcData.push({
		          y: J.etc[i].charge,
		          x: new Date(J.etc[i].visitDate),
		          usage: J.etc[i].usage,
		          temp : J.etc[i].assetNo,
		          exploded: true
		        });	}
		
		 for(var i in J.round){
				roundData.push({
			          y: J.round[i].charge,
			          legendText : J.round[i].assetCategory,
			          label: J.round[i].assetCategory,
			          exploded: true
			        });	} 
		
		 bar(foodData,trafficData,entranceData,shoppingData,roomData,etcData);
	
		 $( "#bar" ).on("click" , function() {	
			 bar(foodData,trafficData,entranceData,shoppingData,roomData,etcData);													
			});		 
		 
		 $( "#pie" ).on("click" , function() {
			 pie(roundData);																
			});
		
	}

function bar(foodData,trafficData,entranceData,shoppingData,roomData,etcData){
	var chart = new CanvasJS.Chart("listasset", {
		title:{
			text:"여행 가계부"
		},
		exportEnabled: true,
                animationEnabled: true,
		axisX:{
			valueFormatString: "YYYY년 MM월 DD일",
			labelFontSize: 18,
			interval: 1,
			 intervalType: "day"
		},
		axisY2:{
			valueFormatString: " 0 원",
			lineThickness: 0				
		},
		toolTip:{             
			FontFamily: "tahoma",
			content: "{name}({usage}): <strong>{y}원</strong> :: 합 : <strong>#total 원</strong>"
	      },
		data: [
		       
		{     
			click: function(e){
				/*alert(  "dataSeries Event => Type: "+ e.dataSeries.type+ ", dataPoint { x:" + e.dataPoint.x + ", y: "+ e.dataPoint.y + " }"+"[temp = "+e.dataPoint.temp+"]" );	*/
				conform(e.dataPoint.temp);
			  },	
			type: "stackedBar",
			showInLegend: true,
			name: "식비",
			axisYType: "secondary",
			dataPoints: foodData
		},
		
		{     
			click: function(e){
				conform(e.dataPoint.temp);	
			  },	
			type: "stackedBar",
			showInLegend: true,
			name: "교통비",
			axisYType: "secondary",
			dataPoints: trafficData
		},
		
		{     
			click: function(e){
				conform(e.dataPoint.temp);	
			  },	
			type: "stackedBar",
			showInLegend: true,
			name: "숙박비",
			axisYType: "secondary",
			dataPoints: roomData
		},
		
		{     
			click: function(e){
				conform(e.dataPoint.temp);	
			  },	
			type: "stackedBar",
			showInLegend: true,
			name: "쇼핑",
			axisYType: "secondary",
			dataPoints: shoppingData
		},
		
		{     
			click: function(e){
				conform(e.dataPoint.temp);
			  },	
			type: "stackedBar",
			showInLegend: true,
			name: "입장료",
			axisYType: "secondary",
			dataPoints: entranceData
		},
		
		{     
			click: function(e){
				conform(e.dataPoint.temp);
			  },	
			type: "stackedBar",
			showInLegend: true,
			name: "기타",
			axisYType: "secondary",
			dataPoints: etcData
		},
		
		
		
		],legend:{
			        cursor:"pointer",
			        itemclick:function(e) {
			          if(typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible){
			            e.dataSeries.visible = false;
			          }
			          else {
			            e.dataSeries.visible = true;
			          }
			          
			          chart.render();
			        }
			      } 
		});
	chart.render();
}

function pie(roundData){
	
	var chart = new CanvasJS.Chart("listasset",
			{
				title:{
					text: "여행 가계부"
				},
				exportEnabled: true,
		                animationEnabled: true,
				legend:{
					verticalAlign: "center",
					horizontalAlign: "left",
					fontSize: 20,
					fontFamily: "Helvetica"        
				},
				theme: "theme2",
				data: [
				{        
					click: function(e){
						/*alert(  "dataSeries Event => Type: "+ e.dataSeries.type+ ", dataPoint { x:" + e.dataPoint.x + ", y: "+ e.dataPoint.y + " }"+"[temp = "+e.dataPoint.temp+"]" );	*/
					  },	
					type: "pie",       
					indexLabelFontFamily: "Garamond",       
					indexLabelFontSize: 20,
					indexLabel: "{label} :  #percent %",
					startAngle:-20,      
					showInLegend: true,
					toolTipContent:"{legendText} : <strong>{y}원</strong>",
					dataPoints: roundData
				}
				]
			});
			chart.render();
	
}

function newGraph(condi){	
	   
		$.ajax("/asset/getAssetJSON/"+condi,{
					method : "GET" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(J , status) { graph(J); }						
									})
	
}

