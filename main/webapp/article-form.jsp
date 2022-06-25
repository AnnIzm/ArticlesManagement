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
    <c:if test="${article != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${article == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${article != null}">
                            Edit Article
                        </c:if>
                        <c:if test="${article == null}">
                            Add New Article
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${article != null}">
                    <input type="hidden" name="id" value="<c:out value='${article.id}' />" />
                </c:if>
                <tr>
                    <th>Title: </th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${article.title}' />"/>
                    </td>
                </tr>
                <tr>
                    <th>Brief: </th>
                    <td>
                        <input type="text" name="brief" size="45"
                               value="<c:out value='${article.brief}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Content: </th>
                    <td>
                        <input type="text" name="content" size="45"
                               value="<c:out value='${article.content}' />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
