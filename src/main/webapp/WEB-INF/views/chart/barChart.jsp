<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>
    
<h2>BarChart</h2>

<p>
<%-- <button type="button" onclick="location.href='${path}/chart/pieChart';">PieChart</button>
<button type="button" onclick="location.href='${path}/chart/lineChart';">LineChart</button> --%>
<button type="button" onclick="location.href='${path}/chart/barChart';">BarChart</button>
</p>

<table border="1" width="500px;">
<c:set var="percent" value="0"/>
<c:set var="k" value="0"/>

<c:forEach var="i" begin="1" end="${list.size() }" step="1">
	<c:set var="dto" value="${list[k] }"/>
	
		<c:set var="percent" value="${dto.amount * dto.amountAll / 100 }"/>
	
	<tr>
		<td width="100px;">${dto.productName }</td>
		
		<td>
			<hr style="width:${percent }%; height: 20px; background-color: blue;" align="left">
			(${dto.amount } / ${dto.amountAll } / ${percent }%)
		</td>
	</tr>
	
	<c:set var="k" value="${k = k + 1 }"/>
</c:forEach>

</table>