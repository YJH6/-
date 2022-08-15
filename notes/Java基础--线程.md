# Java基础--线程

## Java线程

### 1 线程介绍

线程相关概念

* 程序（progarm）

  是为完成特定任务、用某种语言编写的一组指令的集合。简单的说：就是我们写的代码

* 进程

  1、进程是指运行中的程序，比如我们使用QQ，就启动了一个进程，操作系统就会为该进程分配内存空间。

  2、进程是程序的一次执行过程，或是正在运行的一个程序。是动态过程：又它自身的产生、存在和消亡的过程

* 线程

  1、线程是由进程创建的，是进程的一个实体

  2、一个进程可以拥有多个线程

* 其它相关概念

  1、单线程：同一个时刻，只允许执行一个线程

  2、多线程：同一个时刻，可以执行多个线程，比如：一个qq进程，可以同时打开多个聊天窗口，一个迅雷进程，可以同时下载多个文件

  3、并发：同一个时刻，多个任务交替执行，造成一种“貌似同时”的错觉，简单的说，单核cpu实现的多任务就是并发

  4、并行：同一个时刻，多个任务同时执行。多核cpu可以实现并行

### 2 线程的基本使用

在java中线程使用有两种方法

1、继承Thread类，重写run方法

2、实现Runnable接口，重写run方法

！new Thread时，传入的Runnable实例不是同一个的话，方法和属性也不是同一个

* 线程应用案例1-继承Thread类

  ```java
  //开启一个线程，该线程每隔1秒，在控制台输出“喵”
  //当输出8次“喵”时，结束线程
  /**
   * 演示通过继承Thread类创建线程
   */
  public class Thread01 {
      public static void main(String[] args) {
          //创建一个Cat对象
          Cat cat = new Cat();
          //启动线程->最终会执行cat的run()
          cat.start();
          
          //说明：当main线程启动一个子线程Thread-0后，主线程不会阻塞，会继续执行
          
          for(int i = 0; i < 10; i++){
              System.out.println(Thread.currentThread().getName()+i);
          }
      }
  }
  
  /**
   * 1、当一个类继承Thread类时，该类就可以当做线程使用
   * 2、我们会重写run方法，写上自己的业务代码
   * 3、run方法是Thread类实现了Runnable接口的run方法
   *
   *     @Override
   *     public void run() {
   *         if (target != null) {
   *             target.run();
   *         }
   *     }
   */
  class Cat extends Thread{
      int times=0;
      @Override
      public void run() {//重写run方法，写上自己的业务代码
          while (true){
                  //该线程每隔一秒，在控制台输出“喵”
                  System.out.println("喵");
                  ++times;
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              //如果times到了8，退出while
              if(times==8){
                  break;
              }
          }
      }
  }
  ```

  为什么使用.start()而不是.run()：

  因为run方法就是一个普通的方法，.run()其实就是main线程调用run方法，并没有创建一个线程。

  start()源码：

  ```java
  /*
      (1)
      public synchronized void start(){
      start0()
      }
      (2)
      start0()是本地方法，是JVM调用，底层是c/c++实现
      真正实现多线程的效果的是start0(),而不是run
      private native void start0();
  */
  ```

  ![image-20220719151628371](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220719151628371.png)

* 线程应用案例2-实现Runnable接口

  1、java是单继承的，在某些情况下一个类可能已经继承了某个父类，这时再用继承Thread类来创建线程就不行了

  2、java设计者提供了另一个方式创建线程，就是通过实现Runnable接口来创建线程，这里底层使用了设计模式【代理模式】

  ```java
  /**
   * 该程序可以每隔1秒，在控制台输出“hi!”，当输出10次后，自动退出
   * 使用实现Runnable接口的方式实现，这里底层使用了设计模式【代理模式】
   */
  public class Thread02 {
  
      public static void main(String[] args) {
          Dog dog = new Dog();
  
          /**
           * dog.start();这里不能调用start方法，因为runnable是个接口
           * 创建了Thread对象，把dog对象（实现了Runnable接口），放入Thread
           */
          Thread thread = new Thread(dog);
          thread.start();
      }
  
  }
  
  class Dog implements Runnable{ //通过实现Runnable接口，开发线程
      int count=0;
      @Override
      public void run() {
          while (true){
              System.out.println("hi!");
              ++count;
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
  
              if(count==10){
                  break;
              }
          }
  
      }
  }
  ```

  ```java
  //【线程代理】
  //线程代理类，模拟了一个极简的Thread类
  class ThreadProxy implements Runnable{
      private Runnable target = null;
      @Override
      public void run() {
          if(target != null){
              target.run();//动态绑定（与运行类型相同）
          }
      }
  
      public ThreadProxy(Runnable target) {
          this.target = target;
      }
      
      public void start(){
          start0();//这个方法时真正实现多线程方法
      }
      
      public void start0(){
          run();
      }
  }
  ```

