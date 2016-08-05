响应处理器
========

响应处理器用于在http请求提交后，做一些处理，比如说读取文档，读取结果等等。

现有的响应处理器实现包括：

- [结果作为Json处理asJson](asJson.md)
- [获取Http头getHeader](getHeader.md)
- [获取Http结果代码result](result.md)

响应处理器必须配置在[http请求](restbase.md)的response区域内。

### 配置参数

一个请求处理器可以配置下列基础参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpResponseId | HttpResponse对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpResponse对象，本id缺省为httpResponse |



