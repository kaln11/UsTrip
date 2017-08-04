<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="EUC-KR">
<title>Insert title here</title>

<style type="text/css">
        html, body {
          height: 100%;
          margin: 0;
          padding: 0;
          overflow:hidden
        }
 
        #map {
          height: 100%;
          margin-left: 20%;
        }
          </style>
</head>
<body>
<div id="map"></div>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyBlWKR_u9NsT-3h0mdZ_5gg-aB4Eh58Ajo&sensor=false"></script>
<script src="/js/gmaps.js"></script>
 <script type="text/javascript">
  var delay = 100;
  var infowindow = new google.maps.InfoWindow();
  
  
  var latlng = new google.maps.LatLng(21.0000, 78.0000);
  var mapOptions = {
    zoom: 8,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  var geocoder = new google.maps.Geocoder(); 
  var map = new google.maps.Map(document.getElementById("map"), mapOptions);
  var bounds = new google.maps.LatLngBounds();

  function geocodeAddress(address, next) {
    geocoder.geocode({address:address}, function (results,status)
      { 
         if (status == google.maps.GeocoderStatus.OK) {
          var p = results[0].geometry.location;
          var lat=p.lat();
          var lng=p.lng();
          createMarker(address,lat,lng);
        }
        else {
           if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT) {
            nextAddress--;
            delay++;
          } else {
                        }   
        }
        next();
      }
    );
  }
 function createMarker(add,lat,lng) {
   var contentString = add;
   var marker = new google.maps.Marker({
     position: new google.maps.LatLng(lat,lng),
     map: map,
           });

  google.maps.event.addListener(marker, 'click', function() {
     infowindow.setContent(contentString); 
     infowindow.open(map,marker);
   });

   bounds.extend(marker.position);

 }
 var locations = [ '서울', '인천', '대전', '대구', '광주광역시', '부산', '울산',
   				'세종특별자치시', '가평군', '고양시', '과천시', '광명시', '광주시', '구리시', '군포시',
   				'김포시', '남양주시', '동두천시', '부천시', '성남시', '수원시', '시흥시', '안산시',
   				'안성시', '안양시', '양주시', '양평군', '여주시', '연천군', '오산시', '용인시', '의왕시',
   				'	의정부시	', '	이천시	', '	파주시	', '	평택시	', '	포천시	', '	하남시	', '	화성시',
   				'	강릉시	', '	고성군	', '	동해시	', '	삼척시	', '	속초시	', '	양구군	', '	양양군	',
   				'	영월군	', '	원주시	', '	인제군	', '	정선군	', '	철원군	', '	춘천시	', '	태백시	',
   				'	평창군	', '	홍천군	', '	화천군	', '	횡성군	', '	괴산군	', '	단양군	', '	보은군	',
   				'	영동군	', '	옥천군	', '	음성군	', '	제천시	', '	진천군	', '	청원군	', '	청주시	',
   				'	충주시	', '	증평군	', '	공주시	', '	금산군	', '	논산시	', '	당진시	', '	보령시	',
   				'	부여군	', '	서산시	', '	서천군	', '	아산시	', '	예산군	', '	천안시	', '	청양군	',
   				'	태안군	', '	홍성군	', '	계룡시	', '	경산시	', '	경주시	', '	고령군	', '	구미시	',
   				'	군위군	', '	김천시	', '	문경시	', '	봉화군	', '	상주시	', '	성주군	', '	안동시	',
   				'	영덕군	', '	영양군	', '	영주시	', '	영천시	', '	예천군	', '	울릉군	', '	울진군	',
   				'	의성군	', '	청도군	', '	청송군	', '	칠곡군	', '	포항시	', '	거제시	', '	거창군	',
   				'	고성군	', '	김해시	', '	남해군	', '	마산시	', '	밀양시	', '	사천시	', '	산청군	',
   				'	양산시	', '	의령군	', '	진주시	', '	진해시	', '	창녕군	', '	창원시	', '	통영시	',
   				'	하동군	', '	함안군	', '	함양군	', '	합천군	', '	고창군	', '	군산시	', '	김제시	',
   				'	남원시	', '	무주군	', '	부안군	', '	순창군	', '	완주군	', '	익산시	', '	임실군	',
   				'	장수군	', '	전주시	', '	정읍시	', '	진안군	', '	강진군	', '	고흥군	', '	곡성군	',
   				'	광양시	', '	구례군	', '	나주시	', '	담양군	', '	목포시	', '	무안군	', '	보성군	',
   				'	순천시	', '	신안군	', '	여수시	', '	영광군	', '	영암군	', '	완도군	', '	장성군	',
   				'	장흥군	', '	진도군	', '	함평군	', '	해남군	', '	화순군	', '	제주도	', '	서귀포시',
   				'	제주시	'
   		];
  var nextAddress = 0;
  function theNext() {
    if (nextAddress < locations.length) {
      setTimeout('geocodeAddress("'+locations[nextAddress]+'",theNext)', delay);
      nextAddress++;
    } else {
      map.fitBounds(bounds);
    }
  }
  theNext();

</script>

</body>
</html>