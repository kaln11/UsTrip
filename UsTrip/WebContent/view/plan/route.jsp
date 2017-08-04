<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Displaying text directions with <code>setPanel()</code></title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #right-panel {
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }

      #right-panel select, #right-panel input {
        font-size: 15px;
      }

      #right-panel select {
        width: 100%;
      }

      #right-panel i {
        font-size: 12px;
      }
      #right-panel {
        height: 100%;
        float: right;
        width: 390px;
        overflow: auto;
      }
      #map {
        margin-right: 400px;
      }
      #floating-panel {
        background: transparent;
        padding: 5px;
        font-size: 14px;
        font-family: Arial;
        border: 0;
        box-shadow: 0;
        display: none;
      }
      @media print {
        #map {
          height: 500px;
          margin: 0;
        }
        #right-panel {
          float: none;
          width: auto;
        }
      }
    </style>
  </head>
  <body>
    <div id="floating-panel">
    </div>
     <form id="form">
     <input type='hidden' id="day" value="2017/07/05" />
    출발시간을 변경하세요<select id='Time' name='Time' class='Time'>
    		   <option>01:00</option>
    		   <option>02:00</option>
    		   <option>03:00</option>
    		   <option>04:00</option>
    		   <option>05:00</option>
    		   <option>06:00</option>
    		   <option>07:00</option>
    		   <option selected='selected'>08:00</option>
    		   <option>09:00</option>
    		    <option>10:00</option>
    		    <option>11:00</option>
    		    <option>12:00</option>
    		    <option>13:00</option>
    		    <option>14:00</option>
    		    <option>15:00</option>
    		    <option>16:00</option>
    		    <option>17:00</option>
    		    <option>18:00</option>
    		    <option>19:00</option>
    		    <option>20:00</option>
    		    <option>21:00</option>
    		    <option>22:00</option>
    		    <option>23:00</option>
    		    <option>24:00</option>
    		    </select><br>
    </form>
    <div id="right-panel"></div>
    <div id="map"></div>
    <script>
    var startRoute = decodeURIComponent(document.location.href.split("?")[1]); 
	var endRoute = decodeURIComponent(document.location.href.split("?")[2]); 
      function initMap() {
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var directionsService = new google.maps.DirectionsService;
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 8,
          center: {lat: 37.5172, lng: 127.0473}
        });
        
        directionsDisplay.setMap(map);
        directionsDisplay.setPanel(document.getElementById('right-panel'));
        var control = document.getElementById('floating-panel');
        control.style.display = 'block';
        map.controls[google.maps.ControlPosition.TOP_CENTER].push(control);
        
        google.maps.event.addListenerOnce(map, 'idle', function(){

        	calculateAndDisplayRoute(directionsService, directionsDisplay);

    	  });

        var onChangeHandler = function() {
          calculateAndDisplayRoute(directionsService, directionsDisplay);
        };
        document.getElementById('Time').addEventListener('change', onChangeHandler);
      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    	  
    	  
     	var departureDateTime = $("#day").val()+" "+$("#Time").val();
    	
        var start = startRoute;
        var end = endRoute;
        directionsService.route({
          origin: start,
          destination: end,
          travelMode: 'TRANSIT',
          transitOptions: {
        	    departureTime: new Date(departureDateTime)
        	  },
          provideRouteAlternatives : true
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
          
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDgS9rLrRIo9sBKIyAK7Opc5fMeVvbzhy4&callback=initMap">
    </script>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
  
  </body>
</html>