# Java基础--泛型

## 泛型的理解和好处

* 看一个需求

  1、请编写程序，在ArrayList中，添加3个Dog对象

  2、Dog对象含有name和age，并输出name和age（要求使用getXxx()）

  先使用传统的方法来解决 -> 引出泛型

  ```java
  public class Generic01 {
      public static void main(String[] args) {
          //使用传统的方法来解决
          ArrayList arrayList = new ArrayList();
          arrayList.add(new Dog("小黑",10));
          arrayList.add(new Dog("旺财",5));
          arrayList.add(new Dog("王泽",2));
  
          //如果程序员不小心加入了一支猫
          arrayList.add(new Cat("局座",20));
          
          
          //遍历
          for (Object o:arrayList){
              //向下转型Object -> Dog
              Dog dog = (Dog) o ;//出错
              System.out.println(dog.toString());
          }
      }
  }
  class Dog{
  	public String name;
      public int age;
      public Dog(String name,int age){
          this.name = name;
          this.age = age;
      }
      public String getName(){
          return this.name;
      }
      public int getAge(){
          return this.age;
      }
      @Override
      public String toString(){
          return "Dog[name=" + name + ",age=" +age+ "]";
      }
  }
  ```

* 使用传统方法的问题分析

  1、不能对加入到集合ArrayList中的数据类型进行约束（不安全）

  2、遍历的时候，需要进行类型转换，如果集合中的数据量较大，对效率有影响

* 泛型快速体验-用泛型解决前面的问题

  ```java
  public class Generic01 {
      public static void main(String[] args) {
          //使用泛型
          ArrayList<Dog> arrayList = new ArrayList<Dog>();
          arrayList.add(new Dog("小黑",10));
          arrayList.add(new Dog("旺财",5));
          arrayList.add(new Dog("王泽",2));
  
          //如果程序员不小心加入了一支猫
          //arrayList.add(new Cat("局座",20));//出错
          
          
          //遍历
          for (Dog dog:arrayList){
              System.out.println(dog.toString());
          }
      }
  }
  ```

* 泛型的好处

  1、编译时，检查添加元素的类型，提高了安全性

  2、减少了类型转换的次数，提高效率

  3、不再提示编译警告

## 泛型介绍

理解：泛（广泛）型（类型）=》 Integer、String、Dog

1）泛型又称参数化类型，是Jdk5.0出现的新特性，解决数据类型的安全性问题

2）在类声明或实例化时只需要制定好需要的具体的类型即可

3）Java泛型可以保证如果程序在编译时没有发出警告，运行就不会产生ClassCastException异常。同时，代码更加简洁、健壮

4）泛型的作用是：可以在类声明时通过一个标识表示类中某个属性的类型，或者是某个方法的返回值的类型，或者是参数类型

```java
//4)
class Person<E>{
	//E表示s的数据类型，该数据类型在定义Person对象的时候再指定
	//即在编译期间，就确定E是什么类型
	E s;
    
    public Person(E s){//E也可以是参数类型
    	this.s = s;
    }
    
    public E f(){//E也可以是返回类型
    	return s;
    }
}
```

## 泛型的语法

* 泛型的声明

  interface 接口\<T>{}和 class 类<K,V>{}

  说明：

  1、其中，T、K、V不代表值，而是表示类型

  2、任意字母都可以。常用T表示，是Type的缩写

* 泛型的实例化

  要在类名后面指定类型参数的值（类型）

  1、List\<String> strList = new ArrayList\<String>();

  2、Iterator\<Customer> iterator = customers.interator();

  ```java
  public class GenericExp {
      public static void main(String[] args) {
          Set<Student> students = new HashSet<Student>();
          students.add(new Student("小明",99));
          students.add(new Student("yjh",23));
  
          Iterator<Student> iterator = students.iterator();
          while (iterator.hasNext()) {
              Student next = iterator.next();
              System.out.println(next.toString());
          }
  
          System.out.println("=====================");
  
  
          Map<String,Student> map = new HashMap<>();
          map.put("ante",new Student("ante",10));
          map.put("ate",new Student("ate",10));
  
          Set<Map.Entry<String, Student>> entries = map.entrySet();
          Iterator<Map.Entry<String, Student>> iterator1 = entries.iterator();
          while (iterator1.hasNext()) {
              Map.Entry<String, Student> next = iterator1.next();
              System.out.println(next.toString());
          }
      }
  }
  
  class Student{
      String name;
      int age;
  
      public Student(String name, int age) {
          this.name = name;
          this.age = age;
      }
  
      @Override
      public String toString() {
          return "Student{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  }
  ```

## 泛型的使用

