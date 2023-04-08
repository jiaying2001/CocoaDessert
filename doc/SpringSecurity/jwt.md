## 验证JWT流程 token-based authentication
组件：
1. JwtRequestFilter: 如果token证实，创建authentication并且设置到Context。否则什么都不做。
2. JwtDetailServive: 从服务器拉取用户信息。
3. JwtTokenUtil: 创建和证实token。

流程:
1. 新请求由于未证实重定向到登入页面。
2. 如果通过验证，返回token。
3. 带token请求会在JwtRequestFilter拦截并设置authentication，此时请求会顺利通过之后的过滤器。
4. 到达对应的控制器

![](https://github.com/jiaying2001/CocoaDessert/blob/main/doc/SpringSecurity/img/jwt-auth-flow.jpeg)
