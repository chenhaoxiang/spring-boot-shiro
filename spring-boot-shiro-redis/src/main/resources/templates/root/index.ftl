<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
</head>
<body>
<h1>主页</h1>
<ul>
    <li>
    ${(user.username)!"游客"}, 欢迎您的访问!
    </li>
    <@shiro.hasPermission name="user:select">
        <li>user:select</li>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="user:update">
        <li>user:update</li>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="user:delete">
        <li>user:delete</li>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="user:select">
        <li>user:select</li>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="test">
        <li>test</li>
    </@shiro.hasPermission>
</ul>
</body>
</html>