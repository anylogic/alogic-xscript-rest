请求处理器
========

请求处理器用于在http请求提交前，做一些处理，比如说设置参数，设置文档等等。

现有的请求处理器实现包括：

- [通过Form提交byForm](byForm.md)
- [通过Json文档提交ByJson](byJson.md)
- [设置Http头setHeader](setHeader.md)
- [URI改写setUri](setUri.md)

请求处理器必须配置在[http请求](restbase.md)的request区域内。

### 配置参数

一个请求处理器可以配置下列基础参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |



