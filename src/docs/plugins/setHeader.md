setHeader
=========

setHeader是[请求处理器](requesthandler.md)的一种，主要用来增加http头。

### 实现类

com.alogic.xscript.rest.request.setHeader

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 3 | name | http头ID |
| 4 | value | http头的取值，支持变量计算 |

### 案例

向登录服务提交表单：

```xml

	<?xml version="1.0"?>
	<script>
		<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
		
		<set id="username" value="alogic"/>
		<set id="password" value="alogic"/>
		
		<rest base="http://localhost/services">
			<mPost path="/login/Login">
				<request>
					<setUri>
						<param id="userId" value="${username}"/>
						<param id="pwd" value="${password}"/>
					</setUri>
					<setHeader name="User-Agent" value="alogic ketty"/>
				</request>
			</mPost>
		</rest>
	</script>

```


