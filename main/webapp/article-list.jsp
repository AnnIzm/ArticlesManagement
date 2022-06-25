<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>News Management</title>
</head>
<body>
<div style="text-align: center;">
    <h1>News Management</h1>
    <h2>
        <a href="new">Add New Article</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All News</a>

    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Articles</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Date</th>
            <th>Brief</th>
            <th>Content</th>
        </tr>
        <c:forEach var="article" items="${listArticle}">
            <tr>
                <td><c:out value="${article.id}" /></td>
                <td><c:out value="${article.title}" /></td>
                <td><c:out value="${article.date}" /></td>
                <td><c:out value="${article.brief}" /></td>
                <td><c:out value="${article.content}" /></td>
                <td>
                    <a href="edit?id=<c:out value='${article.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${article.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>