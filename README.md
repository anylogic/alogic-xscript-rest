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

### 如何开始？

为了更好的开始使用alogic-xscript-rest，我们建议您先了解[xscript2.0](https://github.com/yyduan/alogic/blob/master/alogic-doc/alogic-common/xscript2.md)以及
[xscript2.0基础插件](https://github.com/yyduan/alogic/blob/master/alogic-doc/alogic-common/xscript2-plugins.md).

您可以参考我们的[Demo代码](src/test/java/Demo.java)来测试您的脚本。我们的Demo非常简单:

```java

	public class Demo {
	
		/**
		 * to run the script.
		 */
		public static void run(String src,Properties p){
			Script script = Script.create(src, p);
			if (script == null){
				System.out.println("Fail to compile the script");
				return;
			}
			long start = System.currentTimeMillis();
			Map<String,Object> root = new HashMap<String,Object>();
			LogicletContext ctx = new LogicletContext(p);
			script.execute(root, root, ctx, new ExecuteWatcher.Quiet());
			
			System.out.println("Script:" + src);
			System.out.println("Duration:" + (System.currentTimeMillis() - start) + "ms");
			
			JsonProvider provider = JsonProviderFactory.createProvider();
			System.out.println("#########################################################");
			System.out.println(provider.toJson(root));				
			System.out.println("#########################################################");
		}
		
		public static void main(String[] args) {
			Settings settings = Settings.get();		
			settings.addSettings(new CommandLine(args));		
			settings.addSettings("java:///conf/settings.xml#Demo",null,Settings.getResourceFactory());
			
			run("java:///xscript/Helloworld.xml#Demo",settings);
			run("java:///xscript/weather.gz.xml#Demo",settings);
			run("java:///xscript/weather.sz.xml#Demo",settings);
		}	
	}

```

您可以发现，你只要简单的告诉Demo，你的脚本在哪儿，Demo就能够帮你运行，上面的[java:///xscript/weather.gz.xml#Demo](src/test/resources/xscript/weather.gz.xml#Demo)就是案例中的脚本。

当然，您的脚本最终都是要作为一个服务或者批处理集成在应用中，您可以参考[基于xscript的together](https://github.com/yyduan/alogic/blob/master/alogic-doc/alogic-common/xscript2-together.md)。

### 插件参考

参见[alogic-xscript-rest参考](src/docs/reference.md).

### 版本历史
    - 0.0.1 [20160726 duanyy]
		+ 初次发布
