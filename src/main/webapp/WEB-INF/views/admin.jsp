<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
    <div class="container">
        <c:if test="${pageContext.request.userPrincipal.name!=null}">
            <form id="logoutForm" method="post" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>Admin page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
        </c:if>
    </div>

    <h2>Book List</h2>

    <c:if test="${!empty listBooks}">
        <table class="tg">
            <tr>
                <th width="80">ID</th>
                <th width="120">Title</th>
                <th width="120">Author</th>
                <th width="120">Price</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${listBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.bookTitle}</td>
                    <td>${book.bookAuthor}</td>
                    <td>${book.price/100}${book.price%100}</td>
                    <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h1>Add a Book</h1>

    <c:url var="addAction" value="/admin/add"/>

    <form:form action="${addAction}" commandName="book">
        <table>
            <c:if test="${!empty book.bookTitle}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="bookTitle">
                        <spring:message text="Title"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="bookTitle"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="bookAuthor">
                        <spring:message text="Author"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="bookAuthor"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="price">
                        <spring:message text="Price"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="price"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty book.bookTitle}">
                        <input type="submit"
                               value="<spring:message text="Edit Book"/>"/>
                    </c:if>
                    <c:if test="${empty book.bookTitle}">
                        <input type="submit"
                               value="<spring:message text="Add Book"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>

</body>
</html>
