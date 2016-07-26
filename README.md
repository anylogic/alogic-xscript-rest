alogic-xscript-rest
===================

alogic-xscript-rest是基于xscript2.0的rest插件，提供了调用rest服务所需的相关指令。

### 案例

调用www.weather.com.cn的天气服务接口，查询广州的天气情况。

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

### 版本历史
    - 0.0.1 [20160726 duanyy]
		+ 初次发布
