# Springboot 2

注解：

* @RestController：包含@Controller、@ResponseBody
* @Configuration：告诉SpringBoot这是一个配置类  =》 配置文件

* @Bean：

  ```java
  @Bean("自定义")//给容器中添加组件。以方法名作为组件的id，或者自定义。返回类型就是组件类型。返回的值，就是组件在容器中的实例
  public User user01(){
  	return new User("zhangsan",18)
  }
  ```

  

### 4、开发小技巧

* Lombok

  作用：简化JavaBean开发

  ```java
  @Data
  @ToString
  @AllArgsConstructor  //全参构造器
  @NoArgsConstructor   //无参构造器
  @slf4j //日志
  ```

* dev-tools

  ctrl-F9

* Spring Initalizr

### 5、Web开发

* 静态资源配置原理

  * SpringBoot启动默认加载xxxAutoConfiguration类（自动配置类）

  * SpringMVC功能的自动配置类WebMvcAutoConfiguration，生效
  * 

* 拦截器 Interceptor

  编写一个拦截器实现HandlerInterceptor接口

  * preHandle：目标方法执行之前
  * postHandle：目标方法完成之后
  * afterCompletion：页面渲染之后

  拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）

  ```
  @Override
  public void addInterceptors(InterceptorRegistry registry){
  	registry.addInterceptor(new LoginInterceptor())
  			.addPathPatterns("/**") //所有请求都会被拦截
  			.excludePathPatterns("/","/login","/css","/js")；
  }
  ```

  指定拦截规则，如果是拦截所有，静态资源也会被拦截