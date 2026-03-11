# java9模块
> 不要被模块这个新概念唬住了！！！
在maven中，我们经常有多模块的项目，A模块依赖B模块，就要在自己的pom.xml中添加对B模块的依赖，在pom.xml中，声明依赖的格式如下：
```xml
<dependency>
    <groupId>org.java.order</groupId>
    <artifactId>order-module</artifactId>
    <version>1.0.0</version>
</dependency>
```
java中的模块也是类似的，只是在java中，依赖某个模块的声明是通过一个模块下名为module-info.java的文件完成的，长得样子大概如下
```java
module biz-module {
    // 注意，这里写的是依赖某个模块，这个模块需要在其他的模块被声明出来，具体可以参考order-module下的module-info.java
    requires order-module;
}
```
> java模块和maven的模块应该是一一对应的关系，一个maven模块中写多个java模块的做法是不可行的
> 
> 原因：一个maven模块编译出来一个jar包，java模块系统要求一个jar包里面只能有一个module-info.java，这注定了只能maven模块和java模块的一对一关系
 
在一个maven管理的多模块（maven多个模块，java也多个模块，本项目中是一对一的关系）项目中，module-info.java位于`src/main/java`下。
```
.
├── README.md
├── biz-module
│ ├── pom.xml
│ └── src
│     ├── main
│     │ └── java
│     │     ├── module-info.java // 这里是biz-module的module-info.java
│     │     └── org
│     │         └── java
│     │             └── biz
│     │                 └── App.java
│     └── test
│         └── java
│             └── org
│                 └── java
│                     └── biz
│                         └── AppTest.java
├── order-module
│ ├── pom.xml
│ └── src
│     ├── main
│     │ └── java
│     │     ├── module-info.java // 这里是order-module的module-info.java
│     │     └── org
│     │         └── java
│     │             └── order
│     │                 └── OrderManager.java
│     └── test
│         └── java
│             └── org
│                 └── java
│                     └── order
│                         └── AppTest.java
└── pom.xml
```
查看这些module-info.java中写的内容，基本就可以知道它在干嘛了。他们基本都在做两个事情，即
>
> 我这个模块对外提供了些什么功能
> 
> 我这个模块需要依赖其他哪些模块才能正常工作

具体的，把这两个模块中的module-info.java中的声明看完就能理解，不复杂，不要给自己预设难度。

我理解的，模块系统的好处如下
1. 选择性暴露我的能力，我不主动暴露，你用不了我代码里面的功能，我对我的代码有掌控感。即export关键字，我可以选择暴露我的哪些能力给外部，我不暴露，你用不了。
2. java运行时更轻量了。即我运行的时候，只加载我需要的模块，我不需要的模块，我不加载。

