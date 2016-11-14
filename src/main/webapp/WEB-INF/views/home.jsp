<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/style.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.js"></script>
<title>Fin Math</title>
</head>
<body>


<div class="header"></div>
<div class="main">
<div class="calculat">
<sf:form action="adddata" method="GET">
<fieldset>
start date:<input type="date" name="start" placeholder="startdate">
<br>
<br>
end date: <input type="date" name="end" placeholder="enddate"> 
<br>
<br>
d (%): <input type="text" name="d" placeholder="d">
</fieldset>
<br>
<input type="submit" value="add" class="btn">
</sf:form>
<table>
<th>START DATE</th>
<th>END DATE</th>
<th>D</th>
<c:forEach   items="${dates}" var="data" >

<tr>
<td>${data.start}</td>
<td>${data.end}</td>
<td>${data.d}</td>
</tr>
</c:forEach>
</table>
<br>
<a href="clear" class="btn" >clear</a>


<sf:form action="calculate" method="GET" >

<br>
<br>
<fieldset>
simple<input type="radio" name="T" value="1">
commercial<input type="radio" name="T" value="2">
exact<input type="radio" name="T" value="3">
</fieldset>
<br>
FIND:
<fieldset>
<br>
FV <input type="radio" name="radioCALC" value="FV"> 
<input type="text" name="PV" placeholder="PV">
<br>
PV <input type="radio" name="radioCALC" value="PV"> 
<input type="text" name="FV" placeholder="FV">

</fieldset>
<br>
<fieldset>
Average interest rate 
<br>
<input type="radio" name="average" value="true"> 
</fieldset>
<br>
<fieldset>
Inflation
<br>
yes<input type="radio" name="inflation" value="true"> 
no<input type="radio" name="inflation" value="false"> 
<br>
tau(%):<input type="text" name="tau" placeholder="tau">

</fieldset>
<br>
<input type="submit" value="calculate" class="btn">
</sf:form>



	
<table>
<th>PV</th>
<th>FV</th>
<th>AVERAGE</th>
<th>PV INFLATION</th>
<th>FV INFLATION</th>
<c:forEach   items="${results}" var="data" >

<tr>
<td>${data.PV}</td>
<td>${data.FV}</td>
<td>${data.averD}</td>
<td>${data.PVinf}</td>
<td>${data.FVinf}</td>
</tr>
</c:forEach>

</table>
<br>
<a href="clearres" class="btn">clear</a>
</div>


<div id="plots">
	<div id="myDiv1" style="width:400px; height:400px;"></div>
<div id="myDiv2" style="width:400px; height:400px;"></div>
<div id="myDiv3" style="width:400px; height:400px;"></div>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<script>

var graph1=document.getElementById('myDiv1');
var graph2=document.getElementById('myDiv2');
var graph3=document.getElementById('myDiv3');
var FV1 = [];
var FV2 = [];
var FV3 = [];
var nmas =[];
var dmas =[];
var tmas=[];
var n=0;
var d=0;
var t=0;
for(var i=0; i<10; i++)
	{

	n=n+30;
	nmas[i]=n;
	d=d+0.1
	dmas[i]=d*100;
	t=t+0.1;
	tmas[i]=t*100;

	FV1[i]=1000/(1-0.15*n/365);
	FV2[i]=1000/(1-d*1/2);
	FV3[i]=1000/(1-((t+0.15)/(1+t*1/2))*1/2);
	};

for (var i = 0; i < 10; i++) {
var trace1 = {
  x: nmas,
  y: FV1,

  mode: 'lines+markers',
  name: 'FV(n)'
}};

for (var i = 0; i < 10; i++) {
var trace2 = {
  y: FV2,
  x: dmas,

  mode: 'lines+markers',
  name: 'FV(d)'
}};

for (var i = 0; i < 10; i++) {
var trace3 = {
  y: FV3,
  x: tmas,

  mode: 'lines+markers',
  name: 'FV(tau)'
}};

var data1 = [ trace1 ];
var data2 = [ trace2 ];
var data3= [ trace3 ];

var layout1 = {
  title:'Залежність нарощеної суми від часу'
};
var layout2 = {
  title:'Залежність нарощеної суми від ставки d'
};
var layout3 = {
  title:'Залежність нарощеної суми від  інфляції'
};

Plotly.newPlot(graph1, data1, layout1);
Plotly.newPlot(graph2, data2, layout2);
Plotly.newPlot(graph3, data3, layout3);
</script>

</div>


</div>
</body>
</html>