# 注解的使用

## 1.理解Annotation

- jdk5.0 新增功能
- `Annotation`其实就是代码里的特殊标记, 这些标记可以在编译, 类加
  载, 运行时被读取, 并执行相应的处理。通过使用 Annotation, 程序员
  可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
- 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，
忽略警告等。在JavaEE/Android中注解占据了更重要的角色，例如
  用来配置应用程序的任何切面， 代替JavaEE旧版中所遗留的繁冗
  代码和XML配置等

## 2.Annotation的使用

### 示例一：生成文档相关的注解

- `@author` 标明开发该类模块的作者， 多个作者之间使用,分割
- `@version` 标明该类模块的版本
- `@see` 参考转向， 也就是相关主题
- `@since` 从哪个版本开始增加的
- `@param` 对方法中某参数的说明， 如果没有参数就不能写
- `@return` 对方法返回值的说明， 如果方法的返回值类型是void就不能写
- `@exception` 对方法可能抛出的异常进行说明 ， 如果方法没有用throws显式抛出的异常就不能写其中

`@param` `@return` 和 `@exception` 这三个标记都是只用于方法的。

- `@param`的格式要求： `@param 形参名 形参类型 形参说明`
- `@return` 的格式要求： `@return 返回值类型 返回值说明`
- `@exception`的格式要求：`@exception 异常类型 异常说明`
- `@param`和`@exception`可以并列多个

### 示例二： 在编译时进行格式检查(JDK内置的三个基本注解)

- `@Override`: 限定重写父类方法, 该注解只能用于方法
- `@Deprecated`: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
- `@SuppressWarnings`: 抑制编译器警告

### 示例三： 跟踪代码依赖性，实现替代配置文件功能

## 3.自定义Annotation

定义新的注解类型使用`@interface`关键字

- 自定义注解自动继承了`java.lang.annotation.Annotation`接口
- Annotation 的成员变量在 Annotation 定义中以**无参数方法**的形式来声明。 其方法名和返回值定义了该成员的名字和类型。 我们称为配置参数。 
类型只能是八种基本数据类型：**String类型**、 **Class类型**、 **enum类型**、 **Annotation类型**及以上所有类型的数组。
- 可以在定义注解的成员变量时为其指定初始值, 指定成员变量的初始值可使用`default`关键字
- 如果只有一个参数成员， 建议使用参数名为`value`
- 如果定义的注解含有配置参数， 那么使用时必须指定参数值， 除非它有默认值。 格式是`参数名 = 参数值` ， 如果只有一个参数成员， 且名称为`value`，
可以省略`value=`
- 没有成员定义的 Annotation 称为标记; 包含成员变量的 Annotation 称为元数据 Annotation

注意： 自定义注解必须配上注解的信息处理流程（使用反射）才有意义。

## 4.元注解

JDK 的元注解用于修饰其他注解定义

JDK5.0提供了4个标准的meta-annotation类型， 分别是：
- Retention
- Target
- Documented
- Inherited

### @Retention

`@Retention`: 只能用于修饰一个注解定义, 用于指定该注解的生命周期, `@Rentention` 包含一个 `RetentionPolicy` 类型的成员变量, 使用
`@Rentention` 时必须为该 `value` 成员变量指定值:

- `RetentionPolicy.SOURCE`:在源文件中有效（即源文件保留），编译器直接丢弃这种策略的注释
- `RetentionPolicy.CLASS`:在class文件中有效（即class保留），当运行 Java 程序时, JVM不会保留注解。 这是**默认值**
- `RetentionPolicy.RUNTIME`:在运行时有效（即运行时保留），当运行 Java 程序时, JVM 会保留注释。程序可以通过反射获取该注释

### @Target

`@Target`: 用于修饰注解定义, 用于指定被修饰的注解能用于修饰哪些程序元素。`@Target`也包含一个名为`value`的成员变量。

### @Document

`@Documented`: 用于指定被该元注解修饰的注解类将被javadoc工具提取成文档。 

默认情况下， javadoc是不包括注解的。

定义为Documented的注解必须设置Retention值为RUNTIME。

### @Inherited

`Inherited`: 被它修饰的注解将具有继承性。如果某个类使用了被`@Inherited` 修饰的注解, 则其子类将自动具有该注解。

比如：如果把标有`@Inherited`注解的自定义的注解标注在类级别上，子类则可以继承父类类级别的注解。

实际应用中，使用较少。