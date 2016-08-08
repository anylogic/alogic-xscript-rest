copy
====

copy是[JSON处理器](jsonhandler.md)的一种，用于将http返回的Json文档中指定的节点拷贝到当前文档。

### 实现类

com.alogic.xscript.rest.request.Copy

### 配置参数

支持下列参数：

| 编号 | 代码 | 说明 |
| ---- | ---- | ---- |
| 1 | jsonId | JSON文档的上下文id,缺省为json |
| 2 | tag | 放置到当前文档的tag ,缺省为data |
| 3 | path | 要拷贝过来的节点路径，采用jsonPath进行定位 ｜

### 案例

参见[asJson](asJson.md)。