setUri
======

setUri是[请求处理器](requesthandler.md)的一种，主要用来改写当前的URI.在实际操作中，更多的用来实现参数的编码。

### 实现类

com.alogic.xscript.rest.request.serUri

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 3 | path | rest服务路径，最终服务调用的URL为${base}/${path},缺省将采用rest方法语句中的path |

可以通过XML子节点配置来为path附加上Query参数，如下：
| 编号 | XPath | 说明 |
| ---- | ---- | ---- |
| 1 | param[*] | 定义一个参数项，参数可以有多个 |
| 1.1 | param[*]/@id | 参数项的id | 
| 1.2 | param[*]/@value | 参数项的值,支持变量计算 |

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
				</request>
			</mPost>
		</rest>
	</script>

```

上面的代码相当于：

```xml

	<?xml version="1.0"?>
	<script>
		<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
		
		<set id="username" value="alogic"/>
		<set id="password" value="alogic"/>
		
		<rest base="http://localhost/services">
			<mPost path="/login/Login?userId=${username}&pwd=${password}">
			</mPost>
		</rest>
	</script>

```

区别在于：使用setUri可以对参数值采用URLEncoder进行编码，减少参数取值中存在URL保留字符的风险。

