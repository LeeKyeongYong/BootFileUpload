<%--
  Created by IntelliJ IDEA.
  User: sleek
  Date: 2023-04-21
  Time: 오후 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>파일 업로드 코드</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="contents">
    <h3>오류!!</h3>
    <table>
        <tr>
            <th width="60" align="center">원인</th>
            <td>${exception.message}</td>
        </tr>
    </table><br>
    <div align="center">
        <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/index'">
    </div>
</div>
</body>
</html>

