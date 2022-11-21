# MySQL

## 查询

### 16-根据字段位置排序

```sql
select ename,sal from emp order by 2; //2表示第二列，第二列是sal
```

按照查询结果的第二列sal排序

了解一下，不建议在开发中这样写，因为不健壮。因为列的顺序很容易发生改变，列顺序修改之后，2就废了。

### -综合案例

找出工资在1250到3000之间的员工信息，要求按照薪资降序排列。

```sql
select 
	ename,sql
from
	emp
where
	sal between 1250 and 3000
order by
	sal desc;
```

### 17-单行处理函数

单行处理函数的特点：一个输入对应一个输出。

和单行处理函数相对的是：多行处理函数。（多行处理函数特点：多个输入，对应1个输出）

1、常见的单行处理函数：

lower 转成小写

```sql
select lower(ename) as ename from emp;
```

upper 转成大写

```sql
select upper(ename) as name from emp;
```

substr 取子串（substr(被截取的字符串，起始下标(从1开始)，截取的长度)）

```sql
select substr(ename,1,1) as ename from emp;
//找出员工名字第一个字母是A的员工信息
select ename from emp where ename like 'A%';
select ename from emp where substr(ename,1,1)='A';
//首字母大写
select concat(upper(substr(name,1,1)),substr(name,2,length(name)-1)) from emp;
```

length 取长度

```sql
select length(ename) enamelength from emp;
```

trim 去空格

```sql
select * from emp where ename = trim(' KING      ')
```

str_to_date 将字符串转换成日期

date_format 格式化日期

format 设置千分位

case..when..then..when..then..else..end

```sql
//当员工的工作岗位是MANAGER的时候，工资上调10%，当工作岗位是SALESMAN的时候，工资上调50%（注意：不修改数据库，只是将查询结果显示为工资上调）
select ename,job,sal as oldsal,(case job when 'MANAGER' then sal*1.1 when 'SALESMAN' then sal*1.5 else sal end) as newsal from emp;
```

round 四舍五入

```sql
select round(1236.567,0) as result from emp;
//round(值，保留小数位数)(保留位数负数时，即保留到十千等位数，如round(1234,-1)=1230)
```

rand() 生成随机数

```sql
select rand() from emp;
```

ifnull 可以将null转换成一个具体值

​	ifnull是空处理函数。专门处理空的。

​	在所有数据库中。只要有NULL参与的数学运算，最终结果就是NULL

```sql
select ename,sal+ifnull(comm,0) as salcomm from emp;
```

### 18-多行处理函数（分组函数）

多行处理函数的特点：输入多行，最终输出一行

注意：分组函数在使用的时候必须先进行分组，然后才能使用。如果你没有对数据进行分组，整张表默认为一组。

count 计数

```sql
select count(ename) from emp;
```

sum 求和

```sql
select sum(sal) from emp;
```

avg 平均值

```sql
select avg(sal) from emp;
```

max 最大值

```sql
select max(sal) from emp;
```

min 最小值

```sql
select min(sql) from emp;
```

分组函数在使用的时候需要注意哪些？

1、分组函数自动忽略NULL，你不需要提前对NULL进行处理。（包括count）

2、分组函数中count(*)和count(具体字段)有什么区别？

count(具体字段)：表示统计该字段下所有不为NULL的元素的总数

count(*)：表示统计表当中的总行数（不存在所有字段为NULL的记录）

3、分组函数不能够直接使用在where子句中。

```sql
找出比最低工资高的员工信息
select ename,sal from emp where sal>min(sal);
报错：ERROR 1111 (HY000): Invalid use of group function
因为分组函数在使用的时候必须先分组之后才能使用，where执行的时候，还没有分组，所有where后面不能出现分组函数。
为什么 select sum(sal) from emp;没有分组，也可以使用sum()函数？
因为select在sum()(group by)之后执行
```

4、所有的分组函数可以组合起来一起用。

```sql
select sum(sal),min(sal),max(sal),avg(sal),count(sal) from emp;
```

### 19-分组查询

1、什么是分组查询？

