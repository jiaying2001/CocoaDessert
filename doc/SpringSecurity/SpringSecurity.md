

## Servlet Filters
从请求的接受到请求的响应过程中，请求需要经过一系列的过滤器，如下图：

![](https://github.com/jiaying2001/CocoaDessert/blob/main/doc/SpringSecurity/img/servlet-container.png.png)

## Spring Security Architecture
Spring Security对Java服务器的支持是基于Servlet Filter来实现的。由于servlet container并不能察觉到spring-defiend beans，通过servlet容器的标准去注册一个名为delegatingFilterProxy代理，并委托给一个实现了Filter接口名为FilterChainProxy的spring bean，从而建立了servlet容器和spring beans的桥梁。一个filter并不能满足需求，FilterChainProxy代理了许多条过滤器链，即一系列的过滤器。为了解决一个请求匹配多条过滤器链，过滤器链通过实现Ordered接口有序地排列。有序地遍历并尝试匹配请求和链条，第一个匹配成功的过滤器链将会被调用，如下图：

![](https://github.com/jiaying2001/CocoaDessert/blob/main/doc/SpringSecurity/img/multi-securityfilterchain.png)

## Handling Security Exceptions
表明一个登入失败状态，无疑运用的是异常去表明。ExceptionTranslationFilter将异常转换成一个http响应。
```java
try {
	filterChain.doFilter(request, response); // 检测请求是否已登入，通过BasicAuthenticationFilter。
} catch (AccessDeniedException | AuthenticationException ex) { 
	if (!authenticated || ex instanceof AuthenticationException) {
		startAuthentication(); // 没有登入，则跳转至登入页面； where it calls AuthenticationEntryPoint rendering login page.
	} else {
		accessDenied();
	}
}
```
##  A comprehensive list of Spring Security Filter ordering
ForceEagerSessionCreationFilter

ChannelProcessingFilter

WebAsyncManagerIntegrationFilter

SecurityContextPersistenceFilter

HeaderWriterFilter

CorsFilter

CsrfFilter

LogoutFilter

OAuth2AuthorizationRequestRedirectFilter

Saml2WebSsoAuthenticationRequestFilter

X509AuthenticationFilter

AbstractPreAuthenticatedProcessingFilter

CasAuthenticationFilter

OAuth2LoginAuthenticationFilter

Saml2WebSsoAuthenticationFilter

UsernamePasswordAuthenticationFilter

DefaultLoginPageGeneratingFilter

DefaultLogoutPageGeneratingFilter

ConcurrentSessionFilter

DigestAuthenticationFilter

BearerTokenAuthenticationFilter

BasicAuthenticationFilter

RequestCacheAwareFilter

SecurityContextHolderAwareRequestFilter

JaasApiIntegrationFilter

RememberMeAuthenticationFilter

AnonymousAuthenticationFilter

OAuth2AuthorizationCodeGrantFilter

SessionManagementFilter

ExceptionTranslationFilter

FilterSecurityInterceptor

SwitchUserFilter
