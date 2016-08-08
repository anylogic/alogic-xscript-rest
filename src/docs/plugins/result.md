result
======

result是[响应处理器](responsehandler.md)的一种，用来提取http返回结果代码和原因，并作为变量存入上下文。

### 实现类

com.alogic.xscript.rest.request.Result

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 3 | code | http结果代码的变量id，缺省为httpCode |
| 4 | reason | http结果原因的变量id,缺省为httpReason |

### 案例

从服务响应中提取结果代码.

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
				<response>
					<result/>
					<log msg="The http code is ${httpCode}"/>
					<log msg="The http reason is ${httpReason}"/>
				</response>
			</mPost>
		</rest>
	</script>

```
