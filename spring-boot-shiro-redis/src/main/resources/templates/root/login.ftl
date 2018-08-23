<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body style="text-align: center">
<h1>登录页面</h1>
<form action="/toLogin" method="post">
    <input id="username" name="username" type="text" placeholder="用户名"/>
    <input id="password" name="password" type="text" placeholder="密码"/>
    <input type="checkbox" name="rememberMe"/>记住我
    <button type="submit">登录</button>
</form>
</body>
</html>