在实际的应用中，可能有这样的需求，需要先进行分组，然后对每一组的数据进行操作。这个时候我们需要使用分组查询，怎么进行分组查询呢？

select ... from ... group by ...；

计算每个部门的工资和？

计算每个工作岗位的平均薪资？

计算每个工作岗位的最高薪资？

2、之前的关键字的执行顺序：

select ... from ... where ... group by ... order by ...

​    4             1             2              3                      5

3、找出每个工作岗位的工资和？

```sql
select job,sum(sal) from emp group by job;
```

4、优化策略：

where和having，优先选择where，where解决不了的，再选择having

### 20-单表查询总结

select ... from ... where ... group by ... having ... order by ...

​    5              1             2               3                 4                 6

```sql
//找出每个岗位的平均薪资，要求显示平均薪资大于1500的，除MANAGER岗位之外，要求按照平均薪资降序排列
select job , avg(sal) as avgsal from emp where job != 'MANAGER' group by job having avg(sal)>1500 order by avgsal desc;
```

### 21-把查询结果去除重复记录

distinct只能出现在所有字段的最前方，表示多个字段联合起来去重

```sql
select distinct job，deptno from emp;
```

### 22-连接查询

1、什么是连接查询？

从一张表中单独查询，称为单表查询。

emp表和dept表联合起来查询数据，从emp表中取员工名字，从dept表中取部门名字，这种跨表查询，多张表联合起来查询数据，被称为连接查询。

2、连接查询的分类？

* 根据语法的年代分类：

​		SQL92：1992年出现的语法

​		SQL99：1999年出现的语法（主要学习）

* 根据表连接的方式分类：

  内连接：

  ​	等值连接

  ​	非等值连接

  ​	自连接

  外连接：

  ​	左外连接（左连接）

  ​	右外连接（右连接）

  全连接：

3、当两种表进行连接查询时，没有任何条件的限制的时候，最终查询结果条数，是两张表条数的乘积，这种现象被称为“笛卡尔积现象”

4、怎么避免笛卡尔积现象？

连接时加条件，满足这个条件的记录被筛选出来。（但是其实匹配的次数并没有减少）

所以表的连接次数越多，效率越低，应该尽量避免表的连接次数。

5、内连接之等值连接

```sql
//查询每个员工所在部门名称，显示员工名和部门名？
(SQL92)
select e.ename,d.dname from emp e,dept d where e.deptno=d.deptno;
```

SQL92的缺点：结构不清晰，表的连接条件，和后期进一步筛选的条件，都放到了where后面。


```sql
(SQL99)
select e.ename,d.dname from emp e [inner] join dept d on e.deptno=d.deptno;
```

SQL99的优点：表连接的条件是独立的，连接之后，如果还需要进一步筛选，再往后添加where

6、内连接之非等值连接

```SQL
//找出每个员工的薪资等级，要求显示员工名，薪资，薪资等级
select e.ename,e.sal,s.grade from emp e join salgrade s on e.sal between s.losal and s.hisal;
```

7、内连接之自连接

```sql
//查询员工的上级领导，要求显示员工名和对应的领导名
select e1.ename,e2.ename from emp e1 join emp e2 on e1.mgr=e2.empno; 
```

8、外连接

```sql
select e.ename,d.dname from emp e right join dept d on e.deptno = d.deptno; 
```

right表示将join关键字右边的这张表看成主表，主要是为了将这张表的数据全部查询出来，捎带着关联查询左边的表。在外连接中，两张表连接，产生了主次关系。

9、多张表怎么连接？

```sql
select ... from a join b on a和b的连接条件 join c on a和c的连接条件 join d on a和d的连接条件
```

一条SQL中内连接和外连接可以混合，都可以出现

```sql
//找出每个员工的部门名称以及工资等级，要求显示员工名、部门名、薪资和薪资等级
select e.ename,d.dname,e.sal,s.grade from 
emp e left join dept d on e.deptno=d.deptno 
	  left join salgrade s on e.sal between s.losal and s.hisal;
```

### 23-子查询

1、子查询：select语句中嵌套select语句，被嵌套的select语句被称为子查询。

2、子查询可以出现在：

