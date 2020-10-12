# SpringBoot学习笔记（四）：Thymeleaf基本使用

[Thymeleaf官网](https://www.thymeleaf.org/)

## Thymeleaf的简单介绍

- **Thymeleaf**是适用于Web和独立环境的现代服务器端Java模板引擎。

## 标准表达式语法

- 1.变量表达式：${}
- 2.选择或星号表达式：*{}
- 3.文字国际化表达式：#{}
- 4.URL 表达式：@{}

## pom.xml引入依赖

```xml
<!--Thymeleaf模板-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

## application.properties

```properties
#关闭thymeleaf缓存
spring.thymeleaf.cache=false
```
- html标签
```html
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
```

## 自定义错误处理

- 在启动类下添加以下代码
- 新建一个配置类（@Configuration标注的类），添加以下代码

```java
@Bean public WebServerFactoryCustomizer webServerFactoryCustomizer(){
	return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
		@Override
		public void customize(ConfigurableServletWebServerFactory factory) {
			ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST,"/400.html");
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
			ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
			ErrorPage error503Page = new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/503.html");
			factory.addErrorPages(error400Page,error401Page, error403Page,error404Page, error500Page,error503Page);
		}
	};
}
```

## 控制类

- HelloController类

```java
@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("message", "Hello World!");
        return "hello";
    }
}
```

- ExampleController类

```java
@Controller
public class ExampleController {
    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "Jax");
        return "string";
    }
    @RequestMapping("/if")
    public String ifunless(ModelMap map) {
        map.addAttribute("flag", "yes");
        return "if";
    }
    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }
    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("type", "more");
        map.addAttribute("img", "https://www.baidu.com/img/flexible/logo/pc/privacy.gif");
        return "url";
    }
    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "example");
        map.addAttribute("age", 30);
        map.addAttribute("flag", "yes");
        return "eq";
    }
    @RequestMapping("/switch")
    public String switchcase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }
    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("Tom",21,"123");
        User user2=new User("Jenny",22,"124");
        User user3=new User("Alice",20,"125");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return  list;
    }
}
```

## template包下的html文件

- hello.html

```html
<h1 th:text="${message}">Hello World</h1>
```

- string.html

```html
<h1>name</h1>
<p th:text="${userName}">example</p>
<span th:text="'Welcome  ' + ${userName} + '!'"></span>
<br/>
<span th:text="|Welcome ${userName}!|"></span>
```

- if.html

```html
<h1>If/Unless</h1>
<a th:if="${flag == 'yes'}"  th:href="@{http://www.baidu.com/}"> 百度 </a>
<br/>
<a th:unless="${flag != 'no'}" th:href="@{http://www.runoob.com/}" >菜鸟教程</a>
```

- list.html

```html
<tr  th:each="user,iterStat : ${users}">
    <td th:text="${user.name}">example</td>
    <td th:text="${user.age}">6</td>
    <td th:text="${user.pass}">213</td>
    <td th:text="${iterStat.index}">index</td>
</tr>
```

- url.html

```html
<h1>URL</h1>
<a  th:href="@{http://www.baidu.com/{type}(type=${type})}">link1</a>
<br/>
<div th:style="'background:url(' + @{${img}} + ');'">
    <br/><br/><br/>
</div>
```

- eq.html

```html
<h1>EQ</h1>
<input th:value="${name}"/>
<br/>
<input th:value="${age gt 30 ? '中年':'年轻'}"/>
<br/>
<a th:if="${flag eq 'yes'}"  th:href="@{http://www.runoob.com/}"> 菜鸟 </a>
```


- switch.html

```html
<div th:switch="${sex}">
    <p th:case="'woman'">她是一个姑娘...</p>
    <p th:case="'man'">这是一个爷们!</p>
    <!-- *: case的默认的选项 -->
    <p th:case="*">未知性别的一个家伙。</p>
</div>
```

