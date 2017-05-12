alogic-xscript-rest
===================

### Overview

alogic-xscript-rest是基于xscript2.0的rest插件，提供了调用rest服务所需的相关指令。

### Getting started

按照以下步骤，您可轻松在您的项目中使用alogic-xscript-rest.

不过开始之前，我们希望您了解xscript的相关知识。

- [xscript2.0](https://github.com/yyduan/alogic/blob/master/alogic-doc/alogic-common/xscript2.md) - 您可以了解xscript的基本原理及基本编程思路
- [xscript2.0基础插件](https://github.com/yyduan/alogic/blob/master/alogic-doc/alogic-common/xscript2-plugins.md) - 如何使用xscript的基础插件
- [基于xscript的together](https://github.com/yyduan/alogic/blob/master/alogic-doc/alogic-common/xscript2-together.md) - 如何将你的script发布为alogic服务

#### 增加maven依赖

您可以在[中央仓库](http://mvnrepository.com/)上找到[alogic-xscript-rest](http://mvnrepository.com/search?q=com.github.anylogic%3Aalogic-xscript-rest)的发布版本。

```xml

    <dependency>
        <groupId>com.github.anylogic</groupId>
        <artifactId>alogic-xscript-rest</artifactId>
        <version>4.5.2-20160825</version>
    </dependency>   	

```

> alogic-xscript-rest版本号前面的4.5.2是其所依赖的[HttpClient](http://hc.apache.org/httpcomponents-client-4.5.x/index.html)的版本号，后面的20160825是其发布的日期。

#### 引入Namespace

在您的脚本中，你需要引入rest作为Namespace，比如:

```xml
	
	<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
	
	<rest>
		<!--
			在这里你可以使用alogic-xcript-rest提供的语句
		-->
	</rest>
```

### Example

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

### Reference

参见[alogic-xscript-rest参考](src/docs/reference.md).

### History

- 4.5.2 [20160726 duanyy]
	+ 初次发布
- 4.5.2.1 [20160825 duanyy]
	+ 发布20160825版本
- 4.5.2.2 [20170315 duanyy]
	+ 基于alogic-1.6.7,发布20170315版本
- 4.5.2.3 [20170512 duanyy]
	+ 基于alogic-1.6.8发布新版本
	+ 解决1.6.8中xscript的兼容性问题