​		select ..(select) . from ..(select) . where .. (select) . 

3、where子句中的子查询：

```sql
//找出比最低工资高的员工姓名和薪资
select ename,sal from emp where sal>(select min(sal) from emp);
```

4、from子句中的子查询：

from后面的子查询，可以将子查询的查询结果当作一张临时表

```sql
//找出每个岗位的平均薪资的薪资等级
select job,grade from (select job,avg(sal) as avgsal from emp group by job) t join salgrade s on t.avgsal between s.losal and s.hisal;
```

5、select后面出现的子查询()

```sql
//找出每个员工的部门名称，要求显示员工名、部门名
select e.ename,(select d.dname from dept d where e.deptno = d.deptno) as dname from emp e;
```

注意：select后面的子查询来说，这个子查询只能一次返回1条结果，多于1条，就报错。

### 24-union合并查询结果集

union在多表时比连接匹配次数少

```sql
//查询工作岗位是MANAGER和SALESMAN的员工
select ename,job from emp where job = 'MANAGER' or job = 'SALESMAN';
select ename,job from emp where job in ('MANAGER','SALESMAN');
select ename,job from emp where job = 'MANAGER' union select ename,job from emp where job = 'SALESMAN';
```

union在使用的时候的注意事项：

union在进行结果集合并的时候，要求两个结果集的列数相同。

union结果集合并时列和列的数据类型也要相同

### 25-limit

1、limit是将查询结果集的一部分取处理，通常使用在分页查询当中。

2、使用方法：limit startIndex , length

startIndex 起始下标（从0开始），length长度

```sql
//按照薪资降序，取出排名在前5名的员工
select ename,sal from emp order by sal desc limit 5;
//完整写法
select ename,sal from emp order by sal desc limit 0,5;
```

3、注意：mysql当中，limit在order by 之后执行

4、分页：

每页显示3条记录

​		第1页：limit 0,3		[0  1  2]
​		第2页：limit 3,3		[3  4  5]
​		第3页：limit 6,3		[6  7  8]
​		第4页：limit 9,3		[9  10  11]

每页显示pageSize条记录

​		第pageNo页：limit (pageNo-1) * pageSize，pageSize

### 26-快速复制表

```sql
create table emp2 as select * from emp；
```

原理：将一个查询结果当作一张表新建，这个可以完成表的快速复制。表创建除了，同时表中的数据也存在了。

### 27-将查询结果插入一张表

```sql
insert into emp select * from emp;
```

### 28-快速删除表中的数据

```sql
delete from emp2;
```

这种删除数据的方式比较慢。

delete语句删除数据的原理：表中的数据被删除了，但是这个数据在硬盘上的真实存储空间不会被释放。这种删除的缺点是：删除效率比较低，优点是：支持回滚（rollback），后悔了可以再恢复数据。

```sql
truncate table emp2;(DDL)
```

这种删除效率比较高，表被一次截断，物理删除。

缺点：不支持回滚；优点：快速

### 29-约束(constraint)

1、什么是约束？

在创建表的时候，我们可以给表中的字段加上一些约束，来保证这个表中数据的完整性、有效性。

约束的作用：就是为了保证：表中的数据有效。

2、常见约束：

非空约束：not null

唯一性约束：unique

主键约束：primary key（简称PK）

外键约束：foreign key（简称FK）

检查约束：check（mysql不支持，oracle支持）

3、非空约束

4、唯一性约束：unique

唯一性约束unique约束的字段不能重复，但是可以为NULL。

如果需要两个字段联合起来具有唯一性：

```sql
create table t_vip(
	id int,
	name varchar(255),
	email varchar(255),
	unique(name,email) //约束直接添加到列的后面，这种约束被称为表级约束
);
```

什么时候使用表级约束？需要给多个字段联合起来添加某一个约束的时候，需要使用表级约束。

5、如果一个字段同时被not null和unique约束，这个字段就自动变成了主键字段。（注意：oracle中不一样！）

6、主键约束（primary key，简称PK）

主键约束的相关术语：主键约束、主键字段、主键值

用处：主键值是每一行记录的唯一标识。

