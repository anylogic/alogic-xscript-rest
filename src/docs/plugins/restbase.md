restbase
========
 
restbase提供了rest调用的基本功能。所有的请求方法都是从这里继承，所谓的请求方法包括：

- [GET方法mGet](mGet.md)
- [POST方法mPost](mPost.md)
- [OPTIONS方法mOptions](mOptions.md)
- [PATCH方法mPatch](mPatch.md)
- [PUT方法mPut](mPut.md)
- [DELETE方法mDelete](mDelete.md)
- [TRACE方法mTrace](mTrace.md)

restbase需要配置[请求处理器](requesthandler.md)和[响应处理器](responsehandler.md)来进行http调用的输入输出处理。

在请求处理器中，您可以：
- 改写URI
- 添加/修改http头
- 添加Http请求参数
- 添加Http请求Form
- 添加Http请求文档

在响应处理器中，您可以
- 读取http结果代码和原因
- 处理http结果文档
- 读取Http头

### 配置参数

所有方法的http调用支持下列参数

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | path | rest服务路径，最终服务调用的URL为${base}/${path}，此路径可以被setUri插件所改写 |
| 2 | pid | domain的上下文对象id,缺省为$rest-domain |
| 3 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 4 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 5 | httpResponseId | HttpResponse对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpResponse对象，本id缺省为httpResponse |

### 配置方法

根据应用具体情况，restbase需要另外配置一个到多个请求处理器和响应处理器。请求处理器可以配置在request节点下，而响应处理器则配置在response节点下。

例如：
```xml

	<script>
		<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
		<rest base="http://www.weather.com.cn/data">
			<mGet path="/sk/${city}.html">
				<request>
					<!--这里是requesthandler-->
				</request>
				<response>
					<!--这里是reponsehandler-->
				</response>
			</mGet>
		</rest>
	</script>
	
```