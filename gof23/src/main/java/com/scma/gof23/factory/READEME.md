#### 简单工厂模式（静态工厂模式）
> 虽然某种程度上不符合设计原则，但实际使用最多
>
#### 工厂方法模式
> 不修改已有类的前提下，通过新增工厂类实现拓展
>

#### 抽象工厂模式
> 不可以增加产品，可以增加产品族
>

### 应用场景
1、JDK中Calendar的getInstance方法
2、JDBC中Connection对象的获取
3、Spring中IOC容器创建管理Bean对象
4、反射中Class对象的newInstance方法