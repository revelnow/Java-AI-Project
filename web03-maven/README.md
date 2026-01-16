# 📅 Day03：Maven 基础 + JUnit5 单元测试（web03-maven）

> 目标：用一个可运行的 Maven 模块，理解 **POM/依赖/排除依赖/生命周期**，并通过 **JUnit5** 建立“用断言验证业务”的工程习惯。

---

## 🎯 今日目标
- [x] 能解释 Maven 的核心概念：POM / 坐标 / 仓库 / 生命周期
- [x] 会在 `pom.xml` 中引入依赖，并在 IDEA 中 Reload
- [x] 理解 **排除依赖（exclusions）** 的作用：控制传递依赖
- [x] 掌握 JUnit5：`@Test`、断言、`@BeforeEach`、参数化测试
- [x] 用一个业务类 + 单测跑通：`mvn test / mvn package / mvn clean`

---

## 📦 项目结构（我今天交付了什么）
```text
web03-maven
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  ├─ HelloWorld.java        # Maven 项目能编译运行的最小验证
│  │  │  └─ UserService.java       # 业务：身份证 -> 年龄/性别
│  │  └─ resources
│  └─ test
│     └─ java
│        ├─ UserServiceTest.java   # 手写测试：断言/参数化/异常断言
│        └─ UserServiceAiTest.java # AI 辅助测试：BeforeEach + 多用例
└─ pom.xml
- **HelloWorld**：输出 “Hello Maven ~”
  - 作用：验证 Maven 项目能编译运行

- **UserService**：实现 `getAge()` 和 `getGender()`
  - `getAge()`：身份证 → 年龄
  - `getGender()`：身份证 → 性别

- **UserServiceTest**：演示断言、异常断言、参数化测试
  - `assertEquals`
  - `assertThrows`
  - `@ParameterizedTest + @ValueSource`

- **UserServiceAiTest**：演示 `@BeforeEach` 初始化与多用例
  - 注意：AI 生成的 `assertThrows(..., "xxx")` 里的字符串是“断言失败提示”，不是异常 message 校验（见下方审计）
```
---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ Maven 的“存在意义”不是命令，而是统一工程语义
Maven 把项目构建抽象成统一流程：

- `clean / compile / test / package / install`

执行后面的阶段，会自动包含前面的阶段（因为阶段有顺序依赖）。

---

### 2️⃣ 我用 Spring 依赖只是“样本”，不是在学 Spring
`pom.xml` 引入一个依赖（如 `spring-context`）的目的：

- 让我观察：**依赖下载 / 传递依赖 / 排除依赖（exclusions）** 的效果
- 代码层面：**不使用 Spring API**，不引入 IOC 等概念（避免跑偏）

✅ 结论：依赖可以复杂，但学习目标必须单一——我现在只学 Maven 的依赖管理能力。

---

### 3️⃣ 单元测试“绿了”不代表业务对：必须用断言
只打印 `System.out.println()` 不叫测试。  
必须用断言判断结果是否符合预期。

我在 `UserServiceTest` 中已经开始用：
- `assertEquals`
- `assertThrows`

---

## 🛠️ 关键代码与结论（我今天最重要的产出）

### A) Maven 最常用命令（写进肌肉记忆）
```bash
mvn -v
mvn clean
mvn test
mvn package

### B) 业务代码：身份证 → 年龄 / 性别
- **年龄**：截取生日 `yyyyMMdd`，用 `Period.between(birth, now)` 算年数  
- **性别**：取第 17 位（索引 16）奇偶判断：奇数男，偶数女  

### C) JUnit5：三类测试我都跑通了
1) 普通测试：`@Test`  
2) 异常断言：`assertThrows(...)`  
3) 参数化测试：`@ParameterizedTest + @ValueSource`  

---
```
## 🛡️ 工业级审计 (Industrial Audit)

### 1) 依赖与排除依赖（exclusions）
我在 `pom.xml` 中练习了“排除依赖”的概念：  
**主动断开某个传递依赖（被排除的依赖无需写版本）。**

**审计原则：排除必须“说得清 + 验证得出”**
- **说得清**：为什么排除（减少体积 / 避免不需要的能力 / 控制依赖树）
- **验证得出**：用 `mvn dependency:tree` 看依赖树是否生效（下次补截图/结果）

### 2) 测试代码的位置规范
测试代码应放在 `src/test/java`，放 main 也能跑但不规范。

### 3) 我今天发现的 2 个“测试工程坑”（下次必修）
**坑1：println 不算测试**  
`UserServiceTest.testGetAge()` 目前是 println，需要改成断言。

**坑2：AI 测试里 assertThrows 的 message 参数容易误解**  
`assertThrows(..., "无效的身份证号码")` 这个字符串是“断言失败提示”，不是校验异常 message。

如果要校验异常信息，应该：
```java
IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
    () -> userService.getGender(null));
assertEquals("无效的身份证号码", ex.getMessage());
```
## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| Maven 坐标是什么？ | jar/模块的唯一标识：`groupId + artifactId + version` |
| 生命周期是什么？ | `clean / default / site` 三套独立生命周期；阶段有顺序依赖 |
| `mvn package` 做了什么？ | 会包含 `compile/test` 等前置阶段，最终打包 |
| 依赖范围 scope | `compile/test/provided/runtime`，决定哪里可用、是否参与打包 |
| 为什么要用断言？ | “绿灯 ≠ 业务正确”，断言用来验证输出是否符合预期 |
| 什么是排除依赖？ | 主动断开传递依赖，被排除依赖不需要写版本 |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？
**误区1：跑起来就算会了**  
只看到测试“绿”，但没有断言，就无法证明业务正确。

**误区2：以为 `assertThrows` 的字符串是异常信息校验**  
实际上它只是断言失败时的提示，需要手动取 `ex.getMessage()` 才能校验。

### 我最后记住的 3 句话
1) 生命周期是流程，不是命令集合。
2) 依赖要“说得清 + 验证得出”（`dependency:tree`）。
3) 测试不看 println，只看断言。  

