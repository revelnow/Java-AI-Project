# 📅 2026.01.12：Java 高级特性（反射、枚举与动态代理）深度实战

## 🎯 今日目标
- [x] **枚举 (Enum)**：理解有限实例的“逻辑锁死”机制
- [x] **反射 (Reflection)**：掌握 `Class.forName → newInstance → invoke` 核心链条
- [x] **动态代理 (Proxy)**：实现基于 `InvocationHandler` 的“中介加戏”模式
- [x] **深度审计 (Audit)**：识别反射安全漏洞与 `InvocationTargetException` 包装坑点

---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ 为什么“中介”（代理对象）必须面向接口？
- **本质**：JDK 动态代理在运行时通过字节码技术生成一个新类。这个新类必须与“房东”（真实类）长得一样（拥有相同的方法签名）。
- **策略**：接口就是身份证。中介通过实现相同的接口来合法地伪装成真实对象，从而让调用者（客户端）无感知地切换。

### 2️⃣ 反射是“双刃剑”：灵活性 vs 安全性
- **灵活性**：解耦的终极工具。通过配置文件中的字符串就能动态换掉整个业务逻辑，无需重新编译。
- **代价**：绕过编译期类型检查。如果不对外部输入的字符串进行白名单校验，会引发任意类加载安全风险。

---

## 🛠️ 关键代码实现：动态代理日志框架

> **深度审计结论**：代理逻辑必须处理异常拆箱，否则上层业务无法感知真实错误。

```java
// 中介的大脑（InvocationHandler）
public class LogHandler implements InvocationHandler {
    private Object target; // 真实的“房东”

    public LogHandler(Object target) { this.target = target; }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            // 1. 加戏：前置审计
            if (method.getName().startsWith("save")) {
                System.out.println("[AUDIT] 正在执行敏感操作：" + method.getName());
            }
            // 2. 核心：通过反射触发真实逻辑
            return method.invoke(target, args);
        } catch (InvocationTargetException e) {
            // 3. 关键审计：拆开反射包装的大箱子，抛出真实业务异常
            throw e.getCause();
        }
    }
}
```
## 🛡️ 高级特性审计 (Industrial Audit)

- **反射入口白名单**：执行 `Class.forName()` 前，必须校验类名是否以信任包名（如 `com.yourproject.`）开头，防御 RCE 攻击。
- **暴力反射保护**：使用 `setAccessible(true)` 会破坏封装性，仅限在框架底层（如 ORM 映射）使用，业务层严禁滥用。
- **异常拆箱**：在 `InvocationHandler` 中必须捕获 `InvocationTargetException` 并通过 `getCause()` 获取真凶，防止业务逻辑异常被掩盖。
- **接口限定**：`Proxy.newProxyInstance` 的第二个参数必须是接口数组 `clazz.getInterfaces()`，传入实现类会报错。

---

## ✅ How to Run（可复现运行指南）

### 1) 模拟环境
- 定义接口 `UserService` 与实现类 `UserServiceImpl`
- 实现一个会抛出业务异常（如 `UserNotFoundException`）的方法

### 2) 运行测试
运行：`proxy.ProxyTest`

### ✅ Expected（验收标准）
- 调用 `save()` 时，控制台自动打印 `[AUDIT]` 日志（代理生效）。
- 捕获到的异常类型应为 `UserNotFoundException` 而非 `InvocationTargetException`（拆箱生效）。

---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| JDK 代理与 CGLIB 的区别 | JDK 代理要求目标类必须有接口；CGLIB 通过继承实现，不需要接口但不能代理 `final` 类。 |
| 反射如何破坏单例？ | 通过 `setAccessible(true)` 强行调用私有构造器。防御：在构造器中加入二次校验。 |
| 反射的性能损耗在哪？ | 安全检查、参数校验、无法触发 JIT 即时编译优化。 |
| 为什么要用 `getCause()`？ | 反射调用会统一包装异常。不拆箱会导致上层捕获不到具体的业务异常。 |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？
- **类型误区**：误以为代理对象可以强转为实现类（`UserServiceImpl`）。  
  ✅ 纠正：代理对象是动态生成的同胞兄弟，它们唯一的共同点是接口，只能强转为 `UserService`。
- **异常误区**：直接透传反射异常，导致 JUnit 测试断言失败。  
  ✅ 纠正：必须剥开 `InvocationTargetException` 的外壳。

### 我最后记住的 3 句话
1. **接口是代理的基石**：没身份证（接口）的中介没人信。
2. **反射调用要“脱壳”**：用 `getCause()` 还原真实的业务报错。
3. **外部输入要校验**：`forName()` 前必做 `startsWith()` 白名单，防止黑客进家门。

---

## Self-Reflection（自我反思）
- **我今天的提升**：从“只会背反射 API”升级到“能识别反射安全隐患并实现非侵入式代码增强”。
- **我发现的短板**：对字节码底层的生成逻辑（`$Proxy0`）还比较模糊，后续可以查阅资料看看生成的代理类源码。
- **下周最小动作**：尝试用反射 + 自定义注解，自己实现一个极简版的“权限校验过滤器”。  

