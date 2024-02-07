<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Instagram Page</title>
    <script>
        function submitForm() {
            var instagramUrl = document.getElementById("instagramUrl").value;

            // Create a new FormData object and append the Instagram URL
            var formData = new FormData();
            formData.append("instagramUrl", instagramUrl);

            // Create a new XMLHttpRequest object
            var xhr = new XMLHttpRequest();

            // Get the context path from a hidden input field in the form
            var contextPath = document.getElementById("contextPath").value;
            console.log("here1");
            // Specify the POST request method, endpoint, and set up a callback function
            xhr.open("POST", "http://localhost:8083/project2/instaurl/processUrl", true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // Handle the response if needed
                    console.log(xhr.responseText);
                }
            };

            // Send the POST request with the form data
            xhr.send(formData);
        }
    </script>
</head>
<body>
    <form onsubmit="event.preventDefault(); submitForm();">
        <input type="hidden" id="contextPath" value="${pageContext.request.contextPath}">
        <label for="instagramUrl">Enter Instagram URL:</label>
        <input type="text" id="instagramUrl" name="instagramUrl" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
