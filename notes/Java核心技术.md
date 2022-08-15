### 6.4 内部类

* 内部类方法可以访问该类定义所在的作用域中的数据，包括私有的数据。
* 内部类可以对同一个包中的其他类隐藏起来。
* 当想要定义一个回调函数并且不想编写大量代码时，使用匿名（anonymous）内部类比较便捷。

#### 6.4.1 使用内部类访问对象状态

内部类的对象有一个隐式引用， 它引用了实例化该内部对象的外围类对象。通过这个指针， 可以访问外围类对象的全部状态

从传统意义上讲，一个方法可以引用调用这个方法的对象数据域。内部类既可以访问自身的数据域，也可以访问创建它的外围类对象的数据域。内部类的对象总有一个隐式引用， 它指向了创建它的外部类对象。

外围类的引用在构造器中设置。编译器修改了所有的内部类的构造器， 添加一个外围类引用的参数。

![image-20220715135251251](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220715135251251.png)

#### 6.4.2 内部类的特殊语法规则

可以通过显式地命名将外围类引用设置为其他的对象。例如， 如果 TimePrinter 是一个公有内部类，对于任意的语音时钟都可以构造一个 TimePrinter：

```java
TalkingClock jabberer = new Ta1kingClock(1000, true);
TalkingOock.TiiePrinter listener = jabberer.new TimePrinter()；
```

需要注意， 在外围类的作用域之外，可以这样引用内部类：

*OuterClass**.**InnerClass*

* 内部类中声明的所有静态域都必须是 final。原因很简单。我们希望一个静态域只有一个实例， 不过对于每个外部对象， 会分别有一个单独的内部类实例。如果这个域不是 final , 它可能就不是唯一的。
* 内部类不能有 static 方法。Java 语言规范对这个限制没有做任何解释。也可以允许有静态方法，但只能访问外围类的静态域和方法

#### 6.4.3 内部类是否有用、必要和安全

编译器将会把内部类翻译成用$分隔外部类名与内部类名的常规类文件，而虚拟机对此一无所知

如：TalkingClock$TimePrinter.class

```java
public class TalkingClock$TimePrinter
{
    public TalkingGockJTimePrinter(TalkingCtock);
    public void actionPerformed(java.awt.event.ActionEvent);
    final TalkingClock this$O; 
}
```

可以清楚地看到， 编译器为了引用外围类， 生成了一个附加的实例域 this$0 (名字this$0 是由编译器合成的，在自己编写的代码中不能够引用它）。另外，还可以看到构造器的TalkingClock 参数。

#### 6.4.4 局部内部类

```java
public void start0 {
    class TiiePrinter inpleients ActionListener
    {
        public void actionPerforaed(ActionEvent event) {
            Systei.out.println("At the tone, the tine is " + new Date());
            if (beep) Toolkit.getDefaul tToolki10•beep();
        } 
    }
    ActionListener listener = new TimePrinter();
    Timer t = new Timer(interva1, listener);
    t.start(); 
}
```

* 局部类不能用 public 或 private 访问说明符进行声明。它的作用域被限定在声明这个局部类的块中。

* 局部类有一个优势， 即对外部世界可以完全地隐藏起来。 即使 TalkingClock 类中的其他代码也不能访问它。除 start 方法之外， 没有任何方法知道 TimePrinter 类的存在。