* 泛型使用的注意事项和细节

  1、interface List\<T>{} , public class HashSet\<E>{} ...等等

  说明：T，E只能是引用类型

  看看下面语句是否正确？：

  List\<Integer> list = new ArrayList\<Interger>();  √

  List\<int> list2 = new ArrayList\<int>();  ×

  2、在给泛型指定具体类型后，可以传入该类型或者其子类类型

  3、泛型使用形式

  List\<Interger> list1 = new ArrayList\<Interger>();

  List\<Interger> list2 = new ArrayList\<>();

  说明：编译器会进行类型推断，推荐使用简写

​		4、如果这样写

​		List list3 = new ArrayList(); 默认给它的泛型是[\<E> E就是Object]

​		即：

​		List\<Obejct> list3 = new ArrayList<>();

* 泛型练习题

  ```java
  /**
   * 定义Employee类
   * 1）该类包含：private成员变量name,sal,birthday，其中birthday为MyDate类的对象
   * 2）为每一个属性定义getter\setter方法
   * 3）重写toString方法输出name,sal,birthday
   * 4）MyDate类包含：private成员变量month,day,year，并为每一个属性定义getter,setter方法
   * 5）创建该类的3个对象，并把这些对象放入ArrayList集合中（ArrayList需使用泛型来定义），
   * 6）对集合中的元素进行排序，并遍历输出：
   * 排序方式：调用ArrayList的sort方法，传入Comparator对象[使用泛型]，先按照name排序
   * 如果name相同，则按生日日期的先后排序
   */
  public class GenericExerise {
      public static void main(String[] args) {
          ArrayList<Employee> employees = new ArrayList<>();
          employees.add(new Employee("余",BigDecimal.valueOf(500.20),new MyDate(8,6,1999)));
          employees.add(new Employee("余",BigDecimal.valueOf(1500.20),new MyDate(8,7,1999)));
          employees.add(new Employee("er",BigDecimal.valueOf(2500.20),new MyDate(8,6,1999)));
          employees.sort(new Comparator<Employee>() {
               // 排序方式：调用ArrayList的sort方法，传入Comparator对象[使用泛型]，先按照name排序
               // 如果name相同，则按生日日期的先后排序
              @Override
              public int compare(Employee o1, Employee o2) {
                  //先对传入的参数进行验证
                  if(!(o1 instanceof Employee && o2 instanceof Employee)) {
                      System.out.println("类型不正确");
                      return 0;
                  }
                  //比较name
                  int i = o1.getName().compareTo(o2.getName());
                  if( i != 0){
                      return i;
                  }
                  //如果name相同，则比较birthday
                  int date = o1.getMyDate().compareTo(o2.getMyDate());
                  if(date != 1){
                      return date;
                  }
                  return 0;
              }
          });
  
          System.out.println(employees);
      }
  }
  
  
  class MyDate implements Comparable<MyDate>{
      private int month;
      private int day;
      private int year;
  
      @Override
      public int compareTo(MyDate o) {
  
          //如果name相同，则比较birthday - yer
          int year = this.getYear()-o.getYear();
          if ( year != 0){
              return year;
          }
          int month = this.getMonth()-o.getMonth();
          if ( month != 0){
              return month;
          }
          int day = this.getDay()-o.getDay();
          if ( day != 0){
              return day;
          }
          return 0;
      }
  }
  ```

## 自定义泛型

* 自定义泛型类

  * 基本语法

    class 类名<T,R...>{

    ​		成员

    }

  * 注意细节

    1、普通成员可以使用泛型（属性，方法）

    2、使用泛型的数组，不能初始化

    3、静态方法中不能使用类的泛型

    4、泛型类的类型，是在创建对象时确定的（因为创建对象时，需要指定确定类型）

    5、如果在创建对象时，没有指定类型，默认为Object

* 自定义泛型接口

  * 基本语法

    interface 接口名<T,R...>{

    }

  * 注意细节

    1、接口中，静态成员也不能使用泛型（这个和泛型类规定一样）

    2、泛型接口的类型，在**继承接口**或者**实现接口**时确定

    3、没有指定类型，默认为Object

* 自定义泛型方法

  * 基本语法

    修饰符 <T,R...> 返回类型 方法名(参数列表){

    }

  * 注意细节

    1、泛型方法，可以定义在普通类中，也可以定义在泛型类中

    2、当泛型方法被调用时，类型会确定

    3、public void eat(E e){}，修饰符后没有<T,R...> eat方法不是泛型方法，而是使用了泛型

## 泛型的继承和通配符

* 泛型不具备继承性
* <?>：支持任意泛型类型
* <? extends A>：支持A类以及A类的子类，规定了泛型的上限
* <? super A>：支持A类以及A类的父类，不限于直接父类，规定了泛型的下限