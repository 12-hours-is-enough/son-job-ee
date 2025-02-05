<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Job List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
    <script defer src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</head>
<body>
    <h1>Job Listings</h1>
    <table id="jobTable" border="1">
        <thead>
            <tr>
                <th>Job ID</th>
                <th>Company ID</th>
                <th>Location</th>
                <th>Category</th>
                <th>Salary</th>
                <th>Schedule</th>
                <th>Application Deadline</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="job" items="${jobs}">
                <tr>
                    <td>${job.id}</td>
                    <td>${job.companyId}</td>
                    <td>${job.location}</td>
                    <td>${job.jobCategory}</td>
                    <td>${job.salary}</td>
                    <td>${job.schedule}</td>
                    <td>${job.applicationDeadline}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>