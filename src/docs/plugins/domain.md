domain
======

domain用于创建一个REST调用域，同时创建一个namespace，所有在domain中注册的插件，不需要再通过using引入。

可在domain中直接使用的插件包括：
- [Http调用相关](restbase.md)
	- [GET方法mGet](mGet.md)
	- [POST方法mPost](mPost.md)
	- [OPTIONS方法mOptions](mOptions.md)
	- [PATCH方法mPatch](mPatch.md)
	- [PUT方法mPut](mPut.md)
	- [DELETE方法mDelete](mDelete.md)
	- [TRACE方法mTrace](mTrace.md)
- [请求处理器](requesthandler.md)
	- [通过Form提交byForm](byForm.md)
	- [通过Json文档提交ByJson](byJson.md)
	- [设置Http头setHeader](setHeader.md)
	- [URI改写setUri](setUri.md)
- [响应处理器](responsehandler.md)
	- [结果作为Json处理asJson](asJson.md)
	- [获取Http头getHeader](getHeader.md)
	- [获取Http结果代码result](result.md)
- [Json响应处理器](jsonhandler.md)
	- [拷贝文档到当前文档copy](copy.md)

### 实现模块

com.alogic.xscript.rest.Domain

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | base | REST的基础地址 |
| 2 | cid | 上下文对象的id,缺省为$rest-domain |


### 案例

通过下列方法使用domain.

```xml

	<script>
		<using xmlTag="rest" module="com.alogic.xscript.rest.Domain"/>
		
		<rest base="http://www.weather.com.cn/data">
			<!--调用基础地址为http://www.weather.com.cn/data的REST服务-->
		</rest>
	</script>
	
```