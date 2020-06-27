<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>文章新增</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<div class="container">
    <tags:nav/>
    <div class="page-header">
        <h3>文章管理${action}</h3>
    </div>
    <form method="post" action="${ctx}/post/${action}">
        <input type="hidden" name="id" value="${dict.id}">
        <p>文章：<input type="text" class="inpu-medium" name="content" value="${dict.content}"></p>
        <p>创建时间：<input type="date" class="input-medium" name="createdAt" value="${dict.createdAt}"></p>
        <p>最后修改时间：<input type="date" class="input-medium" name="lastModifiedAt" value="${dict.lastModifiedAt}"></p>
        <p>标题：<input type="text" class="input-medium" name="title" value="${dict.title}"></p>
       文章类型： <select name="category">
            <c:forEach items="${categories}" var="category">
                <option value="${category.id }"  <c:if test="${user.user_id==category.id}"><c:out value="selected"/></c:if>   >
                        ${category.name}
                </option>
            </c:forEach>
        </select>

        <p><button type="submit" class="btn btn-primary">保存</button></p>
    </form>
</div>

<script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
