# SOAP

## 简介

SOAP（Simple Object Access Protocol，简单对象访问协议）是一种简单的基于XML的协议

**SOAP是Web Service的通信协议，是基于XML语言和XSD标准**，其定义了一套编码规则，编码规则定义了如何将数据表示为消息，以及怎样通过HTTP协议来传输SOAP消息，由四部分组成：

（1）SOAP信封（Envelope）：定义了一个框架，框架描述了消息中的内容是什么，包括消息的内容、发送者、接收者、处理者以及如何处理消息。

（2）SOAP编码规则：定义了一种系列化机制，用于交换应用程序所定义的数据类型的实例。

（3）SOAP RPC表示：定义了用于表示远程过程调用和应答协定。

（4）SOAP绑定：定义了一种使用底层传输协议来完成在节点间交换SOAP信封的约定。

## 特性

1. SOAP是一种轻量级通信协议
2. 用于应用程序之间的通信
3. 使用SOAP的应用使用HTTP协议通信
4. 独立于平台
5. 独立于编程语言
6. 基于XML
7. 很简单并可扩展
8. 允许绕过防火墙

## SOAP消息组成

一条SOAP消息就是一个普通的XML文档，文档包括下列元素：

- **必须** 的Envelope元素，可把此XML文档标识为一条Soap消息。
- **可选** 的Header元素，包含头部所需信息。
- **必须** 的Body元素，包含所有请求和响应信息。
- **可选** 的Fault元素，提供有关的在处理此消息时所发生的错误信息。

这里是一些重要的语法规则：

* SOAP 消息必须用 XML 来编码

* SOAP 消息必须使用 SOAP Envelope 命名空间

* SOAP 消息必须使用 SOAP Encoding 命名空间

* SOAP 消息不能包含 DTD 引用

* SOAP 消息不能包含 XML 处理指令

### SOAP消息的基本结构

```xml
<?xml version="1.0"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope"
               soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">

<soap:Header>
    ......
</soap:Header>
<soap:Body>
    ....
    <soap:Fault>
    ......
    </soap:Fault>
</soap:Body>
</soap:Envelope>
```

