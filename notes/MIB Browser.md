# MIB Browser

![image-20220718153425644](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220718153425644.png)

![image-20220718153631104](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220718153631104.png)

打开配置文件，主要设置Community和SNMP Version

![image-20220718153310417](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220718153310417.png)

打开MIB文件

![image-20220718153759749](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220718153759749.png)

打开的MIB文件的树

![image-20220718153857784](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220718153857784.png)

Address：agrent的IP

OID

Operations:方法

* Get 获取当前OID的值

* Get Next 获取下一个OID的值，会自动增加OID序号

* Get Bulk 分页批量获取，一次获取10个值，会自动增加OID序号

* Get Subtree 获取OID下面的所有子树的值

* Walk 获取OID的值，会自动增加OID序号，且一直持续查询