* 继承Thread vs 实现Runnable的区别

  1、从java的设计来说，通过继承Thread或者实现Runnable接口来创建线程本质上没有区别，从jdk帮助文档我们可以看到Thread类本身就实现了Runnable接口

  2、实现Runnable接口方式更加适合多个线程共享一个资源的情况，并且避免了单继承的限制

### 线程终止

* 基本说明

  1、当线程完成任务后，会自动退出

  2、还可以通过使用变量来控制run方法退出的方式停止线程，即通知方式

### 线程常用方法

* 常用方法第一组

  1、setName //设置线程名称，使之与参数name相同

  2、getName //返回该线程的名称

  3、start //使该线程开始执行；Java虚拟机底层调用该线程的start0方法

  4、run //调用线程对象的run方法

  5、setPriority //更改线程的优先级

  6、getPriority //获取线程的优先级

  7、sleep //在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）

  8、interrupt //中断线程

* 注意事项和细节

  1、start底层会创建新的线程，调用run，run就是一个简单的方法调用，不会启动新线程

  2、线程优先级的范围

  3、interrupt，中断线程，但并没有真正的结束线程。所以一般用于中断正在休眠的线程

  4、sleep：线程的静态方法，使当前线程休眠

* 常用方法第二组

  1、yield：线程的礼让。让出cpu，让其他线程执行，但礼让的事件不确定，所以也不一定礼让成功

  2、join：线程的插队。插队的线程一旦插队成功，则肯定先执行完插入的线程所有的任务

  案例：创建一个子线程，每隔1s输出“hello”，输出20次，主线程每隔1秒，输出“hi”，输出20次。要求：两个线程同时执行，当主线程输出5次后，就让子线程运行完毕，主线程再继续。

  ```java
  public class JoinMethod {
      public static void main(String[] args) {
          T t = new T();
          t.start();
          int count = 0;
          while (true){
              System.out.println("hi");
              ++count;
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              if (count==5) try {
                  t.join();//让t插队，而不是插t的队
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              if (count==20)break;
          }
      }
  }
  
  class T extends Thread{
      public int count = 0;
      @Override
      public void run() {
          while(true){
              System.out.println("hello");
              ++count;
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              if(count==20)break;
          }
      }
  }
  ```


* 用户线程和守护线程

  1、用户线程：也叫作工作线程，当线程的任务执行完或通知方式结束

  2、守护线程：一般是为工作线程服务的，当所有的用户线程结束，守护线程自动结束

  3、常见的守护线程：垃圾回收机制

  ```java
  //案例：如何把一个线程设置为守护线程
  public class ThreadMethod03 {
      public static void main(String[] args) {
          MyDaemonThread thread = new MyDaemonThread();
          //如果我们希望当mian线程结束后，子线程自动结束
          //只需要将子线程设置为守护线程即可
          thread.setDaemon(true);
  
          thread.start();
  
          for (int i = 0;i<50;i++){
              System.out.println("上班ing");
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  
  class MyDaemonThread extends Thread{
      @Override
      public void run() {
          for (;;){
              try {
                  Thread.sleep(1000);
                  System.out.println("摸鱼ing");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```

### 线程的生命周期

* JDK中用Thread.State枚举表示了线程的几种状态

  ![image-20220720093305525](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220720093305525.png)

* 线程状态转换图

  ![image-20220720094308713](C:\Users\Sino\AppData\Roaming\Typora\typora-user-images\image-20220720094308713.png)

```java
//查看线程状态
public class GetThreadState {
    public static void main(String[] args) {
        T t = new T();
        System.out.println(t.getName()+"状态:"+t.getState());
        t.start();
        //循环条件：线程状态不是终止状态
        while (t.getState() != Thread.State.TERMINATED){
            System.out.println(t.getName()+"状态:"+t.getState());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(t.getName()+"状态:"+t.getState());

    }
}
class T extends Thread{
    @Override
    public void run() {
        for (int i = 0; i<10;i++){
            try {
                System.out.println("摸鱼ing");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
```

### 线程的同步

