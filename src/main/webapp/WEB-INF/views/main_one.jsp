<%--
  Created by IntelliJ IDEA.
  User: sleek
  Date: 2023-04-21
  Time: 오후 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>파일 업로드 페이지</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    </head>
    <body>
        <div id="contents">
            <h3>글 목록</h3>
            <table>
                <thead>
                    <tr>
                        <th width="60" align="center">글 번호</th>
                        <th align="center">제목</th>
                        <th width="160" align="center">작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${fieleList}" var="file">
                        <tr>
                            <td align="center">${file.no}</td>
                            <td><a href="${pageContext.request.contextPath}/filupload/fileUploadView.do">${file.title}</a></td>
                            <td align="center"><fmt:formatDate value="${file.wdate}" type="both" pattern="yyyy.mm.dd"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table><br/>
            <div align="center">
                <input type="button" value="새 글 쓰기" onclick="location.href='${pageContext.request.contextPath}/filupload/fileUploadWrite.do'"/>
            </div>
        </div>
    </body>
</html>
