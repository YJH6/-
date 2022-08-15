# SNMP4J

## 一、SNMP4J介绍

SNMP4J是一个用Java来实现SNMP协议的开源项目，它支持以命令行的形式进行管理与响应（？为什么要用以命令行的）。SNMP4J是纯面向对象设计，与SNMP++（用C++实现SNMPv1/v2/v3）相类似。

SNMP4J API提供以下特性（没准哪天需要了可以看看，先放这）：

- 支持MD5和SHA验证，DES，3DES,AES128、AES192和AES256加密的SNMPv3。
- 支持MPv1,MPv2C和MPv3，带执行的可阻塞的信息处理模块。
- 全部PDU格式。
- 可阻塞的传输拓扑。支持UPD、TCP、TLS 。
- 可阻塞的超时模块。
- **同步和异步请求**。
- 命令发生器以及命令应答器的支持。
- 基于Apache license的开源免费。
- JAVA 1.4.1或更高版本(2.0或更高版本需要jdk1.6及以上的支持)。
- 基于LOG4J记录日志。
- 使用GETBULK实现Row-based的有效的异步表格获取。
- **支持多线程**。