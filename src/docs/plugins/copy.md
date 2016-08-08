copy
====

copy用于将http返回的Json文档中指定的节点拷贝到当前文档。

### 实现类

com.alogic.xscript.rest.request.Copy

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | httpClientId | HttpClient对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpClient对象，本id缺省为httpClient |
| 2 | httpRequestId | HttpRequest对象id,为了控制需要，alogic-xscript-rest需要在上下文中传递一个HttpRequest对象，本id缺省为httpRequest |
| 3 | tag | 放置到当前文档的tag ,缺省为data |
| 4 | path | 要拷贝过来的节点路径，采用jsonPath进行定位 ｜

### 案例

参见[asJson](asJson.md)。