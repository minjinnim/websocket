<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	var websocket=new WebSocket("ws://192.168.0.70:8080/datasocket");
	
	websocket.onopen=function(message){
		console.log('open');
	}
	websocket.onclose=function(message){
		console.log('close');
	}
	websocket.onerror=function(message){
		console.log('error');
	}
	websocket.onmessage=function(message){
		console.log(message.data);
		
		var data=message.data.split(":");
		data.forEach(element => console.log(element));
		

		document.querySelector("#one").innerHTML=data[0];
		document.querySelector("#two").innerHTML=data[1];
		document.querySelector("#three").innerHTML=data[2];
		document.querySelector("#four").innerHTML=data[3];
		
	}
	
	function send(num){
		//var number=num;
		websocket.send(num);
	}
	
	
	
</script>
<style>
	button{
		width:150px;
		height:150px;
		background-color:blue;
		border-radius:100px;
	}
</style>
</head>
<body>

	<button onclick="send(1)" id="b1">send1</button>
	<button onclick="send(2)" id="b2">send2</button>
	<button onclick="send(3)" id="b3">send3</button>
	<button onclick="send(4)" id="b4">send4</button>
	
	<button id="one"></button>
	<button id="two"></button>
	<button id="three"></button>
	<button id="four"></button>
	
</body>
</html>