任何一张表都应该有主键，否则表无效。

主键约束也可以多个字段联合起来添加约束，表级约束，单一主键-》复合主键。

一张表只能有一个主键约束。



主键除了：单一主键和复合主键之外，还可以这样进行分类：

​	自然主键：主键值是一个自然数，和业务没关系。

​	业务主键：主键值和业务紧密关联，例如拿银行卡账号做主键。这就是业务主键。

在实际开发中自然主键使用比较多，因为主键只要做大不重复就行，不需要有意义。业务主键不好，因为主键一旦和业务挂钩，那么当业务发生变动的时候，可能会影响到主键值，所以业务主键不建议使用。尽量使用自然主键。

在mysql中，有一种机制，可以帮助我们自动维护一个主键值。

```sql
drop table if exists t_vip；
create table t_vip(
	id int primary key auto_increment,//表示自增，从1开始，以1递增
	name varchar(255)
);
```

7、外键约束（foreign key，简称FK）

外键约束的相关术语：外键约束、外键字段、外键值

```
create table t_class(
	class_no int primary key;
	class_name varchar(255)
);
create table t_student(
	student_no int primary key auto_increment,
	name varchar(255),
	cno int,
	foreign key(cno) references t_class(class_no)
);
```

### 30-存储引擎

1、什么是存储引擎，有什么用？

​	存储引擎是MySQL中特有的一个术语，其它数据库没有。（Oracle中有，但是不叫这个名字）

​	存储引擎实际上是一个表存储/组织数据的方式，不同的存储引擎，表存储数据的方式不同。

2、MyISAM存储引擎：

​	它管理的表具有以下特征：使用三个文件表示每张表：

  * 格式文件 - 存储表结构的定义（mytable.frm）

  * 数据文件 - 存储表行的内容（mytable.MYD）

  * 索引文件 - 存储表上索引（mytable.MYI）：索引是一本书的目录，缩小扫描范围，提高查询效率的一种机制。

  可被转换为压缩、只读表来节省空间

​	提示一下：对于一张表来说，只要是主键，或者加有unique约束的字段上会自动创建索引。

​	MyISAM存储引擎特点：

​		可被转换为压缩、只读表来节省空间

​		这是这种存储引擎的优势！

3、InnoDB存储引擎：

​	这是MySQL默认的存储引擎，同时也是一个重量级的存储引擎。

​	InnoDB支持事务，支持数据库崩溃后自动恢复机制。

​	InnoDB存储引擎最主要的特点是：非常安全。

它管理的表具有下列主要特征：
		– 每个 InnoDB 表在数据库目录中以.frm 格式文件表示
		– InnoDB 表空间 tablespace 被用于存储表的内容（表空间是一个逻辑名称。表空间存储数据+索引。）
		– 提供一组用来记录事务性活动的日志文件
		– 用 COMMIT(提交)、SAVEPOINT 及ROLLBACK(回滚)支持事务处理
		– 提供全 ACID 兼容
		– 在 MySQL 服务器崩溃后提供自动恢复
		– 多版本（MVCC）和行级锁定
		– 支持外键及引用的完整性，包括级联删除和更新
	InnoDB最大的特点就是支持事务：
		以保证数据的安全。效率不是很高，并且也不能压缩，不能转换为只读，
		不能很好的节省存储空间。

4、MEMORY

使用 MEMORY 存储引擎的表，其数据存储在内存中，且行的长度固定，
	这两个特点使得 MEMORY 存储引擎非常快。
	MEMORY 存储引擎管理的表具有下列特征：
		– 在数据库目录内，每个表均以.frm 格式的文件表示。
		– 表数据及索引被存储在内存中。（目的就是快，查询快！）
		– 表级锁机制。
		– 不能包含 TEXT 或 BLOB 字段。
	MEMORY 存储引擎以前被称为HEAP 引擎。
	MEMORY引擎优点：查询效率是最高的。不需要和硬盘交互。
	MEMORY引擎缺点：不安全，关机之后数据消失。因为数据和索引都是在内存当中。

### 31-事务

1、一个事务其实就是一个完整的业务逻辑。

