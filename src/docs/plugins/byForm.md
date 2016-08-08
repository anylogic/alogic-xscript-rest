byForm
======

byForm是[请求处理器](requesthandler.md)的一种，以application/x-www-form-urlencoded形式对输入参数进行编码，并作为http请求body。

### 实现类

com.alogic.xscript.rest.request.ByForm

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |


表单数据通过XML子节点配置，如下：
| 编号 | XPath | 说明 |
| ---- | ---- | ---- |
| 1 | field[*] | 定义一个表单项，表单可以有多个 |
| 1.1 | field[*]/@id | 表单项的id | 
| 1.2 | field[*]/@value | 表单项的值,支持变量计算 |


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
					<byForm>
						<field id="userId" value="${username}"/>
						<field id="pwd" value="${password}"/>
					</byForm>
				</request>
			</mPost>
		</rest>
	</script>

```
