<%--
  Created by IntelliJ IDEA.
  User: sleek
  Date: 2023-04-21
  Time: 오후 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>스프링부트 파일 업로드 만들기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/util.js"></script>
    <script>
        const checkForm=(f) => {
            let result=true;
            if(isNull(f.title,document.getElementById("title_error"),"제목을 입력하세요!!")) result=false;
            if(isNull(f.content,document.getElementById("content_error"),"내용을 입력하세요!!")) result=false;
            if(isNull(f.picture,document.getElementById("picture_error"),"파일을 선택하세요!!")) result=false;
            return result;
        }
    </script>
</head>
<body>
    <div id="contents">
            <h3>새글쓰기</h3>
            <form method="post" action="" enctype="multipart/form-data" onsubmit="return checkForm(this)">
                <table>
                        <tr>
                            <th width="60" align="center"><label for="title">제목</label></th>
                            <td>
                                <input type="text" name="title" id="title" size="60" value="" />
                                <span class="error-text" id="title_error"></span>
                            </td>
                        </tr>
                        <tr>
                            <th align="center"><label for="content">내용</label></th>
                            <td>
                                <textarea rows="20" cols="80" name="content" id="content" ></textarea>
                                <span class="error-text" id="content_error"></span>
                            </td>
                        </tr>
                        <tr>
                            <th align="center"><label for="picture">사진</label></th>
                            <td>
                                <input type="file" name="picture" id="picture" size="60" >
                                <br/><br/>
                                <span class="error-text" id="picture_error"></span>
                            </td>
                        </tr>
                </table><br/>
                <div align="center">
                    <input type="submit" value="확인">
                    <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/index'">
                </div>
            </form>
    </div>
</body>
</html>
