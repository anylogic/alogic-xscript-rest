getHeader
=========

getHeader是[响应处理器](responsehandler.md)的一种，用来提取http头的取值，并将取值存入上下文变量。

### 实现类

com.alogic.xscript.rest.request.getHeader

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 3 | id | 待存入的上下文变量id |
| 4 | name | http头的ID |

### 案例

从服务响应中提取http头。

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
					<getHeader id="cookies" name="Cookie"/>
					
					<log msg="The cookies is ${cookies}"/>
				</response>
			</mPost>
		</rest>
	</script>

```
