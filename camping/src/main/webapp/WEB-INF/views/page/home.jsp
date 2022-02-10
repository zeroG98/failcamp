<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<!-- <%@ page session="false" %> -->
<html>
<%@ include file="../head.jsp" %>
<body>
<%@ include file="../navbar.jsp" %>
<div class = "camplist">
	<table border="1">
	<caption>캠핑장 목록</caption>
	<thead>
    <th>캠핑장명</th>
    <th>지역</th>
    <th>업종</th>
  </thead>
  <tbody>
  <c:forEach items="${campInfos}" var="campInfo">
    <tr>
      <%-- <td><a class="campView" href="<c:out value='${campInfo.getHomepage()}'/>"><c:out value="${campInfo.getFacltNm()}"/></a></td> --%>
      <td><a class="campView" href="Campinfo.do"><c:out value="${campInfo.getFacltNm()}"/></td>
      <td><c:out value="${campInfo.getSigunguNm()}"/></td>
      <td><c:out value="${campInfo.getInduty()}"/></td>
    </tr>
  </c:forEach>
  </tbody>
	</table>
</div>
</body>
</html>