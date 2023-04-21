<%--
  Created by IntelliJ IDEA.
  User: sleek
  Date: 2023-04-21
  Time: 오후 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>스프링부트 파일 업로드 만들기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<div id="contents">
    ${pageContext.request.contextPath}/dd<br/>
    ${file.title}<br/>
    ${file.content}<br/>
    <fmt:formatDate value="${file.wdate}" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/><br/>
     <img src="${pageContext.request.contextPath}${imageUrl}" alt="image">

</div>
</body>
</html>
