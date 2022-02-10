<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
<!-- <%@ page session="false" %> -->
<html>
<%@ include file="../head.jsp" %>
<body>
<%@ include file="../navbar.jsp" %>
<div class = "campinfo">
<table style="undefined;table-layout: fixed; width: 360px">
<colgroup>
<col style="width: 118px">
<col style="width: 242px">
</colgroup>
<caption>캠핑장 정보</caption>
<tbody>
<c:forEach items="${campInfos}" var="campInfo">
  <tr>
    <td>주소</td>
    <td><c:out value="${campInfo.getAddr1()}"/> <c:out value="${campInfo.getAddr2()}"/></td>
  </tr>
  <tr>
    <td>홈페이지</td>
    <td><c:out value="${campInfo.getHomepage()}"/></td>
  </tr>
  <tr>
    <td>문의및안내</td>
    <td><c:out value="${campInfo.getTel()}"/></td>
  </tr>
  <tr>
    <td>운영 요일</td>
    <td><c:out value="${campInfo.getOperDeCl()}"/></td>
  </tr>
  <tr>
    <td>시설</td>
    <td></td>
  </tr>
  <tr>
    <td>애완동물 동반 가능 여부</td>
    <td><c:out value="${campInfo.getAnimalCmgCl()}"/></td>
  </tr>
  <tr>
    <td>캠핑장비대여</td>
    <td><c:out value="${campInfo.getEqpmnLendCl()}"/></td>
  </tr>
  <tr>
    <td>캠핑장 소개</td>
    <td><c:out value="${campInfo.getIntro()}"/></td>
  </tr>
</c:forEach>
</tbody>
</table>
</div>
</body>
</html>