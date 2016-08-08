byJson
======

byJson是[请求处理器](requesthandler.md)的一种，由其子语句构造一份JSON文档，并作为http请求body。

byJson支持子语句，并创建一份JSON供子语句拼接，

### 实现类

com.alogic.xscript.rest.request.ByJson

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |


### 案例

向服务器提交下列文档：

```

	{
	    "userId": "alogic", 
	    "pwd": "alogic"
	}

```

脚本代码如下：

```xml

	<?xml version="1.0"?>
	<script>
		<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
		
		<set id="username" value="alogic"/>
		<set id="password" value="alogic"/>
		
		<rest base="http://localhost/services">
			<mPost path="/login/Login">
				<request>
					<byJson>
				        <get id="userId" value="${username}"/>
				        <get id="pwd" value="${password}"/>
					</byJson>
				</request>
			</mPost>
		</rest>
	</script>

```