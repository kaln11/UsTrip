<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBlWKR_u9NsT-3h0mdZ_5gg-aB4Eh58Ajo&v=3.exp&libraries=places&region=kr"></script>

<script type="text/javascript" >   
var map;
var target;

jQuery(document).ready(function(){
	
    target = [
              {lat: 36.8488, lng: 127.7792},
              {lat:37.4923, lng:127.0292}
            ];
	
	var position = {
			lon : 37.5651,
			lat : 126.98955
	}
/* 	var position = {
			lon : $('.cityX').val(),
			lat : $('.cityY').val()
	} */
	/* 37.5651, 126.98955 */
    google.maps.event.addDomListener(window, 'load', initialize(target));
 
function initialize(target){
	console.log(target);
	
    if($("#map").length) {
	    var mapOptions = { //구글 맵 옵션 설정
	        zoom : 9, //기본 확대율
	      /*   center : new google.maps.LatLng(target.lon, data.lat), // 지도 중앙 위치 */
	        center: {lat: 36.8488, lng: 127.7792} ,
	        scrollwheel : true, //마우스 휠로 확대 축소 사용 여부
	        mapTypeControl : false, //맵 타입 컨트롤 사용 여부
	        
	        disableDefaultUI : true, //기본 UI 사용 여부
	        disableDoubleClickZoom : true, //더블클릭 중심으로 확대 사용 여부
	        draggable : true, //지도 드래그 이동 사용 여부
	        keyboardShortcuts : true,
    	};
 
    var map = new google.maps.Map(document.getElementById('map'), mapOptions); //구글 맵을 사용할 타겟
    


var i;

for (i = 0; i < target.length; i++) { 
marker = new google.maps.Marker({
position: new google.maps.LatLng(target[i]),
map: map
});
marker.setMap(map);
}

                 var polylinePath = new google.maps.Polyline({
                  path: target,
                  geodesic: true,
                  strokeColor: '#FF0000',
                  strokeOpacity: 1.0,
                  strokeWeight: 2
                });

					 polylinePath.setMap(map);

    
    
    
    /* var image = 'http://cheolguso.com/img/iconimg.png'; //마커 이미지 설정 */
 
  /*   var marker = new google.maps.Marker({ //마커 설정
        map : map,
        position : map.getCenter(), //마커 위치
        icon : image //마커 이미지 
    }); */
    
    google.maps.event.addDomListener(window, "resize", function() { //리사이즈에 따른 마커 위치
        var center = map.getCenter();
        google.maps.event.trigger(map, "resize");
        map.setCenter(center); 
    });
    
    
    }//if-end
}//function-end
 
});//jQuery-en
</script>

	<div id="map" style="width: 100%; height: 400px;"></div>
	
 		<c:forEach items="${city}" var="city" varStatus="status">
 			<hr/>
			<input type="text" class="cityX" value="${city.cityX}">
			<input type="text" class="cityY" value="${city.cityY}">
		</c:forEach>



