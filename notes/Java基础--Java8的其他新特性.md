# Java基础--Java8的其他新特性

## Lambda表达式

```JAVA
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class Lambda_ {
    @Test
    public void T1(){
        //匿名内部类
        Comparator<Integer> comp1= new Comparator<>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int i1 = comp1.compare(22,33);
        System.out.println(i1);

        System.out.println("=============================");
        //lambda表达式
        Comparator<Integer> comp2 = (o1,o2) -> Integer.compare(o1,o2);
        int i2 = comp2.compare(33,22);
        System.out.println(i2);

        System.out.println("=============================");
        //方法引用
        Comparator<Integer> comp3 = Integer :: compare;
        int i3 = comp3.compare(22,33);
        System.out.println(i3);
    }
    
}
```

* Lambda表达式的使用

  1、举例：(o1,o2) -> Integer.compare(o1,o2);

  2、格式：

  ​		  -> ：lambda操作符 或 箭头操作符

  ​	->左边：lambda形参列表（其实就是接口中的抽象方法的形参列表）

  ​	->右边：lambda体（其实就是重写的抽象方法的方法体）

  3、Lambda表达式的使用：

  * 无参、无返回值

    ```java
    Runnable r1 = () -> {Systme.out.println("Hello Lambda!");};
    ```

  * Lambda需要一个参数，但是没有返回值

    ```java
    Consumer<String> con = (String str) -> {System.out.println(str);};
    ```

  * 数据类型可以忽略，因为可由编译器推断得出，称为“类型推断”

    ```java
    Consumer<String> con = (str) -> {System.out.println(str);};
    ```

  * Lambda若只需要一个参数时，参数的小括号可以省略

    ```java
    Consumer<String> con = str -> {System.out.println(str);};
    ```

  * Lambda需要两个或以上的参数，多条执行语句，并且可以有返回值

    ```java
    Comparator<Integer> comp = (o1,o2) -> {
    	System.out.println("实现函数式接口方法")；
    	return Integer.compare(o1,o2);
    };
    ```

  * 当Lambda体只有一条语句时，return与大括号若有，都可以省略

    ```java
    Comparator<Integer> comp = (o1,o2) -> Integer.compare(o1,o2);
    ```

Lambda表达式的本质：作为函数式接口的实例

## 函数式接口

* 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口

```java
@FunctionalInterface
public interface FunctionalInterface_ {
    void method1();
}
```

* 可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常（即：非运行时异常），那么该异常需要在目标接口的抽象方法上声明）
* 可以在一个接口上使用**@FunctionalInterface**注解，这样做可以检测它是否是一个函数式接口。通过javadoc也会包含一条声明，说明这个接口是一个函数式接口
* 在java.uitl.function包下定义了Java8的丰富的函数式接口

## 方法引用与构造器引用

* 当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用

* 方法引用可以看作是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖

* 要求：实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致

* 格式：使用操作符 ” :: “将类（或对象）与方法名分割开来

* 如下三种主要使用情况：

  * 对象 :: 非静态方法名

    ```java
    @Test
    public void test1(){
    	Consumer<String> con1 = str -> System.out.println(str);
    	con1.accept("北京");
    	
    	System.out.println("===================")
    	
    	PrintStream ps = System.out;
    	Consumer<String> con2 = ps :: println;
    	con2.accept("beijing")
    }
    ```

    

  * 类 :: 静态方法名

  * 类 :: 非静态方法名

## Stream API



## Optional类

