<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your Form</title>
    <style><%@include file="/WEB-INF/css/instaPage.css"%></style>
</head>
<body>

    <form id="myForm" method="post" action="<%= request.getContextPath() %>/instaurlapi/processUrl">
        <label for="urlInput">Enter URL:</label>
        <input type="text" id="urlInput" name="instagramUrl" required>
        <br>
        <input type="submit" value="Submit">
    </form>

    <script>
        function submitForm() {
            var form = document.getElementById("myForm");
            form.action = "<%= request.getContextPath() %>/instaurlapi/processUrl";
            form.submit();
        }
    </script>
</body>
</html>