* 线程同步机制

  1、在多线程编程，一些敏感数据不允许呗多个线程同时访问，此时就使用同步访问技术，保证数据在任何同一时刻，最多有一个线程访问，以保证数据的完整性

  2、也可以这样理解：线程同步，即当有一个线程在对内存进行操作时，其他线程不可以对这个内存地址进行操作，直到该线程完成操作，其他线程才能对该内存地址进行操作

* 同步具体方法-Synchronized

  1、同步代码块

  ```java
  synchronized(对象){ //得到对象的锁，才能操作同步代码
  	//需要被同步代码;
  }
  ```

  2、synchronized还可以放在方法声明中，表示整个方法为同步方法

  ```java
  public synchronzied void m(String name){
  	//需要被同步的代码
  }
  ```

  3、使用synchronzied解决售票问题

  ```java
  class SellTicket1 implements Runnable{
      private static int ticketNum=100;//让多个线程共享 ticketNum
  
      private boolean loop = true; //循环控制
  
      public synchronized void sell(){//同步方法
          if (ticketNum<=0){
              loop = false;
              return;
          }
          try {
  //            System.out.println(Thread.currentThread().getName()+"正在售票");
              Thread.sleep(10);
              ticketNum--;
  //            System.out.println(Thread.currentThread().getName()+"售出一张票");
              System.out.println("还剩"+ticketNum+"张票");
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  
      @Override
      public void run() {
          while (loop){
                  sell();
              }
          }
  
  }
  ```

### 互斥锁

* 基本介绍

  1、Java语言中，引入了对象互斥锁的概念，来保证共享数据操作的完整性

  2、每隔对象都对应于一个可称为“互斥锁”的标记，这个标记用来保证在任一时刻，只能又一个线程访问该对象

  3、关键字synchronized来与对象的互斥锁联系。当某个对象用synchronized修饰时，表明该对象在任一时刻只能有一个线程访问

  4、同步的局限性：导致线程的执行效率降低

  5、同步方法（非静态的）的锁可以是this，也可以是其他对象（要求是同一个对象）

  6、同步方法（静态的）的锁为当前类对象

* 注意事项和细节

  1、同步方法如果没有使用static修饰：默认锁对象为this

  2、如果方法使用static修饰，默认锁对象：当前lei.class

  3、实现的落地步骤：

  * 需要先分析上锁的代码
  * 选择同步代码块或同步方法
  * 要求多个线程的锁对象为同一个即可

### 线程的死锁

* 基本介绍

  多个线程都占用了对方的锁资源，但不肯相让，导致了死锁，在编程中是一定要避免死锁的发送

```java
/**
 * 模拟线程死锁
 */
public class DeadLock_ {
    public static void main(String[] args) {
        //模拟死锁现象
        DeadLockDemo A = new DeadLockDemo(true);
        DeadLockDemo B = new DeadLockDemo(false);
        A.setName(" A ");
        B.setName(" B ");
        A.start();
        B.start();
    }
}

class DeadLockDemo extends Thread{
    boolean flag = true;
    static Object o1 = new Object(); //保证多线程，共享一个对象，这里使用static
    static Object o2 = new Object();

    public DeadLockDemo(boolean flag){//构造器
        this.flag=flag;
    }
    @Override
    public void run() {
        if (flag){
            /**
             * 1、如果flag为T，线程A就会先得到/持有o1对象锁，然后尝试去获取o2对象锁
             * 2、如果线程A得不到o2对象锁，就会Blocked
             * 3、如果flag为F，线程B就会先得到/持有o2对象锁，然后尝试去获取o1对象锁
             * 4、如果线程B得不到o1对象锁，就会Blocked
             */
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+"进入o1");
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName()+"进入o2");
                }
            }
        }else{
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+"进入o2");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName()+"进入o1");
                }
            }
        }
    }
}
```

### 释放锁

* 下面操作会释放锁

  1、当前线程的同步方法、同步代码块执行结束

  2、当前线程在同步代码块、同步方法中遇到break、return

  3、当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导致异常结束

  4、当前线程在同步代码块、同步方法中执行了线程对象的wait()方法，当前线程暂停，并释放锁

* 下面操作不会释放锁

  1、线程执行同步代码块或同步方法时，程序调用Thread.sleep()、Thread.yield()方法暂停当前线程的执行，不会释放锁

  2、线程执行同步代码块时，其他线程调用了该线程的suspend()方法将该线程挂起，该线程不会释放锁

  提示：应尽量避免使用suspend()和resume()来控制线程，方法不再推荐使用
