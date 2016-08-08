asJson
======

asJson是[响应处理器](responsehandler.md)的一种，用来将服务返回正文解析为JSON文档，并将该文档作为上下文对象，供子语句使用。

asJson支持子语句，通常的做法是将JSON文档中的某些节点[Copy](copy.md)到当前文档，再进行处理。

### 实现类

com.alogic.xscript.rest.request.asJson

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 3 | jsonId | JSON文档的上下文id,缺省为json |

### 案例

调用[www.weather.com.cn](http://www.weather.com.cn)的天气服务，将结果的JSON文档copy到当前文档。

```xml

	<script>
		<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
		
		<!-- 城市:广州 -->
		<set id="city" value="101280101"/>
		
		<rest base="http://www.weather.com.cn/data">
			<mGet path="/sk/${city}.html">
				<response>
					<result/>
					<switch value="${httpCode}">
						<asJson case="200">
							<copy tag="city" path="$.weatherinfo"/>
						</asJson>
						<throw case="default" code="core.rest_failed" msg="远程服务调用错误."/>
					</switch>
				</response>
			</mGet>
		</rest>
	</script>

```
