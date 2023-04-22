<%--
  Created by IntelliJ IDEA.
  User: sleek
  Date: 2023-04-21
  Time: 오후 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-with,initial-scale=1.0">
        <title>스프링부트 파일 업로드 만들기</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
    </head>
    <body>
        <div id="contents">
            <h3>글 보기</h3>
            <table>
                <tr>
                    <th width="60" align="center">제목</th>
                    <td>${file.title}</td>
                </tr>
                <tr>
                    <th width="60" align="center">작성일</th>
                    <td><fmt:formatDate value="${file.wdate}" type="both" pattern="yyyy/MM/dd a hh:mm:ss"/></td>
                </tr>
                <tr>
                    <th width="60" align="center">내용</th>
                    <td valign="top" height="200">
                        ${file.content}<br/><br/>
                            <img src="${pageContext.request.contextPath}${imageUrl}" alt="image">
                    </td>
                </tr>
            </table><br/><br/>
            <div align="center">
                <input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/filupload/fileUploadEdit.do?no=${file.no}'"/>&nbsp;
                <input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/filupload/fileUploadRemove.do?no=${file.no}'"/>&nbsp;
                <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/index'"/>&nbsp;
            </div>
        </div>
    </body>
</html>





