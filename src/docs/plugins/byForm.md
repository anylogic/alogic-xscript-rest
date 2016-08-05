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


### 案例

- to be defined
